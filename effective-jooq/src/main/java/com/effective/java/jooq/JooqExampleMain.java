package com.effective.java.jooq;

import org.jooq.*;
import org.jooq.Record;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import static com.effective.java.jooq.tables.Author.AUTHOR;
import static com.effective.java.jooq.tables.Book.BOOK;
import static com.effective.java.jooq.tables.BookStore.BOOK_STORE;

public class JooqExampleMain {
    private static final Logger log = LoggerFactory.getLogger(JooqExampleMain.class);

    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    public static void main(String[] args) throws SQLException {
        String storeName = "store" + new Random().nextInt(1000);
        log.debug("Store to save: " + storeName);
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        CountDownLatch latch3 = new CountDownLatch(1);

        new Thread(() -> {
            try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD)) {
                connection.setAutoCommit(false);
                DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
                create.transaction(configuration -> {
                    log.debug("tx1 waits");
                    latch1.await();

                    log.debug("tx1 inserts");
                    DSL.using(configuration)
                            .insertInto(BOOK_STORE, BOOK_STORE.NAME)
                            .values(storeName)
                            .returning()
                            .fetchOne();
                    latch2.countDown();

                    log.debug("tx1 waits");
                    latch3.await();
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }).start();

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            log.debug("Transaction isolation level: " + connection.getTransactionIsolation());
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

            create.transaction(configuration -> {
                selectBootStores(configuration);
                log.debug("tx2 release tx1");
                latch1.countDown();
                latch2.await();

                selectBootStores(configuration);
                latch3.countDown();

                Thread.sleep(1000);
                selectBootStores(configuration);
            });
        }
    }


    private static Result<Record1<String>> selectBootStores(Configuration configuration) {
        return DSL.using(configuration)
                .select(BOOK_STORE.NAME)
                .from(BOOK_STORE)
                .fetch();
    }
}

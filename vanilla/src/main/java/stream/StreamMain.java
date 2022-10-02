package stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamMain {
    public static void main(String[] args) {
        getAll().forEach(System.out::println);
    }

    public static Stream<String> getAll() {
        Supplier<List<String>> supplier = new Supplier<>() {
            private int rounds = 0;

            @Override
            public List<String> get() {
                if (rounds > 2) return Collections.emptyList();
                List<String> items = loadMore();
                rounds++;
                return items;
            }
        };

        ListSupplierSpliterator<String> spliterator = new ListSupplierSpliterator<>(supplier);
        return StreamSupport.stream(spliterator, false);
    }

    private static List<String> loadMore() {
        System.out.println("Loading values...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList("v1", "v2", "v3");
    }
}
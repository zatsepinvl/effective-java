package testcoverage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleServiceTest {

    SimpleService service = new SimpleService();

    @Test
    void doSomethingTrue() {
        service.doSomething(true);
    }

    @Test
    void doSomethingFalse() {
        service.doSomething(false);
    }
}
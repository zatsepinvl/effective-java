package inheritance;

public class Child extends Parent {
    public void test() {
        System.out.println("A");
    }

    public static void main(String[] args) {
        Parent b = new Child();
        b.test();
    }
}

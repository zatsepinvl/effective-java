package trycatch;

public class TryCatchSample {
    public static void main(String[] args) {
        exceptional();
    }

    public static void exceptional() {
        try {
            throw new RuntimeException("try");
        } finally {
            throw new RuntimeException("finally");
        }
    }
}

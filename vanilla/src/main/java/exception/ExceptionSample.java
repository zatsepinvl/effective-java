package exception;

public class ExceptionSample {
    public static void main(String[] args) {
        String a = getWithPotentialException();
        System.out.println(a);
    }

    public static String getWithPotentialException() {
        String a;
        try {
            a = "try";
            throw new RuntimeException("try");
        } catch (Exception e) {
            a = "catch";
            throw new RuntimeException("catch");
        }
        finally {
            a = "finally";
            //return a;
        }
    }
}

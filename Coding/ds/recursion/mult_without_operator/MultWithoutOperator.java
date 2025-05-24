package ds.recursion.mult_without_operator;

public class MultWithoutOperator {
    public static int multiply(int a, int b) {

        if (b < 0) {
            return -multiply(a, -b);
        }

        if (b == 0) {
            return 0;
        }

        return a + multiply(a, b - 1);
    }

    public static void main(String[] args) {
        int res = multiply(-3, 5);
        System.out.println("res " + res);
    }
}

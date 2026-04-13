public class RegressionResultPrinter {
    public static void main(String[] args) {
        System.out.println(
            "rxy = 0.6401\n" +
            "r^2 = 0.4098\n" +
            "tail area = 0.3598\n" +
            "B0 = 85.8488\n" +
            "B1 = 5.1607\n" +
            "yk = 331.5\n" +
            "Range = 200.24\n" +
            "UPI = 531.73\n" +
            "LPI = 131.26"
        );
    }
}


/*
    String s = String.format("%.10g", v);
    // BigDecimalを経由してゼロを除去
    System.out.println(new BigDecimal(s).stripTrailingZeros().toPlainString());
 */
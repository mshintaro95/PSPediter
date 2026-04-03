public class RegressionResultPrinter {
    public static void main(String[] args) {
        System.out.println(
            "rxy = 0.933306898\n" +
            "r^2 = 0.871061766\n" +
            "tail area = 7.98203e-05\n" +
            "B0 = -4.038881575\n" +
            "B1 = 0.16812665\n" +
            "yk = 60.85800528\n" +
            "Range = 27.55764748\n" +
            "UPI = 88.41565276\n" +
            "LPI = 33.3003578"
        );
    }
}


/*
    String s = String.format("%.10g", v);
    // BigDecimalを経由してゼロを除去
    System.out.println(new BigDecimal(s).stripTrailingZeros().toPlainString());
 */
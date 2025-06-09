public class RegressionResultPrinter {
    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("使い方: java RegressionResultPrinter b_0 b_1 r_xy r2 y_k");
            return;
        }

        double b0 = Double.parseDouble(args[0]);
        double b1 = Double.parseDouble(args[1]);
        double r_xy = Double.parseDouble(args[2]);
        double r2 = Double.parseDouble(args[3]);
        double yk = Double.parseDouble(args[4]);

        System.out.println(
            "b_0 = " + b0 + "\n" +
            "b_1 = " + b1 + "\n" +
            "r_xy = " + r_xy + "\n" +
            "r2 = " + r2 + "\n" +
            "y_k = " + yk
        );
    }
}

/******************************************************
/*プログラム課題: 5-Output
/*名前: 三留 慎太郎
/*日付: 20250713
/*プログラムの説明:シンプソンの公式を用いてt分布関数を数値積分する
/*クラスの説明：データを小数点以下第6位を四捨五入してコマンドラインに出力する。
/********************************************************/ 

public class Output {
    public static void output(double rxy, double r2, double tailArea, double Beta0, double Beta1, double yk, double Range, double UPI, double LPI){
        System.out.println("rxy = "      + String.format("%.10g", rxy));
        System.out.println("r2 = "       + String.format("%.10g", r2));
        if(tailArea < 0.0001){
            System.out.println("tailArea = " + String.format("%.10g", tailArea));
        } else {
            System.out.println("tailArea = " + String.format("%.9e", tailArea));
        }
        System.out.println("Beta0 = "    + String.format("%.10g", Beta0));
        System.out.println("Beta1 = "    + String.format("%.10g", Beta1));
        System.out.println("yk = "       + String.format("%.10g", yk));
        System.out.println("Range = "    + String.format("%.10g", Range));
        System.out.println("UPI = "      + String.format("%.10g", UPI));
        System.out.println("LPI = "      + String.format("%.10g", LPI));
    }
}
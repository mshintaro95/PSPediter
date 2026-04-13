/******************************************************
/*プログラム課題: 7-Program7
/*名前: 三留 慎太郎
/*日付: 20260412
/*プログラムの説明:過去のデータから見積もり値に対する予測値と70%の予測区間を計算する。
/*クラスの説明：データを有効桁通りもしくは有効桁10桁でコマンドラインに出力する。
/********************************************************/ 

public class Output {
    public static void output(double rxy, double r2, double tailArea, double Beta0, double Beta1, double yk, double Range, double UPI, double LPI){
        System.out.println("rxy = "      + String.format("%.10g", rxy));
        System.out.println("r2 = "       + String.format("%.10g", r2));
        if(tailArea > 0.0001){
            System.out.println("tail area = " + String.format("%.10g", tailArea));
        } else {
            System.out.println("tailArea = " + String.format("%.9e", tailArea));
        }
        System.out.println("B0 = "    + String.format("%.10g", Beta0));
        System.out.println("B1 = "    + String.format("%.10g", Beta1));
        System.out.println("yk = "       + String.format("%.10g", yk));
        System.out.println("Range = "    + String.format("%.10g", Range));
        System.out.println("UPI = "      + String.format("%.10g", UPI));
        System.out.println("LPI = "      + String.format("%.10g", LPI));
    }
}
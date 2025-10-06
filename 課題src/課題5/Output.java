/******************************************************
/*プログラム課題: 5-Output
/*名前: 三留 慎太郎
/*日付: 20250713
/*プログラムの説明:シンプソンの公式を用いてt分布関数を数値積分する
/*クラスの説明：データを小数点以下第6位を四捨五入してコマンドラインに出力する。
/********************************************************/ 

public class Output {
    public static void output(double integral, double topEndX, int dof){
        System.out.println("積分値(" + topEndX + ", " + dof + ") = " + String.format("%.5f", Math.round(integral * 100000.0) / 100000.0));
    }
}

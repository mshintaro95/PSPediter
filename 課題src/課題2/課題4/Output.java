/******************************************************
/*プログラム課題: 4-Output
/*名前: 三留 慎太郎
/*日付: 20250616
/*プログラムの説明:対数正規分布の標準偏差を用いて、VS,S,M,L,VL の相対規模範囲を計算し、それぞれの中点を出力する。
/*クラスの説明：データを小数点以下第5位を四捨五入してコマンドラインに出力する。
/********************************************************/ 

public class Output {
    public static void output(double[] relativeSizeRanges){
        System.out.println("VS = " + Math.round(relativeSizeRanges[0] * 10000.0) / 10000.0);
        System.out.println("S = " + Math.round(relativeSizeRanges[1] * 10000.0) / 10000.0);
        System.out.println("M = " + Math.round(relativeSizeRanges[2] * 10000.0) / 10000.0);
        System.out.println("L = " + Math.round(relativeSizeRanges[3] * 10000.0) / 10000.0);
        System.out.println("VL = " + Math.round(relativeSizeRanges[4] * 10000.0) / 10000.0);
    }
}

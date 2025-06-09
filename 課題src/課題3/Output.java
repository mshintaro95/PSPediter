/******************************************************
/*プログラム課題: 3-Output
/*名前: 三留 慎太郎
/*日付: 20250604
/*プログラムの説明: :入力されたデータ対群と見積もり値xkから回帰パラメータベータ0,ベータ1,相関係数rxy,r2,予測値ykを計算する
/*クラスの説明：データを小数点以下第7意を四捨五入してコマンドラインに出力する。
/********************************************************/ 

public class Output {
    public static void output(double Beta0, double Beta1, double rxy, double r2, double yk){
        System.out.println("b_0 = " + Math.round(Beta0 * 1000000.0) / 1000000.0);
        System.out.println("b_1 = " + Math.round(Beta1 * 1000000.0) / 1000000.0);
        System.out.println("r_xy = " + Math.round(rxy * 1000000.0) / 1000000.0);
        System.out.println("r_2 = " + Math.round(r2 * 1000000.0) / 1000000.0);
        System.out.println("y_k = " + Math.round(yk * 1000000.0) / 1000000.0);
    }
}

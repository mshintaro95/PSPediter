/******************************************************
/*プログラム課題: 3-Pair
/*名前: 三留 慎太郎
/*日付: 20250604
/*プログラムの説明:入力されたデータ対群と見積もり値xkから回帰パラメータベータ0,ベータ1,相関係数rxy,r2,予測値ykを計算する
/*クラスの説明：2つの変数を扱うPair型を定義する
/********************************************************/ 
import java.util.*;

public class Pair<T1, T2> {
    public T1 firstData;
    public T2 secondData;

    public Pair(T1 firstData, T2 secondData){
        this.firstData = firstData;
        this.secondData = secondData;
    }

}

/******************************************************
/*プログラム課題: 4-Pair
/*名前: 三留 慎太郎
/*日付: 20250616
/*プログラムの説明:対数正規分布の標準偏差を用いて、VS,S,M,L,VL の相対規模範囲を計算し、それぞれの中点を出力する。
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

/******************************************************
/*プログラム課題: 5-Pair
/*名前: 三留 慎太郎
/*日付: 20250713
/*プログラムの説明:シンプソンの公式を用いてt分布関数を数値積分する
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

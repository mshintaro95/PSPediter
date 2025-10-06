/******************************************************
/*プログラム課題: 6-Pair
/*名前: 三留 慎太郎
/*日付: 20251006
/*プログラムの説明:t 関数を 0 から x まで積分した値が p になるような x の値を求める。
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

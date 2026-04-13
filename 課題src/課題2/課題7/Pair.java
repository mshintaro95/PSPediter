/******************************************************
/*プログラム課題: 7-Program7
/*名前: 三留 慎太郎
/*日付: 20260412
/*プログラムの説明:過去のデータから見積もり値に対する予測値と70%の予測区間を計算する。
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

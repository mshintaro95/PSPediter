/******************************************************
/*プログラム課題: 1-LinkedListNode
/*名前: 三留 慎太郎
/*日付: 20250514
/*プログラムの説明:入力された実数群の平均と標準偏差を計算する
/*クラスの説明：双方向連結リストの前後のノードの参照を保持
/********************************************************/ 

public class LinkedListNode{
    public double data;
    public LinkedListNode next;
    public LinkedListNode prev;

    public LinkedListNode(double data){  // コンストラクタ
        this.data = data;
        this.next = null;
        this.prev = null;
}
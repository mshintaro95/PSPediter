/******************************************************
/*プログラム課題: 4-LinkedListNode
/*名前: 三留 慎太郎
/*日付: 20250616
/*プログラムの説明:対数正規分布の標準偏差を用いて、VS,S,M,L,VL の相対規模範囲を計算し、それぞれの中点を出力する。
/*クラスの説明：双方向連結リストの前後のノードの参照を保持
/********************************************************/ 

public class LinkedListNode<T>{
    public T data;
    public LinkedListNode<T> next;
    public LinkedListNode<T> prev;

    public LinkedListNode(T data){  // コンストラクタ
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
/******************************************************
/*プログラム課題: 6-LinkedListNode
/*名前: 三留 慎太郎
/*日付: 20251006
/*プログラムの説明:t 関数を 0 から x まで積分した値が p になるような x の値を求める。
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
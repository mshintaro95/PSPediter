/******************************************************
/*プログラム課題: 5-LinkedListNode
/*名前: 三留 慎太郎
/*日付: 20250713
/*プログラムの説明:シンプソンの公式を用いてt分布関数を数値積分する
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
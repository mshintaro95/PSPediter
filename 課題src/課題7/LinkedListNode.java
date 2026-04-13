/******************************************************
/*プログラム課題: 7-Program7
/*名前: 三留 慎太郎
/*日付: 20260412
/*プログラムの説明:過去のデータから見積もり値に対する予測値と70%の予測区間を計算する。
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
/******************************************************
/*プログラム課題: 3-LinkedListNode
/*名前: 三留 慎太郎
/*日付: 20250604
/*プログラムの説明:入力されたデータ対群と見積もり値xkから回帰パラメータベータ0,ベータ1,相関係数rxy,r2,予測値ykを計算する
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
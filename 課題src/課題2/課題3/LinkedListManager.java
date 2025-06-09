/******************************************************
/*プログラム課題: 3-LinkedListManager
/*名前: 三留 慎太郎
/*日付: 20250604
/*プログラムの説明::入力されたデータ対群と見積もり値xkから回帰パラメータベータ0,ベータ1,相関係数rxy,r2,予測値ykを計算する
/*クラスの説明：双方向リンクしストを管理する
/********************************************************/ 

public class LinkedListManager<T> {
    public LinkedListNode<T> head;
    public LinkedListNode<T> tail;

    public void addNode(T data){
        LinkedListNode<T> llnode = new LinkedListNode<>(data);// 新しいノード
        if(head == null){
            head = llnode;
            tail = llnode;
    } else {
        tail.next = llnode;
        llnode.prev = tail;
        tail = llnode;
    }
    }

    public void removeNode(){
        if(head == tail){
            tail = null;
            head = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
    }

    public LinkedListNode<T> nextNode(LinkedListNode<T> current_node){
        return current_node.next;
    }

    public LinkedListNode<T> priorNode(LinkedListNode<T> current_node){
        return current_node.prev;
    }

}

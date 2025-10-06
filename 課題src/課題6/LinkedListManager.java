/******************************************************
/*プログラム課題: 6-LinkedListManager
/*名前: 三留 慎太郎
/*日付: 20251006
/*プログラムの説明:t 関数を 0 から x まで積分した値が p になるような x の値を求める。
/*クラスの説明：双方向リンクリストを管理する
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

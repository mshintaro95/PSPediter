/******************************************************
/*プログラム課題: 5-LinkedListManager
/*名前: 三留 慎太郎
/*日付: 20250713
/*プログラムの説明:シンプソンの公式を用いてt分布関数を数値積分する
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

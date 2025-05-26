import java.io.*;

public class LinkedListManager {
    public static LinkedListNode head;
    public static LinkedListNode tail;

    public LinkedListManager(){
        this.head = null;
        this.tail = null;
    }

    public static void addNode(double data){
        LinkedListNode llnode = new LinkedListNode(data);
        if(head == null){
            head = llnode;
            tail = llnode;
    } else {
        tail.next = llnode;
        llnode.prev = tail;
        tail = llnode;
    }
    }

    public static void removeNode(){
        if(head == tail){
            tail = null;
            head = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
    }

    public static LinkedListNode nextNode(LinkedListNode current_node){
        return current_node.next;
    }

    public static LinkedListNode priorNode(LinkedListNode current_node){
        return current_node.prev;
    }

    public static void readFile(String file_name){
        while(head != tail){
            removeNode();
        }
        String line;

        try{
            File f = new File(file_name);
            BufferedReader br = new BufferedReader(new FileReader(f));
            while((line = br.readLine()) != null){
                double data = Double.parseDouble(line);
                addNode(data);
            }
            br.close();
        }catch (IOException e) {
        e.printStackTrace();
        }
    }
}

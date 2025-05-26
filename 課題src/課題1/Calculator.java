public class Calculator{
    public static double calcAverage(){
        double sum = 0.0;
        double ave = 0.0;
        int i = 0;
        LinkedListNode current_node = LinkedListManager.head;
        while(current_node != null){
            i++;
            sum += current_node.data;
            current_node = LinkedListManager.nextNode(current_node);
        }
        ave = sum / i;
        return ave;
    }

    public static double calcStandardDeviation(){
        double sum = 0.0;
        double ave = calcAverage();
        double sigma = 0.0;
        int i = 0;
        LinkedListNode current_node = LinkedListManager.head;
        while(current_node != null){
            i++;
            sum += Math.pow((current_node.data - ave), 2);
            current_node = LinkedListManager.nextNode(current_node);
        }
        sigma = Math.sqrt(sum / (i-1));
        return sigma;
    }

}
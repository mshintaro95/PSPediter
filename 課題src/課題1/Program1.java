public class Program1{
    public static void main(String[] args){
        LinkedListManager.readFile(args[0]);
        System.out.println("平均：" + String.format("%.2f", Calculator.calcAverage()));
        System.out.println("標準偏差：" + String.format("%.2f", Calculator.calcStandardDeviation()));
    }
}
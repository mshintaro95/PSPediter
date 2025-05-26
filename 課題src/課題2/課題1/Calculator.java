/******************************************************
/*プログラム課題: 1-Calculator
/*名前: 三留 慎太郎
/*日付: 20250514
/*プログラムの説明:入力された実数群の平均と標準偏差を計算する
/*クラスの説明：数値データの平均値と標準偏差を計算する
/********************************************************/ 

public class Calculator{
    public static double calcAverage(){
        double sum = 0.0;//データの合計
        double ave = 0.0;//平均を格納する
        int count = 0;//データの個数をカウント
        LinkedListNode current_node = LinkedListManager.head;
        while(current_node != null){//リンクリストのすべての要素を処理したか？
            count++;
            sum += current_node.data;
            current_node = LinkedListManager.nextNode(current_node);
        }
        ave = sum / count;
        return ave;
    }

    public static double calcStandardDeviation(){
        double sum = 0.0;//データの合計
        double ave = calcAverage();//データの平均
        double sigma = 0.0;//標準偏差を格納する
        int count = 0;//データの個数をカウント
        LinkedListNode current_node = LinkedListManager.head;
        while(current_node != null){//リンクリストのすべての要素を処理したか？
            count++;
            sum += Math.pow((current_node.data - ave), 2);
            current_node = LinkedListManager.nextNode(current_node);
        }
        sigma = Math.sqrt(sum / (count-1));
        return sigma;
    }
}
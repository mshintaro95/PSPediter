/******************************************************
/*プログラム課題: 3-Calculator
/*名前: 三留 慎太郎
/*日付: 20250604
/*プログラムの説明:入力されたデータ対群と見積もり値xkから回帰パラメータベータ0,ベータ1,相関係数rxy,r2,予測値ykを計算する
/*クラスの説明：データ対群の平均値と標準偏差,回帰パラメータを計算する。
/********************************************************/ 

public class Calculator{
    public static double[] calcAverage(LinkedListManager<Pair<Double, Double>> linkedList){
        double sum1 = 0.0;//データの合計1
        double sum2 = 0.0;//データの合計2
        double[] averages = {0.0, 0.0};//それぞれのカラムの平均を格納する
        int count = 0;//データの個数をカウント
        LinkedListNode<Pair<Double, Double>> current_node = linkedList.head;
        while(current_node != null){//リンクリストのすべての要素を処理したか？
            count++;
            sum1 += current_node.data.firstData;
            sum2 += current_node.data.secondData;
            current_node = linkedList.nextNode(current_node);
        }
        averages[0] = sum1 / count;
        averages[1] = sum2 / count;
        return averages;
    }

    public static double[] calcStandardDeviation(LinkedListManager<Pair<Double, Double>> linkedList){
        double sum1 = 0.0;//データの合計1
        double sum2 = 0.0;//データの合計2
        double[] averages = calcAverage(linkedList);//データ対それぞれの平均
        double[] sigmas = {0.0, 0.0};//標準偏差を格納する
        int count = 0;//データの個数をカウント

        LinkedListNode<Pair<Double, Double>> current_node = linkedList.head;
        while(current_node != null){//リンクリストのすべての要素を処理したか？
            count++;
            sum1 += Math.pow((current_node.data.firstData - averages[0]), 2);
            sum2 += Math.pow((current_node.data.secondData - averages[1]), 2);
            current_node = linkedList.nextNode(current_node);
        }
        sigmas[0] = Math.sqrt(sum1 / (count-1));
        sigmas[1] = Math.sqrt(sum2 / (count-1));
        return sigmas;
    }

    public static double calcBeta1(LinkedListManager<Pair<Double, Double>> linkedList){

        double sumxy = 0.0;//xyの合計を格納
        double sumx2 = 0.0;//x2乗の合計を格納
        double[] averages = calcAverage(linkedList);//データ対それぞれの平均
        int count = 0;//データの個数をカウント

        LinkedListNode<Pair<Double, Double>> current_node = linkedList.head;
        while(current_node != null){//リンクリストのすべての要素を処理したか？
            count++;
            double x = current_node.data.firstData;
            double y = current_node.data.secondData;
            sumxy += x * y;
            sumx2 += x * x;
            current_node = linkedList.nextNode(current_node);
        }
        double numerator = sumxy - (count * averages[0] * averages[1]);
        double denominator = sumx2 - (count * averages[0] * averages[0]);
        double Beta1 = numerator / denominator;

        return Beta1;

    }

    public static double calcBeta0(LinkedListManager<Pair<Double, Double>> linkedList){
        double[] averages = calcAverage(linkedList);//データ対それぞれの平均
        
        double Beta0 = averages[1] - (calcBeta1(linkedList) * averages[0]);

        return Beta0;
    }

}
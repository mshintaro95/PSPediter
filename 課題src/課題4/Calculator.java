/******************************************************
/*プログラム課題: 4-Calculator
/*名前: 三留 慎太郎
/*日付: 20250616
/*プログラムの説明:対数正規分布の標準偏差を用いて、VS,S,M,L,VL の相対規模範囲を計算し、それぞれの中点を出力する。
/*クラスの説明：対数正規化したデータの平均値と標準偏差,回帰パラメータ,相対規模範囲を計算する。
/********************************************************/ 

public class Calculator{
    public static double calcAverage(LinkedListManager<Pair<Double, Double>> linkedList){
        double sum = 0.0;//データの合計
        double average = 0.0;//それぞれのカラムの平均を格納する
        int count = 0;//データの個数をカウント
        LinkedListNode<Pair<Double, Double>> current_node = linkedList.head;
        while(current_node != null){//リンクリストのすべての要素を処理したか？
            int numItems = current_node.data.secondData.intValue();
            count++;
            sum += LogNormallyTransform(current_node.data.firstData/numItems);
            current_node = linkedList.nextNode(current_node);
        }
        average = sum / count;
        return average;
    }

    public static double calcStandardDeviation(LinkedListManager<Pair<Double, Double>> linkedList){
        double sum = 0.0;//データの合計
        double average = calcAverage(linkedList);//
        double sigma = 0.0;//標準偏差を格納する
        int count = 0;//データの個数をカウント

        LinkedListNode<Pair<Double, Double>> current_node = linkedList.head;
        while(current_node != null){//リンクリストのすべての要素を処理したか？
            int numItems = current_node.data.secondData.intValue();
            count++;
            sum += Math.pow((LogNormallyTransform(current_node.data.firstData/numItems) - average), 2);
            current_node = linkedList.nextNode(current_node);
        }
        sigma = Math.sqrt(sum / (count-1));
        return sigma;
    }

    public static double LogNormallyTransform(double sizeData){
        if(sizeData <= 0){//自然対数をとる値が正か
            System.out.println(sizeData + "は0以下の数なので自然対数が定義できません");
            System.exit(0);
        }
        
        return Math.log(sizeData);
    }

    public static double[] CalcRelativeSizeRanges(LinkedListManager<Pair<Double, Double>> linkedList){
        double[] relativeSizeRanges = {0,0,0,0,0};

        double standardDeviation = calcStandardDeviation(linkedList);
        double Average = calcAverage(linkedList);

        for(int i = -2; i < 3; i++){//VSからVLまで処理したか
            relativeSizeRanges[i+2] = Math.pow(Math.E, Average + (i * standardDeviation));
        }

        return relativeSizeRanges;
    }

}
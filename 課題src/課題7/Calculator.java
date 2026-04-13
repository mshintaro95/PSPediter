/******************************************************
/*プログラム課題: 7-Program7
/*名前: 三留 慎太郎
/*日付: 20260412
/*プログラムの説明:過去のデータから見積もり値に対する予測値と70%の予測区間を計算する。
/*クラスの説明：プログラムに必要な計算を担うメソッドを定義する。
/********************************************************/ 

public class Calculator{

    public static double gammaFunction(double value){
        if (value == 0.5) {//値が1/2？
            return Math.sqrt(Math.PI);
        }

        if (value == 1.0) {//値が1?
            return 1.0;
        }

        return (value - 1.0) * gammaFunction(value - 1.0);
    }

    public static double tDistributionFunction(int dof, double x){
        double numerator = gammaFunction((dof +1.0) / 2.0);//分子
        double denominator = Math.sqrt(dof * Math.PI) * gammaFunction(dof / 2.0);//分母
        double exponent = -((dof + 1.0) / 2.0);//指数部
        double base = 1.0 + (x * x) / dof; //底

        return (numerator / denominator) * Math.pow(base, exponent);
    }

    public static double calcSimpsonIntegral(double upperLimit, int dof){
        double ToleranceE = 0.00001;//許容誤差

        int numSeg = 10;//セグメント数初期値
        double prevResult = 0.0;//1つ前の積分値格納
        double integral;//積分値格納
        
        while (true) {
            double widthSeg = upperLimit / numSeg; // セグメントの幅 w
            double sum = 0.0;                   // 公式の各項の合計を格納

            sum += tDistributionFunction(dof, 0.0); // F(0) を加える

            for (int i = 1; i < numSeg; i++) {//セグメントの幅すべて処理したか？
                double fx = tDistributionFunction(dof, i * widthSeg); // F(xi)
                if (i % 2 == 1) {
                    sum += 4 * fx; // 奇数項は4倍
                } else {
                    sum += 2 * fx; // 偶数項は2倍
                }
            }

            sum += tDistributionFunction(dof, upperLimit); // F(x) を加える

            double result = (widthSeg / 3.0) * sum; // シンプソン公式による積分値

            if (Math.abs(result - prevResult) < ToleranceE) { // 許容誤差内?
                integral = result;
                break;
            } else {
                prevResult = result; // 前回の結果を更新
                numSeg *= 2;         // セグメント数を2倍
            }
        }

        return integral;
    }

    public static double findUpperLimit(double p, int dof){
        double trialUpperLimit = 1.0;//試行値
        double ToleranceE = 0.000000001;//許容誤差
        double d = 1.0;//試行値の変動幅
        boolean flag = true;//誤差フラグ

        double integral = calcSimpsonIntegral(trialUpperLimit, dof);

        while(Math.abs(p - integral) >= ToleranceE){
            if(trialUpperLimit == 0.0){//0の時
                trialUpperLimit = 0.00001;//正の極小値代入
            }

            if(p - integral > 0.0){
                if(flag == false){
                    d = d / 2;
                }
                trialUpperLimit += d;
                flag = true;
            } else if(p - integral < 0.0){
                if(flag == true){
                    d = d / 2;
                }
                trialUpperLimit -= d;
                flag = false;
            }
            integral = calcSimpsonIntegral(trialUpperLimit, dof);
        }

        return trialUpperLimit;
    }


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

    public static double calcStandardDeviation(LinkedListManager<Pair<Double, Double>> linkedList, int dataNum){
        double sum = 0.0;//データの合計
        double sigma;//標準偏差を格納する
        double Beta1 = calcBeta1(linkedList);
        double Beta0 = calcBeta0(linkedList);

        LinkedListNode<Pair<Double, Double>> current_node = linkedList.head;
        while(current_node != null){//リンクリストのすべての要素を処理したか？
            sum += Math.pow((current_node.data.secondData - Beta0 - (Beta1 * current_node.data.firstData)), 2);
            current_node = linkedList.nextNode(current_node);
        }
        sigma = Math.sqrt(sum / (dataNum-2));

        return sigma;
    }

    public static double[] calcStandardDeviations(LinkedListManager<Pair<Double, Double>> linkedList){
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

    public static double calcTailArea(double rxy, int dataNum){
        double upperLimitX = (Math.abs(rxy) * Math.sqrt(dataNum - 2)) / Math.sqrt(1 - (rxy * rxy));
        double p = calcSimpsonIntegral(upperLimitX, dataNum - 2);//積分

        double tailArea = 1 - 2 * p;
        return tailArea;
    }

    public static double calcRange(LinkedListManager<Pair<Double, Double>>linkedList, int dataNum, double xk){
        double tFunctionValue = findUpperLimit(0.35,dataNum - 2);//t関数の積分値がpになる上限値を計算
        double sigmaValue = calcStandardDeviation(linkedList, dataNum);//標準偏差計算
        double[] averages = calcAverage(linkedList);//平均値取得
        double denominator = 0.0;

        LinkedListNode<Pair<Double, Double>> current_node = linkedList.head;
        while(current_node != null){//リンクリストのすべての要素を処理したか？
            denominator += Math.pow((current_node.data.firstData - averages[0]), 2);
            current_node = linkedList.nextNode(current_node);
        }

        double Range = tFunctionValue * sigmaValue * Math.sqrt(1.0 + (1.0 / dataNum) + (Math.pow(xk - averages[0], 2) / denominator));

        return Range;
    }

}
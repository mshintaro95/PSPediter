/******************************************************
/*プログラム課題: 5-Calculator
/*名前: 三留 慎太郎
/*日付: 20250713
/*プログラムの説明:シンプソンの公式を用いてt分布関数を数値積分する
/*クラスの説明：シンプソンの公式をを用いて対応する自由度に対応するt分布関数を0~topEndXまでの範囲で積分計算する
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

    public static double[] calcSimpsonIntegral(LinkedListManager<Pair<Double, Double>> linkedList){
        double ToleranceE = 0.00001;//許容誤差
        double[] integrals = {0.0,0.0,0.0};//3組の積分値を格納
        int index = 0;//何組目を処理しているか(0から)

        LinkedListNode<Pair<Double, Double>> current_node = linkedList.head;

        while(current_node != null){//リンクリストのすべての要素を処理したか？
            double topEndX = current_node.data.firstData.doubleValue();//積分範囲の上端
            int dof = current_node.data.secondData.intValue();//自由度dof

            int numSeg = 10;//セグメント数初期値
            double prevResult = 0.0;//1つ前の積分値格納
            
            while (true) {
                double widthSeg = topEndX / numSeg; // セグメントの幅 w
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

                sum += tDistributionFunction(dof, topEndX); // F(x) を加える

                double result = (widthSeg / 3.0) * sum; // シンプソン公式による積分値

                if (Math.abs(result - prevResult) < ToleranceE) { // 許容誤差内?
                    integrals[index] = result;
                    break;
                } else {
                    prevResult = result; // 前回の結果を更新
                    numSeg *= 2;         // セグメント数を2倍
                }
            }

            current_node = current_node.next;
            index++;
        }

        return integrals;
    }

}
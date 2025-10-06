/******************************************************
/*プログラム課題: 6-Calculator
/*名前: 三留 慎太郎
/*日付: 20251006
/*プログラムの説明:t 関数を 0 から x まで積分した値が p になるような x の値を求める。
/*クラスの説明：シンプソンの公式をを用いてt分布関数を0~試行値までの範囲で積分計算し、積分値が目的値pとなる積分範囲の上端を計算する。
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
        double ToleranceE = 0.0000000001;//許容誤差
        double integral = 0.0;//積分値を格納

        double topEndX = upperLimit;//積分範囲の上端

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

}
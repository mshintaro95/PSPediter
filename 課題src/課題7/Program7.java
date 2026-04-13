/******************************************************
/*プログラム課題: 7-Program7
/*名前: 三留 慎太郎
/*日付: 20260412
/*プログラムの説明:過去のデータから見積もり値に対する予測値と70%の予測区間を計算する。
/*クラスの説明：プログラムの開始点として起動を行う。
/********************************************************/ 

/******************************************************
/*コンテンツリスト:
/* 
/* 
/* コマンドプロンプトで以下を入力してコンパイル
/* javac Program7.java
/* java Program7 data_program7.csv 384
/*  クラス宣言:
/*      -Program7
/*      -LinkedListManager
/*      -LinkedListNode
/*      -Calculator
/*      -Pair
/*      -CsvFileReader
/*      -Output
/*  実装メソッド
/*      -main()
/*      -addNode()
/*      -removeNode()
/*      -nextNode()
/*      -priorNode()
/*      -LinkedListNode()
/*      -gammaFunction()
/*      -tDistributionFunction()
/*      -calcSimpsonIntegral()
/*      -calcAverage()
/*      -calcStandardDeviation()
/*      -calcStandardDeviations()
/*      -calcBeta1()
/*      -calcBeta0()
/*      -calcTailArea()
/*      -calcRange()
/*      -Pair()
/*      -readFile()
/*      -output()
/******************************************************/ 


public class Program7{
    public static void main(String[] args){
        String fileName = args[0];
        double xk = Double.parseDouble(args[1]);

        LinkedListManager<Pair<Double, Double>> linkedList = CsvFileReader.readFile(fileName, 1, 2);//格納済みリンクリスト取得

        // リストの要素数を数えて dataNum として渡す
        LinkedListNode<Pair<Double, Double>> current_node = linkedList.head;
        int dataNum = 0;//データの個数
        for (LinkedListNode<Pair<Double, Double>> node = current_node; node != null; node = node.next) {//データ数カウント
            dataNum++;
        }
        double[] stdDevs = Calculator.calcStandardDeviations(linkedList); //x,yの標準偏差を計算
        double Beta0 = Calculator.calcBeta0(linkedList);//Beta0計算
        double Beta1 = Calculator.calcBeta1(linkedList);//Beta1計算
        double yk = Beta0 + (xk * Beta1);//予測値計算
        double rxy = Beta1 * (stdDevs[0] / stdDevs[1]);//相関係数計算
        double r2 = rxy * rxy;
        double tailArea = Calculator.calcTailArea(rxy, dataNum);//テイルエリア計算
        double Range = Calculator.calcRange(linkedList, dataNum, xk);
        double UPI = yk + Range;
        double LPI = yk - Range;

        Output.output(rxy, r2, tailArea, Beta0, Beta1, yk, Range, UPI, LPI);
    }
}
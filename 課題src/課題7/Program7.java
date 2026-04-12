/******************************************************
/*プログラム課題: 5-Program5
/*名前: 三留 慎太郎
/*日付: 20250713
/*プログラムの説明:シンプソンの公式を用いてt分布関数を数値積分する
/*クラスの説明：プログラムの開始点として起動を行う。3組の積分範囲上端と自由度から積分値を計算しそれぞれoutputに渡す。
/********************************************************/ 

/******************************************************
/*コンテンツリスト:
/* 
/* 再利用の説明
/* gammaFunction(double value)
/* 値を与えると対応するガンマ関数の数値を返す
/* 
/* コマンドプロンプトで以下を入力してコンパイル
/* javac Program5.java
/* java Program5 data_program5.csv 1 2
/*  クラス宣言:
/*      -Program5
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
/*      -Pair()
/*      -readFile()
/*      -output()
/******************************************************/ 


public class Program7{
    public static void main(String[] args){
        String fileName = args[0];
        double xk = Double.parseDouble(args[1]);

        LinkedListManager<Pair<Double, Double>> linkedList = CsvFileReader.readFile(fileName, 1, 2);//格納済みリンクリスト取得

        double[] stdDevs = Calculator.calcStandardDevision(linkedList);//t分布関数の積分値を3組の自由度、積分範囲の上端に対して計算

        int outputIndex = 0;
        while(current_node != null){//リンクリストのすべての要素を処理したか？
            double topEndX = current_node.data.firstData.doubleValue();//積分範囲の上端
            int dof = current_node.data.secondData.intValue();//自由度dof
            Output.output(integrals[outputIndex], topEndX, dof);
            current_node = current_node.next;
            outputIndex++;
        }
    }
}
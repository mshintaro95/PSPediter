/******************************************************
/*プログラム課題: 6-Program6
/*名前: 三留 慎太郎
/*日付: 20251006
/*プログラムの説明:t 関数を 0 から x まで積分した値が p になるような x の値を求める。
/*クラスの説明：プログラムの開始点として起動を行う。すべてのデータについてそれぞれ積分値が目的値となる積分範囲上端を計算しoutputに渡す。
/********************************************************/ 

/******************************************************
/*コンテンツリスト:
/* 
/* 
/* コマンドプロンプトで以下を入力してコンパイル
/* javac Program6.java
/* java Program6 data_program6.csv
/*  クラス宣言:
/*      -Program6
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
/*      -findUpperLimit()
/*      -Pair()
/*      -readFile()
/*      -output()
/******************************************************/ 


public class Program6{
    public static void main(String[] args){
        String fileName = args[0];

        LinkedListManager<Pair<Double, Double>> linkedList = CsvFileReader.readFile(fileName, 1, 2);//格納済みリンクリスト取得
        LinkedListNode<Pair<Double, Double>> current_node = linkedList.head;

        while(current_node != null){//リンクリストのすべての要素を処理したか？
            double p = current_node.data.firstData.doubleValue();//積分の目標値
            int dof = current_node.data.secondData.intValue();//自由度dof
            double topEndX = Calculator.findUpperLimit(p, dof);
            Output.output(p, dof, topEndX);
            current_node = current_node.next;
        }
    }
}
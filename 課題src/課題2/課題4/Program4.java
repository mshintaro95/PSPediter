/******************************************************
/*プログラム課題: 4-Program4
/*名前: 三留 慎太郎
/*日付: 20250616
/*プログラムの説明:対数正規分布の標準偏差を用いて、VS,S,M,L,VL の相対規模範囲を計算し、それぞれの中点を出力する。
/*クラスの説明：プログラムの開始点として起動を行う。相対規模範囲それぞれの中点を計算してoutputに渡す。
/********************************************************/ 

/******************************************************
/*コンテンツリスト:
/* 
/* 
/* コマンドプロンプトで以下を入力してコンパイル
/* javac Program4.java
/* java Program4 data_program4_2.csv
/*  クラス宣言:
/*      -Program4
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
/*      -calcAverage()
/*      -calcStandardDeviation()
/*      -calcBeta1()
/*      -calcBeta0()
/*      -LogNormallyTransform()
/*      -CalcRelativeSizeRanges()
/*      -Pair()
/*      -readFile()
/*      -output()
/******************************************************/ 


public class Program4{
    public static void main(String[] args){
        String fileName = args[0];
        int columnX = 2;
        int columnY = 3;

        LinkedListManager<Pair<Double, Double>> linkedList = CsvFileReader.readFile(fileName, columnX, columnY);//格納済みリンクリスト取得

        double[] relativeSizeRanges = Calculator.CalcRelativeSizeRanges(linkedList);//
        Output.output(relativeSizeRanges);
    }
}
/******************************************************
/*プログラム課題: 3-Program3
/*名前: 三留 慎太郎
/*日付: 20250604
/*プログラムの説明:入力されたデータ対群と見積もり値xkから回帰パラメータベータ0,ベータ1,相関係数rxy,r2,予測値ykを計算する
/*クラスの説明：プログラムの開始点として起動を行う。相関係数,予測値を計算して各種データをoutputに渡す。
/********************************************************/ 

/******************************************************
/*コンテンツリスト:
/* 
/* 
/* コマンドプロンプトで以下を入力してコンパイル
/* javac Program1.java
/* java Program1 data_pair.csv 2 3 386
/*  クラス宣言:
/*      -Program3
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
/*      -Pair()
/*      -readFile()
/*      -output()
/******************************************************/ 

/******************************************************
/*再利用の説明
/* 
/*  LinkedListManager<Pair<Double, Double>> readFile(String file_name, int column1, int column2)
/*  目的：csvファイルから指定の2列を取り出しPair型でリンクリストに格納する
/*  制約：Pairクラス,LinkedListManagerクラス,LinkedListNodeクラスが定義されていること。
/*  
/*  Pair(T1 firstData, T2 secondData)
/*  目的：2つのデータを扱うPair型を定義すること
/*  
/******************************************************/ 

public class Program3{
    public static void main(String[] args){
        String fileName = args[0];
        int columnX = Integer.parseInt(args[1]);
        int columnY = Integer.parseInt(args[2]);
        double xk = Double.parseDouble(args[3]);

        LinkedListManager<Pair<Double, Double>> linkedList = CsvFileReader.readFile(fileName, columnX, columnY);//格納済みリンクリスト取得

        double[] stdDevs = Calculator.calcStandardDeviation(linkedList);//x,yの標準偏差取得
        double Beta0 = Calculator.calcBeta0(linkedList);//ベータ0取得
        double Beta1 = Calculator.calcBeta1(linkedList);//ベータ1取得

        double yk = Beta0 + (xk * Beta1);//予測値計算
        double rxy = Beta1 * stdDevs[0] / stdDevs[1];//相関係数計算
        double r2 = rxy * rxy;

        Output.output(Beta0, Beta1, rxy, r2, yk);
    }
}
/******************************************************
/*プログラム課題: 1-Program1
/*名前: 三留 慎太郎
/*日付: 20250514
/*プログラムの説明:入力された実数群の平均と標準偏差を計算する
/*クラスの説明：プログラムの開始点として起動を行う。
/********************************************************/ 

/******************************************************
/*コンテンツリスト:
/* 
/* 
/* コマンドプロンプトで以下を入力してコンパイル
/* javac Program1.java
/* java Program1 input.txt
/*  クラス宣言:
/*      -Program1
/*      -LinkedListManager
/*      -LinkedListNode
/*      -Calculator
/*  実装メソッド
/*      -main()
/*      -LinkedListManager()
/*      -addNode()
/*      -removeNode()
/*      -nextNode()
/*      -priorNode()
/*      -readFile()
/*      -Calculator()
/*      -calcStandardDeviation()
/*      -LinkedListNode()
/******************************************************/ 

public class Program1{
    public static void main(String[] args){
        LinkedListManager.readFile(args[0]);
        System.out.println("平均：" + String.format("%.2f", Calculator.calcAverage()));
        System.out.println("標準偏差：" + String.format("%.2f", Calculator.calcStandardDeviation()));
    }
}
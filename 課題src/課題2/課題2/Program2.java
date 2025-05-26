/******************************************************
/*プログラム課題: 2-Program2
/*名前: 三留 慎太郎
/*日付: 20250514
/*プログラムの説明: java プログラム全体の LOC, クラス毎の LOC, クラス毎のメソッド数を数える。
/*クラスの説明：プログラムの開始点として起動を行う。
/********************************************************/ 

/******************************************************
/*コンテンツリスト:
/* 再利用の説明
/* getClassName(String dirName)
/* dirNameで指定したデイレクトリに入っているファイルの名前を取得する
/* ディレクトリ名は課題+プログラム番号(例：課題2)とし、カレントディレクトリに配置すること。
/* 
/* getProgramNumber(String dirName)
/* 与えられたデイレクトリ名からプログラム番号を取得する。
/* ディレクトリ名は課題+プログラム番号(例：課題2)とすること。
/*
/* lineCounter(String dirName)
/* dirNameを引数としてjavaファイルそれぞれの行数をカウントしてint型配列で返す。
/* メソッドの中で用いるためgetClassName(),getProgramNumber()の二つを利用できる必要がある。
/* 
/* methodCounter(String dirName)
/* dirNameを引数としてjavaファイルそれぞれのメソッド数をカウントしてint型配列で返す。
/* メソッドの中で用いるためgetClassName(),getProgramNumber()の二つを利用できる必要がある。
/* 
/* 
/* コマンドプロンプトで以下を入力してコンパイル
/* javac Program2.java
/* java Program2 課題+課題番号
/*  クラス宣言:
/*      -Program1
/*      -GetFileName
/*      -LineCounter
/*      -ToCsv
/*  実装メソッド
/*      -main()
/*      -getClassName()
/*      -getProgramNumber()
/*      -lineCounter()
/*      -methodCounter()
/*      -locToCsv()
/******************************************************/ 

public class Program2{
    public static void main(String[] args){
        ToCsv.locToCsv(args[0]);        //locToCsvを呼び出して規模をcsvファイルに書き出す
    }
}
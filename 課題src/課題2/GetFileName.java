/******************************************************
/*プログラム課題: 2-GetFileName
/*名前: 三留 慎太郎
/*日付: 20250514
/*プログラムの説明: java プログラム全体の LOC, クラス毎の LOC, クラス毎のメソッド数を数える。
/*クラスの説明：デイレクトリに存在するjavaファイルの名前とプログラム番号を取得する
/********************************************************/ 

import java.io.*;

public class GetFileName {

    public static String[] getClassName(String dirName){
        File dir = new File("./" + dirName);
        File[] files = dir.listFiles();

        if(files == null){      //デイレクトリのすべてのファイルを処理できるまで
            return new String[0];
        }

        String[] className = new String[files.length];      //クラス数分のクラス名を保持する配列作成
        for(int i = 0; i < files.length; i++){              //クラス数分処理したか？
            className[i] = files[i].getName();
        }
        return className;
    }

    public static int getProgramNumber(String dirName){
        String numberOfString = dirName.replaceAll("[^0-9]", "");       //デイレクトリ名からプログラム番号をStringで抽出
        int programNumber = Integer.parseInt(numberOfString);           //intに変換

        return programNumber;
    }
}

/******************************************************
/*プログラム課題: 2-ToCsv
/*名前: 三留 慎太郎
/*日付: 20250514
/*プログラムの説明: java プログラム全体の LOC, クラス毎の LOC, クラス毎のメソッド数を数える。
/*クラスの説明：データをCSVファイルとして出力する。
/********************************************************/ 

import java.io.*;

public class ToCsv {
    public static void locToCsv(String dirName){
        String[] classNames = GetFileName.getClassName(dirName);    //すべてのクラス名
        int programNumber = GetFileName.getProgramNumber(dirName);  //プログラム番号
        int[] locs = LineCounter.lineCounter(dirName);              //クラス毎のLOC
        int[] methodCount = LineCounter.methodCounter(dirName);     //クラス毎のメソッド数
        int totalLoc = 0;       //プログラム全体のLOCを数える

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.csv"))) {
            String line = "プログラム番号,クラス名,メソッド数,クラス規模,合計規模";     //1行目に表示する項目を書く
            writer.write(line);
            writer.newLine();
            line = programNumber + "," + classNames[0].replace(".java", "") + "," + methodCount[0]+ "," + locs[0] + ",";    //2行目にプログラム番号と一つ目のクラスの情報を書く
            writer.write(line);
            writer.newLine();
            totalLoc += locs[0];
            for (int i = 1; i < classNames.length; i++) {       //クラスすべての規模を出力できたか？
                line = "," + classNames[i].replace(".java", "") + "," + methodCount[i]+ "," + locs[i] + ",";
                writer.write(line);
                writer.newLine();
                totalLoc += locs[i];
            }
            line = ",,,," + totalLoc;           //最後の行は合計規模のみを書く
            writer.write(line);
            writer.newLine();

            System.out.println("CSVファイルに出力しました。");
        } catch (IOException e) {       //ファイル処理でエラーが起きた時の処理
            System.out.println("ファイル出力中にエラーが発生しました: " + e.getMessage());
        }
    }
}

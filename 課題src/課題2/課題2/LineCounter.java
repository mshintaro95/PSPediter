/******************************************************
/*プログラム課題: 2-LineCounter
/*名前: 三留 慎太郎
/*日付: 20250514
/*プログラムの説明: java プログラム全体の LOC, クラス毎の LOC, クラス毎のメソッド数を数える。
/*クラスの説明：ファイル毎にLOCをカウントする。
/********************************************************/ 
import java.io.*;
public class LineCounter {
    public static int[] lineCounter(String dirName){
        String[] classNames = GetFileName.getClassName(dirName);
        int[] locs = new int[classNames.length];        //各クラスの行数を保持する配列
        int count = 0;                                  //何個目のクラスを処理しているかを管理
        for(int i = 0; i < classNames.length; i++){     //すべてのクラスを処理したか？
            File file = new File("./課題" + GetFileName.getProgramNumber(dirName) + "/" + classNames[i]);
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String line;
                while((line = reader.readLine()) != null) {     //行数分処理
                    if(line.isEmpty() || line.startsWith("/*")){        //空行もしくはコメント行はカウントしない
                        ;;
                    } else {
                        locs[count]++;      //行数をカウント
                    }
                }
            } catch (IOException e) {
                System.out.println("ファイル読み込み中にエラーが発生しました: " + e.getMessage());
            }
            count++;
        }
        return locs;
    }
    public static int[] methodCounter(String dirName){
        String[] classNames = GetFileName.getClassName(dirName);
        int[] methodCount = new int[classNames.length];     //各クラスのメソッド数を保持する配列
        int count = 0;                                      //何個目のクラスをほっじしているかを管理
        for(int i = 0; i < classNames.length; i++){         //すべてのクラスを処理したか？
            File file = new File("./課題" + GetFileName.getProgramNumber(dirName) + "/" + classNames[i]);
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String line;
                while((line = reader.readLine()) != null) {     //行数分処理
                    if(line.startsWith("    ")){
                        if(line.startsWith("     ") || line.startsWith("    }")){           //以下6行で4字分のインデントがあり、5文字目が空白もしくは}ではないものをメソッドとして数える。
                            ;;
                        } else if(line.endsWith(";")) {
                            ;;
                        } else {
                            methodCount[i]++;       //メソッド数をカウント
                            System.out.println(gyou);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("ファイル読み込み中にエラーが発生しました: " + e.getMessage());
            }
        }
        return methodCount;
    }
}
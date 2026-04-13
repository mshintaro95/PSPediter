/******************************************************
/*プログラム課題: 7-Program7
/*名前: 三留 慎太郎
/*日付: 20260412
/*プログラムの説明:過去のデータから見積もり値に対する予測値と70%の予測区間を計算する。
/*クラスの説明：Csvから2列を取り出しPair型でリンクリストに格納する。
/********************************************************/ 

import java.io.*;

public class CsvFileReader {

    public static LinkedListManager<Pair<Double, Double>> readFile(String file_name, int column1, int column2){

        LinkedListManager<Pair<Double, Double>> linkedList = new LinkedListManager<>();

        try{
            File f = new File("./" + file_name);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while((line = br.readLine()) != null){//ファイルのすべての行を処理したか？
                String[] tokens = line.split(",");
                double value1 = Double.parseDouble(tokens[column1 - 1].trim());//column1のデータ抽出
                double value2 = Double.parseDouble(tokens[column2 - 1].trim());//column2のデータ抽出
                Pair<Double, Double> data = new Pair<>(value1, value2);
                linkedList.addNode(data);
            }
            br.close();
        }catch (IOException e) {
        e.printStackTrace();
        }

        return linkedList;
    }
}

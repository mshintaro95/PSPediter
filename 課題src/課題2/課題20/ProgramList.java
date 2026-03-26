/*****************************************************
/*プログラム課題: ProjectX-ProgramList(Cycle 2)
/*名前: Shun Kakumura
/*日付: 2026-02-24
/*プログラムの説明:プログラムの差分を抽出し、変更について管理する
/*クラスの説明：プログラムの差分を抽出する
/*****************************************************/

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate; //add
import java.time.format.DateTimeFormatter; //add

public class ProgramList {
    String newVersion;           // 今回付けるバージョン番号
    List<LineInfo> addLines;     // 追加行情報
    List<LineInfo> deleteLines;  // 削除行情報
    int addLoc;                  // 追加Loc
    int deleteLoc;               // 削除Loc
    List<LineInfo> afterLines;   // 変更後ファイルの行情報
    List<LineInfo> labeledLines; // 行ラベルが付けられた行情報

    ProgramList(List<LineInfo> beforeLines, List<LineInfo> afterLines, String newVersion) {
        this.addLines = new ArrayList<>();
        this.deleteLines = new ArrayList<>();
        this.labeledLines = new ArrayList<>(); //add

        this.newVersion = newVersion;

        /*
        // == test part
        System.out.println("beforeLines=");
        for (LineInfo line : beforeLines) {
            System.out.println(String.valueOf(line.rowNumber) + " " + line.content);
        }
        System.out.println("afterLines=");
        for (LineInfo line : afterLines) {
            System.out.println(String.valueOf(line.rowNumber) + " " + line.content);
        }
        // test part ==
        */
        analyzeDiff(beforeLines, afterLines);
    }

    public int[] getLoc() {
        int[] loc = {addLoc, deleteLoc};
        return loc;
    }

    @SuppressWarnings("unchecked")
    public List<LineInfo>[] getDiffLines() {
        List<LineInfo>[] diff = new List[2];
        diff[0] = addLines;
        diff[1] = deleteLines;
        return diff;
    }

    private void analyzeDiff(List<LineInfo> before, List<LineInfo> after) {
        int i = 0; // before index
        int j = 0; // after index
        LineInfo b;
        LineInfo a;
        String trimmed; //add
        int labeledIndex = 9; //add ヘッダコメント8行から行番号を振りなおす

        // beforeのヘッダコメント部分をスキップ
        if ( before.get(1).content.startsWith(" * バージョン番号：") ) { //add
            i = 8; //add
        } //add

        // beforeとafterの短い方の終わりまで一行ずつ比較
        while ( i < before.size() && j < after.size() ) {
            b = before.get(i);
            a = after.get(j);

            // 削除行ラベルの場合、labeledLinesに追加してスキップ
            trimmed = b.content.trim(); //add
            if ( trimmed.startsWith("// {") && trimmed.endsWith("]") ) { //add
                b = new LineInfo(labeledIndex, b.content); //add
                labeledIndex++; //add
                labeledLines.add(b); //add
                i++; //add
                continue; //add
            } //add

            // 行の内容が同じなら、次の行に進む
            if ( isSameLine(b, a) ) {
                a = new LineInfo(labeledIndex, a.content); //add
                labeledIndex++; //add
                labeledLines.add(a); //add
                i++;
                j++;
                continue;
            }
            // 比較対象がafterで後から出てくる = afterに追加行
            // afterLinesのみ次の行に進む
            else if ( existsLater(after, j + 1, b) ) {
                addLines.add(labeled(a, "ADD"));
                a = new LineInfo(labeledIndex, a.content); //add
                labeledIndex++; //add
                labeledLines.add(labeled(a, "ADD")); //add
                j++;
            }
            // 比較対象がbeforeで後から出てくる = beforeに削除行
            // beforeLinesのみ次の行に進む
            else if ( existsLater(before, i + 1, a) ) {
                deleteLines.add(labeled(b, "DEL"));
                b = new LineInfo(labeledIndex, b.content); //add
                labeledIndex++; //add
                labeledLines.add(labeled(b, "DEL")); //add
                i++;
            }
            // どれにも当てはまらない = 同じ行に追加・削除がある
            else {
                addLines.add(labeled(a, "ADD"));
                a = new LineInfo(labeledIndex, a.content); //add
                labeledIndex++; //add
                labeledLines.add(labeled(a, "ADD")); //add
                i++;
                deleteLines.add(labeled(b, "DEL"));
                b = new LineInfo(labeledIndex, b.content); //add
                labeledIndex++; //add
                labeledLines.add(labeled(b, "DEL")); //add
                j++;
            }
        }

        // afterを先に読み終わったら、beforeの残りを削除行として処理
        while ( i < before.size() ) {
            b = before.get(i);
            // 削除行ラベルの場合、labeledLinesに追加してスキップ
            trimmed = b.content.trim(); //add
            if ( trimmed.startsWith("// {") && trimmed.endsWith("]") ) { //add
                b = new LineInfo(labeledIndex, b.content); //add
                labeledIndex++; //add
                labeledLines.add(b); //add
                i++; //add
                continue; //add
            } //add
            deleteLines.add(labeled(b, "DEL"));
            b = new LineInfo(labeledIndex, b.content); //add
            labeledIndex++; //add
            labeledLines.add(labeled(b, "DEL")); //add
            i++;
        }

        // beforeを先に読み終わったら、afterの残りを追加行として処理
        while ( j < after.size() ) {
            a = after.get(j);
            addLines.add(labeled(a, "ADD"));
            a = new LineInfo(labeledIndex, a.content); //add
            labeledIndex++; //add
            labeledLines.add(labeled(a, "ADD")); //add
            j++;
        }

        addLoc = addLines.size();
        deleteLoc = deleteLines.size();

        // 変更がない場合エラー出力
        if ( addLoc == 0 && deleteLoc == 0 ) {
            System.out.println("変更が見つかりませんでした。");
        }
    }

    private boolean isSameLine(LineInfo lineA, LineInfo lineB) {
        String strA = lineA.content;
        int index = strA.indexOf(" //");
        if ( index >= 0 ) {
            strA = strA.substring(0, index);
        }

        String strB = lineB.content;
        index = strB.indexOf(" //");
        if ( index >= 0 ) {
            strB = strB.substring(0, index);
        }

        return strA.equals(strB);
    }

    private LineInfo labeled(LineInfo line, String type) {
        if ( type.equals("ADD") ) {
            String temp = line.content + " // [";
            temp += newVersion + "]";
            LineInfo labeledLine = new LineInfo(line.rowNumber, temp);
            return labeledLine;
        }

        if ( type.equals("DEL") ) {
            String temp = "// {" + line.content;
            temp += "} [" + newVersion + "]";
            LineInfo labeledLine = new LineInfo(line.rowNumber, temp);
            return labeledLine;
        }

        return line;
    }

    private boolean existsLater(List<LineInfo> lines, int start, LineInfo x) {
        int i;

        for ( i = start; i < lines.size(); i++ ) {
            if ( isSameLine(lines.get(i), x) ) {
                return true;
            }
        }

        return false;
    }

    // generateSourceFile: 全部add
    List<LineInfo> generateSourceFile(String oldVersion, String author, String reason) {
        // 今日の日付を取得
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date = today.format(formatter);

        // ヘッダコメント生成
        LineInfo head = new LineInfo(1, "/*");
        LineInfo nv = new LineInfo(2, " * バージョン番号：" + newVersion);
        LineInfo ov = new LineInfo(3, " * 前バージョン　：" + oldVersion);
        LineInfo d = new LineInfo(4, " * 変更日　　　　：" + date);
        LineInfo a = new LineInfo(5, " * 担当者　　　　：" + author);
        LineInfo r = new LineInfo(6, " * 変更理由　　　：" + reason);
        LineInfo tail = new LineInfo(7, " */");
        LineInfo space = new LineInfo(8, "");

        List<LineInfo> header = new ArrayList<LineInfo>(){{
                add(head);
                add(nv);
                add(ov);
                add(d);
                add(a);
                add(r);
                add(tail);
                add(space);
        }};

        // labeledLinesと結合
        header.addAll(labeledLines);
        return header;
    }
}
/*****************************************************
/*プログラム課題: ProjectX-Main
/*名前: 三留 慎太郎
/*日付: 20251227
/*プログラムの説明:プログラムの差分を抽出し、変更について管理する
/*クラスの説明：オプションを識別し、対応した関数を呼び出す
/*****************************************************/

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.File;
import java.util.Arrays;



public class Main {
    private String afterFileName;            //ファイル名(例：main.java)
    private String beforeFileName;
    private String newVersion;          //変更後ファイルのバージョン番号
    private String oldVersion;          //変更前ファイルバージョン番号
    private List<LineInfo> beforeLines; //変更前ファイル内容
    private List<LineInfo> afterLines;  //変更後ファイル内容
    private String option;              //オプション(-c, -d)
    private String author;              //担当者名
    private String reason;              //変更理由
    private final String[] options = {"-c", "-d", "-o", "-p", "-l"}; //オプション

    public static void main(String[] args){
        Output output = new Output();
        Main myObj = new Main();
        if(args.length < 2){
            output.printUsage();//使い方を出力
            System.exit(1);
        }
        myObj.parseArgs(args, output);
        myObj.execute(output);
    }

    void parseArgs(String[] args, Output output){
        afterFileName = "";
        newVersion = "";
        oldVersion = "";
        option = "";

        //受け取ったオプションがoptionsに含まれているか確認
        if(Arrays.asList(options).contains(args[0])) {
            option = args[0];
        }
        else {
            output.printUsage();//使い方を出力
            System.exit(1);
        }
        afterFileName = args[1];
        if(option.equals("-c") || option.equals("-d") || option.equals("-l")) {
            lengthChecker(output, args, 3);
            if (args.length >= 3) {
                isVersionNumber(output, args[2]) ;
                oldVersion = args[2];
            }
        }
        else if(option.equals("-o") || option.equals("-p")) {
            lengthChecker(output, args, 5);
            if (args.length == 5) {
                if(isVersionNumber(output, args[2])) {
                    oldVersion = args[2];
                    author = args[3];
                    reason = args[4];
                } 
                else {
                    output.printUsage();//使い方を出力
                    System.exit(1);
                }
            }
            else if(args.length == 4) {
                if(isVersionNumber(output, args[2])) {
                    output.printUsage();//使い方を出力
                    System.exit(1);
                }
                author = args[2];
                reason = args[3];
            }
        }
    }

    void execute(Output output){
        if(option.equals("-c")){
            executeCountLoc(output);
        } else if(option.equals("-d")){
            executeDiffLines(output);
        } else if(option.equals("-o")){
            executeGenerateProgramSource(output);
        } else if(option.equals("-p")){
            executeGeneratePrintProgramSource(output);
        } else if(option.equals("-l")){
            executePrintProgramSource(output);
        } else {
            output.printUsage();//使い方を出力
            System.exit(1);
        }
    }

    private void executeCountLoc(Output output){
        readBeforeFile();
        readAfterFile();
        ProgramList programList = new ProgramList(beforeLines, afterLines, newVersion);
        int[] loc = programList.getLoc();
        int addLoc = loc[0];
        int deleteLoc = loc[1];

        output.printLoc(addLoc, deleteLoc);
    }

    private void executeDiffLines(Output output){
        readBeforeFile();
        readAfterFile();
        
        ProgramList programList = new ProgramList(beforeLines, afterLines, newVersion);
        List<LineInfo>[] differentLine = programList.getDiffLines();
        List<LineInfo> diffLines = new ArrayList<>();
        diffLines.addAll(differentLine[1]);
        diffLines.addAll(differentLine[0]);        
        
        output.printDiff(diffLines);
    }

    private void executeGenerateProgramSource(Output output){
        readBeforeFile();
        readAfterFile();
        
        ProgramList programList = new ProgramList(beforeLines, afterLines, newVersion);
        List<LineInfo> generatedProgramList = programList.generateSourceFile(oldVersion, author, reason);
        output.makeProgramList(generatedProgramList, afterFileName, newVersion);
    }
    
    private void executeGeneratePrintProgramSource(Output output){
        readBeforeFile();
        readAfterFile();

        if ( "".equals(oldVersion) ) {
            programFileReader.readLatestVersionFile(afterFileName, ".versions/");
            oldVersion = programFileReader.getLatestVersion();
        }

        ProgramList programList = new ProgramList(beforeLines, afterLines, newVersion);
        List<LineInfo> generatedProgramList = programList.generateSourceFile(oldVersion, author, reason);     
        output.makeProgramList(generatedProgramList, afterFileName, newVersion);
        output.printProgramList(generatedProgramList, afterFileName, newVersion);
    }

    private void executePrintProgramSource(Output output){
        String path =  ".versions/";
        ProgramFileReader programFileReader = new ProgramFileReader();

        //ファイル名から.versions/changeReport/のファイルの中身を取得してくる
        if("".equals(oldVersion)){
            beforeLines = programFileReader.readLatestVersionFile(afterFileName, path);
            oldVersion = programFileReader.getLatestVersion();
        } 
        else{
            beforeLines = programFileReader.readFileByVersion(afterFileName, oldVersion, path);
        }
        output.printProgramList(beforeLines, afterFileName, oldVersion);
    }


    private void lengthChecker(Output output, String[] args, int maxRange) {
        int minRange = maxRange - 1;

        if (!(args.length >= minRange && args.length <= maxRange)) {
            output.printUsage();//使い方を出力
            System.exit(1);
        } 
    }

    private boolean isVersionNumber(Output output, String versionNumber) {
        if(versionNumber == null || versionNumber.isEmpty()) {
            output.printUsage();//使い方を出力
            System.exit(1);
        }
        for (int i =0; i < versionNumber.length(); i++) {
            char c = versionNumber.charAt(i);
            if((!(Character.isDigit(c))) && c != '.') {
                return false;
            }
        }
        return true;
    }

    private void readBeforeFile() {
        String path = ".versions/";

        ProgramFileReader programFileReader = new ProgramFileReader();
        if("".equals(oldVersion)){
            beforeLines = programFileReader.readLatestVersionFile(afterFileName, path);
            newVersion = programFileReader.getNewVersion(afterFileName, path);
            beforeFileName = programFileReader.getFileName();
        } else if("0".equals(oldVersion)){
            String baseName = afterFileName.replaceFirst("\\.java$", "");
            String beforeFileName = "src/" + baseName + "_ver0.java";
            beforeLines= programFileReader.readFile(beforeFileName);
            newVersion = "1.0";
        } else {
            beforeLines= programFileReader.readFileByVersion(afterFileName, oldVersion, path);
            newVersion = programFileReader.getNewVersionByVersion(afterFileName, oldVersion, path);
            beforeFileName = programFileReader.getFileName();
        }
    }
    private void readAfterFile() {
        ProgramFileReader programFileReader = new ProgramFileReader();
        afterLines= programFileReader.readFile("src/" + afterFileName);
    }
}
/******************************************************
* プログラム課題: ProjectX-ProgramFileReader
* 名前: 高橋 龍馬/
* 日付: 20260102/
* プログラムの説明:ファイル名やバージョン番号から該当するファイルをⅠ行ずつ行番号と内容を取得し、返す
* クラスの説明：ファイル名やバージョン番号から該当するファイルを取得する為のクラス
********************************************************/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class ProgramFileReader{
    String versionDirNumber = ".versions/";
    String beforeFileName;
    String latestVersion;

    ProgramFileReader() {
        beforeFileName = "";
    }
    public ArrayList<LineInfo> readFile(String fileName) {
        int rowCounter = 0;
        ArrayList<LineInfo> fileInfo = new ArrayList<>();
        javaExtensionCheck(fileName);

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = br.readLine()) != null) {
               rowCounter++;
               LineInfo tmpInfo = new LineInfo(rowCounter, line);

               fileInfo.add(tmpInfo);
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("指定したファイルが見つかりません");
            System.exit(0); 
        } catch (IOException e) {
            System.out.println("指定したファイルが見つかりません");
            System.exit(0);
        }
        return fileInfo;
    }

    public ArrayList<LineInfo> readFileByVersion(String fileName, String version, String path) {
        String baseFileName;
        String filePath; 
        javaExtensionCheck(fileName);
        baseFileName = fileExtensionRemoval(fileName);
        filePath = path + baseFileName + "_ver" + String.valueOf(Double.parseDouble(version)) + ".java";
        File file = new File(filePath);
        if(!file.exists()) {
            System.out.println("指定したバージョンのファイルはありません。");
            System.exit(0);
        }
        ArrayList<LineInfo> fileInfo = new ArrayList<>();
        fileInfo = readFile(filePath);
        beforeFileName = fileName;
        return fileInfo;
    }

    public ArrayList<LineInfo> readLatestVersionFile(String fileName, String path) {
        String latestFileName = null;
        long latestModified = -1L;
        File versionDir;
        File [] files;
        String baseFileName; 
        javaExtensionCheck(fileName);
        baseFileName = fileExtensionRemoval(fileName);

        //隠しDir内のファイル一覧を取得
        versionDir = new File(path); 
        files = versionDir.listFiles();

        //if ファイル一覧　!= null
        if (files != null) {
            //ファイル一つ一つに対して引数のファイル名で始まるか確認
            for (File file : files) {
                if (file.getName().startsWith(baseFileName + "_ver")) {
                    //そのファイルの更新日時を取得
                    long modified = file.lastModified();
                    //既に保持している更新日時より新しいか確認
                    if (latestModified < modified) {
                        //if 新しいなら
                        //ファイルを更新
                        latestModified = modified;
                        latestFileName = file.getName();
                    }
                }
            }
        }      
        // if 保持するファイル名 = null 
        if (latestFileName == null) {
            System.out.println("指定したファイルの最新ファイルはありません。");
            System.exit(0);
        }

        ArrayList<LineInfo> fileInfo = new ArrayList<>();
        fileInfo = readFile(path + latestFileName);
        beforeFileName = latestFileName;

        return fileInfo;   
    }

    public String getNewVersion(String fileName, String path) {
        String latestFileName = "";
        long latestModified = -1L;
        String newVersion = "";
        int searchStartPosition;
        int searchFinalPosition;
        String baseFileName = ""; //拡張子なしのファイル名
        File versionDir;
        File [] files;

        baseFileName = fileExtensionRemoval(fileName);
        //隠しDir内のファイル一覧を取得
        versionDir = new File(path); 
        files = versionDir.listFiles();

        //if ファイル一覧　!= null
        if (files != null) {
            //ファイル一つ一つに対して引数のファイル名で始まるか確認
            for (File file : files) {
                if (file.getName().startsWith(baseFileName + "_ver")) {
                    //そのファイルの更新日時を取得
                    long modified = file.lastModified();
                    //既に保持している更新日時より新しいか確認
                    if (latestModified < modified) {
                        //if 新しいなら
                        //ファイルを更新
                        latestModified = modified;
                        latestFileName = file.getName();
                    }
                }
            }
            if(latestFileName == "") {
                return "-1";
            }
        }
        else{
            return "-2";
        }
        //_verから.javaまでの文字列を取得する関数
        //_verのrのlastindexを取得
        searchStartPosition = latestFileName.lastIndexOf("r") + 1;
        //.javaのlastindexを取得
        searchFinalPosition = latestFileName.lastIndexOf(".java");
        //indexからindexのsubstringを取得する
        newVersion = String.valueOf(Double.parseDouble(latestFileName.substring(searchStartPosition, searchFinalPosition)) + 1);
        return newVersion;
    }
    public String getNewVersionByVersion(String fileName, String version, String path) {
        String baseFileName = ""; //拡張子なしのファイル名
        String potentialVersion = "";
        String potentialFilePath = "";
        String newVersion = "";
        String filePath = "";
        int versionIndex;
        int fixedVersion;
        File file;
        File potentialFile;
        String lastVersion;
        String preVersion;
        int isFirst = 0;

        //baseFileNameを取得
        baseFileName = fileExtensionRemoval(fileName);
        //baseFileName + version のファイルがあるのか確認
        filePath = path + baseFileName + "_ver" + String.valueOf(Double.parseDouble(version)) + ".java";
        file = new File(filePath);
        if(!file.exists()) {
            return "-1";
        }
        while(newVersion.isEmpty()) {
            //versionをdouble型数値に変換し,+1する
            potentialVersion = String.valueOf(Double.parseDouble(version) + 1);
            //potentialVersionとbaseFileNameでファイルがあるのか確認
            potentialFilePath = path + baseFileName + "_ver" + potentialVersion + ".java";
            potentialFile = new File(potentialFilePath);
            if(!potentialFile.exists()) {
                newVersion = potentialVersion;
            }
            else  {
                //versionを最後の.を取得
                versionIndex = version.lastIndexOf(".");
                //そのインデックスより前の値を取得
                preVersion = version.substring(0, versionIndex);
                //そのインデックスより後の値を取得
                lastVersion = version.substring(versionIndex + 1);

                if (isFirst == 0) {
                    if(Double.parseDouble(lastVersion) != 0) {
                        version = preVersion + "." + lastVersion + ".1";
                    }
                    isFirst++;
                }
                else {
                    //後の値に++
                    //前の値と.と++後のあとの値を合体する
                    fixedVersion = Integer.parseInt(lastVersion);
                    fixedVersion++;
                    version = preVersion + "." + String.valueOf(fixedVersion);
                }
            }
        }
        return newVersion;
    }
    public String getFileName() {
        return beforeFileName;
    }
    public String getLatestVersion() {
        int lastVerIndex = beforeFileName.lastIndexOf("ver");
        int lastDotJavaIndex = beforeFileName.lastIndexOf(".java"); 
        
        return beforeFileName.substring(lastVerIndex + 3, lastDotJavaIndex);
    }

    private String fileExtensionRemoval(String fileName)
    {
        int extension;
        String baseFileName = fileName;
        extension = fileName.lastIndexOf(".");
        if (extension >= 0) {
            baseFileName = fileName.substring(0, extension);
        }
        return baseFileName;   
    }
    private void javaExtensionCheck(String fileName) {
        if(fileName.endsWith(".java")) {
            return;
        }
        else {
            System.out.println("javaファイルを指定してください");
            System.exit(0);
        }
    }
}


import java.util.List;

public class Output {

    public void printUsage() {
        System.out.println("使い方");
    }

    public void printLoc(int addLoc, int deleteLoc) {
        System.out.println("addLoc=" + addLoc);
        System.out.println("deleteLoc=" + deleteLoc);
    }

    public void printDiff(List<LineInfo> diffLines) {
        if (diffLines == null) {
            System.out.println("(diffLines is null)");
            return;
        }
        System.out.println("diffLines：");
        for (LineInfo li : diffLines) {
            System.out.println(li.rowNumber + "\t" + li.content);
        }
    }
}
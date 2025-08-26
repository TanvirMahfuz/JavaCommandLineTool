package printer;
import java.util.List;

public class OutputPrinter {
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m"; // files
    private static final String BLUE = "\u001B[34m";  // directories

    public void print(List<String> lines) {
        for (String line : lines) {
            printColoredLine(line);
        }
    }

    private void printColoredLine(String line) {
        line = line.trim();
        if (line.startsWith("d")) {          // directory
            System.out.println(BLUE + line + RESET);
        } else if (line.startsWith("-")) {   // file
            System.out.println(GREEN + line + RESET);
        } else {
            System.out.println(line);        // header or other
        }
    }
}

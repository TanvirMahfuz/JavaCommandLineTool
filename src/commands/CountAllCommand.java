package commands;

import commandbuilder.CommandBuilder;
import java.io.File;

public class CountAllCommand implements Command {

    private final CommandBuilder builder;

    public CountAllCommand(CommandBuilder builder) {
        this.builder = builder;
    }

    @Override
    public void execute() {
        File dir = builder.getWorkingDirectory();

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Directory does not exist or is not accessible.");
            return;
        }

        CountResult result = countRecursive(dir);

        System.out.println("Total directories (including subdirectories): " + result.dirCount);
        System.out.println("Total files: " + result.fileCount);
    }

    private CountResult countRecursive(File dir) {
        int dirCount = 0;
        int fileCount = 0;

        File[] files = dir.listFiles();
        if (files == null) return new CountResult(0, 0);

        for (File f : files) {
            if (f.isDirectory()) {
                dirCount++; // count this directory
                CountResult subResult = countRecursive(f); // count inside recursively
                dirCount += subResult.dirCount;
                fileCount += subResult.fileCount;
            } else if (f.isFile()) {
                fileCount++;
            }
        }

        return new CountResult(dirCount, fileCount);
    }

    // Helper class to hold counts
    private static class CountResult {
        int dirCount;
        int fileCount;

        CountResult(int dirCount, int fileCount) {
            this.dirCount = dirCount;
            this.fileCount = fileCount;
        }
    }
}

package commands;

import commandbuilder.CommandBuilder;

import java.io.File;

public class CountCommand implements Command {
    private final CommandBuilder builder;

    public CountCommand(CommandBuilder builder) {
        this.builder = builder;
    }

    @Override
    public void execute() {
        File dir = builder.getWorkingDirectory();
        File[] files = dir.listFiles();

        if (files == null) {
            System.out.println("Cannot access directory or it's empty.");
            return;
        }

        int fileCount = 0;
        int dirCount = 0;

        for (File f : files) {
            if (f.isFile()) fileCount++;
            else if (f.isDirectory()) dirCount++;
        }

        System.out.println("Directories: " + dirCount);
        System.out.println("Files: " + fileCount);
    }
}

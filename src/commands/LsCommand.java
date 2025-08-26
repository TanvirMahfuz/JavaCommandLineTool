package commands;


import commandbuilder.CommandBuilder;

import java.io.File;

public class LsCommand implements Command {

    private final CommandBuilder builder;

    // ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m"; // files
    private static final String BLUE = "\u001B[34m";  // directories

    public LsCommand(CommandBuilder builder) {
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

        for (File f : files) {
            if (f.isDirectory()) {
                System.out.println(BLUE + f.getName() + RESET);
            } else if (f.isFile()) {
                System.out.println(GREEN + f.getName() + RESET);
            }
        }
    }
}


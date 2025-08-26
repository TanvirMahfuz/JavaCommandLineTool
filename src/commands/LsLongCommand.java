package commands;

import commandbuilder.CommandBuilder;
import java.io.File;
import java.text.SimpleDateFormat;

public class LsLongCommand implements Command {

    private final CommandBuilder builder;

    // ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m"; // files
    private static final String BLUE = "\u001B[34m";  // directories

    private static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public LsLongCommand(CommandBuilder builder) {
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
            String type = f.isDirectory() ? "d" : "-";
            long size;

            if (f.isDirectory()) {
                // Option 1: use number of items inside as "size"
                File[] inner = f.listFiles();
                size = inner != null ? inner.length : 0;

                // Option 2: optional, just set to 4096 like Linux
                // size = 4096;
            } else {
                size = f.length();
            }

            String lastModified = DATE_FORMAT.format(f.lastModified());
            String name = f.getName();

            String coloredName = f.isDirectory() ? BLUE + name + RESET : GREEN + name + RESET;

            System.out.printf("%s %10d %s %s%n", type, size, lastModified, coloredName);
        }
    }
}

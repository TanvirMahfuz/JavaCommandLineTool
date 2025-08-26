package commands;


import commandbuilder.CommandBuilder;

import java.io.File;
import java.io.IOException;

public class CdCommand implements Command {
    private final CommandBuilder builder;
    private final String folderName;

    public CdCommand(CommandBuilder builder, String folderName) {
        this.builder = builder;
        this.folderName = folderName;
    }

    @Override
    public void execute() {
        try {
            File newDir = new File(builder.getWorkingDirectory(), folderName).getCanonicalFile();
            if (newDir.exists() && newDir.isDirectory()) {
                builder.setWorkingDirectory(newDir);
            } else {
                System.out.println("Directory does not exist: " + folderName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

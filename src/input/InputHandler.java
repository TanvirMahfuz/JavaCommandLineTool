package input;

import commandbuilder.CommandBuilder;

import java.io.File;
import java.util.*;

public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final CommandBuilder builder;

    public InputHandler(CommandBuilder builder) {
        this.builder = builder;
    }

    public String getCommand() {
        System.out.print(builder.getWorkingDirectory() + " > ");
        return scanner.nextLine().trim();
    }

    public void handleCdCommand(String input) {
        String folderName = input.substring(3).trim();
        File newDir = new File(builder.getWorkingDirectory(), folderName);
        if (newDir.exists() && newDir.isDirectory()) {
            builder.setWorkingDirectory(newDir);
        } else {
            System.out.println("Directory does not exist: " + folderName);
        }
    }
}

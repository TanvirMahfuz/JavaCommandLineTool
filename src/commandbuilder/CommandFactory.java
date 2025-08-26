package commandbuilder;

import commands.*;

public class CommandFactory {
    private final CommandBuilder builder;

    public CommandFactory(CommandBuilder builder) {
        this.builder = builder;
    }

    public Command getCommand(String input) {
        input = input.trim();
        String[] parts = input.split("\\s+"); // split by whitespace

        String cmd = parts[0]; // main command
        String arg = parts.length > 1 ? parts[1] : ""; // optional argument

        switch (cmd) {
            case "cd":
                if (!arg.isEmpty()) {
                    return new CdCommand(builder, arg);
                } else {
                    return () -> System.out.println("cd requires a folder name");
                }

            case "count":
                if ("*".equals(arg)) {
                    return new CountCommand(builder);
                }
                if ("-a".equals(arg)) {
                    return new CountAllCommand(builder);
                } else if ("-m".equals(arg)){
                    return new CountAllMulCommand(builder);
                }

            case "ls":
                if ("-l".equals(arg)) {
                    return new LsLongCommand(builder);
                } else {
                    return new LsCommand(builder);
                }

            case "exit":
                return () -> System.exit(0);

            default:
                String finalInput = input;
                return () -> System.out.println("Unknown command: " + finalInput);
        }
    }
}

import commandbuilder.CommandBuilder;
import commandbuilder.CommandFactory;

import commands.Command;
import input.InputHandler;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        CommandBuilder commandBuilder = new CommandBuilder();
        InputHandler inputHandler = new InputHandler(commandBuilder);
        CommandFactory commandFactory = new CommandFactory(commandBuilder);

        while (true) {
            String input = inputHandler.getCommand();
            Command command = commandFactory.getCommand(input);
            Long startTime = System.currentTimeMillis();
            command.execute();
            Long endTime = System.currentTimeMillis();
            System.out.println("Command execution time: " + (endTime - startTime) + "ms");

        }
    }
}

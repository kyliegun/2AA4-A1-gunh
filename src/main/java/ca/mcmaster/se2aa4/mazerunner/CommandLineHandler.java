package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;

public class CommandLineHandler {
    private CommandLine cmd;

    public CommandLineHandler(String[] args) {
        try {
            CommandLineParser parser = new DefaultParser();
            cmd = parser.parse(setupOptions(), args);
        } catch (ParseException e) {
            System.err.println("Error parsing command-line arguments: " + e.getMessage());
        }
    }

    private Options setupOptions() {
        Options options = new Options();
        options.addOption(Option.builder("i").hasArg().desc("Maze file path").build());
        options.addOption(Option.builder("p").hasArg().desc("Move sequence for validation").build());
        return options;
    }

    public String getMazeFile() {
        return cmd.getOptionValue("i");
    }

    public String getMoveSequence() {
        return cmd.getOptionValue("p");
    }
}
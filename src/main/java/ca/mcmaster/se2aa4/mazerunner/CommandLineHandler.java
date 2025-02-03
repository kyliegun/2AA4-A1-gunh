/**
 * Kylie Gun
 * Assignment 1 MazeRunner 2AA4 - Winter 2025
 * 400524717
 */

package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;

/*
 * This class handles parsing of command line arguments
 */
public class CommandLineHandler {
    private CommandLine cmd; //Stores parsed arguments

    //Constructor that initializes the parser and processes the argument inputted
    public CommandLineHandler(String[] args) {
        try {
            CommandLineParser parser = new DefaultParser(); //Using Apache CLI for parsing
            cmd = parser.parse(setupOptions(), args);
        } catch (ParseException e) {
            System.err.println("Error parsing command-line arguments: " + e.getMessage());
        }
    }

    //Defining the command line options for the program
    private Options setupOptions() {
        Options options = new Options();
        options.addOption(Option.builder("i").hasArg().desc("Maze file path").build());
        options.addOption(Option.builder("p").hasArg().desc("Move sequence for validation").build());
        return options;
    }

    // -i specifies the maze file path
    public String getMazeFile() {
        return cmd.getOptionValue("i");
    }

    // -p specifies a move sequence for path validation
    public String getMoveSequence() {
        return cmd.getOptionValue("p");
    }
}
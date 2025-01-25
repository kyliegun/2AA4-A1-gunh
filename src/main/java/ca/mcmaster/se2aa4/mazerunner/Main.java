package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options options = new Options(); //creating option for -i flag
        Option fileOption = new Option("i", true, "Text file that contains the maze.");
        fileOption.setRequired(true); // Set the -i flag as required
        options.addOption(fileOption);

        options.addOption("p", true, "Maze path that should be verified."); //-p flag for verifying path
        
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        String inputFilePath = null;
        String pathToVerify = null;


        try {
            cmd = parser.parse(options, args); //parse command line arguments
            inputFilePath = cmd.getOptionValue("i"); //assign maze file path
            if (cmd.hasOption("p")){
                pathToVerify = cmd.getOptionValue("p"); //assign the path to verify
            } 
        } catch (ParseException e) {
            logger.error("Error parsing command line:  " + e.getMessage());
            logger.error("Usage: java -jar mazerunner.jar -i <inputfile> [-p <path>]");
            return;
        }

        if (inputFilePath == null || !new File(inputFilePath).exists()) {
            logger.error("Invalid file path: " + inputFilePath);
            return;
        }

        try {
            logger.info("**** Reading the maze from file" + inputFilePath);
            Maze maze = new Maze(inputFilePath);
            logger.info("**** Maze loaded successfully.");
            logger.info("Entry point: (" + maze.getEntryColumn() + ", " + maze.getEntryRow() + ")");
            logger.info("Exit point: (" + maze.getEntryColumn() + ", " + maze.getExitRow() + ")");

            maze.printMaze();

            if (pathToVerify != null) {
                logger.info("**** Verifying the path: " + pathToVerify);
                boolean pathValid = maze.verifyPath(pathToVerify);
                if (pathValid) {
                    logger.info("The path is valid.");
                 } else {
                    logger.error("The path is invalid.");
                }
            }

        } catch (Exception e) {
            logger.error("/!\\ An error has occured: " + e.getMessage());
        }
           
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}

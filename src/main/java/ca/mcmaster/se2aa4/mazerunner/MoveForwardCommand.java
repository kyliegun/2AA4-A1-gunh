package ca.mcmaster.se2aa4.mazerunner;

public class MoveForwardCommand implements MoveCommand {
    private DirectionAnalyzer directionAnalyzer;

    public MoveForwardCommand(DirectionAnalyzer directionAnalyzer) {
        this.directionAnalyzer = directionAnalyzer;
    }

    @Override
    public void execute() {
        directionAnalyzer.moveForward();
    }
}
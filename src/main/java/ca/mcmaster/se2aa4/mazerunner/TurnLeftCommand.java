package ca.mcmaster.se2aa4.mazerunner;

public class TurnLeftCommand implements MoveCommand {
    private final DirectionAnalyzer directionAnalyzer;

    public TurnLeftCommand(DirectionAnalyzer directionAnalyzer) {
        this.directionAnalyzer = directionAnalyzer;
    }

    @Override
    public void execute() {
        directionAnalyzer.processMove('L');
    }
}
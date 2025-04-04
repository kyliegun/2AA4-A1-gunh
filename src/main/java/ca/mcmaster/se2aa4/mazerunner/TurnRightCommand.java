package ca.mcmaster.se2aa4.mazerunner;

public class TurnRightCommand implements MoveCommand {
    private final DirectionAnalyzer directionAnalyzer;

    public TurnRightCommand(DirectionAnalyzer directionAnalyzer) {
        this.directionAnalyzer = directionAnalyzer;
    }

    @Override
    public void execute() {
        directionAnalyzer.processMove('R');
    }
}
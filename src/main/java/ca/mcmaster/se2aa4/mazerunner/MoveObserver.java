package ca.mcmaster.se2aa4.mazerunner;

public interface MoveObserver {
    void onMove(char move, int[] position, char direction);
}


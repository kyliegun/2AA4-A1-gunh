package ca.mcmaster.se2aa4.mazerunner;

public interface Subject {
    void addObserver(MoveObserver observer);
    void removeObserver(MoveObserver observer);
    void notifyObservers(char move);
}
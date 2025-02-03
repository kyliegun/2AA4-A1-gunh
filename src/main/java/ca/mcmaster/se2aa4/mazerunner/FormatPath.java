/**
 * Kylie Gun
 * Assignment 1 MazeRunner 2AA4 - Winter 2025
 * 400524717
 */

package ca.mcmaster.se2aa4.mazerunner;

//Handles path formatting
public class FormatPath {
    private String expandedPath;
    private String compressedPath;

    //expands a compressed path
    public void setExpandedPath(String path) {
        this.expandedPath = expandPath(path); 
    }

    public String getExpandedPath() {
        return this.expandedPath; //returns expanded path
    }

    //compresses the path sequence
    public void setCompressedPath(String expandedPath) {
        this.compressedPath = compressPath(expandedPath);
    }

    public String getCompressedPath() {
        return this.compressedPath; //returns this sequence
    }

    //converts one form to the other
    private String expandPath(String path) {
        StringBuilder result = new StringBuilder();
        int repeatCount = 0;

        for (char ch : path.toCharArray()) {
            if (Character.isDigit(ch)) {
                repeatCount = repeatCount * 10 + Character.getNumericValue(ch);
            } else {
                result.append(String.valueOf(ch).repeat(Math.max(1, repeatCount)));
                repeatCount = 0;
            }
        }
        return result.toString();
    }

    //converts one form to the other
    private String compressPath(String path) {
        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) == path.charAt(i - 1)) {
                count++;
            } else {
                result.append(count > 1 ? count : "").append(path.charAt(i - 1));
                count = 1;
            }
        }
        result.append(count > 1 ? count : "").append(path.charAt(path.length() - 1));
        return result.toString();
    }
}

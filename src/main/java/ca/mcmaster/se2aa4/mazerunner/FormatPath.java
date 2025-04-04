/**
 * Kylie Gun
 * Assignment 1 MazeRunner 2AA4 - Winter 2025
 * 400524717
 */

package ca.mcmaster.se2aa4.mazerunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Handles path formatting
public class FormatPath {
    private String expandedPath;
    private String compressedPath;

    // expands a compressed path like 3F2R into FFFRR
    public void setExpandedPath(String path) {
        this.expandedPath = expandPath(path.replaceAll("\\s+", ""));

    }

    public String getExpandedPath() {
        return this.expandedPath;
    }

    // compresses a path like FFFRR into 3F2R
    public void setCompressedPath(String expandedPath) {
        this.compressedPath = compressPath(expandedPath);
    }

    public String getCompressedPath() {
        return this.compressedPath;
    }

    // Converts compressed path (e.g. 3F2R) to expanded form (e.g. FFFRR)
    private String expandPath(String path) {
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile("(\\d*)([FRL])");
        Matcher matcher = pattern.matcher(path.replaceAll("\\s+", "")); // remove spaces

        while (matcher.find()) {
            String countStr = matcher.group(1);
            char move = matcher.group(2).charAt(0);
            int count = countStr.isEmpty() ? 1 : Integer.parseInt(countStr);
            result.append(String.valueOf(move).repeat(count));
        }

        return result.toString();
    }

    // Converts expanded path (e.g. FFFRR) to compressed form (e.g. 3F2R)
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
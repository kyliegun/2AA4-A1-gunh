package ca.mcmaster.se2aa4.mazerunner;

public class FormatPath {
    private String expandedPath;
    private String compressedPath;

    public void setExpandedPath(String path) {
        this.expandedPath = expandPath(path);
    }

    public String getExpandedPath() {
        return this.expandedPath;
    }

    public void setCompressedPath(String expandedPath) {
        this.compressedPath = compressPath(expandedPath);
    }

    public String getCompressedPath() {
        return this.compressedPath;
    }

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

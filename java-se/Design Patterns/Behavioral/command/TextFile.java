package Behavioral.command;

// Receiver
public class TextFile {
    private final String fileName;

    public TextFile(String fileName) {
        this.fileName = fileName;
    }

    public String open() {
        return "Opening file " + fileName;
    }

    public String save() {
        return "Saving file " + fileName;
    }
}

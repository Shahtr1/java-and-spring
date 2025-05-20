package Behavioral.command;

// Client
public class Main {
    public static void main(String[] args) {
        TextFileOperationExecutor executor = new TextFileOperationExecutor();
        executor.executeOperation(
                new OpenTextFileOperation(new TextFile("file1.txt"))
        );
        executor.executeOperation(
                new SaveTextFileOperation(new TextFile("file2.txt"))
        );
    }
}

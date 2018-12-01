package ro.jademy.io.files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTester {

    // defining a relative file name as a constant
    private static final String RESOURCES_DIR_NAME = "resources";
    private static final String INPUT_FILE_NAME = "resources/contacts.csv";
    private static final String TEMP_FILE_NAME = "resources/temp.txt";

    public static void main(String[] args) {

        // getting a path to a file
        Path path = Paths.get(INPUT_FILE_NAME);

        System.out.println("Path exists: " + Files.exists(path));
    }
}

package ro.jademy.io.files;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class FileTester {

    // defining a relative file name as a constant
    private static final String RESOURCES_DIR_NAME = "resources";
    private static final String INPUT_FILE_NAME = "resources/contacts.csv";
    private static final String TEMP_FILE_NAME = "resources/temp.txt";

    public static void main(String[] args) {

        // creating a new file object using a relative path
        File contactsFile = new File(INPUT_FILE_NAME);

        // the java runtime constructs the full path by prefixing it with the user directory
        // we can find out the current user directory by printing the system property
        System.out.println("The current user directory is " + System.getProperty("user.dir"));

        // we can check if the file exists on disk, is a directory or a file and if it was creating using an absolute path
        System.out.println("File exists: " + contactsFile.exists());
        System.out.println("File is a directory: " + contactsFile.isDirectory());
        System.out.println("File is a file: " + contactsFile.isFile());
        System.out.println("File is a absolute: " + contactsFile.isAbsolute());

        System.out.println();

        // getting path information
        System.out.println("File name: " + contactsFile.getName());
        System.out.println("File parent name: " + contactsFile.getParent());
        System.out.println("File path: " + contactsFile.getPath());
        System.out.println("File absolute path: " + contactsFile.getAbsolutePath());
        System.out.println("File URI: " + contactsFile.toURI());
        try {
            System.out.println("File URL: " + contactsFile.toURI().toURL());
        } catch (MalformedURLException e) {
            System.out.println("Failed to get file URL for file with path " + INPUT_FILE_NAME);
        }
        try {
            System.out.println("File canonical path: " + contactsFile.getCanonicalPath());
        } catch (IOException e) {
            System.out.println("Failed to get canonical path for file with path " + INPUT_FILE_NAME);
        }

        System.out.println();

        // we can create a new file or rename it
        File tempFile = new File(TEMP_FILE_NAME);
        boolean tempFileCreated = false;
        if (!tempFile.exists()) {
            try {
                tempFileCreated = tempFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Failed to create file with name: " + TEMP_FILE_NAME);
            }
        }

        File newTempFile = null;
        boolean tempFileRenamed = false;
        if (tempFileCreated) {
            System.out.println("Temporary file was created!");
            newTempFile = new File(RESOURCES_DIR_NAME + "/newTempFileName.txt");

            if (newTempFile.exists()) {
                // if the file we want to rename it to exists, the rename will not succeed
                newTempFile.delete();
            }

            tempFileRenamed = tempFile.renameTo(newTempFile);
        } else {
            System.out.println("Temporary file was not created!");
        }

        if (tempFileRenamed) {
            System.out.println("Temporary file was renamed!");
        } else {
            System.out.println("Temporary file was not renamed!");
        }

        if (newTempFile != null) {
            // after renaming, the original temporary file does not exist on disk anymore
            System.out.println("Original temporary file exists: " + tempFile.exists());
            // we need to use the new reference
            System.out.println("The new temporary file exists: " + newTempFile.exists());

            // we can also mark a file to be deleted when the program exists
            newTempFile.deleteOnExit();
        }

        System.out.println();

        // listing files in a directory
        File resourceDir = new File(RESOURCES_DIR_NAME);
        if (resourceDir.isDirectory()) {
            String[] fileNames = resourceDir.list();

            System.out.println("Listing files in the resource directory: ");
            for (String fileName : fileNames) {
                System.out.println(fileName);
            }
        }

    }
}

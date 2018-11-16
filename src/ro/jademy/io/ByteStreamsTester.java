package ro.jademy.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ByteStreamsTester {

    public static void main(String[] args) {

        String fileName = "resources/contacts.csv";

        readContents(fileName);

        System.out.println("\n");
        readContentsFinallyBlock(fileName);

        System.out.println("\n");
        readContentsTryWithResources(fileName);

    }

    private static void readContents(String fileName) {

        // reading the file contents using a byte stream and closing the stream in the try block

        try {
            FileInputStream in = new FileInputStream(fileName);
            int character;
            while ((character = in.read()) != -1) {
                System.out.print((char) character);
            }

            // note that this method will throw the same exception as te in.read() method
            // so the message cannot be specific in this case
            // still, logging the exception itself will give enough details as to where the error occurred
            in.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to open file " + fileName + "\n" + ex);
        } catch (IOException ex) {
            // the message is not specific to one of the two methods that can throw this exception
            System.out.println("Failed to read content from file or close file " + fileName + "\n" + ex);
        }
    }

    private static void readContentsFinallyBlock(String fileName) {

        // reading the file contents using a byte stream and closing the stream in the finally block

        FileInputStream in = null;
        try {
            in = new FileInputStream(fileName);
            int character;
            while ((character = in.read()) != -1) {
                System.out.print((char) character);
            }

            in.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to open file " + fileName + "\n" + ex);
        } catch (IOException ex) {
            System.out.println("Failed to read content from file " + fileName + "\n" + ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                System.out.println("Failed to close input stream\n" + ex);
            }
        }
    }

    private static void readContentsTryWithResources(String fileName) {

        // reading the file contents using a byte stream and closing the stream automatically
        // using the try-with-resources construct

        // if the in.close() method called by the tr-with-resources statement throws an exception, that exception is
        // suppressed
        try (FileInputStream in = new FileInputStream(fileName)){
            int character;
            while ((character = in.read()) != -1) {
                System.out.print((char) character);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to open file " + fileName + "\n" + ex);
        } catch (IOException ex) {
            System.out.println("Failed to read content from file " + fileName + "\n" + ex);
        }
    }
}

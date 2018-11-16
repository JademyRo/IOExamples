package ro.jademy.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BufferedStreamsTester {

    public static void main(String[] args) {

        String inputFileName = "resources/contacts.csv";
        String outputFileName = "resources/contacts_sorted.csv";

        List<String> lines = getContentsTryWithResources(inputFileName);

        writeContentsSorted(lines, outputFileName);
    }

    private static List<String> getContentsTryWithResources(String fileName) {

        // reading the file contents using a byte stream and closing the stream automatically
        // using the try-with-resources construct

        List<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to open file " + fileName + "\n" + ex);
        } catch (IOException ex) {
            System.out.println("Failed to read content from file " + fileName + "\n" + ex);
        }

        return lines;
    }

    private static void writeContentsSorted(List<String> lines, String fileName) {

        try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) {

            // we do not want to change the collection received as parameter; it may be used by someone else
            List<String> sorted = new ArrayList<>();
            sorted.add(lines.get(0));
            sorted.addAll(lines.stream().skip(1).sorted().collect(Collectors.toList()));

            for (String s : sorted) {
                out.write(s);
                out.newLine();
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Failed to open file " + fileName + "\n" + ex);
        } catch (IOException ex) {
            System.out.println("Failed to write content to file " + fileName + "\n" + ex);
        }
    }

}

package util;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class FileManager {

    public static void saveScore(String name, int score) {
        try {
            String fileContent = getAllScores(); // Read the content of the file as a string
            fileContent += "\n"+(name+","+score)+"\n";
            System.out.println (fileContent);
            List<String> lines = Arrays.asList(fileContent.split("\n")); // Split content into lines
            lines.sort((line1, line2) -> {
                int num1 = Integer.parseInt(line1.split(",")[1].trim());
                int num2 = Integer.parseInt(line2.split(",")[1].trim());
                return num1 > num2 ? - 1 : 1;
            });
            writeFile("scores.txt", lines.subList (0,5)); // Write the sorted lines back to the file
        }
        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static String getAllScores(){
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("scores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            return "";
        }
        return content.toString().trim();
    }

    public static void writeFile(String filePath, List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
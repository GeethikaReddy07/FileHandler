import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DynamicFileHandler {

    private static final String FILE_NAME = "sample.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void writeToFile() {
        System.out.println("✍️ Enter content to write to the file:");
        String content = scanner.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(content);
            System.out.println("✅ File written successfully.");
        } catch (IOException e) {
            System.err.println("❌ Error writing to file: " + e.getMessage());
        }
    }

    public static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            System.out.println("📖 Reading file content:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(">> " + line);
            }
        } catch (IOException e) {
            System.err.println("❌ Error reading file: " + e.getMessage());
        }
    }

    public static void modifyFile() {
        System.out.print("🔍 Enter word to replace: ");
        String target = scanner.nextLine();
        System.out.print("✏️ Enter replacement word: ");
        String replacement = scanner.nextLine();

        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
            List<String> modifiedLines = new ArrayList<>();

            for (String line : lines) {
                modifiedLines.add(line.replaceAll(target, replacement));
            }

            Files.write(Paths.get(FILE_NAME), modifiedLines);
            System.out.println("✅ File modified successfully.");
        } catch (IOException e) {
            System.err.println("❌ Error modifying file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n📂 File Handling Menu:");
            System.out.println("1. Write to file");
            System.out.println("2. Read from file");
            System.out.println("3. Modify file content");
            System.out.println("4. Exit");
            System.out.print("👉 Choose an option (1-4): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    writeToFile();
                    break;
                case "2":
                    readFromFile();
                    break;
                case "3":
                    modifyFile();
                    break;
                case "4":
                    System.out.println("👋 Exiting. Goodbye!");
                    return;
                default:
                    System.out.println("⚠️ Invalid choice. Try again.");
            }
        }
    }
}

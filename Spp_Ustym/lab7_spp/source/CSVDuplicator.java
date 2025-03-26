import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class CSVDuplicator {
    public static void main(String[] args) {
        String resourcePath = "/filtered.csv"; // Path in resources
        String outputFile = "50kfiltered.csv";  // Output file in the project root
        int targetLines = 50000;

        try {
            // Load the input file from resources
            InputStream inputStream = CSVDuplicator.class.getResourceAsStream(resourcePath);
            if (inputStream == null) {
                throw new FileNotFoundException("Resource file not found: " + resourcePath);
            }

            // Read the input file line by line
            List<String> lines;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                lines = reader.lines().collect(Collectors.toList());
            }

            int originalSize = lines.size();

            // Write to output file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                for (int i = 0; i < targetLines; i++) {
                    writer.write(lines.get(i % originalSize)); // Repeat data cyclically
                    writer.newLine();
                }
            }

            System.out.println("CSV file duplicated up to 50,000 lines successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




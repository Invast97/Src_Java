import java.io.BufferedReader;
import java.io.FileReader;

// Інтерфейс для читання CSV
interface CsvReader {
    void read(String filePath, LineProcessor processor) throws Exception;
}

// Функціональний інтерфейс для обробки рядків
interface LineProcessor {
    void process(String[] columns);
}

// Реалізація CsvReader
class SimpleCsvReader implements CsvReader {
    @Override
    public void read(String filePath, LineProcessor processor) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Пропуск заголовку
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = parseCSVLine(line);
                processor.process(columns);
            }
        }
    }

    private String[] parseCSVLine(String line) {
        return line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    }
}


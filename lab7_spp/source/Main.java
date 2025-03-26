public class Main {
    public static void main(String[] args) {
        // Шукаємо файл як ресурс
        String fileName = "filtered.csv";
        String filePath = getFilePathFromResources(fileName);

        CsvReader reader = new SimpleCsvReader();
        PersonFilter filter = new FemaleAgeFilter();
        PersonCounter counter = new PersonCounter(filter);

        try {
            reader.read(filePath, counter::process);
            System.out.println("Кількість жінок віком від 15 до 25 років: " + counter.getCount());
        } catch (Exception e) {
            System.err.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }

    // Метод для отримання шляху до файлу з папки res
    private static String getFilePathFromResources(String fileName) {
        ClassLoader classLoader = Main.class.getClassLoader();
        if (classLoader.getResource(fileName) == null) {
            throw new IllegalArgumentException("Файл " + fileName + " не знайдено в ресурсах!");
        }
        return classLoader.getResource(fileName).getPath();
    }
}


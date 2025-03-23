import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Отримуємо URL до файлу в ресурсах
        URL shapesURL = Main.class.getResource("/shapes.csv");

        // Перевіряємо, чи файл знайдений
        if (shapesURL == null) {
            System.out.println("Файл не знайдений!");
            return;
        }

        // Читаємо фігури з CSV файлу
        ShapeReader reader = new ShapeReader();
        List<Shape> shapes = reader.readShapesFromCSV(shapesURL.getPath());

        // Виводимо інформацію про кожну фігуру
        for (Shape shape : shapes) {
            System.out.println("Shape: " + shape.getClass().getSimpleName());
            System.out.println("Area: " + shape.getArea());
            System.out.println("Volume: " + shape.getVolume());
            System.out.println();
        }
    }
}
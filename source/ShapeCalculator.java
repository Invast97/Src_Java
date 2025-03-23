import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

abstract class Shape {
    abstract double getArea();
    abstract double getPerimeter();
}

class Rectangle extends Shape {
    private final double width, height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    double getArea() {
        return width * height;
    }

    @Override
    double getPerimeter() {
        return 2 * (width + height);
    }
}

class Triangle extends Shape {
    private final double a, b, c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    double getArea() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    double getPerimeter() {
        return a + b + c;
    }
}

class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}

public class ShapeCalculator {
    public static void main(String[] args) {
        List<Shape> shapes = readShapesFromCSV(
                Objects.requireNonNull(ShapeCalculator.class.getResource("/shapes.csv")).getPath());
        for (Shape shape : shapes) {
            System.out.println("Фігура: " + shape.getClass().getSimpleName());
            System.out.println("Площа: " + shape.getArea());
            System.out.println("Периметр: " + shape.getPerimeter());
            System.out.println();
        }
    }

    public static List<Shape> readShapesFromCSV(String fileName) {
        List<Shape> shapes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Пропускаємо заголовок CSV
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0].trim().toLowerCase();

                try {
                    switch (type) {
                        case "rectangle" -> shapes.add(new Rectangle(
                                Double.parseDouble(parts[1]), Double.parseDouble(parts[2])));
                        case "triangle" -> shapes.add(new Triangle(
                                Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
                        case "circle" -> shapes.add(new Circle(Double.parseDouble(parts[1])));
                        default -> System.out.println("Невідомий тип фігури: " + type);
                    }
                } catch (Exception e) {
                    System.out.println("Помилка при обробці рядка: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }
        return shapes;
    }
}

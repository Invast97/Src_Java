import java.io.*;
import java.util.*;

class ShapeReader {

    public List<Shape> readShapesFromCSV(String fileName) {
        List<Shape> shapes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String type = values[0];
                String name = values[1];
                double parameter1 = Double.parseDouble(values[2]);
                double parameter2 = values.length > 3 ? Double.parseDouble(values[3]) : 0;

                Shape shape = createShape(type, parameter1, parameter2);
                shapes.add(shape);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shapes;
    }

    private Shape createShape(String type, double parameter1, double parameter2) {
        switch (type.toLowerCase()) {
            case "circle":
                return new Circle(parameter1);
            case "rectangle":
                return new Rectangle(parameter1, parameter2);
            case "cube":
                return new Cube(parameter1);
            case "sphere":
                return new Sphere(parameter1);
            case "cylinder":
                return new Cylinder(parameter1, parameter2);
            default:
                throw new IllegalArgumentException("Unknown shape type: " + type);
        }
    }
}

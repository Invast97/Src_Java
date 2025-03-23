// Інтерфейс для фігур
interface Shape {
    double getArea();  // Площа
    double getVolume();  // Об'єм (для 3D фігур)
}

// Клас для кола (2D)
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getVolume() {
        // Коло не має об'єму, тому повертаємо 0
        return 0;
    }
}

// Клас для прямокутника (2D)
class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getVolume() {
        // Прямокутник не має об'єму
        return 0;
    }
}

// Клас для куба (3D)
class Cube implements Shape {
    private double sideLength;

    public Cube(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getArea() {
        return 6 * Math.pow(sideLength, 2);
    }

    @Override
    public double getVolume() {
        return Math.pow(sideLength, 3);
    }
}

// Клас для сфери (3D)
class Sphere implements Shape {
    private double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getVolume() {
        return (4.0 / 3) * Math.PI * Math.pow(radius, 3);
    }
}

// Клас для циліндра (3D)
class Cylinder implements Shape {
    private double radius;
    private double height;

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    @Override
    public double getArea() {
        return 2 * Math.PI * radius * (radius + height);
    }

    @Override
    public double getVolume() {
        return Math.PI * Math.pow(radius, 2) * height;
    }
}

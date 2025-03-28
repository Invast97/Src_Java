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
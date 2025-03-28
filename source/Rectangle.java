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
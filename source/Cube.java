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

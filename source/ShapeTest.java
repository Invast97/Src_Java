import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShapeTest {

    @Test
    void testCircle() {
        Shape circle = new Circle(5.0);
        assertEquals(Math.PI * 25, circle.getArea(), 0.001);
        assertEquals(0, circle.getVolume(), 0.001);
    }

    @Test
    void testRectangle() {
        Shape rectangle = new Rectangle(5.0, 10.0);
        assertEquals(50.0, rectangle.getArea(), 0.001);
        assertEquals(0, rectangle.getVolume(), 0.001);
    }

    @Test
    void testCube() {
        Shape cube = new Cube(4.0);
        assertEquals(96.0, cube.getArea(), 0.001);
        assertEquals(64.0, cube.getVolume(), 0.001);
    }

    @Test
    void testSphere() {
        Shape sphere = new Sphere(7.0);
        assertEquals(4 * Math.PI * 49, sphere.getArea(), 0.001);
        assertEquals((4.0 / 3) * Math.PI * Math.pow(7.0, 3), sphere.getVolume(), 0.001);
    }

    @Test
    void testCylinder() {
        Shape cylinder = new Cylinder(3.0, 10.0);
        assertEquals(2 * Math.PI * 3 * (3 + 10), cylinder.getArea(), 0.001);
        assertEquals(Math.PI * Math.pow(3.0, 2) * 10.0, cylinder.getVolume(), 0.001);
    }
}
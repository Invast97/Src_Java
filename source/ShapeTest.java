
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ShapeTest {
    @Test
    public void testRectangle() {
        Rectangle rect = new Rectangle(4, 5);
        assertEquals(20, rect.getArea(), 0.001);
        assertEquals(18, rect.getPerimeter(), 0.001);
    }

    @Test
    public void testTriangle() {
        Triangle tri = new Triangle(3, 4, 5);
        assertEquals(6, tri.getArea(), 0.001);
        assertEquals(12, tri.getPerimeter(), 0.001);
    }

    @Test
    public void testCircle() {
        Circle circ = new Circle(7);
        assertEquals(153.938, circ.getArea(), 0.001);
        assertEquals(43.982, circ.getPerimeter(), 0.001);
    }
}

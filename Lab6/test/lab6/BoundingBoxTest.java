package lab6;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoundingBoxTest {

    @Test
    public void testToString() {

        BoundingBox bb = new BoundingBox();
        bb.addPoint(3.4, 2.6);
        bb.addPoint(6.7, 1.3);

        assertEquals("3.4 1.3 6.7 2.6", bb.toString());
    }

    @Test
    public void addPoint() {
        BoundingBox bb = new BoundingBox();
        bb.addPoint(3.4, 2.6);
        bb.addPoint(6.7, 1.3);

        assertTrue(3.4 == bb.xmin);
        assertTrue(6.7 == bb.xmax);
    }

    @Test
    public void contains() {
        BoundingBox bb = new BoundingBox();
        bb.addPoint(1.1, 1.1);
        bb.addPoint(5.5, 5.5);

        assertTrue(bb.contains(3.3, 3.3));
    }

    @Test
    public void contains1() {
        BoundingBox bb = new BoundingBox();
        bb.addPoint(1.1, 1.1);
        bb.addPoint(5.5, 5.5);

        BoundingBox bb2 = new BoundingBox();
        bb2.addPoint(2.2, 2.2);
        bb2.addPoint(4.4, 4.4);

        assertTrue(bb.contains(bb2));
    }

    @Test
    public void intersects() {
        BoundingBox bb = new BoundingBox();
        bb.addPoint(1.1, 1.1);
        bb.addPoint(5.5, 5.5);

        BoundingBox bb2 = new BoundingBox();
        bb2.addPoint(2.2, 2.2);
        bb2.addPoint(6.6, 4.4);

        assertTrue(bb.intersects(bb2));
    }

    @Test
    public void add() {
        BoundingBox bb = new BoundingBox();
        bb.addPoint(1.1, 1.1);
        bb.addPoint(5.5, 5.5);

        BoundingBox bb2 = new BoundingBox();
        bb2.addPoint(2.2, 2.2);
        bb2.addPoint(6.6, 4.4);

        bb.add(bb2);
        assertTrue(6.6 == bb.xmax);
    }

    @Test
    public void isEmpty() {
        BoundingBox bb = new BoundingBox();

        assertTrue(bb.isEmpty());
    }

    @Test(expected = IllegalThreadStateException.class)
    public void getCenterX() {
        BoundingBox bb = new BoundingBox();

        bb.getCenterX();
    }

    @Test
    public void getCenterY() {
        BoundingBox bb = new BoundingBox();

        bb.addPoint(1.1, 1.1);
        bb.addPoint(5.5, 5.5);

        assertTrue(3.3 == bb.getCenterY());
    }
}
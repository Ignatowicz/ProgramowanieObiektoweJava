package lab6;

import org.junit.Test;

import static org.junit.Assert.*;

public class AdminUnitTest {
    @Test
    public void testToString() throws Exception {
        AdminUnit au = new AdminUnit();
        au.name = "Krakow";
        au.adminLevel = 3;
        au.area = 34.2;
        au.population = 55.3;
        au.density = 1.4;

        AdminUnit parent = new AdminUnit();
        parent.name = "malopolskie";
        au.parent = parent;

        BoundingBox bb = new BoundingBox();
        bb.addPoint(3.4, 2.6);
        bb.addPoint(6.7, 1.3);
        au.bbox = bb;


        assertEquals("Krakow 3 34.2 55.3 1.4 malopolskie 3.4 1.3 6.7 2.6", au.toString());
    }

    @Test
    public void getName() {
        AdminUnit au = new AdminUnit();
        au.name = "Krakow";
        assertEquals("Krakow", au.getName());
    }

    @Test
    public void getPopulation() {
        AdminUnit au = new AdminUnit();
        au.population = 34.2;
        assertTrue(34.2 == au.getPopulation());
    }

    @Test
    public void getArea() {
        AdminUnit au = new AdminUnit();
        au.area = 55.3;
        assertTrue(55.3 == au.getArea());
    }

    @Test
    public void getParent() {
        AdminUnit au = new AdminUnit();

        AdminUnit parent = new AdminUnit();
        parent.name = "malopolskie";
        au.parent = parent;

        assertEquals("malopolskie", au.getParent().name);
    }
}
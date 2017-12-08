package lab6;

import org.junit.Test;

import static org.junit.Assert.*;

public class AdminUnitTest {
    @Test
    public void testToString() throws Exception {
        AdminUnit au = new AdminUnit();
        au.name = "Krakow";
        au.adminLevel = 3;
        au.population = 55;
        au.area = 34;
        au.density = 1.4;
        AdminUnit parent = new AdminUnit();
        parent.name = "malopolskie";
        au.parent = parent;
        BoundingBox bb = new BoundingBox();
        bb.addPoint(3.4,2.6);
        bb.addPoint(6.7,1.3);
        au.bbox = bb;

        assertEquals("Krakow 3 55 34 1.4 malopolskie 3.4 1.3 2.6 6.7", au.toString());
    }

}
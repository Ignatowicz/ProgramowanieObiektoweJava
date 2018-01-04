package lab6;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Comparator;

import static org.junit.Assert.*;

public class AdminUnitListTest {

    @Test
    public void read() throws IOException {
        AdminUnitList aul = new AdminUnitList();

        aul.read("admin-units.csv");


        aul.fixMissingValues(aul.units.get(0));

        //System.out.println(aul.units.get(0).population);
        assertTrue(-1.0 != aul.units.get(0).population);
    }


    @Test
    public void list() throws IOException {
        AdminUnitList aul = new AdminUnitList();

        aul.read("admin-units.csv");

        System.out.println("Test of not null answer for list(PrintStream out,int offset, int limit ) method\n");

        aul.list(System.out, 2, 2);

        System.out.println("End of this test.\n");
    }


    @Test
    public void selectByName() throws IOException {
        AdminUnitList aul = new AdminUnitList();

        aul.read("admin-units.csv");

        AdminUnitList ret = new AdminUnitList();

        System.out.println("Test of not null answer for selectByName(String pattern, boolean regex) method\n");

        ret = aul.selectByName("olon", true);

        System.out.println(ret.units.get(0).name);

        System.out.println("End of this test.\n");
    }

    @Test
    public void getNeighbours() throws IOException {

        AdminUnitList aul = new AdminUnitList();

        aul.read("admin-units.csv");

        AdminUnitList neigh = new AdminUnitList();

        System.out.println("Test of not null answer for getNeighbours() method\n");

        neigh = aul.getNeighbours(aul.units.get(4), 15.0);

        System.out.println(neigh.units.get(0).name);

        System.out.println("End of this test.\n");
    }

    @Test
    public void sortInPlaceByName() throws IOException {

        AdminUnitList aul = new AdminUnitList();

        aul.read("admin-units.csv");

        aul.sortInPlaceByName();

        System.out.println("Test of sortInPlaceByName() method\n");

        System.out.println(aul.units.get(0));
        System.out.println(aul.units.get(1));
        System.out.println(aul.units.get(2));
        System.out.println(aul.units.get(3));
        System.out.println(aul.units.get(4));
        System.out.println(aul.units.get(5));
        System.out.println(aul.units.get(6));
        System.out.println(aul.units.get(7));
        System.out.println(aul.units.get(8));

        System.out.println("End of this test.\n");

    }

    @Test
    public void sortInPlaceByArea() throws IOException {
        AdminUnitList aul = new AdminUnitList();

        aul.read("admin-units.csv");

        aul.sortInPlaceByArea();

        System.out.println("Test of sortInPlaceByArea() method\n");

        System.out.println(aul.units.get(1000).area);
        System.out.println(aul.units.get(1001).area);
        System.out.println(aul.units.get(1002).area);
        System.out.println(aul.units.get(1003).area);
        System.out.println(aul.units.get(1004).area);
        System.out.println(aul.units.get(1005).area);
        System.out.println(aul.units.get(1006).area);
        System.out.println(aul.units.get(1007).area);
        System.out.println(aul.units.get(1008).area);

        System.out.println("End of this test.\n");
    }

    @Test
    public void sortInplaceByPopulation() throws IOException {
        AdminUnitList aul = new AdminUnitList();

        aul.read("admin-units.csv");

        aul.sortInplaceByPopulation();

        System.out.println("Test of sortInPlaceByPupolation() method\n");

        System.out.println(aul.units.get(13500).population);
        System.out.println(aul.units.get(13501).population);
        System.out.println(aul.units.get(13502).population);
        System.out.println(aul.units.get(13503).population);
        System.out.println(aul.units.get(13504).population);
        System.out.println(aul.units.get(13505).population);
        System.out.println(aul.units.get(13506).population);
        System.out.println(aul.units.get(13507).population);
        System.out.println(aul.units.get(13508).population);

        System.out.println("End of this test.\n");
    }


    @Test
    public void filter() throws IOException {

        AdminUnitList aul = new AdminUnitList();

        aul.read("admin-units.csv");

        aul = aul.filter(a -> a.name.startsWith("Ż")).sortInPlaceByArea();

        System.out.println("Test of filter() method\n");
        System.out.println(aul.units.get(0).name);
        System.out.println("End of this test.\n");
    }

    @Test
    public void filterByK() throws IOException {

        AdminUnitList aul = new AdminUnitList();

        aul.read("admin-units.csv");

        aul = aul.filterByK();

        System.out.println("Test of filterByK method\n");
        System.out.println(aul.units.get(0).name);
        System.out.println("End of this test.\n");
    }


    @Test
    public void filterByParentMalopolskie() throws IOException {

//        AdminUnitList aul = new AdminUnitList();
//
//        aul.read("admin-units.csv");
//
//        aul = aul.filterByParentMalopolskie();
//
//        System.out.println("Test of filterByParentMalopolskie method\n");
//        System.out.println(aul.units.get(0).name);
//        System.out.println("End of this test.\n");
    }
}
package lab6;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

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

        aul.list(System.out, 2,2);

        System.out.println("End of this test.\n");
    }


    @Test
    public void selectByName() throws IOException {
        AdminUnitList aul = new AdminUnitList();

        aul.read("admin-units.csv");

        AdminUnitList ret = new AdminUnitList();

        System.out.println("Test of not null answer for selectByName(String pattern, boolean regex) method\n");

        ret = aul.selectByName("olon",true);

        System.out.println(ret.units.get(0).name);

        System.out.println("End of this test.\n");
    }

    @Test
    public void getNeighbours() throws IOException {

        AdminUnitList aul = new AdminUnitList();

        aul.read("admin-units.csv");

        AdminUnitList neigh = new AdminUnitList();

        System.out.println("Test of not null answer for getNeighbours() method\n");

        neigh = aul.getNeighbours(aul.units.get(4),15.0);

        System.out.println(neigh.units.get(0).name);

        System.out.println("End of this test.\n");
    }

    @Test
    public void sortInPlaceByName() {
    }

    @Test
    public void sortInPlaceByArea() {
    }

    @Test
    public void sortInplaceByPopulation() {
    }

    @Test
    public void sortInplace() {
    }

    @Test
    public void sort() {
    }

    @Test
    public void filter() {
    }

    @Test
    public void filterByK() {
    }

    @Test
    public void filter1() {
    }

    @Test
    public void filter2() {
    }
}
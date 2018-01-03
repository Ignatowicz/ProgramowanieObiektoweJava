package lab6;

import java.io.IOException;
import java.util.Locale;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) throws IOException {

//        CSVReader reader = new CSVReader("admin-units.csv", ",", true);
//
//        while (reader.next()) {
//            int id = reader.getInt("id");
//            int parent = reader.getInt("parent");
//            String name = reader.get("name");
//            double x1 = reader.getDouble("x1");
//            double population = reader.getDouble("density");
//
//
//            System.out.printf(Locale.US, "%d %d %s %f %f", id, parent, name, x1, population);
//            System.out.println();
//        }

        AdminUnitList aul = new AdminUnitList();

        aul.read("admin-units.csv");
    }
}
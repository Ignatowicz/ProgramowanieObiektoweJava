package lab6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdminUnitList {

    private List<AdminUnit> units = new ArrayList<>();


    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader("admin-units.csv", ",", true);
        while (reader.next()) {

        }
    }

    public void fixMissingValues() {

    }

    public void selectAdminLevel(int level){

    }

    public void selectNameMatches(String name) {

    }

    public void selectInside(double x1, double y1, double x2, double y2) {

    }
}

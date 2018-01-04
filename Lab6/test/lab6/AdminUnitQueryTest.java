package lab6;

import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class AdminUnitQueryTest {

    @Test
    public void execute() throws IOException {
        AdminUnitList list = new AdminUnitList();

        list.read("admin-units.csv");

        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(list)
                .where(a->a.area>1000)
                .or(a->a.name.startsWith("Sz"))
                .sort((a,b)->Double.compare(a.area,b.area))
                .limit(100);

        query.execute().list(System.out);

    }
}
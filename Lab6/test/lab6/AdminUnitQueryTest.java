package lab6;

import org.junit.Test;

import java.io.PrintStream;

import static org.junit.Assert.*;

public class AdminUnitQueryTest {

    @Test
    public void execute() {
        AdminUnitList list = new AdminUnitList();

        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(list)
                .where(a->a.area>1000)
                .or(a->a.name.startsWith("Sz"))
                .sort((a,b)->Double.compare(a.area,b.area))
                .limit(100);

        query.execute().list(System.out);
    }
}
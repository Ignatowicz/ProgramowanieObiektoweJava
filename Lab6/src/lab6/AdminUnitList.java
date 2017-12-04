package lab6;

import com.sun.org.glassfish.external.amx.AMX;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class AdminUnitList {

    private List<AdminUnit> units = new ArrayList<>();


    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader("admin-units.csv", ",", true);

        Map<Long, AdminUnit> id = new HashMap<>();
        Map<AdminUnit, Long> parentid = new HashMap<>();

        Map<Long, List<AdminUnit>> parentid2child = new HashMap<>();


        while (reader.next()) {
            AdminUnit unit = new AdminUnit();
            unit.name = reader.get("name");
            unit.adminLevel = reader.getInt("adminLevel");
            unit.population = reader.getDouble("population");
            unit.area = reader.getDouble("area");
            unit.density = reader.getDouble("density");

            id.put(reader.getLong("id"), unit);
            parentid.put(unit, reader.getLong("parent"));

            //tymczasowe przypisanie, zeby mozna sie bylo odwolac do tego elementu
            unit.parent = unit;

            units.add(unit);
        }

        //  iteruje po mapie id, jednak dzieki posiadanym tym samym jednostkowm AdminUnit
        //  moze korzystac tez z mapy parentid
        for (Map.Entry<Long, AdminUnit> entry : id.entrySet()) {

            // ustawia referencje w obiektach units
            if (parentid.get(entry.getValue()) == -1)
                units.get(units.indexOf(entry.getValue())).parent = null;
            units.get(units.indexOf(entry.getValue())).parent = id.get(parentid.get(entry.getValue()));

            //  child
            if (parentid2child.get(entry.getKey()) == null) {
                List<AdminUnit> tmp = new ArrayList<AdminUnit>();
                tmp.add(units.get(units.indexOf(entry.getValue())));
                parentid2child.put(entry.getKey(), tmp);
            }
            parentid2child.get(entry.getKey()).add(units.get(units.indexOf(entry.getValue())));
        }
    }

    private void fixMissingValues(AdminUnit au) {
        if (au.population == -1) {
            if (au.parent.population == -1)
                fixMissingValues(au.parent);
            au.density = au.parent.density;
            au.population = au.area * au.density;
        }
    }

    public void selectAdminLevel(int level) {

    }

    public void selectNameMatches(String name) {

    }

    public void selectInside(double x1, double y1, double x2, double y2) {

    }


    /**
     * Wypisuje zawartość korzystając z AdminUnit.toString()
     *
     * @param out
     */
    void list(PrintStream out) {
        for (AdminUnit unit : units) {
            out.println(unit.toString());
        }
    }

    /**
     * Wypisuje co najwyżej limit elementów począwszy od elementu o indeksie offset
     *
     * @param out    - strumień wyjsciowy
     * @param offset - od którego elementu rozpocząć wypisywanie
     * @param limit  - ile (maksymalnie) elementów wypisać
     */
    void list(PrintStream out, int offset, int limit) {
        for (int i = offset; i < offset + limit; i++) {
            out.println(units.get(i).toString());
        }
    }

    /**
     * Zwraca nową listę zawierającą te obiekty AdminUnit, których nazwa pasuje do wzorca
     *
     * @param pattern - wzorzec dla nazwy
     * @param regex   - jeśli regex=true, użyj finkcji String matches(); jeśli false użyj funkcji contains()
     * @return podzbiór elementów, których nazwy spełniają kryterium wyboru
     */
    AdminUnitList selectByName(String pattern, boolean regex) {
        AdminUnitList ret = new AdminUnitList();
        // przeiteruj po zawartości units
        // jeżeli nazwa jednostki pasuje do wzorca dodaj do ret
        for (AdminUnit unit : units) {
            if (unit.toString().contains(pattern))
                ret.units.add(unit);
        }
        return ret;
    }
}
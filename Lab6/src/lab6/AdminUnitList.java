package lab6;

import com.sun.org.glassfish.external.amx.AMX;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class AdminUnitList {

    public List<AdminUnit> units = new ArrayList<>();


    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename, ",", true);

        Map<Long, AdminUnit> id = new HashMap<>();
        Map<AdminUnit, Long> parentid = new HashMap<>();

        Map<Long, List<AdminUnit>> parentid2child = new HashMap<>();

        int i=0;
        while (reader.next()) {
            i++;
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

            // bbox case
            unit.bbox.addPoint(reader.getDouble("x1"), reader.getDouble("y1"));
            unit.bbox.addPoint(reader.getDouble("x2"), reader.getDouble("y2"));
            unit.bbox.addPoint(reader.getDouble("x3"), reader.getDouble("y3"));
            unit.bbox.addPoint(reader.getDouble("x4"), reader.getDouble("y4"));
            unit.bbox.addPoint(reader.getDouble("x5"), reader.getDouble("y5"));


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
            units.get(units.indexOf(entry.getValue())).children = parentid2child.get(entry.getKey());
        }
    }

    public void fixMissingValues(AdminUnit au) {
        if (au.population == -1.0) {
            if (au.parent.population == -1.0)
                fixMissingValues(au.parent);
            au.density = au.parent.density;
            au.population = au.area * au.density;
        }
    }


    /**
     * Wypisuje zawartość korzystając z AdminUnit.toString()
     *
     * @param out
     */
    public void list(PrintStream out) {
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
    public void list(PrintStream out, int offset, int limit) {
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
    public AdminUnitList selectByName(String pattern, boolean regex) {
        AdminUnitList ret = new AdminUnitList();
        // przeiteruj po zawartości units
        // jeżeli nazwa jednostki pasuje do wzorca dodaj do ret
        for (AdminUnit unit : units) {
            if (unit.name.contains(pattern))
                ret.units.add(unit);
        }
        return ret;
    }

    /**
     * Zwraca listę jednostek sąsiadujących z jendostką unit na tym samym poziomie hierarchii admin_level.
     * Czyli sąsiadami wojweództw są województwa, powiatów - powiaty, gmin - gminy, miejscowości - inne miejscowości
     *
     * @param unit        - jednostka, której sąsiedzi mają być wyznaczeni
     * @param maxdistance - parametr stosowany wyłącznie dla miejscowości, maksymalny promień odległości od środka unit,
     *                    w którym mają sie znaleźć punkty środkowe BoundingBox sąsiadów
     * @return lista wypełniona sąsiadami
     */
    public AdminUnitList getNeighbours(AdminUnit unit, double maxdistance) {

        double t1 = System.nanoTime() / 1e6;

        if (unit.bbox.isEmpty()) throw new RuntimeException("Bbox of given unit is empty");

        AdminUnitList neighbours = new AdminUnitList();

//        for (AdminUnit u : units) {
//            if (u.adminLevel == unit.adminLevel) {
//                if (unit.bbox.intersects(u.bbox)) {
//                    neighbours.units.add(u);
//                }
//            }
//        }

        for(AdminUnit neighbour : units){
            if(neighbour.bbox.isEmpty()) throw new IllegalThreadStateException("You can't solve the distance between points in an empty Bounding Box");
            else{
                if(unit.adminLevel == 8){
                    if(neighbour.adminLevel == 8 && unit.bbox.distanceTo(neighbour.bbox) < maxdistance){
                        neighbours.add(neighbour);
                    }
                }
                else{
                    if(unit.adminLevel == neighbour.adminLevel && unit.bbox.intersects(neighbour.bbox)){
                        neighbours.add(neighbour);
                    }
                }

            }
        }

        double t2 = System.nanoTime() / 1e6;

        System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);

        return neighbours;
    }

    public void add(AdminUnit unit){
        units.add(unit);
    }






    /* Sortowanie - Lab 9 */



    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     *
     * @return this
     */
    AdminUnitList sortInPlaceByName() {
        units.sort(new AdminUnitComparator());
        return this;
    }

    public class AdminUnitComparator implements Comparator<AdminUnit> {
        @Override
        public int compare(AdminUnit adminUnit, AdminUnit t1) {
            return adminUnit.getName().compareTo(t1.getName());
        }
    }

    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     *
     * @return this
     */
    AdminUnitList sortInPlaceByArea() {
        units.sort(new Comparator<AdminUnit>() {
            @Override
            public int compare(AdminUnit adminUnit, AdminUnit t1) {
                return Double.compare(adminUnit.getArea(), t1.getArea());
            }
        });
        return this;
    }


    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     *
     * @return this
     */
    AdminUnitList sortInplaceByPopulation() {
        units.sort(Comparator.comparingDouble(AdminUnit::getPopulation));
        return this;
    }


    AdminUnitList sortInplace(Comparator<AdminUnit> cmp) {
        units.sort(cmp);
        return this;
    }


    AdminUnitList sort(Comparator<AdminUnit> cmp) {
        // Tworzy wyjściową listę
        // Kopiuje wszystkie jednostki
        // woła sortInPlace
        AdminUnitList answer = new AdminUnitList();
        answer.units = new ArrayList<>(this.units);
        answer.sortInplace(cmp);
        return answer;
    }


    /* Predykaty */

    /**
     *
     * @param pred referencja do interfejsu Predicate
     * @return nową listę, na której pozostawiono tylko te jednostki,
     * dla których metoda test() zwraca true
     */
    AdminUnitList filter(Predicate<AdminUnit> pred) {
        AdminUnitList answer = new AdminUnitList();
        for (AdminUnit au : units) {
            if (pred.test(au))
                answer.units.add(au);
        }
        return answer;
    }

    AdminUnitList filterByK() {
        return filter(a->a.getName().startsWith("K"));
    }

    AdminUnitList filterByParentMalopolskie() {
        return filter(a->a.getParent().getName().contains("małopolskie")
                || a.getName().startsWith("powiat"));
    }

    AdminUnitList filterByA() {
        return filter(a->a.getName().startsWith("A"));
    }

    AdminUnitList filterByAPopulation() {
        return filter(a->a.getName().startsWith("A")).sortInplaceByPopulation();
    }

    AdminUnitList filterByParentSlaskieArea() {
        return filter(a->a.getParent().getName().startsWith("śląskie")).sortInPlaceByArea();
    }




    /**
     * Zwraca co najwyżej limit elementów spełniających pred
     * @param pred - predykat
     * @param limit - maksymalna liczba elementów
     * @return nową listę
     */
    AdminUnitList filter(Predicate<AdminUnit> pred, int limit){
        AdminUnitList answer = new AdminUnitList();
        int i = 0;
        for (AdminUnit au : units) {
            if (pred.test(au))
                answer.units.add(au);
            i++;
            if( i > limit )
                break;
        }System.out.println("Test of filter() method\n");
        return answer;
    }


    /**
     * Zwraca co najwyżej limit elementów spełniających pred począwszy od offset
     * Offest jest obliczany po przefiltrowaniu
     * @param pred - predykat
     * @param - od którego elementu
     * @param limit - maksymalna liczba elementów
     * @return nową listę
     */
    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit){
        AdminUnitList answer = new AdminUnitList();
        int i = offset;
        for (AdminUnit au : units) {
            if (pred.test(au))
                answer.units.add(au);
            i++;
            if(i>limit+offset)
                break;
        }
        return answer;
    }










    public PrintStream writeHTML() throws FileNotFoundException, UnsupportedEncodingException {

        PrintStream index = new PrintStream("index.html","ISO-8859-2");
        index.printf("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>" + "INDEX" + "</title>\n" +
                "<head>\n" +
                "<meta charset=\"ISO-8859-2\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>" + "LISTA JEDNOSTEK" + "</h1>\n");

        for(AdminUnit unit : this.units){

            index.printf("<p><a href=\"" + (unit.name + ".html") + "\">" + unit.name + "</a></p>");

            PrintStream out = new PrintStream( (unit.name + ".html"),"ISO-8859-2");

            // tworzy plik
            out.printf("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<title>" + unit.getName() + "</title>\n" +
                    "<meta charset=\"ISO-8859-2\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>" + unit.getName() + "</h1>\n" +
                    "<p>Obszar jednostki: " + unit.getArea() +
                    "\nGęstość zaludnienia: " + unit.getDensity() +
                    "\nLiczba mieszkańców: " + unit.getPopulation() + "</p>");

            // wypisuje rodzica
            out.printf("\n<p>Link do jednostki nadrzędnej:" +
                    "\n<a href=\"" + unit.parent.name + ".html" + "\">" + unit.parent.name + "</a></p>");

            // wypisuje dzieci
            out.printf("<p>Linki do jednostek podrzędnych:");
            for(AdminUnit au: unit.children) {
                out.printf("\n<a href=\"" + au.name + ".html" + "\">" + au.name + "</a>");
            }
            out.printf("</p>\n");



            // wypisywanie sasiadow
            AdminUnitList neighbours = new AdminUnitList();
            neighbours.getNeighbours(unit, 7);

            for(AdminUnit neighbour : neighbours.units){
                out.printf("Sąsiad : <a href=\"" + neighbour.name +
                        ".html\"> sąsiad </a><br/>\n");
            }

            out.printf("\n</body>\n</html>");
            out.close();
        }

        index.printf("\n</body>\n</html>");

        return index;
    }
}
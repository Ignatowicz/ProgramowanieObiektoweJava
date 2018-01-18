package zadanie1;

import org.w3c.dom.ls.LSException;

import java.io.IOException;
import java.util.*;

public class Main {

    public static List<Name> names = new ArrayList<>();

    public static int sumOfBitrhs = 0;
    public static int totalSumOfBitrhs = 0;

    public static Map<Name, Integer> nameAndNumber = new HashMap<>();

    public static Name tmpName;


    public static void main(String[] args) throws IOException {

        CSVReader reader = new CSVReader("imiona-2000-2016.csv", ";", true);


        while (reader.next()) {
            int year = reader.getInt(0);
            String name = reader.get("Imię");
            int liczba = reader.getInt("Liczba");
            String genre = reader.get("Płeć");


            // a) wczytuje i dodaje dane do listy obiektow Imie
            names.add(new Name(year, name, liczba, genre));


//            System.out.printf(Locale.US, "rok = %d imie = %s liczba = %d płeć = %s ",
//                    year, name, liczba, genre);
//            System.out.println();
        }



        // b) Dodaje ilosc imion i podaje najpopularniejsze imie meskie i dmaksie na przestrzeni 17 lat
        int j=0;
        for (int i =2000; i<2017; i++) {
            for(Name name: names) {
                nameAndNumber.putIfAbsent(name, name.liczba);

                j = name.liczba + nameAndNumber.get(name).intValue();

                nameAndNumber.remove(name);

                // podmienia ilosc dla imienia
                nameAndNumber.put(name, j);
            }
        }

        Integer maxk = 0;
        Integer maxm = 0;

        for(Map.Entry<Name, Integer> entry : nameAndNumber.entrySet()) {
            Name name = entry.getKey();
            Integer number = entry.getValue();

            if (maxk < number && name.genre == "K") {
                maxk = number;
            }

            if (maxm < number && name.genre == "M") {
                maxm = number;
            }
        }
        System.out.println("Najpopularniejsze ime zenskie to: " + nameAndNumber.get(maxk) +
        "\nNajpopularniejsze imie meskie to: " + nameAndNumber.get(maxm));


        // c) Podaje ilosc urodzen w latach
        for(int i=2000; i<2017; i++) {
            for (Name name : names) {
                if (name.year == i)
                    sumOfBitrhs += name.liczba;
            }
            System.out.println("W roku " + i + " urodziło się " + sumOfBitrhs + " dzieci.");
            totalSumOfBitrhs += sumOfBitrhs;
            sumOfBitrhs = 0;
        }
        System.out.println("W latach 2000-2016 urodziło się " + totalSumOfBitrhs + " dzieci.");

    }
}
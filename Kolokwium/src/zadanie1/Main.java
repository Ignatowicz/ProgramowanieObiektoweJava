package zadanie1;

import java.io.IOException;
import java.util.*;

public class Main {

    public static List<Name> names = new ArrayList<>();

    public static int sumOfBitrhs = 0;
    public static int totalSumOfBitrhs = 0;

    public static Map<Name, Integer> nameAndNumber = new HashMap<>();
    public static Map<Name, Integer> nameAndPercentage = new HashMap<>();
    public static Map<Name, Integer> nameAndDifference = new HashMap<>();


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
        int j = 0;
        for (int i = 2000; i < 2017; i++) {
            for (Name name : names) {
                nameAndNumber.putIfAbsent(name, name.liczba);

                j = name.liczba + nameAndNumber.get(name);

                nameAndNumber.remove(name);

                // podmienia ilosc dla imienia
                nameAndNumber.put(name, j);
            }
        }

        Integer maxk = 0;
        Integer maxm = 0;

        for (Map.Entry<Name, Integer> entry : nameAndNumber.entrySet()) {
            Name name = entry.getKey();
            Integer number = entry.getValue();

            if (maxk < number && name.genre.equals("K")) {
                maxk = number;
            }

            if (maxm < number && name.genre.equals("M")) {
                maxm = number;
            }
        }


        for (Map.Entry<Name, Integer> entry : nameAndNumber.entrySet()) {
            Name name = entry.getKey();
            Integer number = entry.getValue();

            if (number.equals(maxk)) {
                System.out.println("Najpopularniejsze imię żeńskie to: " + name.name + "\n");
            }

            if (number.equals(maxm)) {
                System.out.println("Najpopularniejsze imię męskie to: " + name.name + "\n");
            }
        }


        // c) Podaje ilosc urodzen w latach
        for (int i = 2000; i < 2017; i++) {
            for (Name name : names) {
                if (name.year == i)
                    sumOfBitrhs += name.liczba;
            }
            System.out.println("W roku " + i + " urodziło się " + sumOfBitrhs + " dzieci.");
            totalSumOfBitrhs += sumOfBitrhs;
            sumOfBitrhs = 0;
        }
        System.out.println("W latach 2000-2016 urodziło się " + totalSumOfBitrhs + " dzieci.");


        // d) Podaje najwieksza procentowa zmiane popularnosci
        // Przypisuje do imienia procentowe wystapienie jego w danym roku
        sumOfBitrhs = 0;
        for (int i = 2000; i < 2017; i++) {

            for (Name name : names) {
                if (name.year == i)
                    sumOfBitrhs += name.liczba;
            }

            for (Name name : names) {
                nameAndPercentage.put(name, name.liczba / sumOfBitrhs);
            }

            sumOfBitrhs = 0;
        }


        Integer maxPercentageK = 0;
        Integer maxPercentageM = 0;

        // szuka imienia, pozniej odpowiednimi warunkami sprawdza roznice procentowa i przypisuje
        // do nowej hashMapy imie z maksymalnym procentowym wynikiem
        for (Map.Entry<Name, Integer> entry : nameAndPercentage.entrySet()) {
            Name name = entry.getKey();
            Integer number = entry.getValue();

            for (Map.Entry<Name, Integer> entrys : nameAndPercentage.entrySet()) {
                Name names = entrys.getKey();
                Integer numbers = entrys.getValue();

                if (name.year != names.year) {
                    if (name.name.equals(names.name) && name.genre.equals("K") && names.genre.equals("K")) {

                        maxPercentageK = Math.abs(number - numbers);

                        nameAndDifference.putIfAbsent(names, maxPercentageK);

                        for (Map.Entry<Name, Integer> entryss : nameAndDifference.entrySet()) {
                            Name namess = entryss.getKey();
                            Integer numberss = entryss.getValue();

                            if (name.name.equals(namess.name) && namess.genre.equals("K")) {
                                if(maxPercentageK > numberss) {
                                    System.out.println("tutaj nie wchodzi nie wiem czemu," +
                                            "warunek maxPer... nie pozwala tu wejsc");
                                    nameAndDifference.remove(namess);
                                    nameAndDifference.put(namess, maxPercentageK);
                                }
                            }
                        }
                    }

                    if (name.name.equals(names.name) && name.genre.equals("M") && names.genre.equals("M")) {

                        maxPercentageM = Math.abs(number - numbers);

                        nameAndDifference.putIfAbsent(name, maxPercentageM);

                        for (Map.Entry<Name, Integer> entryss : nameAndDifference.entrySet()) {
                            Name namess = entryss.getKey();
                            Integer numberss = entryss.getValue();

                            if (name.name.equals(namess.name) && namess.genre.equals("M")) {
                                if (maxPercentageM > numberss) {
                                    nameAndDifference.remove(namess);
                                    nameAndDifference.put(namess, maxPercentageM);
                                }
                            }
                        }
                    }

                    maxPercentageK = 0;
                    maxPercentageM = 0;
                }
            }
        }

        maxk = 0;
        maxm = 0;

        for (Map.Entry<Name, Integer> entry : nameAndDifference.entrySet()) {
            Name name = entry.getKey();
            Integer number = entry.getValue();

            if (maxk < number && name.genre.equals("K")) {
                maxk = number;
            }

            if (maxm < number && name.genre.equals("M")) {
                maxm = number;
            }
        }

        for (Map.Entry<Name, Integer> entry : nameAndDifference.entrySet()) {
            Name name = entry.getKey();
            Integer number = entry.getValue();

            if (number.equals(maxk)) {
                System.out.println("Największa procentowa zmiana nastąpiła dla imienia żeńskiego: "
                        + name.name + "\n");
            }

            if (number.equals(maxm)) {
                System.out.println("Największa procentowa zmiana nastąpiła dla imienia męskiego: "
                        + name.name + "\n");
            }
        }
    }
}

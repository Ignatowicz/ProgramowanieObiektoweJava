package lab6;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVReader {

    public BufferedReader reader;
    public String delimiter;
    public boolean hasHeader;
    public String[] current;

    // nazwy kolumn w takiej kolejności, jak w pliku
    public List<String> columnLabels = new ArrayList<>();

    // odwzorowanie: nazwa kolumny -> numer kolumny
    public Map<String, Integer> columnLabelsToInt = new HashMap<>();


    // konstruktory
    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader)
            parseHeader();
    }

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        this(new FileReader(filename), delimiter, hasHeader);
    }

    public CSVReader(String filename, String delimiter) throws IOException {
        this(new FileReader(filename), delimiter, false);
    }

    public CSVReader(String filename) throws IOException {
        this(new FileReader(filename), new String(","), false);
    }

    //parsowanie
    private void parseHeader() throws IOException {
        // wczytaj wiersz
        String line = reader.readLine();
        if (line == null) {
            return;
        }

        // podziel na pola
        String[] header = line.split(delimiter);

        // przetwarzaj dane w wierszu
        for (int i = 0; i < header.length; i++) {
            // dodaj nazwy kolumn do columnLabels i numery do columnLabelsToInt
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i], i);
        }
    }


    boolean next() throws IOException {

        String line = reader.readLine();
        if (line == null) {
            return false;
        }


        current = line.split(delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        for (int i = 0; i < current.length; i++) {
            columnLabels.add(current[i]);
            columnLabelsToInt.put(current[i], i);
        }

        return true;
    }

    // metody z zadania
    public List<String> getColumnLabels() {
        return columnLabels;
    }

    public int getRecordLength() {
        return current.length;
    }


    public boolean isMissing(int columnIndex) {
        return current.length <= columnIndex || current[columnIndex].isEmpty();
    }

    public boolean isMissing(String columnLabel) {
        return !columnLabelsToInt.containsKey(columnLabel) || isMissing(columnLabelsToInt.get(columnLabel));
    }

    public String get(int columnIndex) {
        if (isMissing(columnIndex))
            return "";
        return current[columnIndex];
    }

    public String get(String columnLabel) {
        if (isMissing(columnLabel))
            return "";
        return current[columnLabelsToInt.get(columnLabel)];
    }

    public int getInt(int columnIndex) {
        if (isMissing(columnIndex))
            return -1;
        return Integer.parseInt(current[columnIndex]);
    }

    public int getInt(String columnLabel) {
        if (isMissing(columnLabel))
            return -1;
        return Integer.parseInt(current[columnLabelsToInt.get(columnLabel)]);
    }

    public long getLong(int columnIndex) {
        if (isMissing(columnIndex))
            return -1;
        return Long.parseLong(current[columnIndex]);
    }

    public long getLong(String columnLabel) {
        if (isMissing(columnLabel))
            return -1;
        return Long.parseLong(current[columnLabelsToInt.get(columnLabel)]);
    }

    public double getDouble(int columnIndex) {
        if (isMissing(columnIndex))
            return -1.0;
        return Double.parseDouble(get(columnIndex));
    }

    public double getDouble(String columnLabel) {
        if (isMissing(columnLabel))
            return -1.0;
        return Double.parseDouble(current[columnLabelsToInt.get(columnLabel)]);
    }

    public LocalTime getTime(int columnIndex, String format) {
        LocalTime time = LocalTime.parse(current[columnIndex], DateTimeFormatter.ofPattern(format));
        System.out.println(time);
        return time;
    }

    public LocalDate getDate(int columnIndex, String format) {
        LocalDate date = LocalDate.parse(current[columnIndex], DateTimeFormatter.ofPattern(format));
        System.out.println(date);
        return date;
    }
}
package lab6;

import org.junit.Test;

import java.io.StringReader;
import java.util.Locale;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class CSVReaderTest {

    // testy konstruktorow
    @Test
    public void TestCSVReader() throws Exception {
        CSVReader reader = new CSVReader("with-header.csv", ";", true);
        assertEquals(true, reader.hasHeader);
        assertEquals(new String(";"), reader.delimiter);
        assertEquals("nazwisko", reader.columnLabels.get(2));
    }

    @Test
    public void TestCSVReaderNoHeader() throws Exception {
        CSVReader reader = new CSVReader("with-header.csv", ";");
        assertEquals(false, reader.hasHeader);
    }

    @Test
    public void TestCSVReaderNoDelimiter() throws Exception {
        CSVReader reader = new CSVReader("with-header.csv");
        assertEquals(new String(","), reader.delimiter);
    }

    @Test
    public void TestCSVReaderOtherFile() throws Exception {
        String text = "a,b,c\n123.4,567.8,91011.12";
        CSVReader reader = new CSVReader(new StringReader(text), ",", true);
        assertEquals("b", reader.columnLabels.get(1));
    }

    // test parsowania next
    @Test
    public void next() throws Exception {
        CSVReader reader = new CSVReader("with-header.csv", ";", true);
        while (reader.next()) {
                int id = reader.getInt(0);
                String name = reader.get(3);
                double fare = reader.getDouble(5);
                System.out.printf(Locale.US, "%d %s %f", id, name, fare);
                System.out.println();
        }
    }


    //testy metod z zadania
    @Test
    public void getColumnLabels() throws Exception {
        CSVReader reader = new CSVReader("with-header.csv", ";", true);
        assertEquals("ulica", reader.columnLabels.get(3));
    }

    @Test
    public void getRecordLength() throws Exception {
        CSVReader reader = new CSVReader("with-header.csv", ";", true);
        int i = 0;
        while (reader.next()) {
            if (i == 2)
                assertEquals(6, reader.getRecordLength());
            if (i == 3) {
                assertEquals(5, reader.getRecordLength());
                break;
            }
            i++;
        }
    }

    @Test
    public void getByIndex() throws Exception {
        CSVReader reader = new CSVReader("with-header.csv", ";", true);
        int i = 0;
        while (reader.next()) {
            if  (i == 2)
                assertEquals("2", reader.get(5));
            if (i == 3) {
                assertEquals("", reader.get(5));
                assertEquals("", reader.get(7));
                break;
            }
            i++;
        }
    }

    @Test
    public void getByLabel() throws Exception {
        CSVReader reader = new CSVReader("with-header.csv", ";", true);
        int i = 0;
        while (reader.next()) {
            if  (i == 2)
                assertEquals("Zofia", reader.get("Zofia"));
            if (i == 3) {
                assertEquals("", reader.get("Maek"));
                break;
            }
            i++;
        }
    }

    @Test
    public void titanic() throws Exception {
        CSVReader reader = new CSVReader("titanic-part.csv", ",", true);
        while (reader.next()) {
            int id = reader.getInt(0);
            String name = reader.get(3);
            double fare = reader.getDouble(9);
            System.out.printf(Locale.US, "%d %s %f", id, name, fare);
            System.out.println();
        }
    }
}
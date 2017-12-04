package lab4;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        Document cv = new Document("Jan Ignatowicz");
        cv.setPhoto("...");
        cv.addSection("Wykształcenie")
                .addParagraph("2004-2010 SP3")
                .addParagraph("2010-2013 G1")
                .addParagraph("2013-2016 LO1");
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Umiejętności")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                );

        cv.writeHTML(System.out);
        cv.writeHTML(new PrintStream("cv.html","ISO-8859-2"));


        cv.write("cv.xml");
        //Document cv2 = Document.read("cv.xml");
        //cv2.writeHTML(System.out);
    }
}

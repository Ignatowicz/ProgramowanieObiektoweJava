package lab4;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlValue;
import java.io.PrintStream;

public class Paragraph {

    @XmlValue
    public String content;

    public Paragraph() {
    }

    public Paragraph(String paragraphText) {
        this.content = paragraphText;
    }

    public Paragraph setContent(String txt) {
        this.content = txt;
        return this;
    }

    public void writeHTML(PrintStream out) {
        out.printf("<p>" + content + "</p>");
    }
}

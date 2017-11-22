package lab4;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import java.io.PrintStream;

public class ListItem {

    @XmlValue
    private String content;

    public ListItem(String txt) {
        this.content = txt;
    }

    public void writeHTML(PrintStream out) {
        out.printf("<li>");
        out.printf(content);
        out.printf("</li>\n");
    }
}
package lab4;

import com.sun.xml.internal.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.PrintStream;

public class Photo {

    @XmlAttribute
    private String url;

    public Photo(String url) {
        this.url = url;
    }

    public void writeHTML(PrintStream out) {
        out.printf("<img src=\"%s\" alt=\"Smiley face\" height=\"42\" width=\"42\"/>\n", url);
    }
}
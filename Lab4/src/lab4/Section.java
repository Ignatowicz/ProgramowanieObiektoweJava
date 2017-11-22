package lab4;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {

    @XmlAttribute
    private String title;

    @XmlElements(value= {
            @XmlElement(name = "paragraph", type = Paragraph.class),
            @XmlElement(name = "paragraph-with-list", type = ParagraphWithList.class)
    })
    private List<Paragraph> paragraphs = new ArrayList<>();

    public Section(String title) {
        this.title = title;
    }

    public Section setTitle(String title) {
        this.title = title;
        return this;
    }

    public Section addParagraph(String paragraphText) {
        this.paragraphs.add(new Paragraph(paragraphText));
        return this;
    }

    public Paragraph addParagraph(Paragraph paragraph) {
        this.paragraphs.add(paragraph);
        return paragraph;
    }

    public void writeHTML(PrintStream out) {
        out.printf("<div>\n");
        out.printf("<h1>%s</h1>\n", this.title);
        for (Paragraph paragraph : paragraphs) {
            paragraph.writeHTML(out);
        }
        out.printf("</div>\n");
    }
}
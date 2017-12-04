package lab4;

import javax.xml.bind.annotation.XmlElement;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ParagraphWithList extends Paragraph {

    @XmlElement(name="unorderedList")
    private UnorderedList unorderedList = new UnorderedList();

    public ParagraphWithList() {
    }

    @Override
    public ParagraphWithList setContent(String content) {
        this.content = content;
        return this;
    }

    public ParagraphWithList addListItem(String txt) {
        unorderedList.addItem(new ListItem(txt));
        return this;
    }


    public void writeHTML(PrintStream out) {
        out.printf("<p>\n");
        unorderedList.writeHTML(out);
        out.printf("</p>\n");
    }
}

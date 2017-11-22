package lab4;

import javax.xml.bind.annotation.XmlElement;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {

    @XmlElement(name="listItem")
    private List<ListItem> listItems = new ArrayList<>();

    public UnorderedList() {
    }


    public UnorderedList addItem(ListItem lt) {
        this.listItems.add(lt);
        return this;
    }


    public void writeHTML(PrintStream out) {
        out.printf("<ul>");
        for (ListItem listItem : listItems) {
            listItem.writeHTML(out);
        }
        out.printf("</ul>\n");
    }
}

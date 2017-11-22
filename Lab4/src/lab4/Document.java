package lab4;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Document {

    @XmlAttribute
    private String title;

    @XmlElement(name="photo")
    private Photo photo;

    @XmlElement(name="section")
    private List<Section> sections = new ArrayList<>();


    public Document() {
    }

    public Document(String title) {
        this.title = title;
    }

    public Document setTitle(String title) {
        this.title = title;
        return this;
    }

    public Document setPhoto(String photoUrl) {
        this.photo = new Photo(photoUrl);
        return this;
    }

    public Section addSection(String sectionTitle) {
        Section section = new Section(sectionTitle);
        this.sections.add(section);
        return section;
    }

    public Document addSection(Section s) {
        this.sections.add(s);
        return this;
    }


    public void writeHTML(PrintStream out) {
        out.printf("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>" + this.title + "</title>\n" +
                "<head>\n" +
                "<meta charset=\"ISO-8859-2\">\n" +
                "</head>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>" + this.title + "</h1>\n");
        photo.writeHTML(out);
        for (Section section : sections) {
            section.writeHTML(out);
        }
        out.printf("</body>\n</html>");
    }


    public void write(String fileName){
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            FileWriter writer= new FileWriter(fileName);;
            m.marshal(this, writer);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    public static Document read(String fileName){
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Unmarshaller m = jc.createUnmarshaller();
            FileReader reader = new FileReader(fileName);
            return (Document) m.unmarshal(reader);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

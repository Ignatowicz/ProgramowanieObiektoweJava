package lab6;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class AdminUnit {

    public String name;
    public int adminLevel;
    public double population;
    public double area;
    public double density;

    public AdminUnit parent;
    public BoundingBox bbox = new BoundingBox();

    public List<AdminUnit> children;

    public String toString() {
        return name + " " + adminLevel + " " + area + " " + population + " " + density +
                " " + parent.name + " " + bbox.toString();
    }

    public String getName() {
        return name;
    }

    public double getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    public AdminUnit getParent() {
        return parent;
    }

    public double getDensity() {
        return this.density;
    }


//    public PrintStream writeHTML() throws FileNotFoundException, UnsupportedEncodingException {
//        PrintStream out = new PrintStream( (this.name + ".html"),"ISO-8859-2");
//        out.printf("<!DOCTYPE html>\n" +
//                "<html>\n" +
//                "<head>\n" +
//                "<title>" + this.getName() + "</title>\n" +
//                "<meta charset=\"ISO-8859-2\">\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<h1>" + this.getName() + "</h1>\n" +
//                "<p>Obszar jednostki: " + this.getArea() +
//                "\nGęstość zaludnienia: " + this.getDensity() +
//                "\nLiczba mieszkańców: " + this.getPopulation() + "</p>");
//        out.printf("\n<p>Link do jednostki nadrzędnej:" +
//                "\n<a href=\"" + this.parent.name + ".html" + "\">" + this.parent.name + "</a></p>");
//
//        out.printf("<p>Linki do jednostek podrzędnych:");
//        for(AdminUnit au: children) {
//            out.printf("\n<a href=\"" + au.name + ".html" + "\">" + au.name + "</a>");
//        }
//        out.printf("</p>\n");
//
////        for (Section section : sections) {
////            section.writeHTML(out);
////        }
//        out.printf("\n</body>\n</html>");
//
//        return out;
//    }
}
package lab6;

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
                " " + parent.toString() + " " + bbox.toString();
    }
}
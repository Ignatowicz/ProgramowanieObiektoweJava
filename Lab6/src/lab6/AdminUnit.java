package lab6;

public class AdminUnit {

    public String name;
    public int adminLevel;
    public double area;
    public double population;
    public double density;

    public AdminUnit parent;
    public BoundingBox bbox = new BoundingBox();

    public String toString() {
        return name + " " + adminLevel + " " + area + " " + population + " " + density +
                " " + parent.toString() + " " + bbox.toString();
    }
}
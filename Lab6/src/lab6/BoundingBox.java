package lab6;

public class BoundingBox {

    public double xmin = Double.NaN;
    public double ymin = Double.NaN;
    public double xmax = Double.NaN;
    public double ymax = Double.NaN;


    public BoundingBox() {
    }

    public String toString() {
        return xmin + " " + ymin + " " + xmax + " " + ymax;
    }

    /**
     * Powiększa BB tak, aby zawierał punkt (x,y)
     *
     * @param x - współrzędna x
     * @param y - współrzędna y
     */
    public void addPoint(double x, double y) {
        if (this.isEmpty()) {       // dodaje pierwszy punkt
            xmin = x;
            ymin = y;
        } else if (xmax == Double.NaN) {    // dodaje drugi punkt
            if (xmin > x) {
                xmax = xmin;
                xmin = x;
            } else {
                xmax = x;
            }
            if (ymin > y) {
                ymax = ymin;
                ymin = y;
            } else {
                ymax = y;
            }
        }
        //  dodaje kazdy kolejny punkt
        if (xmin > x) xmin = x;
        else if (xmax < x) xmax = x;

        if (ymin > y) ymin = y;
        else if (ymax < y) ymax = y;
    }

    /**
     * Sprawdza, czy BB zawiera punkt (x,y)
     *
     * @param x
     * @param y
     * @return
     */
    public boolean contains(double x, double y) {
        return xmin <= x && x <= xmax && ymin <= y && y <= ymax;
    }

    /**
     * Sprawdza czy dany BB zawiera bb
     *
     * @param bb
     * @return
     */
    public boolean contains(BoundingBox bb) {
        return this.contains(bb.xmin, bb.ymin) && this.contains(bb.xmax, bb.ymax);
    }

    /**
     * Sprawdza, czy dany BB przecina się z bb
     *
     * @param bb
     * @return
     */
    public boolean intersects(BoundingBox bb) {
        if (this.contains(bb.xmin, bb.ymin) || this.contains(bb.xmax, bb.ymax))
            return true;
        else if (bb.contains(this.xmax, this.ymax) || bb.contains(this.xmin, this.ymin))
            return true;
        return false;
    }

    /**
     * Powiększa rozmiary tak, aby zawierał bb oraz poprzednią wersję this
     *
     * @param bb
     * @return
     */
    public BoundingBox add(BoundingBox bb) {
        if (this.contains(bb))
            return this;
        addPoint(bb.xmin, bb.ymin);
        addPoint(bb.xmax, bb.ymax);
        return this;
    }

    /**
     * Sprawdza czy BB jest pusty
     *
     * @return
     */
    public boolean isEmpty() {
        return xmax == Double.NaN && xmin == Double.NaN && ymax == Double.NaN && ymin == Double.NaN;
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     *
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    public double getCenterX() {
        if (this.isEmpty())
            throw new RuntimeException("The box is empty!");
        if ((xmax == Double.NaN && xmin == Double.NaN) || (ymax == Double.NaN && ymin == Double.NaN))
            throw new RuntimeException("You've added only one point. Add one more to create a box.");
        return (xmax + xmin) / 2;
    }

    /**
     * Oblicza i zwraca współrzędną y środka
     *
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    public double getCenterY() {
        if (this.isEmpty())
            throw new RuntimeException("The box is empty!");
        if ((xmax == Double.NaN && xmin == Double.NaN) || (ymax == Double.NaN && ymin == Double.NaN))
            throw new RuntimeException("You've added only one point. Add one more to create a box.");
        return (ymax + ymin) / 2;
    }

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     *
     * @param bb prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległosci euklidesowej możesz użyć wzoru haversine
     * (ang. haversine formula)
     */
    public double distanceTo(BoundingBox bb) {
        if (this.isEmpty())
            throw new RuntimeException("The box is empty!");
        if ((xmax == Double.NaN && xmin == Double.NaN) || (ymax == Double.NaN && ymin == Double.NaN))
            throw new RuntimeException("You've added only one point. Add one more to create a box.");

        if (bb.isEmpty())
            throw new RuntimeException("The bbox is empty!");
        if ((bb.xmax == Double.NaN && bb.xmin == Double.NaN) || (bb.ymax == Double.NaN && bb.ymin == Double.NaN))
            throw new RuntimeException("Bbox has only one point added");

        return Math.sqrt(Math.pow(this.getCenterX() + bb.getCenterX(), 2) + Math.pow(this.getCenterY() + bb.getCenterY(), 2));
    }
}
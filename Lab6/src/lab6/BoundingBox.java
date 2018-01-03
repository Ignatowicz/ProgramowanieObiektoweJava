package lab6;

public class BoundingBox {

    public double xmin = Double.MAX_VALUE;
    public double ymin = Double.MAX_VALUE;
    public double xmax = Double.MAX_VALUE;
    public double ymax = Double.MAX_VALUE;


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
            xmax = x;
            ymax = y;
        }
        //  dodaje kazdy kolejny punkt
        else {
            if (xmin > x) xmin = x;
            else if (xmax < x) xmax = x;

            if (ymin > y) ymin = y;
            else if (ymax < y) ymax = y;
        }
    }

    /**
     * Sprawdza czy BB jest pusty
     *
     * @return
     */
    public boolean isEmpty() {
        return xmax == Double.MAX_VALUE && ymax == Double.MAX_VALUE;
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
        return this.contains(bb.xmin, bb.ymin) && this.contains(bb.xmax, bb.ymax)
                && this.contains(bb.xmin, bb.ymax) && this.contains(bb.xmax, bb.ymin);
    }

    /**
     * Sprawdza, czy dany BB przecina się z bb
     *
     * @param bb
     * @return
     */
    public boolean intersects(BoundingBox bb) {
        return (xmax > bb.xmin && ymin < bb.ymax) || (xmax > bb.xmin && ymax > bb.ymin)
                || (xmin < bb.xmax && ymax > bb.ymin) || (xmin < bb.xmax && ymin < bb.ymax);
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
     * Oblicza i zwraca współrzędną x środka
     *
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    public double getCenterX() {
        if (this.isEmpty())
            throw new IllegalThreadStateException("The box is empty!");
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
            throw new IllegalThreadStateException("The box is empty!");
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
            throw new IllegalThreadStateException("The box is empty!");

        if (bb.isEmpty())
            throw new IllegalThreadStateException("The bbox is empty!");


        double startLat = this.getCenterX();
        double endLat = bb.getCenterX();
        double startLong = this.getCenterY();
        double endLong = bb.getCenterY();

        return Haversine.distance(startLat, startLong, endLat, endLong);
        //return Math.sqrt(Math.pow(this.getCenterX() + bb.getCenterX(), 2) + Math.pow(this.getCenterY() + bb.getCenterY(), 2));
    }
}
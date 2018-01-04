package Christmas;

import java.awt.*;

public class Branch implements XmasShape{
    int x1;
    int y1;
    int x2;
    int y2;
    double scale;
    Color lineColor;

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(this.lineColor);
        // ustaw kolor obramowania
        g2d.drawLine(this.x1, this.y1, this.x2, this.y2);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x1,y1);
        g2d.scale(scale,scale);
    }

    public Branch(int x1, int y1, int x2, int y2, double scale, Color lineColor){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.scale = scale;
        this.lineColor = lineColor;

    }
}
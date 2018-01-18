package Christmas;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;



public class DrawPanel extends JPanel {

    List<XmasShape> shapes = new ArrayList<>();

    DrawPanel() {
        setBackground(new Color(21, 37, 128));
        setOpaque(true);
        this.branches();
        this.bubbles();
        this.trunk();
        this.stars();

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (XmasShape s : shapes) {
            s.draw((Graphics2D) g);
        }
        Font font = new Font("Serif", Font.PLAIN, 35);
        g.setFont(font);
        g.drawString("ŚWIĘTA", 270, 320);
        g.drawString("ŚWIĘTA", 830, 320);
        g.drawString("I", 595, 550);
        g.drawString("PO    ŚWIĘTACH", 450, 620);
    }


    void branches() {
        // Dodaje zielone galezie
        int[] x1 = {480, 600, 720};
        int[] y1 = {500, 300, 500};
        Branch b1 = new Branch(x1, y1, 1,
                new GradientPaint(0, 0,
                        new Color(0, 255, 0), 0, 100,
                        new Color(15, 124, 15)));
        shapes.add(b1);

        int[] x2 = {495, 600, 705};
        int[] y2 = {430, 260, 430};
        Branch b2 = new Branch(x2, y2, 1,
                new GradientPaint(0, 0,
                        new Color(0, 255, 0), 0, 100,
                        new Color(15, 124, 15)));
        shapes.add(b2);

        int[] x3 = {510, 600, 690};
        int[] y3 = {360, 220, 360};
        Branch b3 = new Branch(x3, y3, 1,
                new GradientPaint(0, 0,
                        new Color(0, 255, 0), 0, 100,
                        new Color(15, 124, 15)));
        shapes.add(b3);

        int[] x4 = {525, 600, 675};
        int[] y4 = {290, 180, 290};
        Branch b4 = new Branch(x4, y4, 1,
                new GradientPaint(0, 0,
                        new Color(0, 255, 0), 0, 100,
                        new Color(15, 124, 15)));
        shapes.add(b4);

        int[] x5 = {540, 600, 660};
        int[] y5 = {220, 140, 220};
        Branch b5 = new Branch(x5, y5, 1,
                new GradientPaint(0, 0,
                        new Color(0, 255, 0), 0, 100,
                        new Color(15, 124, 15)));
        shapes.add(b5);

    }

    void trunk() {
        // Dodaje brazowy pien
        int[] x = {580, 580, 620, 620};
        int[] y = {580, 500, 500, 580};
        Branch t = new Branch(x, y, 1,
                new GradientPaint(0, 0,
                        new Color(39, 19, 12), 0, 100,
                        new Color(40, 20, 10)));
        shapes.add(t);

    }

    void bubbles() {
        Bubble b1 = new Bubble(620,240,0.15,
                new Color(176, 14, 19),
                new Color(215, 180, 30));
        shapes.add(b1);

        Bubble b2 = new Bubble(570,250,0.15,
                new Color(176, 14, 19),
                new Color(215, 180, 30));
        shapes.add(b2);

        Bubble b3 = new Bubble(590,180,0.1,
                new Color(176, 14, 19),
                new Color(215, 180, 30));
        shapes.add(b3);

        Bubble b4 = new Bubble(610,170,0.1,
                new Color(176, 14, 19),
                new Color(215, 180, 30));
        shapes.add(b4);

        Bubble b5 = new Bubble(620,240,0.15,
                new Color(176, 14, 19),
                new Color(215, 180, 30));
        shapes.add(b5);

        Bubble b6 = new Bubble(634,273,0.175,
                new Color(176, 14, 19),
                new Color(215, 180, 30));
        shapes.add(b6);

        Bubble b7 = new Bubble(660,340,0.175,
                new Color(176, 14, 19),
                new Color(215, 180, 30));
        shapes.add(b7);

        Bubble b8 = new Bubble(570,380,0.18,
                new Color(176, 14, 19),
                new Color(215, 180, 30));
        shapes.add(b8);

        Bubble b9 = new Bubble(620,440,0.18,
                new Color(176, 14, 19),
                new Color(215, 180, 30));
        shapes.add(b9);

        Bubble b10 = new Bubble(680,470,0.2,
                new Color(176, 14, 19),
                new Color(215, 180, 30));
        shapes.add(b10);

        Bubble b11 = new Bubble(550,450,0.2,
                new Color(176, 14, 19),
                new Color(215, 180, 30));
        shapes.add(b11);

        Bubble b12 = new Bubble(583,320,0.18,
                new Color(176, 14, 19),
                new Color(215, 180, 30));
        shapes.add(b12);
    }

    void stars() {
        // Dodaje zolta gwiazde na choince
        int[] x = {600, 590, 570, 585, 580, 600, 620, 615, 630, 610, 600};
        int[] y = {100, 130, 130, 145, 170, 155, 170, 145, 130, 130, 100};
        Branch s = new Branch(x, y, 1,
                new GradientPaint(0, 0,
                        new Color(210, 185, 30), 0, 100,
                        new Color(210, 185, 30)));
        shapes.add(s);

        // Dodaje gwiazdi na niebosklonie
        int[] x1 = {500, 490, 470, 485, 480, 500, 520, 515, 530, 510, 500};
        int[] y1 = {100, 130, 130, 145, 170, 155, 170, 145, 130, 130, 100};
        Branch s1 = new Branch(x1, y1, 0.5,
                new GradientPaint(0, 0,
                        new Color(210, 185, 30), 0, 100,
                        new Color(210, 185, 30)));
        shapes.add(s1);

        int[] x2 = {700, 690, 670, 685, 680, 700, 720, 715, 730, 710, 700};
        int[] y2 = {120, 150, 150, 165, 190, 175, 190, 165, 150, 150, 120};
        Branch s2 = new Branch(x2, y2, 0.5,
                new GradientPaint(0, 0,
                        new Color(210, 185, 30), 0, 100,
                        new Color(210, 185, 30)));
        shapes.add(s2);

        int[] x3 = {1500, 1490, 1470, 1485, 1480, 1500, 1520, 1515, 1530, 1510, 1500};
        int[] y3 = {100, 130, 130, 145, 170, 155, 170, 145, 130, 130, 100};
        Branch s3 = new Branch(x3, y3, 0.5,
                new GradientPaint(0, 0,
                        new Color(210, 185, 30), 0, 100,
                        new Color(210, 185, 30)));
        shapes.add(s3);

        int[] x4 = {1700, 1690, 1670, 1685, 1680, 1700, 1720, 1715, 1730, 1710, 1700};
        int[] y4 = {120, 150, 150, 165, 190, 175, 190, 165, 150, 150, 120};
        Branch s4 = new Branch(x4, y4, 0.5,
                new GradientPaint(0, 0,
                        new Color(210, 185, 30), 0, 100,
                        new Color(210, 185, 30)));
        shapes.add(s4);


        int[] x5 = {2000, 1990, 1970, 1985, 1980, 2000, 2020, 2015, 2030, 2010, 2000};
        int[] y5 = {150, 180, 180, 195, 220, 205, 220, 195, 180, 180, 150};
        Branch s5 = new Branch(x5, y5, 0.5,
                new GradientPaint(0, 0,
                        new Color(210, 185, 30), 0, 100,
                        new Color(210, 185, 30)));
        shapes.add(s5);

        int[] x6 = {2200, 2190, 2170, 2185, 2180, 2200, 2220, 2215, 2230, 2210, 2200};
        int[] y6 = {120, 150, 150, 165, 190, 175, 190, 165, 150, 150, 120};
        Branch s6 = new Branch(x6, y6, 0.5,
                new GradientPaint(0, 0,
                        new Color(210, 185, 30), 0, 100,
                        new Color(210, 185, 30)));
        shapes.add(s6);

        int[] x7 = {400, 390, 370, 385, 380, 400, 420, 415, 430, 410, 400};
        int[] y7 = {200, 230, 230, 245, 270, 255, 270, 245, 230, 230, 200};
        Branch s7 = new Branch(x7, y7, 0.5,
                new GradientPaint(0, 0,
                        new Color(210, 185, 30), 0, 100,
                        new Color(210, 185, 30)));
        shapes.add(s7);

        int[] x8 = {1800, 1790, 1770, 1785, 1780, 1800, 1820, 1815, 1830, 1810, 1800};
        int[] y8 = {20, 50, 50, 65, 90, 75, 90, 65, 50, 50, 20};
        Branch s8 = new Branch(x8, y8, 0.3,
                new GradientPaint(0, 0,
                        new Color(210, 185, 30), 0, 100,
                        new Color(210, 185, 30)));
        shapes.add(s8);


        int[] x9 = {2500, 2490, 2470, 2485, 2480, 2500, 2520, 2515, 2530, 2510, 2500};
        int[] y9 = {250, 280, 280, 295, 320, 305, 320, 295, 280, 280, 250};
        Branch s9 = new Branch(x9, y9, 0.5,
                new GradientPaint(0, 0,
                        new Color(210, 185, 30), 0, 100,
                        new Color(210, 185, 30)));
        shapes.add(s9);

        int[] x10 = {1400, 1390, 1370, 1385, 1380, 1400, 1420, 1415, 1430, 1410, 1400};
        int[] y10 = {220, 250, 250, 265, 290, 275, 290, 265, 250, 250, 220};
        Branch s10 = new Branch(x10, y10, 0.5,
                new GradientPaint(0, 0,
                        new Color(210, 185, 30), 0, 100,
                        new Color(210, 185, 30)));
        shapes.add(s10);

    }
}
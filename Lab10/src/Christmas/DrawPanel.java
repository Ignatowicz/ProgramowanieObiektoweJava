package Christmas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    List<XmasShape> shapes = new ArrayList<>();

    DrawPanel(){
        setBackground(new Color(0, 0, 50));
        setOpaque(true);
        this.branches();
        this.bubbles();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(XmasShape s:shapes) {
            s.draw((Graphics2D) g);
        }


//        g.setFont(new Font("Helvetica", Font.BOLD, 18));
//        g.drawString("Hello World", 20, 20);
//        System.out.println("painting");
//        g.drawLine(10,10,100,100);
//        g.setColor(Color.yellow);
//        g.fillOval(100,101,30,30);
//        g.setColor(Color.black);
//        g.drawOval(100,101,30,30);
//
//
//        int x[]={286,253,223,200,148,119,69,45,0};
//        int y[]={0,101,89,108,79,95,66,80,56};
//        g.fillPolygon(x,y,x.length);
//
//
//        Image img = Toolkit.getDefaultToolkit().getImage("bombka.jpg");
//        g.drawImage(img,200,360,this);
    }

    void bubbles(){
        Bubble b1 = new Bubble(500,100,0.5, new Color(10,100,100,100), new Color(0,0,0));
        Bubble b2 = new Bubble(450,200,0.5, new Color(10,100,100,100), new Color(0,0,0));
        Bubble b3 = new Bubble(550,200,0.5, new Color(10,100,100,100), new Color(0,0,0));
        Bubble b4 = new Bubble(400,300,0.5, new Color(10,100,100,100), new Color(0,0,0));
        Bubble b5 = new Bubble(600,300,0.5, new Color(10,100,100,100), new Color(0,0,0));
        Bubble b6 = new Bubble(500,300,0.5, new Color(10,100,100,100), new Color(0,0,0));
        Bubble b7 = new Bubble(350,400,0.5, new Color(10,100,100,100), new Color(0,0,0));
        Bubble b8 = new Bubble(650,400,0.5, new Color(10,100,100,100), new Color(0,0,0));
        Bubble b9 = new Bubble(450,400,0.5, new Color(10,100,100,100), new Color(0,0,0));
        Bubble b10 = new Bubble(550,400,0.5, new Color(10,100,100,100), new Color(0,0,0));
        shapes.add(b1);
        shapes.add(b2);
        shapes.add(b3);
        shapes.add(b4);
        shapes.add(b5);
        shapes.add(b6);
        shapes.add(b7);
        shapes.add(b8);
        shapes.add(b9);
        shapes.add(b10);
    }

    void branches(){
        Branch b1 = new Branch(254,30,320, 130, 1, new Color(7, 126, 11));
        Branch b2 = new Branch(574,160,0, 0, 1, new Color(7, 126, 11));


        shapes.add(b1);
        shapes.add(b2);


    }
}
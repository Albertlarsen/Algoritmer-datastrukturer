package Oving13;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MapPanel extends JPanel {
    private int width, height;
    private BufferedImage in;
    private double[][] points;

    public MapPanel(int width, int height) {
        this.width = width;
        this.height = height;

        points = new double[4][];
        points[0] = new double[]{51.3, 0};
        points[1] = new double[]{25.02, 121.38};
        points[2] = new double[]{63.42, 10.29};
        points[3] = new double[]{60.02, 10.64};

        try {
            File file = new File("C:\\Users\\Alber\\Documents\\TDAT2005 - Algortimer\\src\\Oving13\\world2.jpg");
            in = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPoints(double[][] points) {
        this.points = points;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //4x resolution
        g2.translate(width, height);
        g2.scale(0.25, 0.25);
        g2.translate(-width*4, -height*4);

        //zoom to scandinavia

        g2.translate(width*2, height*2);
        g2.scale(9, 9);
        g2.translate(-width*2, -height*2);
        g2.translate(-width/4, height*1.38);


        g.drawImage(in, 0, 0, width*4, height*4, null);

        g.setColor(Color.RED);

        for (int i = 0; i < points.length -1; i++) {
            g.drawLine(
                    (int) ((double) width*4/2+points[i][1]*((double) width*4/360.)),
                    (int) ((double) height*4/2-points[i][0]*((double) height*4/180)),
                    (int) ((double) width*4/2+points[i+1][1]*((double) width*4/360.0)),
                    (int) ((double) height*4/2-points[i+1][0]*((double) height*4/180))
            );
        }
    }
}

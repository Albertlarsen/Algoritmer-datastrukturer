package Oving13;

import javax.swing.*;

public class Window extends JFrame {
    private MapPanel mapPanel;

    public Window(int width, int height) {
        super();
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(mapPanel = new MapPanel(width, height));
        validate();
        repaint();
        setVisible(true);
    }

    public void updatePoints(double[][] points) {
        mapPanel.setPoints(points);
        validate();
        repaint();
    }

    public static void main(String[] args) {
        new Window(1600, 980);
    }
}
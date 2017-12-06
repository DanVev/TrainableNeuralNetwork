package UI;


import javafx.geometry.Point2D;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasily Danilin on 05.12.2017.
 */
public class PaintingPanel extends JPanel {

    private List<Point2D> points = new ArrayList<>();

    public List<Point2D> getPoints() {
        return points;
    }

    void addPoint(Point2D point) {
        points.add(point);
    }

    void clear() {
        points.clear();
        this.repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("repaint");
        Graphics2D graphics = (Graphics2D) g;
        for (Point2D point : points)
            graphics.fillOval((int) point.getX(), (int) point.getY(), 10, 10);
    }
}

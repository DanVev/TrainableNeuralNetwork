package NeuralNetwork;

import javafx.geometry.Point2D;

import java.util.List;

/**
 * Created by Vasily Danilin on 06.12.2017.
 */
public class Sample {
    private double[] points;
    private int weigth;
    private int height;
    private int classNumber;

    public Sample(List<Point2D> points, int weigth, int height) {

        setPoints(points);
        this.weigth = weigth;
        this.height = height;
    }

    public Sample(double[] points, int weigth, int height) {
        this.weigth = weigth;
        this.height = height;
        this.points = points;
    }

    public Sample(double[] points, int weigth, int height, int classNumber) {
        this.points = points;
        this.weigth = weigth;
        this.height = height;
        this.classNumber = classNumber;
    }

    public Sample(List<Point2D> points, int weigth, int height, int classNumber) {
        setPoints(points);
        this.weigth = weigth;
        this.height = height;
        this.classNumber = classNumber;
    }

    public double[] getPoints() {
        return points;
    }

    public void setPoints(List<Point2D> points) {
        double[] result = new double[weigth * height];
        for (Point2D point : points)
            result[(int) point.getX() * getWeigth() + (int) point.getY()] = 1;
        this.points = result;
    }

    public int getWeigth() {
        return weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

package cs355.code.model;

import sun.font.StandardTextSource;

import java.awt.*;

/**
 * Created by Andrew on 1/10/2015.
 */
public class Triangle extends Shape{

    Point one;
    Point two;
    Point three;
    int[] Xpoints = new int[3];
    int[] Ypoints= new int[3];

    State state = State.TRIANGLE;

    public Triangle(Point point, Point point1, Point point2) {
        Xpoints[0] = (int)point.getX();
        Xpoints[1] = (int)point1.getX();
        Xpoints[2] = (int)point2.getX();

        Ypoints[0]= (int)point.getY();
        Ypoints[1]= (int)point1.getY();
        Ypoints[2]= (int)point2.getY();
    }

    public Point getOne() {
        return one;
    }
    public void setOne(Point one) {
        this.one = one;
    }

    public Point getThree() {
        return three;
    }
    public void setThree(Point three) {
        this.three = three;
    }

    public Point getTwo() {
        return two;
    }
    public void setTwo(Point two) {
        this.two = two;
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

    public int[] getXpoints() {
        return Xpoints;
    }

    public void setXpoints(int[] xpoints) {
        Xpoints = xpoints;
    }

    public int[] getYpoints() {
        return Ypoints;
    }

    public void setYpoints(int[] ypoints) {
        Ypoints = ypoints;
    }
}

package cs355.code.model;

import java.awt.*;

/**
 * Created by Andrew on 1/10/2015.
 */
public class Ellipse extends Shape{

    Point center;
    double height;
    double width;
    State state = State.ELLIPSE;

    public Ellipse(Point newCenter, double newWidth, double newHeight) {
        this.center = newCenter;
        this.width= newWidth;
        this.height = newHeight;
    }

    public Point getCenter() {
        return center;
    }
    public void setCenter(Point center) {
        this.center = center;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
}

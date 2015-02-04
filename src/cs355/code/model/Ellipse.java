package cs355.code.model;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Andrew on 1/10/2015.
 */
public class Ellipse extends Shape{

    double height;
    double width;
    State state = State.ELLIPSE;

    public Ellipse(Point2D newCenter, double newWidth, double newHeight) {
        setCenter(newCenter);
        this.width= newWidth;
        this.height = newHeight;
        setRotation(0);
    }

    @Override
    public boolean contains(Point2D point) {

        double x = point.getX();
        double y = point.getY();
        double halfWidth = width/2;
        double halfHeight = height/2;

        boolean equation = ((x*x)/(halfWidth*halfWidth) + (y*y)/(halfHeight*halfHeight)) <= 1;

        return equation;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
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

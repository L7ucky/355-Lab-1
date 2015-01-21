package cs355.code.model;

import sun.plugin.liveconnect.OriginNotAllowedException;

import java.awt.*;

/**
 * Created by Andrew on 1/10/2015.
 */
public class Rectangle extends Shape {

    //The Rectangle class should store the location of the upper left corner, the height, and the width.

    private Point upperLeftCorner;
    private double width;
    private double height;
    private double origX;
    private double origY;

    State state = State.RECTANGLE;

    public Rectangle(Point point, double i, double i1) {
        upperLeftCorner = point;
        width = i;
        height = i1;
    }

    public Point getUpperLeftCorner() {
        return upperLeftCorner;
    }
    public void setUpperLeftCorner(Point upperLeftCorner) {
        this.upperLeftCorner = upperLeftCorner;
    }

    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

    public double getOrigX() {
        return origX;
    }
    public void setOrigX(double origX) {
        this.origX = origX;
    }

    public double getOrigY() {
        return origY;
    }
    public void setOrigY(double origY) {
        this.origY = origY;
    }
}

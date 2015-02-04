package cs355.code.model;

import sun.plugin.liveconnect.OriginNotAllowedException;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Andrew on 1/10/2015.
 */
public class Rectangle extends Shape {

    //The Rectangle class should store the location of the upper left corner, the height, and the width.

    private Point2D upperLeftCorner;
    private double width;
    private double height;
    private double origX;
    private double origY;

    State state = State.RECTANGLE;

    public Rectangle(Point2D point, double i, double i1) {
        upperLeftCorner = point;
        width = i;
        height = i1;
        setCenter(calculateCenter(point, i, i1));
        setRotation(0);
    }

    @Override
    public boolean contains(Point2D point) {
        double x = point.getX();
        double y = point.getY();

        boolean right = (x >= -1 * (width/2));
        boolean left = (x <= width/2);
        boolean bottom = (y >= -1 * (height/2));
        boolean top = (y <= height/2);

        return (right && left && bottom && top);
    }

    private Point2D calculateCenter(Point2D point1,  double width1, double height1){

        double halfWidth = width1/2;
        double halfHeight = height1/2;

        double x = point1.getX()+halfWidth;
        double y = point1.getY()+halfHeight;

        return new Point2D.Double(x,y);
    }

    public Point2D getUpperLeftCorner() {
        return upperLeftCorner;
    }
    public void setUpperLeftCorner(Point2D upperLeftCorner) {
        this.upperLeftCorner = upperLeftCorner;
    }


    @Override
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

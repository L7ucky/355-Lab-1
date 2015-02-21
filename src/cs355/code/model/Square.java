package cs355.code.model;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;

/**
 * Created by Andrew on 1/10/2015.
 */
public class Square extends Shape {

    Point2D upperLeftCorner;
    double size;

    State state= State.SQUARE;

    public Square(Point2D newUpperLeft, double size) {

        this.size = size;
        this.upperLeftCorner = newUpperLeft;
        setCenter(calculateCenter(newUpperLeft,(size/2)));
        this.setRotation(0);
    }

    @Override
    public boolean contains(Point2D point) {
        double x = point.getX();
        double y = point.getY();

        boolean right = (x >= -1 * (size/2));
        boolean left = (x <= size/2);
        boolean bottom = (y >= -1 * (size/2));
        boolean top = (y <= size/2);

        return (right && left && top && bottom);
    }



    public Point2D getUpperLeftCorner() {
        return upperLeftCorner;
    }
    public void setUpperLeftCorner(Point2D upperLeftCorner) {
        this.upperLeftCorner = upperLeftCorner;
    }

    public double getSize() {
        return size;
    }
    public void setSize(double size) {
        this.size = size;
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

    private Point2D calculateCenter(Point2D point,  double halfSize){

        double x = point.getX()+halfSize;
        double y = point.getY()+halfSize;

        return new Point2D.Double(x,y);
    }

    @Override
    public double getHeight() {
        return size;
    }

    @Override
    public double getWidth() {
        return size;
    }

    @Override
    public void setWidth(double width) {
this.size = width;
    }

    @Override
    public void setHeight(double height) {
        this.size = height;
    }
}

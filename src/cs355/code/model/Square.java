package cs355.code.model;

import java.awt.*;

/**
 * Created by Andrew on 1/10/2015.
 */
public class Square extends Shape {

    Point upperLeftCorner;
    double size;

    State state= State.SQUARE;

    public Square(Point newUpperLeft, double size) {
        this.size = size;
        this.upperLeftCorner = newUpperLeft;
    }

    public Point getUpperLeftCorner() {
        return upperLeftCorner;
    }
    public void setUpperLeftCorner(Point upperLeftCorner) {
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
}

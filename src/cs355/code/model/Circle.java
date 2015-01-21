package cs355.code.model;

import java.awt.*;

/**
 * Created by Andrew on 1/10/2015.
 */
public class Circle extends Shape{

    public Circle(Point center, double diameter){
        this.center = center;
        this.diameter = diameter;
    }

    Point center;
    double diameter;
    State state= State.CIRCLE;

    public Point getCenter() {
        return center;
    }
    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return diameter;
    }
    public void setRadius(double diameter) {
        this.diameter = diameter;
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
}

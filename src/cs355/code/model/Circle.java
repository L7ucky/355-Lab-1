package cs355.code.model;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Andrew on 1/10/2015.
 */
public class Circle extends Shape{

    public Circle(Point2D center, double radius){
        setCenter(center);
        this.radius = radius;
    }

    double radius;
    State state= State.CIRCLE;

    @Override
    public boolean contains(Point2D point) {
        double x = point.getX(), y = point.getY(), r2 = radius*radius;
        return (
                (x*x)/(r2) + (y*y)/(r2)
        ) <= 1;
    }

    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public double getHeight() {
        return radius*2;
    }

    @Override
    public double getWidth() {
        return radius*2;
    }
}

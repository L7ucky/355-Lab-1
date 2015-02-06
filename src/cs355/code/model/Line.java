package cs355.code.model;

import cs355.code.view.Vector;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Andrew on 1/10/2015.
 */
public class Line extends Shape{

    Point2D start;
    Point2D end;
    State state = State.LINE;

    public Line(Point2D originalClick, Point2D point) {
        this.start = originalClick;
        this.end = point;
    }

    @Override
    public boolean contains(Point2D point) {

        point = new Point2D.Double((point.getX()+start.getX()),(point.getY()+start.getY()));

        Vector starting = new Vector(start);
        Vector ending = new Vector(end);
        Vector pointVector = new Vector(point);

        Vector lineVector = ending.minus(starting);
        Vector pointToLineVector = pointVector.minus(starting);

        double lineLength = lineVector.magnitude();
        lineLength = lineLength * lineLength;

        // Less than or equal to 4 pixels away from line
        double t = pointToLineVector.dot(lineVector)/(lineLength);
        double distS = pointVector.distanceTo(starting);
        double distE = pointVector.distanceTo(ending);

        if (t < 0) return  distS<= 4;
        if (t > 1) return pointVector.distanceTo(ending) <= 4;

        Vector projection = starting.plus(lineVector.times(t));
        double distP = pointVector.distanceTo(projection);
        return distP <= 4;
    }

    public Point2D getStart() {
        return start;
    }
    public void setStart(Point2D start) {
        this.start = start;
    }

    public Point2D getEnd() {
        return end;
    }
    public void setEnd(Point2D end) {
        this.end = end;
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public double getWidth() {
        return 0;
    }
}

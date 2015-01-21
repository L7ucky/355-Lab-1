package cs355.code.model;

import java.awt.*;

/**
 * Created by Andrew on 1/10/2015.
 */
public class Line extends Shape{

    Point start;
    Point end;
    State state = State.LINE;

    public Line(Point originalClick, Point point) {
        this.start = originalClick;
        this.end = point;
    }

    public Point getStart() {
        return start;
    }
    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }
    public void setEnd(Point end) {
        this.end = end;
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
}

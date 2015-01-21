package cs355.code.model;

import java.awt.*;

/**
 * Created by Andrew on 1/10/2015.
 */
public abstract class Shape {
    Color color;
    State state;

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
}

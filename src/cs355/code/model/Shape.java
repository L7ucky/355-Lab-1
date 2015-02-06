package cs355.code.model;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by Andrew on 1/10/2015.
 */
public abstract class Shape {
    private Color color;
    private State state;
    private Point2D center = new Point2D.Double();
    private double rotation = 0;


    public AffineTransform worldToObject() {
        if(this.getState() == State.LINE)
            return lineWorldToObject();
        AffineTransform worldToObj = new AffineTransform();
        worldToObj.rotate(-this.getRotation());
        worldToObj.translate(-this.getCenter().getX(), -this.getCenter().getY());
        return worldToObj;
    }

    private AffineTransform lineWorldToObject() {
        Line line = (Line)this;

        AffineTransform worldToObj = new AffineTransform();
        worldToObj.rotate(-this.getRotation());
        worldToObj.translate(-line.getStart().getX(), -line.getStart().getY());
        return worldToObj;
    }

    public AffineTransform objectToWorld() {
        if(this.getState() == State.LINE)
            return lineObjectToWorld();
        AffineTransform objToWorld = new AffineTransform();
        objToWorld.translate(center.getX(), center.getY());
        objToWorld.rotate(this.getRotation());
        return objToWorld;
    }

    private AffineTransform lineObjectToWorld() {
        Line line = (Line)this;

        AffineTransform objToWorld = new AffineTransform();
        objToWorld.translate(line.getStart().getX(), line.getStart().getY());
        objToWorld.rotate(this.getRotation());
        return objToWorld;
    }

    public boolean contains(Point2D point){return false;}

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

    public Point2D getCenter() {
        return center;
    }
    public void setCenter(Point2D center) {
        this.center = center;
    }

    public double getRotation() {
        return rotation;
    }
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }



    public abstract double getHeight();
    public abstract double getWidth();
}

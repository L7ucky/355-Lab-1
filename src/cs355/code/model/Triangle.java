package cs355.code.model;

import cs355.code.view.Vector;
import sun.font.StandardTextSource;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Andrew on 1/10/2015.
 */
public class Triangle extends Shape{

    Point2D one;
    Point2D two;
    Point2D three;
    int[] Xpoints = new int[3];
    int[] Ypoints= new int[3];
    int[] XpointsObj = new int[3];
    int[] YpointsObj = new int[3];

    State state = State.TRIANGLE;

    public Triangle(Point2D point, Point2D point1, Point2D point2) {
        double x = (point.getX() + point1.getX() + point2.getX())/3;
        double y = (point.getY() + point1.getY() + point2.getY())/3;

        setCenter(new Point2D.Double(x,y));

        Xpoints[0] = (int)point.getX();
        Xpoints[1] = (int)point1.getX();
        Xpoints[2] = (int)point2.getX();

        Ypoints[0]= (int)point.getY();
        Ypoints[1]= (int)point1.getY();
        Ypoints[2]= (int)point2.getY();

        one = point;
        two = point1;
        three = point2;

        XpointsObj = this.getXpointsObjFunc();
        YpointsObj = this.getYpointsObjFunc();
    }

    @Override
    public boolean contains(Point2D p) {

        double planeAB = (XpointsObj[0]-p.getX())*(YpointsObj[1]-p.getY())-(XpointsObj[1]-p.getX())*(YpointsObj[0]-p.getY());
        double planeBC = (XpointsObj[1]-p.getX())*(YpointsObj[2]-p.getY())-(XpointsObj[2] - p.getX())*(YpointsObj[1]-p.getY());
        double planeCA = (XpointsObj[2]-p.getX())*(YpointsObj[0]-p.getY())-(XpointsObj[0] - p.getX())*(YpointsObj[2]-p.getY());
        return sign(planeAB)==sign(planeBC) && sign(planeBC)==sign(planeCA);
    }

    private int sign(double num){
        if(num >= 0)
            return 1;
        else
            return -1;
    }

    private double evaluate(Point2D pointOne, Point2D pointTwo, Point2D pointThree) {
        return (pointOne.getX() - pointThree.getX()) *
                (pointTwo.getY() - pointThree.getY()) - (pointTwo.getX() - pointThree.getX()) *
                (pointOne.getY() - pointThree.getY());
    }

    //Find the highest point on the triangle
    @Override
    public double getHeight() {
        double min= one.getY();
        double max= one.getY();

        if (two.getY() < min)
            min = two.getY();
        if (two.getY() > max)
            max = two.getY();

        if (three.getY() < min)
            min = three.getY();
        if (three.getY() > max)
            max = three.getY();

        return max - min;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    public Point2D getOne() {
        return one;
    }
    public void setOne(Point2D one) {
        this.one = one;
    }

    public Point2D getThree() {
        return three;
    }
    public void setThree(Point2D three) {
        this.three = three;
    }

    public Point2D getTwo() {
        return two;
    }
    public void setTwo(Point2D two) {
        this.two = two;
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

    public int[] getXpoints() {
        return Xpoints;
    }
    public void setXpoints(int[] xpoints) {
        Xpoints = xpoints;
    }

    public int[] getYpoints() {
        return Ypoints;
    }
    public void setYpoints(int[] ypoints) {
        Ypoints = ypoints;
    }

    public int[] getXpointsObj() {
        return XpointsObj;
    }
    public void setXpointsObj(int[] xpointsObj) {
        XpointsObj = xpointsObj;
    }

    public void setYpointsObj(int[] ypointsObj) {
        YpointsObj = ypointsObj;
    }
    public int[] getYpointsObj() {
        return YpointsObj;
    }

    public int[] getXpointsObjFunc() {
        int[] xs = new int[3];
        double centerX= this.getCenter().getX();
        //Deep copy
        for(int i=0; i < Xpoints.length; i++){
            xs[i]=(int)(Xpoints[i]-centerX);
        }

        return xs;
    }
    public int[] getYpointsObjFunc() {
        int[] ys = new int[3];
        double centerY= this.getCenter().getY();

        //Deep copy
        for(int i=0; i < Ypoints.length; i++){
            ys[i]=(int)(Ypoints[i]-centerY);
        }

        return ys;
    }
}

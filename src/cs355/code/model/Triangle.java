package cs355.code.model;

import com.sun.java.accessibility.util.TopLevelWindowListener;
import cs355.code.view.MyTransform;
import cs355.code.view.Vector;
import sun.font.StandardTextSource;

import java.awt.*;
import java.awt.geom.AffineTransform;
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


        one = new Point2D.Double(point.getX()-x,point.getY()-y);
        two = new Point2D.Double(point1.getX()-x,point1.getY()-y);
        three = new Point2D.Double(point2.getX()-x,point2.getY()-y);

        setXpoints(point, point1, point2);
        setYpoints(point, point1, point2);

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

    @Override
    public void setWidth(double width) {

    }

    @Override
    public void setHeight(double height) {

    }

    public Point2D getOne() {
        return one;
    }
    public void setOne(Point2D one) {
        this.one = one;
        Point2D offset = calcCenter();
        two.setLocation(two.getX() - offset.getX(), two.getY() - offset.getY());
        three.setLocation(three.getX() - offset.getX(), three.getY() - offset.getY());

        setXpoints(one, two, three);
        setYpoints(one, two, three);
    }

    public Point2D getThree() {
        return three;
    }
    public void setThree(Point2D three) {
        this.three = three;
        Point2D offset = calcCenter();
        one.setLocation(one.getX() - offset.getX(), one.getY() - offset.getY());
        two.setLocation(two.getX() - offset.getX(), two.getY() - offset.getY());

        setXpoints(one, two, three);
        setYpoints(one, two, three);
    }

    public Point2D getTwo() {
        return two;
    }
    public void setTwo(Point2D two) {
        this.two = two;
        Point2D offset = calcCenter();
        one.setLocation(one.getX() - offset.getX(), one.getY() - offset.getY());
        three.setLocation(three.getX() - offset.getX(), three.getY() - offset.getY());

        setXpoints(one, two, three);
        setYpoints(one, two, three);
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }


    public void setXpoints(Point2D p1, Point2D p2, Point2D p3){
        Xpoints[0] = (int)p1.getX();
        Xpoints[1] = (int)p2.getX();
        Xpoints[2] = (int)p3.getX();
    }
    public int[] getYpoints() {
        return Ypoints;
    }

    public void setYpoints(Point2D p1, Point2D p2, Point2D p3  ) {
        Ypoints[0]= (int)p1.getY();
        Ypoints[1]= (int)p2.getY();
        Ypoints[2]= (int)p3.getY();
    }

    public int[] getXpointsObj() {
        getXpointsObjFunc();
        return XpointsObj;
    }
    public void setXpointsObj(int[] xpointsObj) {
        XpointsObj = xpointsObj;
    }

    public void setYpointsObj(int[] ypointsObj) {
        YpointsObj = ypointsObj;
    }
    public int[] getYpointsObj() {
        getYpointsObjFunc();
        return YpointsObj;
    }

    public int[] getXpointsObjFunc() {
        Xpoints[0] = (int)one.getX();
        Xpoints[1] = (int)two.getX();
        Xpoints[2] = (int)three.getX();

        return Xpoints;
    }
    public int[] getYpointsObjFunc() {
        Ypoints[0]= (int)one.getY();
        Ypoints[1]= (int)two.getY();
        Ypoints[2]= (int)three.getY();

        return Ypoints;
    }
    public Point2D calcCenter(){
        double x = (one.getX() + two.getX() + three.getX())/3;
        double y = (one.getY() + two.getY() + three.getY())/3;

        Point2D move=new Point2D.Double(x,y);

        AffineTransform unRotate = new MyTransform();
        unRotate.rotate(getRotation());

        unRotate.transform(move, move);

        Point2D oldCenter = getCenter();

        setCenter(new Point2D.Double(oldCenter.getX() + move.getX(), oldCenter.getY() + move.getY()));

        return new Point2D.Double(x, y);
    }
}

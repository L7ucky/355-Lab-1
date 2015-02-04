package cs355.code.controller.actionClasses;

import cs355.code.controller.Controller;
import cs355.code.model.Circle;
import cs355.code.model.Shape;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Andrew on 1/11/2015.
 */
public class CircleController extends MouseEventHandler  {

    Controller controller;

    public CircleController(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public Shape mouseDown(Point2D p) {
        return new Circle(convertTOcenter(p, 0,0),0/2);
    }

    @Override
    public void mouseUp(Point2D p) {
        super.mouseUp(p);
    }

    @Override
    public Shape mouseClicked(Point2D p) {
        super.mouseClicked(p);
        return null;
    }

    @Override
    public void mouseMoved(Point2D p) {
        super.mouseMoved(p);
    }

    @Override
    public Shape mouseDragged(Point2D p) {
        super.mouseDragged(p);
        Point2D newUpperLeft = new Point(0, 0);
        double newWidth = 0;
        double newHeight = 0;
        double diameter =0;


            Point2D original = controller.getOriginalClick();
            if (p.getX() >= original.getX()) {
                if (p.getY() > original.getY()) { //--------------------- FOURTH QUADRANT
                    newWidth = Math.abs(p.getX() - original.getX());
                    newHeight = Math.abs(p.getY() - original.getY());
                    diameter = calculateSize(newWidth,newHeight);
                    newUpperLeft = original;
                } else {//------------------------------------------------ FIRST QUADRANT

                    newWidth = Math.abs(p.getX() - original.getX());
                    newHeight = Math.abs(original.getY() - p.getY());
                    diameter = calculateSize(newWidth,newHeight);
                    newUpperLeft = new Point((int) original.getX(), (int)original.getY()-(int)diameter);
                }
            } else {
                if (p.getY() <= original.getY()) {//-------------------- SECOND QUADRANT

                    newWidth = Math.abs(original.getX() - p.getX());
                    newHeight = Math.abs(original.getY() - p.getY());
                    diameter = calculateSize(newWidth,newHeight);
                    newUpperLeft = new Point((int)original.getX()-(int)diameter,(int)original.getY()-(int)diameter);
                } else {//------------------------------------------------ THIRD QUADRANT

                    newWidth = Math.abs(original.getX() - p.getX());
                    newHeight = Math.abs(p.getY() - original.getY());
                    diameter = calculateSize(newWidth,newHeight);
                    newUpperLeft = new Point((int)original.getX()-(int)diameter, (int) original.getY());
                }
            }
        return new Circle(convertTOcenter(newUpperLeft, diameter,diameter),diameter/2);
    }

    private double calculateSize(double width, double height){
        if (width >= height)
            return height;
        else
            return width;
    }

    public Point2D convertTOcenter(Point2D UL, double W, double H){
        double newW =W/2;
        double newH = H/2;

        return new Point2D.Double((UL.getX()+newW),(UL.getY()+newH));
    }
}

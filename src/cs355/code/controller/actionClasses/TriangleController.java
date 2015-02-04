package cs355.code.controller.actionClasses;

import cs355.code.controller.Controller;
import cs355.code.model.Shape;
import cs355.code.model.Triangle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Arrays;

/**
 * Created by Andrew on 1/11/2015.
 */
public class TriangleController extends MouseEventHandler {

    Controller controller;
    int clickCount =0;
    Point2D[] points =  new Point2D[3];

    public TriangleController(Controller controller) {
        super(controller);
        this.controller = controller;
    }



    @Override
    public Shape mouseDown(Point2D p) {
        if(clickCount<=2){
            points[clickCount] = p;
            if(clickCount==2){
                clickCount=0;
                Triangle triangle = new Triangle(points[0],points[1],points[2]);
                Arrays.fill(points, null);
                return triangle;
            }
            else
                clickCount++;
        }
        return null;
    }

    @Override
    public void mouseUp(Point2D p) {
        super.mouseUp(p);
    }

    @Override
    public Shape mouseClicked(Point2D p) {
        return null;
    }

    @Override
    public void mouseMoved(Point2D p) {
        super.mouseMoved(p);
    }

    @Override
    public Shape mouseDragged(Point2D p) {
        super.mouseDragged(p);
        return null;
    }

    @Override
    public void reset() {
        super.reset();
    }
}

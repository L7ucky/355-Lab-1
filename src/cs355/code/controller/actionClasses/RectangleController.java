package cs355.code.controller.actionClasses;

import cs355.code.controller.Controller;
import cs355.code.model.Rectangle;
import cs355.code.model.Shape;

import java.awt.geom.Point2D;

/**
 * Created by Andrew on 1/11/2015.
 */
public class RectangleController extends MouseEventHandler  {

    Controller controller = null;

    public RectangleController(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public Shape mouseDown(Point2D p) {
        return new Rectangle(p,0,0);
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
        Point2D newUpperLeft = new Point2D.Double(0,0);
        double newWidth = 0;
        double newHeight = 0;
        
        Point2D original = controller.getOriginalClick();
        if(p.getX() >= original.getX()){
            if(p.getY() > original.getY()){ //--------------------- FOURTH QUADRANT
                newUpperLeft = original;
                newWidth = Math.abs(p.getX()-original.getX());
                newHeight = Math.abs(p.getY()-original.getY());
            }
            else{//------------------------------------------------ FIRST QUADRANT
                newUpperLeft = new Point2D.Double((int)original.getX(),p.getY());
                newWidth = Math.abs(p.getX()-original.getX());
                newHeight =  Math.abs(original.getY()-p.getY());
            }
        }
        else{
            if(p.getY() <= original.getY()) {//-------------------- SECOND QUADRANT
                newUpperLeft = new Point2D.Double(p.getX(),p.getY());
                newWidth = Math.abs(original.getX() - p.getX());
                newHeight = Math.abs(original.getY()-p.getY());
            }
            else{//------------------------------------------------ THIRD QUADRANT
                newUpperLeft = new Point2D.Double(p.getX(),(int)original.getY());
                newWidth = Math.abs(original.getX() - p.getX());
                newHeight =  Math.abs(p.getY()-original.getY());
            }
        }

        return new Rectangle(newUpperLeft,newWidth,newHeight);
    }

    @Override
    public void reset() {
        super.reset();
    }
}

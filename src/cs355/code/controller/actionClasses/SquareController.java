package cs355.code.controller.actionClasses;

import cs355.code.controller.Controller;
import cs355.code.model.Shape;
import cs355.code.model.Square;

import java.awt.geom.Point2D;

/**
 * Created by Andrew on 1/11/2015.
 */
public class SquareController extends MouseEventHandler {

    Controller controller = null;

    public SquareController(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public Shape mouseDown(Point2D p) {
        return new Square(p, 0);
    }

    @Override
    public void mouseUp(Point2D p) {
        super.mouseUp(p);
    }

    @Override
    public Shape mouseClicked(Point2D p) {
        return super.mouseClicked(p);
    }

    @Override
    public void mouseMoved(Point2D p) {
        super.mouseMoved(p);
    }

    @Override
    public Shape mouseDragged(Point2D p) {
        Point2D newUpperLeft = new Point2D.Double(0, 0);
        double newWidth = 0;
        double newHeight = 0;
        double size =0;



           Point2D original = controller.getOriginalClick();
            if (p.getX() >= original.getX()) {
                if (p.getY() > original.getY()) { //--------------------- FOURTH QUADRANT
                    newWidth = Math.abs(p.getX() - original.getX());
                    newHeight = Math.abs(p.getY() - original.getY());
                    size = calculateSize(newWidth,newHeight);
                    newUpperLeft = original;
                } else {//------------------------------------------------ FIRST QUADRANT

                    newWidth = Math.abs(p.getX() - original.getX());
                    newHeight = Math.abs(original.getY() - p.getY());
                    size = calculateSize(newWidth,newHeight);
                    newUpperLeft = new Point2D.Double((int) original.getX(), (int)original.getY()-(int)size);
                }
            } else {
                if (p.getY() <= original.getY()) {//-------------------- SECOND QUADRANT

                    newWidth = Math.abs(original.getX() - p.getX());
                    newHeight = Math.abs(original.getY() - p.getY());
                    size = calculateSize(newWidth,newHeight);
                    newUpperLeft = new Point2D.Double((int)original.getX()-(int)size,(int)original.getY()-(int)size);
                } else {//------------------------------------------------ THIRD QUADRANT

                    newWidth = Math.abs(original.getX() - p.getX());
                    newHeight = Math.abs(p.getY() - original.getY());
                    size = calculateSize(newWidth,newHeight);
                    newUpperLeft = new Point2D.Double((int)original.getX()-(int)size, (int) original.getY());
                }
            }
        return new Square(newUpperLeft, size);
    }

    @Override
    public void reset() {
        super.reset();
    }

    private double calculateSize(double width, double height){
        if (width >= height)
            return height;
        else
            return width;
    }
}

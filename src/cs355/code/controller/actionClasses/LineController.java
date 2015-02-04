package cs355.code.controller.actionClasses;

import cs355.code.controller.Controller;
import cs355.code.model.Line;
import cs355.code.model.Shape;

import java.awt.geom.Point2D;

/**
 * Created by Andrew on 1/11/2015.
 */
public class LineController extends MouseEventHandler  {

    Controller controller;

    public LineController(Controller controller) {
        super(controller);
        this.controller = controller;
    }


    @Override
    public Shape mouseDown(Point2D p) {
        Point2D orig = controller.getOriginalClick();
        return new Line(orig,p);
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
        return new Line(controller.getOriginalClick(),p);
    }

    @Override
    public void reset() {
        super.reset();
    }
}

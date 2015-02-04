package cs355.code.controller.actionClasses;

import cs355.code.controller.Controller;
import cs355.code.model.DataModel;
import cs355.code.model.Shape;

import java.awt.geom.Point2D;

/**
 * Created by Andrew on 1/29/2015.
 */
public class MouseEventHandler {

    private Controller controller;
    private DataModel dataModel = DataModel.getInstance();

    public MouseEventHandler(Controller controller){
        this.controller = controller;
    }

    public Shape mouseDown(Point2D p) {return null;}
    public void mouseUp(Point2D p) {}
    public Shape mouseClicked(Point2D p) {return null;}
    public void mouseMoved(Point2D p) {}
    public Shape mouseDragged(Point2D p) {return null;}

    public void reset() {
    }
}

package cs355.code.controller.actionClasses;

import cs355.code.controller.Controller;
import cs355.code.controller.actionClasses.handelTypes.Default;
import cs355.code.controller.actionClasses.handelTypes.Handle;
import cs355.code.controller.actionClasses.handelTypes.None;
import cs355.code.controller.actionClasses.handelTypes.Rotate;
import cs355.code.model.DataModel;
import cs355.code.model.Line;
import cs355.code.model.Shape;
import cs355.code.model.Triangle;
import cs355.code.view.GUIFunctions;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by Andrew on 1/29/2015.
 */
public class SelectionController extends MouseEventHandler {
    Controller controller;
    Handle activeHandel;
    Point2D previous;
    public SelectionController(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public Shape mouseDown(Point2D p) {
        previous = p;

        activeHandel = findHandle(p);
        if (activeHandel instanceof Default) {

            int selectedIndex = DataModel.getInstance().selectVisibleShape(p);

            if (selectedIndex >= 0) {
                Shape selected =DataModel.getInstance().getData().get(selectedIndex);
                DataModel.getInstance().setSelected(selected, selectedIndex);
                GUIFunctions.changeSelectedColor(selected.getColor());
                DataModel.getInstance().setCurrentColor(selected.getColor());
            } else {
                activeHandel = new None();
                DataModel.getInstance().setSelected(null,-1);
                GUIFunctions.changeSelectedColor(DataModel.getInstance().getCurrentColor());
            }
        }
        DataModel.getInstance().refresh();
        return null;
    }

    private Handle findHandle(Point2D p) {
        Shape shape = DataModel.getInstance().getSelected();
        if (shape == null) 
            return new Default();

        AffineTransform worldToObj = shape.worldToObject();
        Point2D objCoordinates = new Point2D.Double();
        worldToObj.transform(p, objCoordinates);
        
        if (withinTolerance(objCoordinates, new Point2D.Double(0, (int) -shape.getHeight() / 2 - 17))) {
            System.out.println("Returning the rotate handle");
            return new Rotate();
        }

        return new Default();
        
    }

    //tolerance of 16 pixles squared 4 pix on each side
    private boolean withinTolerance(Point2D objCoordinates, Point2D point) {
        return objCoordinates.distanceSq(point) <= 16;
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public Shape mouseDragged(Point2D p) {

        Shape selected = DataModel.getInstance().getSelected();
        if (selected == null)
            return null;
        Shape shape= activeHandel.handleAction(p, previous, selected);
        previous = p;

        return shape;
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
}

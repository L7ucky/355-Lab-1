package cs355.code.controller.actionClasses;

import cs355.code.controller.Controller;
import cs355.code.controller.actionClasses.handelTypes.*;
import cs355.code.model.*;
import cs355.code.view.GUIFunctions;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by Andrew on 1/29/2015.
 */
public class SelectionController extends MouseEventHandler {
    Controller controller;
    Handle activeHandle;
    Point2D previous;
    public SelectionController(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public Shape mouseDown(Point2D p) {
        previous = p;

        activeHandle = findHandle(p);
        if (activeHandle instanceof Default) {

            int selectedIndex = DataModel.getInstance().selectVisibleShape(p);

            if (selectedIndex >= 0) {
                Shape selected =DataModel.getInstance().getData().get(selectedIndex);
                DataModel.getInstance().setSelected(selected, selectedIndex);
                GUIFunctions.changeSelectedColor(selected.getColor());
                DataModel.getInstance().setCurrentColor(selected.getColor());
            } else {
                activeHandle = new None();
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
        
        if (withinTolerance(objCoordinates, new Point2D.Double(0, (int) -shape.getHeight() / 2 - (int)(17/controller.getView().zoomMagnitude)))) {
            return new Rotate();
        }
        else if(shape.getState() == State.LINE){
            Line line=(Line)shape;
            if(withinTolerance(p, line.getStart())){
                return new StartLine();
            }
            else if(withinTolerance(p, line.getEnd())){
                return new EndLine();
            }
        }
        else if(shape.getState() == State.TRIANGLE){
            Triangle t = (Triangle)shape;

            Point2D one = t.getOne();
            Point2D two = t.getTwo();
            Point2D three = t.getThree();

            if (withinTolerance(objCoordinates, one))
                return new OneTriangle();

            if (withinTolerance(objCoordinates, two))
                return new TwoTriangle();

            if (withinTolerance(objCoordinates, three))
                return new ThreeTriangle();
        }
        else{
            double heightFromcenter= shape.getHeight()/2;
            double widthFromcenter= shape.getWidth()/2;

            Point2D tRight = new Point2D.Double(-widthFromcenter, heightFromcenter);
            Point2D tLeft = new Point2D.Double(-widthFromcenter, -heightFromcenter);
            Point2D bRight = new Point2D.Double(widthFromcenter, heightFromcenter);
            Point2D bLeft = new Point2D.Double(widthFromcenter, -heightFromcenter);

            if(withinTolerance(objCoordinates, tLeft) || withinTolerance(objCoordinates, tRight) || withinTolerance(objCoordinates, bRight) || withinTolerance(objCoordinates, bLeft))
                return new BoundingBox();
        }

        return new Default();
    }

    //tolerance of 16 pixels squared, 4 pix on each side
    private boolean withinTolerance(Point2D objCoordinates, Point2D point) {
        AffineTransform worldToView = controller.getView().worldToView;
        Point2D _p1 = new Point2D.Double();
        Point2D _p2 = new Point2D.Double();
        worldToView.transform(objCoordinates,_p1);
        worldToView.transform(point,_p2);
        return _p1.distanceSq(_p2) <= 200;
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
        Shape shape= activeHandle.handleAction(p, previous, selected);
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

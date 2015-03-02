package cs355.code.view;

import cs355.code.model.*;
import cs355.code.model.Rectangle;
import cs355.code.model.Shape;
import javafx.scene.transform.Affine;

import javax.swing.tree.FixedHeightLayoutCache;
import java.awt.*;

import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;


/**
 * Created by Andrew on 1/10/2015.
 */
public class ViewRefresh implements ViewRefresher, Observer {


    public double zoomMagnitude =1;

    private double scrollHPosition = 0;
    private double scrollVPosition = 0;

    private double viewW=510;
    private double viewH=510;

    public AffineTransform viewToWorld = new MyTransform();
    public AffineTransform worldToView = new MyTransform();
    private AffineTransform objToWorld;
    private AffineTransform objToView;

    public ViewRefresh() {
        DataModel.getInstance().addObserver(this);
        updateTransform();
    }

    @Override
    public void refreshView(Graphics2D g2d) {
        ArrayList<Shape> data = DataModel.getInstance().getData();
        for (int i = 0; i < data.size(); i++) {
            objToWorld = data.get(i).objectToWorld();

            objToView = (AffineTransform) objToWorld.clone();
            objToView.preConcatenate(worldToView);
            State state = data.get(i).getState();

            Point2D center = new Point2D.Double(0, 0);

            objToView.transform(center, center);

            AffineTransform t = new MyTransform();
            t.translate(center.getX(), center.getY());
            t.rotate(data.get(i).getRotation());

            switch (state) {
                case RECTANGLE:
                    Rectangle rectangle = (Rectangle) data.get(i);

                    double width = rectangle.getWidth();
                    double height = rectangle.getHeight();

                    g2d.setTransform(objToView);
                    g2d.setPaint(rectangle.getColor());
                    g2d.fillRect((int) (-width / 2), (int) (-height / 2), (int) width, (int) height);
//                    g2d.setTransform(rectangle.worldToObject());
                    break;
                case CIRCLE:
                    Circle circle = (Circle) data.get(i);
                    double cirW = circle.getRadius() * 2;
                    double cirH = circle.getRadius() * 2;
                    g2d.setTransform(objToView);
                    Ellipse2D circ = new Ellipse2D.Double(-circle.getRadius(), -circle.getRadius(), cirW, cirH);
                    g2d.setColor(circle.getColor());
                    g2d.fill(circ);
//                    g2d.setTransform(circle.worldToObject());
                    break;
                case LINE:
                    Line line = (Line) data.get(i);
                    g2d.setTransform(objToView);
                    Line2D li = new Line2D.Double(
                            new Point2D.Double(0.0, 0.0),
                            new Point2D.Double((line.getEnd().getX() - line.getStart().getX()), (line.getEnd().getY() - line.getStart().getY())));
                    g2d.setColor(line.getColor());
                    g2d.draw(li);
//                    g2d.setTransform(line.worldToObject());
                    break;
                case TRIANGLE:
                    Triangle triangle = (Triangle) data.get(i);
                    g2d.setTransform(objToView);
                    Polygon tri = new Polygon(triangle.getXpointsObj(), triangle.getYpointsObj(), 3);
                    g2d.setColor(triangle.getColor());
                    g2d.fillPolygon(tri);
//                    g2d.setTransform(triangle.worldToObject());
                    break;
                case ELLIPSE:
                    Ellipse ellipse = (Ellipse) data.get(i);
                    double elleW = ellipse.getWidth();
                    double elleH = ellipse.getHeight();
                    g2d.setTransform(objToView);
                    Ellipse2D elli = new Ellipse2D.Double(-elleW / 2, -elleH / 2, elleW, elleH);
                    g2d.setPaint(ellipse.getColor());
                    g2d.fill(elli);
//                    g2d.setTransform(ellipse.worldToObject());
                    break;
                case SQUARE:
                    Square square = (Square) data.get(i);
                    g2d.setTransform(objToView);
                    Rectangle2D squa = new Rectangle2D.Double(-(square.getWidth() / 2), -(square.getHeight() / 2), square.getSize(), square.getSize());
                    g2d.setPaint(square.getColor());
                    g2d.fill(squa);
//                    g2d.setTransform(square.worldToObject());
                    break;
                default:
                    break;
            }
        }
        drawSelected(g2d);
    }

    private void drawSelected(Graphics2D g2d) {
        ArrayList<Shape> data = DataModel.getInstance().getData();
        if (DataModel.getInstance().getSelectedIndex() >= 0) {

            Shape shape = DataModel.getInstance().getSelected();
            objToWorld = shape.objectToWorld();

            objToView = (AffineTransform) objToWorld.clone();
            objToView.preConcatenate(worldToView);

            switch (shape.getState()) {
                case RECTANGLE:
                    Rectangle rectangle = (Rectangle) shape;
                    double width = rectangle.getWidth();
                    double height = rectangle.getHeight();
                    double leftX = rectangle.getCenter().getX() - width / 2;
                    double topY = rectangle.getCenter().getY() - height / 2;

                    g2d.setTransform(objToView);
                    g2d.setPaint(Color.white);
                    g2d.drawRect((int) -width / 2, (int) -height / 2, (int) width, (int) height);
                    drawHandle(rectangle, g2d);
//                    g2d.setTransform(rectangle.worldToObject());
                    break;
                case CIRCLE:
                    Circle circle = (Circle) shape;
                    double cirW = (circle.getRadius() * 2);
                    double cirH = (circle.getRadius() * 2);

                    g2d.setTransform(objToView);
                    Ellipse2D circ = new Ellipse2D.Double(-circle.getRadius(), -circle.getRadius(), cirW, cirH);
                    g2d.setColor(Color.white);
                    g2d.draw(circ);
                    drawHandle(circle, g2d);
//                    g2d.setTransform(circle.worldToObject());
                    break;
                case LINE:
                    Line line = (Line) shape;
                    g2d.setTransform(objToView);
                    drawLineHandles(line, g2d);
//                    g2d.setTransform(line.worldToObject());
                    break;
                case TRIANGLE:
                    Triangle triangle = (Triangle) shape;

                    g2d.setTransform(objToView);
                    int[] xs = triangle.getXpointsObj();
                    int[] ys = triangle.getYpointsObj();

                    Polygon tri = new Polygon(xs, ys, 3);
                    g2d.setPaint(Color.WHITE);
                    g2d.drawPolygon(tri);
                    drawTriangleHandle(triangle, g2d);
//                    g2d.setTransform(triangle.worldToObject());
                    break;
                case ELLIPSE:
                    Ellipse ellipse = (Ellipse) shape;
                    double elleW = ellipse.getWidth();
                    double elleH = ellipse.getHeight();
                    g2d.setTransform(objToView);
                    Ellipse2D elli = new Ellipse2D.Double(-elleW / 2, -elleH / 2, elleW, elleH);
                    g2d.setPaint(Color.WHITE);
                    g2d.draw(elli);
                    drawHandle(ellipse, g2d);
//                    g2d.setTransform(ellipse.worldToObject());
                    break;
                case SQUARE:
                    Square square = (Square) shape;
                    double s = square.getSize();
                    g2d.setTransform(objToView);
                    Rectangle2D squa = new Rectangle2D.Double(-(s / 2), -(s / 2), s, s);
                    g2d.setPaint(Color.WHITE);
                    g2d.draw(squa);
                    drawHandle(square, g2d);
//                    g2d.setTransform(square.worldToObject());
                    break;
                default:
                    break;
            }
        }
    }

    private void drawLineHandles(Line line, Graphics2D g2d) {
        Point2D start = line.getStart();
        Point2D end = line.getEnd();

//        Point2D start = new Point2D.Double();
//        Point2D end = new Point2D.Double();

//        objToView.transform(s, start);
//        objToView.transform(e, end);

        g2d.setColor(Color.white);
        g2d.fillOval(-(int)(12/zoomMagnitude), -(int)(12/zoomMagnitude), (int)(28/zoomMagnitude), (int)(28/zoomMagnitude));
        g2d.fillOval((int) ((end.getX() - start.getX()) - (int)(12/zoomMagnitude)), (int) ((end.getY() - start.getY()) - (int)(12/zoomMagnitude)) - (int)(12/zoomMagnitude), (int)(28/zoomMagnitude), (int)(28/zoomMagnitude));
        g2d.setColor(line.getColor());
    }

    private void drawTriangleHandle(Shape shape, Graphics2D g2d) {
        Point2D center = shape.getCenter();
        Triangle triangle = (Triangle) shape;
        double heightFromcenter = shape.getHeight() / 2;
        g2d.setColor(Color.white);

        // Rotation handle
        Point2D rotationHandlePoint = new Point2D.Double(0, (-heightFromcenter - (int)(17/zoomMagnitude)));
        g2d.fillOval((int) rotationHandlePoint.getX() - (int)(12/zoomMagnitude), (int) rotationHandlePoint.getY() - (int)(12/zoomMagnitude), (int)(28/zoomMagnitude), (int)(28/zoomMagnitude));

        int[] xs = triangle.getXpointsObj();
        int[] ys = triangle.getYpointsObj();

//        for(int i =0; i<3; i++){
//            xs[i]*=zoomMagnitude;
//            ys[i]*=zoomMagnitude;
//        }

        g2d.fillOval(xs[0] - (int)(12/zoomMagnitude), ys[0] - (int)(12/zoomMagnitude), (int)(28/zoomMagnitude), (int)(28/zoomMagnitude));
        g2d.fillOval(xs[1] - (int)(12/zoomMagnitude), ys[1] - (int)(12/zoomMagnitude), (int)(28/zoomMagnitude), (int)(28/zoomMagnitude));
        g2d.fillOval(xs[2] - (int)(12/zoomMagnitude), ys[2] - (int)(12/zoomMagnitude), (int)(28/zoomMagnitude), (int)(28/zoomMagnitude));
    }

    private void drawHandle(Shape shape, Graphics2D g2d) {

        Point2D center = shape.getCenter();
        double heightFromcenter = shape.getHeight() / 2;
        double widthFromcenter = shape.getWidth() / 2;
        g2d.setColor(Color.white);

        if (shape.getState() != State.CIRCLE) {
            // Rotation handle
            Point2D rotationHandlePoint = new Point2D.Double(0, (-heightFromcenter - (int)(17/zoomMagnitude)));
            g2d.fillOval((int) rotationHandlePoint.getX() - (int)(12/zoomMagnitude), (int) rotationHandlePoint.getY() - (int)(12/zoomMagnitude), (int)(28/zoomMagnitude), (int)(28/zoomMagnitude));
        }

        Point2D tRight = new Point2D.Double(-widthFromcenter, heightFromcenter);


        Point2D tLeft = new Point2D.Double(-widthFromcenter, -heightFromcenter);


        Point2D bRight = new Point2D.Double(widthFromcenter, heightFromcenter);


        Point2D bLeft = new Point2D.Double(widthFromcenter, -heightFromcenter);


        g2d.fillOval((int) tLeft.getX() - (int)(12/zoomMagnitude), (int) tLeft.getY() - (int)(12/zoomMagnitude), (int)(28/zoomMagnitude), (int)(28/zoomMagnitude));
        g2d.fillOval((int) tRight.getX() - (int)(12/zoomMagnitude), (int) tRight.getY() - (int)(12/zoomMagnitude), (int)(28/zoomMagnitude), (int)(28/zoomMagnitude));
        g2d.fillOval((int) bLeft.getX() - (int)(12/zoomMagnitude), (int) bLeft.getY() - (int)(12/zoomMagnitude), (int)(28/zoomMagnitude), (int)(28/zoomMagnitude));
        g2d.fillOval((int) bRight.getX() - (int)(12/zoomMagnitude), (int) bRight.getY() - (int)(12/zoomMagnitude), (int)(28/zoomMagnitude), (int)(28/zoomMagnitude));

    }

    private void rotateShape(Graphics2D g2d, Shape shape) {
        AffineTransform objToWorld = shape.objectToWorld();

        Point2D center = new Point2D.Double(0, 0);

        objToWorld.transform(center, center);

        AffineTransform t = new MyTransform();
        t.translate(center.getX(), center.getY());
        t.rotate(shape.getRotation());

        g2d.setTransform(t);
    }

    @Override
    public void update(Observable o, Object arg) {
        GUIFunctions.refresh();
    }

    private Point2D convertFROMcenter(Point2D c, double w, double h) {

        return new Point2D.Double((c.getX() - (w / 2)), (c.getY() - (h / 2)));
    }


    public void scrollX(int value) {
        scrollHPosition = value;
        System.out.println("X:\t"+scrollHPosition);
        updateTransform();
        GUIFunctions.refresh();
    }
    public void scrollY(int value) {
        scrollVPosition = value;
        updateTransform();
        GUIFunctions.refresh();
    }
    public void zoomIN() {
        if(zoomMagnitude>=4)
            return;

        Point2D center = new Point2D.Double(viewW/2, viewH/2);
        viewToWorld.transform(center, center);

        setScroll(2);

        GUIFunctions.setHScrollBarPosit((int) center.getX());
        GUIFunctions.setVScrollBarPosit((int) center.getY());

        GUIFunctions.refresh();
    }

    public void zoomOUT() {
        if (zoomMagnitude <= 0.25)
            return;

        Point2D center = new Point2D.Double(viewW/2, viewH/2);
        viewToWorld.transform(center, center);

        setScroll(0.5);

        GUIFunctions.setHScrollBarPosit((int) center.getX());
        GUIFunctions.setVScrollBarPosit((int) center.getY());

        GUIFunctions.refresh();
    }
    public void setScroll(double mag) {
        //update the magnitude to be zoomed
        zoomMagnitude *= mag;

        //Set the horizontal scroll settings
        GUIFunctions.setHScrollBarMax((int) (2048));
        GUIFunctions.setHScrollBarKnob((int) (2048/zoomMagnitude));
        //Set the vertical scroll settings
        GUIFunctions.setVScrollBarMax((int) (2048));
        GUIFunctions.setVScrollBarKnob((int) (2048/zoomMagnitude));

        //updates the current transform which will be applied to all objects to be drawn.
        updateTransform();
    }

    private void updateTransform() {
        //Resets this transform to the Identity transform.
        worldToView.setToIdentity();
        worldToView.translate(-scrollHPosition, -scrollVPosition);
        worldToView.scale(zoomMagnitude, zoomMagnitude);

        viewToWorld.setToIdentity();
        viewToWorld.scale(1/zoomMagnitude, 1/zoomMagnitude);
        viewToWorld.translate(scrollHPosition, scrollVPosition);
    }
}





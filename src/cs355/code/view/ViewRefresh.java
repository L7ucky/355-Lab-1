package cs355.code.view;

import cs355.code.model.*;
import cs355.code.model.Rectangle;
import cs355.code.model.Shape;

import javax.swing.tree.FixedHeightLayoutCache;
import java.awt.*;

import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * Created by Andrew on 1/10/2015.
 */
public class ViewRefresh implements ViewRefresher, Observer {


    public ViewRefresh(){
        DataModel.getInstance().addObserver(this);
    }

    @Override
    public void refreshView(Graphics2D g2d) {
        ArrayList<Shape>  data = DataModel.getInstance().getData();
        for (int i =0; i < data.size(); i++){
            State state = data.get(i).getState();

            switch(state){
                case RECTANGLE:
                    Rectangle rectangle = (Rectangle)data.get(i);

                    double width = rectangle.getWidth();
                    double height = rectangle.getHeight();

                    g2d.setTransform(rectangle.objectToWorld());
                    g2d.setPaint(rectangle.getColor());
                    g2d.fillRect((int)(-width/2),(int)(-height/2),(int)width,(int)height);
                    g2d.setTransform(rectangle.worldToObject());
                    break;
                case CIRCLE:
                    Circle circle = (Circle) data.get(i);
                    double cirW = circle.getRadius()*2;
                    double cirH = circle.getRadius()*2;
                    g2d.setTransform(circle.objectToWorld());
                    Ellipse2D circ = new Ellipse2D.Double(- circle.getRadius(),-circle.getRadius(),cirW,cirH);
                    g2d.setColor(circle.getColor());
                    g2d.fill(circ);
                    g2d.setTransform(circle.worldToObject());
                    break;
                case LINE:
                    Line line = (Line)data.get(i);
                    g2d.setTransform(line.objectToWorld());
                    Line2D li = new Line2D.Double(
                                new Point2D.Double(0.0,0.0),
                                new Point2D.Double((line.getEnd().getX()-line.getStart().getX()),(line.getEnd().getY()-line.getStart().getY())));
                    g2d.setColor(line.getColor());
                    g2d.draw(li);
                    g2d.setTransform(line.worldToObject());
                    break;
                case TRIANGLE:
                    Triangle triangle = (Triangle)data.get(i);
                    g2d.setTransform(triangle.objectToWorld());
                    Polygon tri = new Polygon(triangle.getXpointsObj(), triangle.getYpointsObj(), 3);
                    g2d.setColor(triangle.getColor());
                    g2d.fillPolygon(tri);
                    g2d.setTransform(triangle.worldToObject());
                    break;
                case ELLIPSE:
                    Ellipse ellipse = (Ellipse) data.get(i);
                    double elleW = ellipse.getWidth();
                    double elleH = ellipse.getHeight();
                    g2d.setTransform(ellipse.objectToWorld());
                    Ellipse2D elli = new Ellipse2D.Double(- elleW/2,-elleH/2,elleW,elleH);
                    g2d.setPaint(ellipse.getColor());
                    g2d.fill(elli);
                    g2d.setTransform(ellipse.worldToObject());
                    break;
                case SQUARE:
                    Square square = (Square)data.get(i);
                    g2d.setTransform(square.objectToWorld());
                    Rectangle2D squa = new Rectangle2D.Double(-(square.getWidth()/2), -(square.getHeight()/2), square.getSize(), square.getSize());
                    g2d.setPaint(square.getColor());
                    g2d.fill(squa);
                    g2d.setTransform(square.worldToObject());
                    break;
                default:
                    break;
            }
        }
        drawSelected(g2d);
    }

    private void drawSelected(Graphics2D g2d) {
        DataModel data = DataModel.getInstance();
        if(data.getSelectedIndex()>=0){
            Shape shape = data.getSelected();

            switch(shape.getState()){
                case RECTANGLE:
                    Rectangle rectangle = (Rectangle)shape;
                    double width = rectangle.getWidth();
                    double height = rectangle.getHeight();
                    double leftX = rectangle.getCenter().getX()-width/2;
                    double topY = rectangle.getCenter().getY()-height/2;

                    g2d.setTransform(rectangle.objectToWorld());
                    g2d.setPaint(Color.white);
                    g2d.drawRect((int) -width / 2, (int) -height / 2, (int) width, (int) height);
                    drawHandle(rectangle, g2d);
                    g2d.setTransform(rectangle.worldToObject());
                    break;
                case CIRCLE:
                    Circle circle = (Circle) shape;
                    double cirW = circle.getRadius()*2;
                    double cirH = circle.getRadius()*2;

                    g2d.setTransform(circle.objectToWorld());
                    Ellipse2D circ = new Ellipse2D.Double(- circle.getRadius(),-circle.getRadius(),cirW,cirH);
                    g2d.setColor(Color.white);
                    g2d.draw(circ);
                    drawHandle(circle, g2d);
                    g2d.setTransform(circle.worldToObject());
                    break;
                case LINE:
                    Line line = (Line)shape;
                    g2d.setTransform(line.objectToWorld());
                    drawLineHandles(line, g2d);
                    g2d.setTransform(line.worldToObject());
                    break;
                case TRIANGLE:
                    Triangle triangle = (Triangle)shape;

                    g2d.setTransform(triangle.objectToWorld());
                    Polygon tri = new Polygon(triangle.getXpointsObj(), triangle.getYpointsObj(), 3);
                    g2d.setPaint(Color.WHITE);
                    g2d.drawPolygon(tri);
                    drawTriangleHandle(triangle, g2d);
                    g2d.setTransform(triangle.worldToObject());
                    break;
                case ELLIPSE:
                    Ellipse ellipse = (Ellipse) shape;
                    double elleW = ellipse.getWidth();
                    double elleH = ellipse.getHeight();
                    g2d.setTransform(ellipse.objectToWorld());
                    Ellipse2D elli = new Ellipse2D.Double(- elleW/2,-elleH/2,elleW,elleH);
                    g2d.setPaint(Color.WHITE);
                    g2d.draw(elli);
                    drawHandle(ellipse, g2d);
                    g2d.setTransform(ellipse.worldToObject());
                    break;
                case SQUARE:
                    Square square = (Square)shape;
                    g2d.setTransform(square.objectToWorld());
                    Rectangle2D squa = new Rectangle2D.Double(-(square.getWidth()/2), -(square.getHeight()/2), square.getSize(), square.getSize());
                    g2d.setPaint(Color.WHITE);
                    g2d.draw(squa);
                    drawHandle(square, g2d);
                    g2d.setTransform(square.worldToObject());
                    break;
                default:
                    break;
            }
        }
    }

    private void drawLineHandles(Line line, Graphics2D g2d) {
        Point2D start = line.getStart();
        Point2D end = line.getEnd();

        g2d.setColor(Color.white);
        g2d.fillOval(-3, -3, 7, 7);
        g2d.fillOval((int)((end.getX()-start.getX()) - 3), (int) ((end.getY()-start.getY()) - 3) - 3, 7, 7);
        g2d.setColor(line.getColor());
    }

    private void drawTriangleHandle(Shape shape, Graphics2D g2d){
        Point2D center= shape.getCenter();
        Triangle triangle = (Triangle)shape;
        double heightFromcenter= shape.getHeight()/2;
        g2d.setColor(Color.white);

        // Rotation handle
        Point2D rotationHandlePoint = new Point2D.Double(0, (-heightFromcenter-17));
        g2d.fillOval((int) rotationHandlePoint.getX()-3, (int) rotationHandlePoint.getY()-3, 7, 7);

        int[] xs = triangle.getXpointsObj();
        int[] ys = triangle.getYpointsObj();
        g2d.fillOval(xs[0] - 3, ys[0] - 3, 7, 7);
        g2d.fillOval(xs[1]  - 3, ys[1] - 3, 7, 7);
        g2d.fillOval(xs[2] - 3, ys[2] - 3, 7, 7);
    }

    private void drawHandle(Shape shape, Graphics2D g2d){

        Point2D center= shape.getCenter();
        double heightFromcenter= shape.getHeight()/2;
        double widthFromcenter= shape.getWidth()/2;
        g2d.setColor(Color.white);

        if(shape.getState() != State.CIRCLE) {
            // Rotation handle
            Point2D rotationHandlePoint = new Point2D.Double(0, (-heightFromcenter - 17));
            g2d.fillOval((int) rotationHandlePoint.getX() - 3, (int) rotationHandlePoint.getY() - 3, 7, 7);
        }

        Point2D tRight = new Point2D.Double(-widthFromcenter, heightFromcenter);
        Point2D tLeft = new Point2D.Double(-widthFromcenter, -heightFromcenter);
        Point2D bRight = new Point2D.Double(widthFromcenter, heightFromcenter);
        Point2D bLeft = new Point2D.Double(widthFromcenter, -heightFromcenter);

        g2d.fillOval((int) tLeft.getX() - 3, (int) tLeft.getY() - 3, 7, 7);
        g2d.fillOval((int) tRight.getX() - 3, (int) tRight.getY()-3, 7, 7);
        g2d.fillOval((int) bLeft.getX()-3, (int) bLeft.getY()-3, 7, 7);
        g2d.fillOval((int) bRight.getX()-3, (int) bRight.getY()-3, 7, 7);

    }

    private void rotateShape(Graphics2D g2d,  Shape shape){
        AffineTransform objToWorld = shape.objectToWorld();

        Point2D center = new Point2D.Double(0, 0);

        objToWorld.transform(center, center);

        AffineTransform t = new AffineTransform();
        t.translate(center.getX(), center.getY());
        t.rotate(shape.getRotation());

        g2d.setTransform(t);
    }

    @Override
    public void update(Observable o, Object arg) {
        GUIFunctions.refresh();
    }

    private Point2D convertFROMcenter(Point2D c, double w, double h){

        return new Point2D.Double((c.getX()-(w/2)),(c.getY()-(h/2)));
    }
}

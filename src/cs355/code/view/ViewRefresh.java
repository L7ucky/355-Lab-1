package cs355.code.view;

import cs355.code.model.*;
import cs355.code.model.Rectangle;
import cs355.code.model.Shape;

import java.awt.*;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
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
                    double leftX = rectangle.getUpperLeftCorner().getX();
                    double topY = rectangle.getUpperLeftCorner().getY();
                    double width = rectangle.getWidth();
                    double height = rectangle.getHeight();

                    Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);
                    g2d.setPaint(rectangle.getColor());
                    g2d.fill(rect);
                    break;
                case CIRCLE:
                    Circle circle = (Circle) data.get(i);
                    double cirW = circle.getRadius()*2;
                    double cirH = circle.getRadius()*2;
                    Point upperLeft = convertFROMcenter(circle.getCenter(),cirW,cirH);
                    Ellipse2D circ = new Ellipse2D.Double(upperLeft.getX(),upperLeft.getY(),cirW,cirH);
                    g2d.setColor(circle.getColor());
                    g2d.fill(circ);
                    break;
                case LINE:
                    Line line = (Line)data.get(i);
                    Line2D li = new Line2D.Double(line.getStart(),line.getEnd());
                    g2d.setColor(line.getColor());
                    g2d.draw(li);
                    break;
                case TRIANGLE:
                    Triangle triangle = (Triangle)data.get(i);
                    Polygon tri = new Polygon(triangle.getXpoints(), triangle.getYpoints(), triangle.getXpoints().length);
                    g2d.setColor(triangle.getColor());
                    g2d.fillPolygon(tri);
                    break;
                case ELLIPSE:
                    Ellipse ellipse = (Ellipse) data.get(i);
                    double elleW = ellipse.getWidth();
                    double elleH = ellipse.getHeight();
                    Point elleUpperLeft = convertFROMcenter(ellipse.getCenter(),elleW,elleH);
                    Ellipse2D elli = new Ellipse2D.Double(elleUpperLeft.getX(),elleUpperLeft.getY(),elleW,elleH);
                    g2d.setColor(ellipse.getColor());
                    g2d.fill(elli);
                    break;
                case SQUARE:
                    Square square = (Square)data.get(i);
                    Rectangle2D squa = new Rectangle2D.Double(square.getUpperLeftCorner().getX(), square.getUpperLeftCorner().getY(), square.getSize(), square.getSize());
                    g2d.setPaint(square.getColor());
                    g2d.fill(squa);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        GUIFunctions.refresh();
    }

    private Point convertFROMcenter(Point c, double w, double h){

        return new Point((int)(c.getX()-(w/2)),(int)(c.getY()-(h/2)));
    }
}

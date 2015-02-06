package cs355.code.controller.actionClasses.handelTypes;

import cs355.code.model.Line;
import cs355.code.model.Shape;
import cs355.code.model.State;
import cs355.code.model.Triangle;

import java.awt.geom.Point2D;

/**
 * Created by Andrew on 2/1/2015.
 */
public class Default implements Handle {
    //This move the shape's center
    @Override
    public Shape handleAction(Point2D newPoint, Point2D previousPoint, Shape selected) {

        double moveX = newPoint.getX()-previousPoint.getX();
        double moveY = newPoint.getY()-previousPoint.getY();

        if(selected.getState() == State.LINE){
            Line line = (Line)selected;
            line.setStart(new Point2D.Double((line.getStart().getX() + moveX), (line.getStart().getY() + moveY)));
            line.setEnd(new Point2D.Double((line.getEnd().getX() + moveX), (line.getEnd().getY() + moveY)));
            return selected;
        }
        else{
            selected.setCenter(new Point2D.Double((selected.getCenter().getX() + moveX), (selected.getCenter().getY() + moveY)));
            return selected;
        }

    }
}

package cs355.code.controller.actionClasses.handelTypes;

import cs355.code.model.Shape;

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

        selected.setCenter(new Point2D.Double((selected.getCenter().getX() + moveX), (selected.getCenter().getY() + moveY)));
        return selected;
    }
}

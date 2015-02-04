package cs355.code.controller.actionClasses.handelTypes;

import cs355.code.model.Shape;

import java.awt.geom.Point2D;

/**
 * Created by Andrew on 2/1/2015.
 */
public class Rotate implements Handle {

    @Override
    public Shape handleAction(Point2D newPoint, Point2D previousPoint, Shape selected) {

        Point2D center = selected.getCenter();
        double dx = newPoint.getX() - center.getX();
        double dy = newPoint.getY() - center.getY();
        double angle = Math.atan2(dx, dy) + Math.PI;
        selected.setRotation(-angle);
        return selected;
    }
}

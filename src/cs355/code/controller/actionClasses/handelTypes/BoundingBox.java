package cs355.code.controller.actionClasses.handelTypes;

import cs355.code.model.Shape;
import cs355.code.model.State;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by Andrew on 2/21/2015.
 */
public class BoundingBox implements Handle {
    @Override
    public Shape handleAction(Point2D newPoint, Point2D previousPoint, Shape selected) {
        Point2D center = selected.getCenter();

        AffineTransform worldToObj = new AffineTransform();
        worldToObj.rotate(-1 * selected.getRotation());
        worldToObj.translate(-1 * center.getX(), -1 * center.getY());

        Point2D newCorner = new Point2D.Double();
        worldToObj.transform(newPoint, newCorner);

        int polarityX = 1;
        int polarityY = 1;
        if (selected.getState() == State.SQUARE || selected.getState() == State.CIRCLE) {
            if(newCorner.getX() < 0)
              polarityX = -1;
            if(newCorner.getY()< 0)
                polarityY = -1;
            double min = Math.min(Math.abs(newCorner.getX()), Math.abs(newCorner.getY()));
            newCorner.setLocation(polarityX * min, polarityY * min);
        }

        if(newCorner.getX() < 0)
            polarityX = -1;
        if(newCorner.getY()< 0)
            polarityY = -1;

        AffineTransform unRotate = new AffineTransform();
        unRotate.rotate(selected.getRotation());

        Point2D oppositeCorner = new Point2D.Double(-polarityX * selected.getWidth()/2, -polarityY * selected.getHeight()/2);
        Point2D centerOffset = new Point2D.Double(
                (newCorner.getX() + oppositeCorner.getX())/2,
                (newCorner.getY() + oppositeCorner.getY())/2
        );
        unRotate.transform(centerOffset, centerOffset);
        selected.setCenter(
                new Point2D.Double(
                center.getX() + centerOffset.getX(),
                center.getY() + centerOffset.getY()
                )
        );
        selected.setWidth(Math.abs(oppositeCorner.getX() - newCorner.getX()));
        selected.setHeight(Math.abs(oppositeCorner.getY() - newCorner.getY()));


        return selected;
    }
}

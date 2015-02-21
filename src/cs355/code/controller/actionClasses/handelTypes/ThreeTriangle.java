package cs355.code.controller.actionClasses.handelTypes;

import cs355.code.model.Shape;
import cs355.code.model.Triangle;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by Andrew on 2/21/2015.
 */
public class ThreeTriangle implements Handle {
    @Override
    public Shape handleAction(Point2D newPoint, Point2D previousPoint, Shape selected) {
        Triangle t  = (Triangle) selected;
        AffineTransform worldToObj = selected.worldToObject();
        worldToObj.transform(newPoint, newPoint);
        t.setThree(newPoint);
        return t;
    }
}

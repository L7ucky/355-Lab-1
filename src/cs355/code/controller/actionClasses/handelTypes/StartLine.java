package cs355.code.controller.actionClasses.handelTypes;

import cs355.code.model.Line;
import cs355.code.model.Shape;

import java.awt.geom.Point2D;

/**
 * Created by Andrew on 2/21/2015.
 */
public class StartLine implements Handle {
    @Override
    public Shape handleAction(Point2D newPoint, Point2D previousPoint, Shape selected) {
        Line line = (Line)selected;
        line.setStart(newPoint);
        return selected;
    }
}

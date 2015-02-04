package cs355.code.controller.actionClasses.handelTypes;

import cs355.code.model.Shape;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Andrew on 2/1/2015.
 */
public interface Handle {
    public Shape handleAction(Point2D newPoint, Point2D previousPoint, Shape selected);
}

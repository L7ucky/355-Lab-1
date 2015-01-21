package cs355.code.controller.actionClasses;

import cs355.code.model.Circle;
import cs355.code.model.Shape;
import cs355.code.model.Triangle;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Andrew on 1/11/2015.
 */
public class TriangleController implements StateAction  {

    @Override
    public Shape run(MouseEvent e, Boolean dragging) {
        return new Triangle(new Point(0,0),new Point(0,0), new Point(0,0));
    }
}

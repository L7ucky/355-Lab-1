package cs355.code.controller.actionClasses;

import cs355.code.controller.Controller;
import cs355.code.model.Circle;
import cs355.code.model.Line;
import cs355.code.model.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Andrew on 1/11/2015.
 */
public class LineController implements StateAction  {

    Controller controller;

    public LineController(Controller cont){
        this.controller = cont;
    }

    @Override
    public Shape run(MouseEvent e, Boolean dragging) {
        return new Line(controller.getOriginalClick(),e.getPoint());
    }
}

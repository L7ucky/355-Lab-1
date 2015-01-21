package cs355.code.controller.actionClasses;

import cs355.code.model.Shape;

import java.awt.event.MouseEvent;

/**
 * Created by Andrew on 1/11/2015.
 */
public interface StateAction {

    public Shape run(MouseEvent e, Boolean dragging);
}

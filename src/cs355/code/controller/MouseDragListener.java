package cs355.code.controller;

import cs355.code.model.DataModel;
import cs355.code.model.Shape;
import cs355.code.model.State;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by Andrew on 1/10/2015.
 */
public class MouseDragListener implements MouseMotionListener {
    Controller controller;

    public MouseDragListener(Controller cont){
        this.controller = cont;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(controller.getShapeState() == State.TRIANGLE){

        }
        else{
            int index = ((DataModel.getInstance().getData().size()) - 1);
            Shape shape = controller.getCurrentState().run(e, true);

            DataModel.getInstance().updateShape(index,shape);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

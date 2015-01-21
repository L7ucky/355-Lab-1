package cs355.code.controller;

import cs355.code.controller.actionClasses.StateAction;
import cs355.code.controller.actionClasses.TriangleController;
import cs355.code.model.DataModel;
import cs355.code.model.Shape;
import cs355.code.model.State;
import cs355.code.model.Triangle;
import sun.security.x509.CRLDistributionPointsExtension;

import javax.management.StandardEmitterMBean;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

/**
 * Created by Andrew on 1/10/2015.
 */
public class ProjectMouseListener implements MouseListener {
    int clickCount =0;
    Point[] points =  new Point[3];
    Controller controller;

    public ProjectMouseListener(Controller cont){
        this.controller = cont;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(controller.getShapeState() == State.TRIANGLE){
            if(clickCount<=2){
                points[clickCount] = e.getPoint();
                if(clickCount==2){
                    clickCount=0;
                    DataModel.getInstance().addShape(new Triangle(points[0],points[1],points[2]));
                    Arrays.fill(points,null);
                }
                else
                    clickCount++;
            }
        }
        else{
            addShape(e);
            controller.setActive(false);
        }
    }

    private void addShape(MouseEvent event){

        if(controller.getShapeState() == State.TRIANGLE){

        }
        else{
            Shape shape = controller.getCurrentState().run(event, controller.isActive());
            DataModel.getInstance().addShape(shape);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(controller.getShapeState() == State.TRIANGLE){

        }
        else{
            controller.setOriginalClick(e.getPoint());
            controller.setActive(true);
            addShape(e);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(controller.getShapeState() == State.TRIANGLE){

        }
        else{
            Shape shape = controller.getCurrentState().run(e, controller.isActive());
            int index = ((DataModel.getInstance().getData().size())-1);
            DataModel.getInstance().updateShape(index, shape);

            controller.setActive(false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

package cs355.code.controller.actionClasses;

import cs355.code.controller.Controller;
import cs355.code.model.Circle;
import cs355.code.model.DataModel;
import cs355.code.model.Rectangle;
import cs355.code.model.Shape;

import javax.swing.tree.FixedHeightLayoutCache;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Andrew on 1/11/2015.
 */
public class RectangleController implements StateAction  {

    Controller controller = null;

    public RectangleController(Controller controller){
        this.controller = controller;
    }



    @Override
    public Shape run(MouseEvent e, Boolean dragging) {
        Point newUpperLeft = new Point(0,0);
        double newWidth = 0;
        double newHeight = 0;

        if(dragging){
            Point original = controller.getOriginalClick();
            if(e.getX() >= original.getX()){
                if(e.getY() > original.getY()){ //--------------------- FOURTH QUADRANT
                    newUpperLeft = original;
                    newWidth = Math.abs(e.getX()-original.getX());
                    newHeight = Math.abs(e.getY()-original.getY());
                }
                else{//------------------------------------------------ FIRST QUADRANT
                    newUpperLeft = new Point((int)original.getX(),e.getY());
                    newWidth = Math.abs(e.getX()-original.getX());
                    newHeight =  Math.abs(original.getY()-e.getY());
                }
            }
            else{
                if(e.getY() <= original.getY()) {//-------------------- SECOND QUADRANT
                    newUpperLeft = new Point(e.getX(),e.getY());
                    newWidth = Math.abs(original.getX() - e.getX());
                    newHeight = Math.abs(original.getY()-e.getY());
                }
                else{//------------------------------------------------ THIRD QUADRANT
                    newUpperLeft = new Point(e.getX(),(int)original.getY());
                    newWidth = Math.abs(original.getX() - e.getX());
                    newHeight =  Math.abs(e.getY()-original.getY());
                }
            }
        }
        else{

        }
        return new Rectangle(newUpperLeft,newWidth,newHeight);
    }
}

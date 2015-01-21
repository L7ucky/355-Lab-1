package cs355.code.controller.actionClasses;

import cs355.code.controller.Controller;
import cs355.code.model.Circle;
import cs355.code.model.Shape;
import cs355.code.model.Square;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Andrew on 1/11/2015.
 */
public class SquareController implements StateAction {

    Controller controller = null;

    public SquareController(Controller cont) {
        this.controller = cont;
    }

    @Override
    public Shape run(MouseEvent e, Boolean dragging) {
        Point newUpperLeft = new Point(0, 0);
        double newWidth = 0;
        double newHeight = 0;
        double size =0;

        if (dragging) {

            Point original = controller.getOriginalClick();
            if (e.getX() >= original.getX()) {
                if (e.getY() > original.getY()) { //--------------------- FOURTH QUADRANT
                    newWidth = Math.abs(e.getX() - original.getX());
                    newHeight = Math.abs(e.getY() - original.getY());
                    size = calculateSize(newWidth,newHeight);
                    newUpperLeft = original;
                } else {//------------------------------------------------ FIRST QUADRANT

                    newWidth = Math.abs(e.getX() - original.getX());
                    newHeight = Math.abs(original.getY() - e.getY());
                    size = calculateSize(newWidth,newHeight);
                    newUpperLeft = new Point((int) original.getX(), (int)original.getY()-(int)size);
                }
            } else {
                if (e.getY() <= original.getY()) {//-------------------- SECOND QUADRANT

                    newWidth = Math.abs(original.getX() - e.getX());
                    newHeight = Math.abs(original.getY() - e.getY());
                    size = calculateSize(newWidth,newHeight);
                    newUpperLeft = new Point((int)original.getX()-(int)size,(int)original.getY()-(int)size);
                } else {//------------------------------------------------ THIRD QUADRANT

                    newWidth = Math.abs(original.getX() - e.getX());
                    newHeight = Math.abs(e.getY() - original.getY());
                    size = calculateSize(newWidth,newHeight);
                    newUpperLeft = new Point((int)original.getX()-(int)size, (int) original.getY());
                }
            }
        } else {

        }
        return new Square(newUpperLeft, size);
    }
    private double calculateSize(double width, double height){
        if (width >= height)
            return height;
        else
            return width;
    }
}

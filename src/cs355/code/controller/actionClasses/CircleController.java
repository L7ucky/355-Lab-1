package cs355.code.controller.actionClasses;

import cs355.code.controller.Controller;
import cs355.code.model.Circle;
import cs355.code.model.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Andrew on 1/11/2015.
 */
public class CircleController implements StateAction {

    Controller controller;

    public CircleController(Controller cont){
        this.controller = cont;
    }

    @Override
    public Shape run(MouseEvent e, Boolean dragging) {
        Point newUpperLeft = new Point(0, 0);
        double newWidth = 0;
        double newHeight = 0;
        double diameter =0;

        if (dragging) {

            Point original = controller.getOriginalClick();
            if (e.getX() >= original.getX()) {
                if (e.getY() > original.getY()) { //--------------------- FOURTH QUADRANT
                    newWidth = Math.abs(e.getX() - original.getX());
                    newHeight = Math.abs(e.getY() - original.getY());
                    diameter = calculateSize(newWidth,newHeight);
                    newUpperLeft = original;
                } else {//------------------------------------------------ FIRST QUADRANT

                    newWidth = Math.abs(e.getX() - original.getX());
                    newHeight = Math.abs(original.getY() - e.getY());
                    diameter = calculateSize(newWidth,newHeight);
                    newUpperLeft = new Point((int) original.getX(), (int)original.getY()-(int)diameter);
                }
            } else {
                if (e.getY() <= original.getY()) {//-------------------- SECOND QUADRANT

                    newWidth = Math.abs(original.getX() - e.getX());
                    newHeight = Math.abs(original.getY() - e.getY());
                    diameter = calculateSize(newWidth,newHeight);
                    newUpperLeft = new Point((int)original.getX()-(int)diameter,(int)original.getY()-(int)diameter);
                } else {//------------------------------------------------ THIRD QUADRANT

                    newWidth = Math.abs(original.getX() - e.getX());
                    newHeight = Math.abs(e.getY() - original.getY());
                    diameter = calculateSize(newWidth,newHeight);
                    newUpperLeft = new Point((int)original.getX()-(int)diameter, (int) original.getY());
                }
            }
        } else {

        }
        return new Circle(convertTOcenter(newUpperLeft, diameter,diameter),diameter/2);
    }
    private double calculateSize(double width, double height){
        if (width >= height)
            return height;
        else
            return width;
    }

    public Point convertTOcenter(Point UL, double W, double H){
        double newW =W/2;
        double newH = H/2;

        return new Point((int)(UL.getX()+newW),(int)(UL.getY()+newH));
    }
}

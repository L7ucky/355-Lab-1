package cs355.code.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import cs355.code.controller.actionClasses.*;
import cs355.code.model.*;
import cs355.code.view.GUIFunctions;
import cs355.code.model.Shape;

import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.security.PrivateKey;
import java.util.Iterator;

/**
 * Created by Andrew on 1/10/2015.
 */
public class Controller implements CS355Controller {

    private DataModel data;
    private MouseEventHandler currentState = new SelectionController(this) ;
    private State shapeState = State.LINE;
    private boolean isActive = false;
    private Point2D originalClick = null;
    private int currentShapeIndex = 0;

    private MouseListener mouseListener;
    private MouseMotionListener mouseDragListener;

    public Controller(DataModel data){
        this.data = data;

        createMouseListener();
        createMouseDraggedListener();
    }

    @Override
    public void colorButtonHit(Color c) {
        data.setCurrentColor(c);
        GUIFunctions.changeSelectedColor(c);

            //There will bea null pointer exception when there is no color selected
            if(data.getSelected()!=null){
                data.getSelected().setColor(c);
                data.updateShape(data.getSelectedIndex(),data.getSelected());
            }

        data.refresh();
    }

    @Override
    public void triangleButtonHit() {
        setCurrentState(new TriangleController(this));
        shapeState = State.TRIANGLE;
        data.setSelected(null,-1);
        data.refresh();
    }

    @Override
    public void squareButtonHit() {
        setCurrentState( new SquareController(this));
        shapeState = State.SQUARE;
        data.setSelected(null,-1);
        data.refresh();
    }

    @Override
    public void rectangleButtonHit() {
        setCurrentState(new RectangleController(this));
        shapeState = State.RECTANGLE;
        data.setSelected(null,-1);
        data.refresh();
    }

    @Override
    public void circleButtonHit() {
        setCurrentState(new CircleController(this));
        shapeState = State.CIRCLE;
        data.setSelected(null,-1);
        data.refresh();
    }

    @Override
    public void ellipseButtonHit() {
        setCurrentState(new EllipseController(this));
        shapeState = State.ELLIPSE;
        data.setSelected(null,-1);
        data.refresh();
    }

    @Override
    public void lineButtonHit() {
        setCurrentState(new LineController(this));
        shapeState = State.LINE;
        data.setSelected(null,-1);
        data.refresh();
    }

    @Override
    public void selectButtonHit() {
        setCurrentState(new SelectionController(this));
    }

    @Override
    public void zoomInButtonHit() {

    }

    @Override
    public void zoomOutButtonHit() {

    }

    @Override
    public void hScrollbarChanged(int value) {

    }

    @Override
    public void vScrollbarChanged(int value) {

    }

    @Override
    public void toggle3DModelDisplay() {

    }

    @Override
    public void keyPressed(Iterator<Integer> iterator) {

    }

    @Override
    public void doEdgeDetection() {

    }

    @Override
    public void doSharpen() {

    }

    @Override
    public void doMedianBlur() {

    }

    @Override
    public void doUniformBlur() {

    }

    @Override
    public void doChangeContrast(int contrastAmountNum) {

    }

    @Override
    public void doChangeBrightness(int brightnessAmountNum) {

    }

    @Override
    public void doLoadImage(BufferedImage openImage) {

    }

    @Override
    public void toggleBackgroundDisplay() {

    }
    //Handle Mouse events

    private void createMouseListener() {
        mouseListener = new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                setOriginalClick(e.getPoint());
                Point2D p = new Point2D.Double();
                p.setLocation(e.getPoint());
                try{
                    Shape shape = currentState.mouseDown(p);
                    DataModel.getInstance().addShape(shape);
                }catch(NullPointerException exception){
                    System.out.println("null pointer in mouse pressed");
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Point2D p = new Point2D.Double();
                p.setLocation(e.getPoint());
                currentState.mouseUp(p);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Point2D p = new Point2D.Double();
                p.setLocation(e.getPoint());
                try{
                    Shape shape = currentState.mouseClicked(p);
                    DataModel.getInstance().addShape(shape);

                }catch(NullPointerException ex){
                    System.out.println("null pointer at mouse clicked");
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
    }

    private void createMouseDraggedListener() {
        mouseDragListener = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentShapeIndex = ((DataModel.getInstance().getData().size()) - 1);
                Point2D p = new Point2D.Double();
                p.setLocation(e.getPoint());
                try{
                    Shape shape = currentState.mouseDragged(p);
                    if(DataModel.getInstance().getSelected()!= null)
                        currentShapeIndex = DataModel.getInstance().getSelectedIndex();
                    DataModel.getInstance().updateShape(currentShapeIndex,shape);
                }catch(NullPointerException ex){

                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point2D p = new Point2D.Double();
                p.setLocation(e.getPoint());
                try{
                    currentState.mouseMoved(p);

                }catch(NullPointerException exception){
                }
            }
        };
    }

    //Getters and Setters --------------------------------------------------------

    public MouseEventHandler getCurrentState() {
        return currentState;
    }
    public void setCurrentState(MouseEventHandler currentState) {
        if (this.currentState != null) {
            this.currentState.reset();
        }
        this.currentState = currentState;
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Point2D getOriginalClick() {
        return originalClick;
    }
    public void setOriginalClick(Point2D originalClick) {
        this.originalClick = originalClick;
    }

    public State getShapeState() {
        return shapeState;
    }
    public void setShapeState(State shapeState) {
        this.shapeState = shapeState;
    }

    public int getCurrentShapeIndex() {
        return currentShapeIndex;
    }
    public void setCurrentShapeIndex(int currentShapeIndex) {
        this.currentShapeIndex = currentShapeIndex;
    }

    public MouseListener getMouseListener() {
        return mouseListener;
    }
    public void setMouseListener(MouseListener mouseListener) {
        this.mouseListener = mouseListener;
    }

    public MouseMotionListener getMouseDragListener() {
        return mouseDragListener;
    }
    public void setMouseDragListener(MouseMotionListener mouseDragListener) {
        this.mouseDragListener = mouseDragListener;
    }
}

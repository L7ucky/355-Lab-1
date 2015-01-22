package cs355.code.controller;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import cs355.code.controller.actionClasses.*;
import cs355.code.model.DataModel;
import cs355.code.model.State;
import cs355.code.view.GUIFunctions;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.PrivateKey;
import java.util.Iterator;

/**
 * Created by Andrew on 1/10/2015.
 */
public class Controller implements CS355Controller {

    private DataModel data;
    private StateAction currentState = new LineController(this) ;
    private State shapeState = State.LINE;
    private boolean isActive = false;
    private Point originalClick = null;

    public Controller(DataModel data){
        this.data = data;
    }

    @Override
    public void colorButtonHit(Color c) {
        data.setCurrentColor(c);
        GUIFunctions.changeSelectedColor(c);
    }

    @Override
    public void triangleButtonHit() {
        currentState =  new TriangleController();
        shapeState = State.TRIANGLE;
    }

    @Override
    public void squareButtonHit() {
        currentState = new SquareController(this);
        shapeState = State.SQUARE;
    }

    @Override
    public void rectangleButtonHit() {
        currentState = new RectangleController(this);
        shapeState = State.RECTANGLE;
    }

    @Override
    public void circleButtonHit() {
        currentState = new CircleController(this);
        shapeState = State.CIRCLE;
    }

    @Override
    public void ellipseButtonHit() {
        currentState = new EllipseController(this);
        shapeState = State.ELLIPSE;
    }

    @Override
    public void lineButtonHit() {
        currentState = new LineController(this);
        shapeState = State.LINE;
    }

    @Override
    public void selectButtonHit() {
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

    //Getters and Setters --------------------------------------------------------

    public StateAction getCurrentState() {
        return currentState;
    }
    public void setCurrentState(StateAction currentState) {
        this.currentState = currentState;
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Point getOriginalClick() {
        return originalClick;
    }
    public void setOriginalClick(Point originalClick) {
        this.originalClick = originalClick;
    }

    public State getShapeState() {
        return shapeState;
    }
    public void setShapeState(State shapeState) {
        this.shapeState = shapeState;
    }
}

package cs355.code.model;


import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Andrew on 1/10/2015.
 */
public class DataModel extends Observable {

    ArrayList<Shape> data = new ArrayList<Shape>();
    State currentState = null;
    Color currentColor = new Color(255,255,0);//Yellow

    private Shape selected;
    private int selectedIndex = -1;

    public int selectVisibleShape(Point2D p) {
        for (int i = data.size()-1;i>=0; i--) {
            AffineTransform worldToObj = data.get(i).worldToObject();
            Point2D objCoordinates = new Point2D.Double();
            worldToObj.transform(p, objCoordinates);

            if (data.get(i).contains(objCoordinates)) {
                return i;
            }
        }

        return -1;
    }

    //Implementing a singleton pattern-----------------------------------
    private static DataModel instance = null;
    protected DataModel() {
        // Exists only to defeat instantiation.
    }
    public static DataModel getInstance() {
        if(instance == null) {
            instance = new DataModel();
        }
        return instance;
    }



    //Functions-----------------------------------------------------------
    public void addShape(Shape shape){
        shape.setColor(currentColor);
        data.add(shape);
        setChanged();
        notifyObservers();
    }
    public void updateShape(int index, Shape shape){
        shape.setColor(currentColor);
        data.set(index, shape);
        setChanged();
        notifyObservers();
    }

    public void refresh(){
        setChanged();
        notifyObservers();
    }

    // Getters and Setters-------------------------------------------------
    public ArrayList<Shape> getData() {
        return data;
    }
    public void setData(ArrayList<Shape> data) {
        this.data = data;
    }

    public State getCurrentState() {
        return currentState;
    }
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public Color getCurrentColor() {
        return currentColor;
    }
    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public Shape getSelected() {
        return selected;
    }
    public void setSelected(Shape selected, int index) {
        this.selected = selected;
        this.selectedIndex = index;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }
    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public void printDataSet(){
        System.out.println("Size is : "+data.size());
        for (int i = 0; i<data.size();i++){
            System.out.println(data.get(i).getState().toString());
        }
    }
}

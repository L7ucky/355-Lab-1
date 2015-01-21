package cs355.code.model;


import javax.swing.plaf.nimbus.State;
import java.awt.*;
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


}

package cs355.code.model;

/**
 * Created by Andrew on 1/11/2015.
 */
public enum State {
    CIRCLE, SQUARE, RECTANGLE, ELLIPSE, LINE,  TRIANGLE;

    @Override
    public String toString() {
        String name = "";
        switch (ordinal()) {
            case 0:
                name = "Circle";
                break;
            case 1:
                name = "Square";
                break;
            case 2:
                name = "Rectangle";
                break;
            case 3:
                name = "Ellipse";
                break;
            case 4:
                name = "Line";
                break;
            case 5:
                name = "Triangle";
                break;
            default:
                name = "";
                break;
        }
        return name;
    }

    public static State getState(String name){
        State type;
        String t = name.toLowerCase();
        if (t.equals("Circle")) {
            type = State.CIRCLE;

        } else if (t.equals("Square")) {
            type = State.SQUARE;

        } else if (t.equals("Rectangle")) {
            type = State.RECTANGLE;

        } else if (t.equals("Ellipse")) {
            type = State.ELLIPSE;

        } else if (t.equals("Line")) {
            type = State.LINE;

        }else if (t.equals("Triangle")) {
            type = State.TRIANGLE;
        }
        else {
            type = null;
        }

        return type;
    }

}

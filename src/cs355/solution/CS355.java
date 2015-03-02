/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355.solution;

import cs355.code.controller.Controller;
import cs355.code.model.DataModel;
import cs355.code.view.GUIFunctions;
import cs355.code.view.ViewRefresh;

/**
 *
 * @author [Andrew Hyte]
 */
public class CS355 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
    	// Fill in the parameters below with your controller, view, 
    	//   mouse listener, and mouse motion listener

        ViewRefresh refreshView = new ViewRefresh();
        Controller controller = new Controller(DataModel.getInstance(), refreshView);
        GUIFunctions.createCS355Frame(controller,refreshView,controller.getMouseListener(),controller.getMouseDragListener());
        GUIFunctions.changeSelectedColor(DataModel.getInstance().getCurrentColor());
        
        GUIFunctions.refresh();        
    }
}
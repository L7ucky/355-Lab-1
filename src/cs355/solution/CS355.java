/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355.solution;

import cs355.code.controller.Controller;
import cs355.code.view.GUIFunctions;

/**
 *
 * @author [your name here]
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
        Controller controller = new Controller();
        GUIFunctions.createCS355Frame(controller,null,null,null);
        
        GUIFunctions.refresh();        
    }
}
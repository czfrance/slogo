package slogo.View;

import javafx.scene.layout.BorderPane;

/**
 * DashboardView class that handles the dashboard where users can interact with the display using buttons
 *
 * @author Brandon Bae, Thivya Sivarajah
 */
public interface DashboardView {

    /**
     * Updates the displayed variable in the dashboard
     */
//    public void updateVariableDisplay(SketchbookView sketch);


    public BorderPane updateVariableDisplay(SketchbookView sketch);

}


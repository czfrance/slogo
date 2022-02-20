package slogo.View;

/**
 * DashboardView class that handles the dashboard where users can interact with the display using buttons
 *
 * @author Thivya Sivarajah
 */
public interface DashboardView {

    /**
     * Updates the displayed variable in the dashboard
     */
    public void updateVariableDisplay();


    /**
     * Generalized Button Action Events for each button that will communicate with the DashboardModel to update backend values
     */
    public void buttonActionEvents();
}


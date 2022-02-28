package slogo.Console;

import javafx.scene.control.Alert;

public class ConsoleAlerts {
  public ConsoleAlerts(String str) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(str);
    alert.showAndWait();
  }
}

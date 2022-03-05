package slogo.Console.Views.InteractiveNodes;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ResourceBundle;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import slogo.Console.ConsoleAlerts;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleInsnModel;

public class InteractiveBasic extends InteractiveNode {

  private String title;
  private Instruction myInstruction;

  public InteractiveBasic(String title, Instruction instruction, TurtleInsnModel model
      ,TurtleCollection turtles, ResourceBundle language) {
    super(title, model, turtles, language);
  }

  @Override
  public void hoverToolTip() {
    String message = null;
    try {
      message = "";
      URL website = new URL(
          "https://courses.cs.duke.edu/compsci308/spring22/assign/03_parser/reference/" + title);
      BufferedReader in = new BufferedReader(new InputStreamReader(website.openStream()));
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        message += inputLine + "\n";
      }
      in.close();
    } catch (Exception e) {
      ConsoleAlerts alert = new ConsoleAlerts("Description not available at this time");
    }
    if (message != null) {
      myLabel.setTooltip(new Tooltip(message));
      myLabel.getTooltip().setShowDelay(Duration.seconds(0.5));
    }

  }
  @Override
  public void popup() {
    InstructionGUI myGui = new InstructionGUI(title, myInstruction, myModel, myTurtles);
  }

  @Override
  public void update() {
    return;
  }
}

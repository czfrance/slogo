package slogo.Console.Views.InteractiveNodes;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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

  private Instruction myInstruction;
  private ResourceBundle instructionTypes = ResourceBundle.getBundle("slogo/languages/InstructionType");

  public InteractiveBasic(String title, TurtleInsnModel model
      ,TurtleCollection turtles, ResourceBundle language) {
    super(title, model, turtles, language);
    hoverToolTip();
  }

  @Override
  public void hoverToolTip() {
    String message = null;

    try {
      var website = new URL(
          "https://courses.cs.duke.edu/compsci308/spring22/assign/03_parser/reference/and");

      var br = new BufferedReader(new InputStreamReader(website.openStream()));
      String line;
      var sb = new StringBuilder();
      while ((line = br.readLine()) != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
      }
      br.close();
    } catch (Exception e) {
      ConsoleAlerts alert = new ConsoleAlerts("Description not available at this time");
    }

    if (message != null) {
      System.out.println(message);
      myLabel.setTooltip(new Tooltip(message));
      myLabel.getTooltip().setShowDelay(Duration.seconds(0.5));
    }
  }
  @Override
  public void popup() {
    if(instructionTypes.getString(super.name).equals("TurtleCommands")){
      InstructionGUI myGui = new InstructionGUI(super.name, myModel, myTurtles);
    }
  }

  @Override
  public void update() {
    return;
  }
}

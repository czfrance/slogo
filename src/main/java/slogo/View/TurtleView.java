package slogo.View;

import java.awt.Dimension;

import javafx.scene.Node;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * View part of turtle that handles UI elements of the turtle and what the user sees
 * @author Thivya Sivarajah
 */


public abstract class TurtleView extends ImageView {

  public static final String RESOURCE_PATH = "/";
  public static final String IMAGE_EXTENSION = ".png";
  public static final Dimension TURTLE_DEFAULT_SIZE = new Dimension(25, 25);
  public static final double ColorZlocation = 1000;

  private Lighting lighting = new Lighting();
  private Light.Point turtleColor = new Light.Point();
  private Color myColor;


  public TurtleView(double x, double y, double heading, String imageName, Color color) {
    super(new Image(RESOURCE_PATH + imageName + IMAGE_EXTENSION));
    myColor = color;
    prepColor(x, y, color);
    setSize();
    setLocation(x, y);
    setRotate(360-heading);
  }

  public void updateTurtle(double x, double y, double heading, Color color) {
    setColor(x, y, color);
    setLocation(x, y);
    setRotate(360-heading);
  }

  private void prepColor(double x, double y, Color color) {
    setColor(x, y, color);
    lighting.setLight(turtleColor);
    setEffect(lighting);
  }

  private void setColor(double x, double y, Color color) {
    turtleColor.setX(x);
    turtleColor.setY(y);
    turtleColor.setZ(ColorZlocation);
    turtleColor.setColor(color);
  }

  private void setLocation(double x, double y) {
    double[] location = calcCenter(x, y);
    setX(location[0]);
    setY(location[1]);
  }

  private double[] calcCenter(double x, double y) {
    //return new double[] {x, y};
    double centerX = getFitWidth() / 2;
    double centerY = getFitHeight() / 2;
    return new double[] {x - centerX, y - centerY};
  }

  private void setSize() {
    setFitWidth(TURTLE_DEFAULT_SIZE.width);
    setFitHeight(TURTLE_DEFAULT_SIZE.height);
  }

  public abstract void updateTurtleView();

  public Color getColor() {
    return myColor;
  }

}


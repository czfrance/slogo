package slogo.View.Pen;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public abstract class Pen {
  protected Color penColor;

  public Pen(Color color) {
    penColor = color;
  }

  public abstract Shape draw(double oldX, double oldY, double newX, double newY);

//  public Shape draw(double oldX, double oldY, double newX, double newY) {
//    Line line = new Line(oldX, oldY, newX, newY);
//    line.setFill(penColor);
//    line.setStroke(penColor);
//    line.setStrokeWidth(2);
//    return line;
//  }
}

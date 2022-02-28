package slogo.View.Pen;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class LinePen extends Pen {

  public LinePen(Color color) {
    super(color);
  }

  @Override
  public Shape draw(double oldX, double oldY, double newX, double newY) {
    Line line = new Line(oldX, oldY, newX, newY);
    line.setFill(penColor);
    line.setStroke(penColor);
    line.setStrokeWidth(2);
    return line;
  }

}

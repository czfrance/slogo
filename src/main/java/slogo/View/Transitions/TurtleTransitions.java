package slogo.View.Transitions;

import javafx.animation.SequentialTransition;
import javafx.animation.Transition;

public class TurtleTransitions {
  SequentialTransition turtleTransition;

  public TurtleTransitions() {
    turtleTransition = new SequentialTransition();
  }

  public void addTransition(Transition t) {
    turtleTransition.getChildren().add(t);
  }
}

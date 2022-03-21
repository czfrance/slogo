package slogo.View;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import slogo.InstructionClasses.TurtleCommands.Home;
import slogo.Model.TurtleInsnModel;
import slogo.Model.TurtleModel;
import slogo.SlogoView;
import slogo.View.Pen.Pen;
import slogo.View.Transitions.Paths.DoNothingPath;
import slogo.View.Transitions.Paths.TurtlePath;
import slogo.View.Transitions.Rotations.TurtleRotation;

public class SketchbookView extends Region {

<<<<<<< HEAD
  public static final Dimension DEFAULT_SIZE = new Dimension(800, 800);
=======
  public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
>>>>>>> master
  public static int TURTLE_SPEED = 50; //pixels per second
  public static String DEFAULT_IMAGE = "turtle";
  public static Color DEFAULT_COLOR = Color.RED;

  Pane myPane;
  Map<TurtleModel, TurtleView> myTurtlesMap;
  private SimulationDisplay mySimulation;
  private BorderPane myRoot;
  private TurtleInsnModel myInsnModel;
  private Group root;
  private boolean isAnimated;


  public SketchbookView(TurtleInsnModel insnModel) {
    myInsnModel = insnModel;
    myTurtlesMap = createTurtleMap();
  }

<<<<<<< HEAD
  public Scene makeScene(BorderPane myFeatures) {
=======
  public Group makeScene() {
>>>>>>> master
    root = new Group();
    addTurtlesToRoot();
    return root;
  }

  private void clearScene() {
    root.getChildren().clear();
    addTurtlesToRoot();
  }

  private Map<TurtleModel, TurtleView> createTurtleMap() {
    Map<TurtleModel, TurtleView> turtlesMap = new HashMap<>();
    Map<Integer, TurtleModel> turtleModelsMap = myInsnModel.getCreatedTurtleMap();
    for (int i : turtleModelsMap.keySet()) {
      TurtleView newTurtle = makeTurtle(turtleModelsMap.get(i));
      turtlesMap.put(turtleModelsMap.get(i), newTurtle);
    }
    return turtlesMap;
  }


  // CHANGE TO TURTLEDISPLAY INSTEAD OF TURTLEVIEW
  private TurtleView makeTurtle(TurtleModel m) {
    return new TurtleView(convertX(m.getNextPos()[0]), convertY(m.getNextPos()[1]),
            m.getHeading(), "turtle", Color.RED) {
      @Override
      public void updateTurtle(double x, double y, double heading, Color color) {

        private void addTurtlesToRoot () {
          for (TurtleModel m : myTurtlesMap.keySet()) {
            root.getChildren().add(myTurtlesMap.get(m));
          }
        }

<<<<<<< HEAD
        private void updateTurtleMap() {
          Map<Integer, TurtleModel> turtleModelsMap = myInsnModel.getCreatedTurtleMap();
          if (!myTurtlesMap.keySet().containsAll(turtleModelsMap.values())) {
            List<TurtleModel> newModels = getNewModels(turtleModelsMap);
            for (TurtleModel m : newModels) {
              TurtleView turtle = makeTurtle(m);
              myTurtlesMap.put(m, turtle);
              root.getChildren().add(turtle);
            }
          }
        }

        private List<TurtleModel> getNewModels(Map < Integer, TurtleModel > turtleModelsMap){
          List<TurtleModel> newModels = new ArrayList<>();
          for (TurtleModel m : turtleModelsMap.values()) {
            if (!myTurtlesMap.containsKey(m)) {
              newModels.add(m);
            }
          }
          return newModels;
        }

        private TurtleView makeTurtle (TurtleModel m){
          return new TurtleDisplay(convertX(m.getNextPos()[0]), convertY(m.getNextPos()[1]),
                  m.getHeading(), "turtle", Color.RED);
        }
=======
  private TurtleView makeTurtle(TurtleModel m) {
    return new TurtleDisplay(convertX(m.getNextPos()[0]), convertY(m.getNextPos()[1]),
            m.getHeading(), DEFAULT_IMAGE, DEFAULT_COLOR);
  }

  public void play() {
    try {
      updateTurtles();
      updateTurtleMap();
      Animation animation = makeAnimation();
      animation.play();
      animation.setOnFinished(e -> play());
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }
>>>>>>> master

        public void play () {
          try {
            updateTurtleMap();
            Animation animation = makeAnimation();
            animation.play();
            //updateCurrTurtle();
            animation.setOnFinished(e -> play());
          } catch (InvocationTargetException e) {
            e.printStackTrace();
          } catch (NoSuchMethodException e) {
            e.printStackTrace();
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          }
        }

        public Animation makeAnimation ()
      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    List<Integer> activeIDList = myInsnModel.getActiveIDList();
    List<TurtleModel> activeTurtles= new ArrayList<>();
    List<TurtleModel> oldActiveTurtles = new ArrayList<>();

    for(int i: activeIDList) {
      TurtleModel currActiveTurtle = myInsnModel.getCreatedTurtleMap().get(i);
      TurtleModel oldActiveTurtle = new TurtleModel(currActiveTurtle.getNextPos()[0], currActiveTurtle.getNextPos()[1],
          currActiveTurtle.getHeading());
      activeTurtles.add(currActiveTurtle);
      oldActiveTurtles.add(oldActiveTurtle);
    }

<<<<<<< HEAD
          TurtleModel oldModelState = new TurtleModel(myModel.getNextPos()[0], myModel.getNextPos()[1],
                  myModel.getHeading());
          Optional<Object> o = myInsnModel.runNextInsn();
          myModel = myInsnModel.getCurrTurtle();
          if (o.isPresent()) {
            return getTransition(o, oldModelState);
          } else {
            return doNothingPath(oldModelState);
          }
        }
  /*
   todo:
    figure out how all this ties in with turtleView
  */

        private Transition getTransition (Optional < Object > o, TurtleModel oldModel)
      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
          PathTransition pt = getPathTransition(o, oldModel);
          RotateTransition rt = getRotateTransition(o, oldModel);
          //PathTransition linetran = getPathTransition(o, oldModel, line);
          return new SequentialTransition(myTurtlesMap.get(myModel), pt, rt);
        }

        private Optional<Method> getChangeFunction (TurtleModel oldModel)
      throws NoSuchMethodException {
          if (!oldModel.getNextPos().equals(myModel.getNextPos())) {
            return Optional.of(this.getClass()
                    .getDeclaredMethod("getPathTransition", Optional.class, TurtleModel.class, MoveTo.class));
          } else if (!(oldModel.getHeading() == myModel.getHeading())) {
            return Optional.of(this.getClass()
                    .getDeclaredMethod("getRotateTransition", Optional.class, TurtleModel.class));
          }
          return Optional.empty();
        }

        private RotateTransition getRotateTransition (Optional < Object > o, TurtleModel oldModel){
          RotateTransition rt = new RotateTransition();
          rt.setNode(myTurtlesMap.get(myModel));
          if (o.isPresent() && !(oldModel.getHeading() == myModel.getHeading())) {
            double angle = (double) o.get();
            rt.setDuration(Duration.seconds(Math.abs(angle) / TURTLE_TURN_SPEED));
            rt.setByAngle(angle);
            return rt;
          } else {
            return doNothingRotate();
          }
        }

        private PathTransition getPathTransition (Optional < Object > o, TurtleModel oldModel)
      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
          MoveTo move = new MoveTo(convertX(oldModel.getNextPos()[0]),
                  convertY(oldModel.getNextPos()[1]));
          Path path = new Path();
          path.getElements().addAll(move,
                  new LineTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1])));
          Duration pathAnimDuration;
          if (o.isPresent() && moved(oldModel.getNextPos())) {
            pathAnimDuration = Duration.seconds(Math.abs((double) o.get()) / TURTLE_SPEED);
            PathTransition pathTrans = new PathTransition(pathAnimDuration, path, myTurtlesMap.get(myModel));
            setListener(pathTrans);
            return pathTrans;
          } else {
            return doNothingPath(oldModel);
          }
        }

        private void setListener (PathTransition pathTrans){
          pathTrans.currentTimeProperty().addListener(new ChangeListener<Duration>() {

            double[] oldLocation = null;

            /**
             * Draw a line from the old location to the new location
             */
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {

              // skip starting at 0/0
              if (oldValue == Duration.ZERO)
                return;

              // get current location
              double x = myTurtlesMap.get(myModel).getBoundsInParent().getCenterX();
              double y = myTurtlesMap.get(myModel).getBoundsInParent().getCenterY();

              // initialize the location
              if (oldLocation == null) {
                oldLocation = new double[]{x, y};
                return;
              }

              // draw line
              if (myModel.penIsDown()) {
                root.getChildren().add(myTurtlesMap.get(myModel).getPen().draw(oldLocation[0], oldLocation[1], x, y));
              }

              // update old location with current one
              oldLocation[0] = x;
              oldLocation[1] = y;
            }
          });
        }

        private PathTransition doNothingPath (TurtleModel oldModel){
          MoveTo move = new MoveTo(convertX(oldModel.getNextPos()[0]),
                  convertY(oldModel.getNextPos()[1]));
          Path path = new Path();
          path.getElements().addAll(move,
                  new LineTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1] - 1)),
                  new LineTo(convertX(oldModel.getNextPos()[0]), convertY(oldModel.getNextPos()[1])));

          return new PathTransition(Duration.seconds(NO_MOVEMENT), path, myTurtlesMap.get(myModel));
        }

        private RotateTransition doNothingRotate () {
          RotateTransition rt = new RotateTransition();
          rt.setNode(myTurtlesMap.get(myModel));
          rt.setDuration(Duration.seconds(NO_MOVEMENT));
          rt.setByAngle(0);
          return rt;
        }

        private boolean moved ( double[] nextPos){
          return (nextPos[0] != myModel.getNextPos()[0] || nextPos[1] != myModel.getNextPos()[1]);
        }

        private double convertX ( double modelX){
          return modelX + (DEFAULT_SIZE.width / 2);
        }
=======
    Optional<Object> o = myInsnModel.runNextInsn();
    if (o.isPresent()) {
      return getTransition(o, oldActiveTurtles, activeTurtles);
    } else {
      return new DoNothingPath(o, oldActiveTurtles.get(0), activeTurtles.get(0), myTurtlesMap.get(activeTurtles.get(0)), root).getPath();
    }
  }

  private Transition getTransition(Optional<Object> o, List<TurtleModel> oldModel, List<TurtleModel> newModel) {

    SequentialTransition transition = new SequentialTransition();
    for(int i = 0; i<oldModel.size(); i++) {
      checkShowing(newModel.get(i));
      //TurtlePath tp = new TurtlePath(o, oldModel.get(i), newModel.get(i), myTurtlesMap.get(newModel.get(i)), root);
      PathTransition pt = new TurtlePath(o, oldModel.get(i), newModel.get(i), myTurtlesMap.get(newModel.get(i)), root).getPathTransition();
      //TurtleRotation tr = new TurtleRotation(o, oldModel.get(i), newModel.get(i), myTurtlesMap.get(newModel.get(i)));
      RotateTransition rt = new TurtleRotation(o, oldModel.get(i), newModel.get(i), myTurtlesMap.get(newModel.get(i))).getTurtleRotation();
      transition.getChildren().addAll(pt, rt);
    }
    return transition;
  }

  private void checkShowing(TurtleModel myCurrModel) {
    if (!myCurrModel.getTurtleRecord().isShowing())  {
      root.getChildren().remove(myTurtlesMap.get(myCurrModel));
    }
    else if (!root.getChildren().contains(myTurtlesMap.get(myCurrModel))){
      root.getChildren().add(myTurtlesMap.get(myCurrModel));
    }
  }

  private double convertX(double modelX) {
    return modelX + (DEFAULT_SIZE.width / 2);
  }
>>>>>>> master

        private double convertY ( double modelY){
          return (DEFAULT_SIZE.height / 2) - modelY;
        }

<<<<<<< HEAD
        public void updateCurrTurtle () {
          double x = myTurtlesMap.get(myModel).getBoundsInParent().getCenterX();
          double y = myTurtlesMap.get(myModel).getBoundsInParent().getCenterY();
          double heading = myModel.getHeading();
          Color color = myTurtlesMap.get(myModel).getColor();
          myTurtlesMap.get(myModel).updateTurtle(x, y, heading, color);
        }
=======
  private void updateTurtle(TurtleModel myCurrModel) {
    double x = myTurtlesMap.get(myCurrModel).getBoundsInParent().getCenterX();
    double y = myTurtlesMap.get(myCurrModel).getBoundsInParent().getCenterY();
    double heading = myCurrModel.getHeading();
    Color color = myTurtlesMap.get(myCurrModel).getColor();
    myTurtlesMap.get(myCurrModel).updateTurtle(x, y, heading, color);
  }
>>>>>>> master

  private void updateTurtles() {
    for (TurtleModel turtle : myTurtlesMap.keySet()) {
      updateTurtle(turtle);
    }
  }

        public void pause () throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
          if (!isAnimated) {
            isAnimated = true;
            makeAnimation();
          }
        }

        public void setAnimationSpeed (Number newValue){
          TURTLE_SPEED = (int) newValue;
        }

<<<<<<< HEAD
        public void reset () {
          makeScene(myRoot);
          isAnimated = false;
          //updateGridPane();
        }

        public Pane getPane () {
          return myPane;
        }
=======
  public void reset() { clearScene(); }
>>>>>>> master

        public BorderPane getBorderPane () {
          return myRoot;
        }

        @Override
        public Node getStyleableNode () {
          return super.getStyleableNode();
        }
      }
    }
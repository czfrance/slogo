package slogo.InstructionClasses;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.CompilerExceptions.CompilerException;
import slogo.CompilerExceptions.NotAValueException;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleInsnModel;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;

public class MultipleTurtleCommandTests {

  public static final String DEFAULT_LANGUAGE = "English";
  private ResourceBundle myErrorBundle;
  private TurtleCollection myCollection;
  private TurtleModel myModel;
  private TurtleInsnModel myInsnModel;

  @BeforeEach
  void setup() {
    myCollection = new TurtleCollection();
    myModel = myCollection.getActiveTurtle();
    myInsnModel = new TurtleInsnModel(myCollection, DEFAULT_LANGUAGE);
  }

  @Test
  public void TellTest()
      throws CompilerException {
    String userInsn = "tell [ 1 ] fd 50";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myModel = myCollection.getActiveTurtle();
    myInsnModel.runNextInsn();

    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(50, updatedRecord.myY());

    userInsn = "tell [ 2 ] bk 50";
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myModel = myCollection.getActiveTurtle();
    myInsnModel.runNextInsn();
    updatedRecord = myInsnModel.getCreatedTurtleMap().get(2).getTurtleRecord();
    assertEquals(-50, updatedRecord.myY());
  }

  @Test
  public void AskTest()
      throws CompilerException {
    String userInsn = "tell [ 1 2 3 ] fd 50";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);
    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myInsnModel.getCreatedTurtleMap().get(1).getTurtleRecord();
    assertEquals(50, updatedRecord.myY());
    updatedRecord = myInsnModel.getCreatedTurtleMap().get(2).getTurtleRecord();
    assertEquals(50, updatedRecord.myY());
    updatedRecord = myInsnModel.getCreatedTurtleMap().get(3).getTurtleRecord();
    assertEquals(50, updatedRecord.myY());

    userInsn = "ask [ 1 ] [ bk 50 ]";
    myInsnModel.addUserInput(userInsn);
    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myInsnModel.getCreatedTurtleMap().get(1).getTurtleRecord();
    assertEquals(0, updatedRecord.myY());

    userInsn = "fd 50";
    myInsnModel.addUserInput(userInsn);
    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myInsnModel.getCreatedTurtleMap().get(1).getTurtleRecord();
    assertEquals(50, updatedRecord.myY());
    updatedRecord = myInsnModel.getCreatedTurtleMap().get(2).getTurtleRecord();
    assertEquals(100, updatedRecord.myY());
    updatedRecord = myInsnModel.getCreatedTurtleMap().get(3).getTurtleRecord();
    assertEquals(100, updatedRecord.myY());
  }

  @Test
  public void AskWithTest()
      throws CompilerException {
    String userInsn = "tell [ 1 ] fd 50";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);
    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myInsnModel.getCreatedTurtleMap().get(1).getTurtleRecord();
    assertEquals(50, updatedRecord.myY());

    userInsn = "tell [ 2 ] bk 50";
    myInsnModel.addUserInput(userInsn);
    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myInsnModel.getCreatedTurtleMap().get(2).getTurtleRecord();
    assertEquals(-50, updatedRecord.myY());

    userInsn = "askwith [ greater? xcor 0 ] [ fd 50 ]";
    myInsnModel.addUserInput(userInsn);
    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myInsnModel.getCreatedTurtleMap().get(1).getTurtleRecord();
    assertEquals(100, updatedRecord.myY());
    updatedRecord = myInsnModel.getCreatedTurtleMap().get(2).getTurtleRecord();
    assertEquals(-50, updatedRecord.myY());
  }


}

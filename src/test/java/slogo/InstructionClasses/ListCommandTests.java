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

public class ListCommandTests {

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
  public void DoTimesTest()
      throws CompilerException {
    String userInsn = "dotimes [ :t 4 ] [ fd :t ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(1, updatedRecord.myY());
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(3, updatedRecord.myY());
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(6, updatedRecord.myY());
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(10, updatedRecord.myY());
  }

  @Test
  public void ForTest()
      throws CompilerException {
    String userInsn = "for [ :t 2 8 2 ] [ fd :t ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(2, updatedRecord.myY());
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(6, updatedRecord.myY());
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(12, updatedRecord.myY());
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(20, updatedRecord.myY());
  }

  @Test
  public void IfElseTest()
      throws CompilerException {
    String userInsn = "ifelse equal? 1 0 [ fd 50 ] [ bk 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();

    updatedRecord = myModel.getTurtleRecord();
    assertEquals(-50, updatedRecord.myY());

    userInsn = "ifelse equal? 0 0 [ fd 50 ] [ bk 50 ]";
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(0, updatedRecord.myY());
  }

  @Test
  public void Repeat()
      throws CompilerException {
    String userInsn = "repeat sum 1 3 [ fd 25 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(25, updatedRecord.myY());
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(50, updatedRecord.myY());
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(75, updatedRecord.myY());
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(100, updatedRecord.myY());
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(100, updatedRecord.myY());
  }
}

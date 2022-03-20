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

public class BooleanOperationTests {

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
  public void AndFalseTest()
      throws CompilerException {
    String userInsn = "if [ and 1 0 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(0, updatedRecord.myY());
  }

  @Test
  public void AndTrueTest()
      throws CompilerException {
    String userInsn = "if [ and 1 1 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(50, updatedRecord.myY());
  }

  @Test
  public void EqualFalseTest()
      throws CompilerException {
    String userInsn = "if [ equal? 0 1 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(0, updatedRecord.myY());
  }

  @Test
  public void EqualTrueTest()
      throws CompilerException {
    String userInsn = "if [ equal? 1 1 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(50, updatedRecord.myY());
  }

  @Test
  public void GreaterEqualTrueTest()
      throws CompilerException {
    String userInsn = "if [ greaterequal? 1 1 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(50, updatedRecord.myY());

    userInsn = "if [ greaterequal? 5 1 ] [ fd 50 ]";
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(100, updatedRecord.myY());
  }

  @Test
  public void GreaterEqualFalseTest()
      throws CompilerException {
    String userInsn = "if [ greaterequal? 0 1 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(0, updatedRecord.myY());
  }

  @Test
  public void GreaterTrueTest()
      throws CompilerException {
    String userInsn = "if [ greater? 5 0 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(50, updatedRecord.myY());
  }

  @Test
  public void GreaterFalseTest()
      throws CompilerException {
    String userInsn = "if [ greater? 1 1 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(0, updatedRecord.myY());

    userInsn = "if [ greater? 0 1 ] [ fd 50 ]";
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(0, updatedRecord.myY());
  }

  @Test
  public void LessEqualTrueTest()
      throws CompilerException {
    String userInsn = "if [ lessequal? 1 1 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(50, updatedRecord.myY());

    userInsn = "if [ lessequal? 1 5 ] [ fd 50 ]";
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(100, updatedRecord.myY());
  }

  @Test
  public void LessEqualFalseTest()
      throws CompilerException {
    String userInsn = "if [ lessequal? 1 0 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(0, updatedRecord.myY());
  }

  @Test
  public void LessTrueTest()
      throws CompilerException {
    String userInsn = "if [ less? 0 5 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(50, updatedRecord.myY());
  }

  @Test
  public void LessFalseTest()
      throws CompilerException {
    String userInsn = "if [ less? 1 1 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(0, updatedRecord.myY());

    userInsn = "if [ less? 5 0 ] [ fd 50 ]";
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(0, updatedRecord.myY());
  }

  @Test
  public void NotEqualTrueTest()
      throws CompilerException {
    String userInsn = "if [ notequal? 0 5 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(50, updatedRecord.myY());
  }

  @Test
  public void NotEqualFalseTest()
      throws CompilerException {
    String userInsn = "if [ notequal? cos 90 cos 90 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(0, updatedRecord.myY());
  }

  @Test
  public void NotTrueTest()
      throws CompilerException {
    String userInsn = "if [ not 0 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(50, updatedRecord.myY());
  }

  @Test
  public void NotFalseTest()
      throws CompilerException {
    String userInsn = "if [ not cos 0 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(0, updatedRecord.myY());
  }

  @Test
  public void OrTrueTest()
      throws CompilerException {
    String userInsn = "if [ or sin 0 sin 90 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(50, updatedRecord.myY());
  }

  @Test
  public void OrFalseTest()
      throws CompilerException {
    String userInsn = "if [ or sin 0 sin 0 ] [ fd 50 ]";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(0, updatedRecord.myY());
  }
}

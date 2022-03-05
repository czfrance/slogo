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

public class MathOperationTests {
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
  public void ArcTanTest()
      throws CompilerException {
    String userInsn = "fd arctangent 1";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(Math.PI/4, updatedRecord.myY());
  }

  @Test
  public void CosTest()
      throws CompilerException {
    String userInsn = "fd cos 0";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(1, updatedRecord.myY());
  }

  @Test
  public void DiffTest()
      throws CompilerException {
    String userInsn = "fd difference 100 10";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(90, updatedRecord.myY());
  }

  @Test
  public void MinusTest()
      throws CompilerException {
    String userInsn = "fd minus -90";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(90, updatedRecord.myY());
  }

  @Test
  public void RandomTest()
      throws CompilerException {
    String userInsn = "fd random 90";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertTrue(updatedRecord.myY()<=90);
  }

  @Test
  public void RandomRangeTest()
      throws CompilerException {
    String userInsn = "fd randomrange 90 180";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertTrue(90<=updatedRecord.myY()&& updatedRecord.myY()<=180);
  }

  @Test
  public void RemainderTest()
      throws CompilerException {
    String userInsn = "fd remainder 13 4";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(1, updatedRecord.myY());
  }

  @Test
  public void SineTest()
      throws CompilerException {
    String userInsn = "fd sin quotient pi 2";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(1, updatedRecord.myY());
  }

  @Test
  public void SquareRootTest()
      throws CompilerException {
    String userInsn = "fd sqrt 4";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(2, updatedRecord.myY());
  }

  @Test
  public void SumTest()
      throws CompilerException {
    String userInsn = "fd sum 86 4";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(90, updatedRecord.myY());
  }

  @Test
  public void TanTest()
      throws CompilerException {
    String userInsn = "fd tangent quotient pi 4";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertTrue(Math.abs(1-updatedRecord.myY()) <= 0.001);
  }
}

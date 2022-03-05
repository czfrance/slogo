package slogo.Model;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.CompilerExceptions.CompilerException;
import slogo.CompilerExceptions.NotAValueException;

class TurtleInsnModelTest {

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
  public void simpleEnglishForwardTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String forwardInsn = "fd 50";
    myInsnModel.addUserInput(forwardInsn);
    myInsnModel.runNextInsn();
    TurtleRecord updatedRecord = myModel.getTurtleRecord();
    assertEquals(50.0, updatedRecord.myY());
  }

  @Test
  public void multiSimpleInstructionTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String multiInsn = "fd 50 rt 90 fd 50";
    int numInstruction = 3;
    myInsnModel.addUserInput(multiInsn);
    for(int i = 0; i<numInstruction; i++) {
      myInsnModel.runNextInsn();
    }

    TurtleRecord updatedRecord = myModel.getTurtleRecord();
    assertEquals(50.0, updatedRecord.myX());
    assertEquals(50.0, updatedRecord.myY());
  }

  @Test
  public void NestedInstructionTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String nestedInsn = "fd lt 90";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(nestedInsn);

    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(180, updatedRecord.myHeading());

    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(-90.0, updatedRecord.myX());
  }

  @Test
  public void UserInstructionTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String userInsn = "to line [ :distance ]\n[\nfd :distance\nrt :distance\n]\nline 90";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(90.0, updatedRecord.myY());

    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(0.0, updatedRecord.myHeading());
  }

  @Test
  public void BackwardsCommandTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String userInsn = "bk 50";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(-50.0, updatedRecord.myY());
  }

  @Test
  public void HideCommandTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String userInsn = "hideturtle";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertEquals(false, updatedRecord.isShowing());
  }

  @Test
  public void HomeCommandTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String userInsn = "fd 50 home";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertTrue(Math.abs(updatedRecord.myX())<=0.001);
    assertTrue(Math.abs(updatedRecord.myY())<=0.001);
  }

  @Test
  public void PenDownTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String userInsn = "pd";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertTrue(updatedRecord.isPenDown());
  }
  @Test
  public void PenUpTest()
      throws NotAValueException, CompilerException {
    String userInsn = "pu";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertFalse(updatedRecord.isPenDown());
  }

  @Test
  public void SetHeadingTest()
      throws NotAValueException, CompilerException {
    String userInsn = "seth 90";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertTrue(updatedRecord.myHeading() == 90);
  }

  @Test
  public void SetPositionTest()
      throws NotAValueException, CompilerException {
    String userInsn = "setxy 90 -90";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    assertTrue(updatedRecord.myX() == 90);
    assertTrue(updatedRecord.myY() == -90);
  }

  @Test
  public void SetTowardsTest()
      throws NotAValueException, CompilerException {
    String userInsn = "towards 90 0";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    System.out.println(updatedRecord.myHeading());
    assertTrue(updatedRecord.myHeading() == 0);
  }

  @Test
  public void ShowTurtleTest()
      throws NotAValueException, CompilerException {
    String userInsn = "ht st";
    TurtleRecord updatedRecord;
    myInsnModel.addUserInput(userInsn);

    myInsnModel.runNextInsn();
    myInsnModel.runNextInsn();
    updatedRecord = myModel.getTurtleRecord();
    System.out.println(updatedRecord.myHeading());
    assertTrue(updatedRecord.isShowing());
  }


}
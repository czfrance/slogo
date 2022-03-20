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


}
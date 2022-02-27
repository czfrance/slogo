package slogo;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.CompilerExceptions.CompilerException;
import slogo.CompilerExceptions.NotAValueException;
import slogo.Model.TurtleModel;

class CompilerTest {

  public static final String DEFAULT_LANGUAGE = "English";
  private ResourceBundle myErrorBundle;
  private TurtleModel myModel;

  Compiler myCompiler;
  @BeforeEach
  void setup() {
    myModel = new TurtleModel(0,0,0);
    myCompiler = new Compiler(DEFAULT_LANGUAGE, myModel);
    myErrorBundle = ResourceBundle.getBundle(Compiler.ERROR_RESOURCE_PACKAGE+DEFAULT_LANGUAGE);
  }


  @Test
  public void simpleEnglishForwardTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String forwardInsn = "fd 50";
    myCompiler.getCommands(forwardInsn);
    assertEquals(String.format("forward %f\n", 50.0), myCompiler.toString());
  }

  @Test
  public void simpleDoubleInstructionTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String doubleInsn = "fd 50 rt 100";
    myCompiler.getCommands(doubleInsn);
    assertEquals(String.format("forward %f\nright %f\n", 50.0, 100.0), myCompiler.toString());
  }

  @Test
  public void forwardSumInstructionTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String doubleInsn = "fd sum 10 sum 10 sum 10 sum 20 20";
    myCompiler.getCommands(doubleInsn);
    assertEquals(String.format("forward %f\nsum %f %f\nsum %f %f\nsum %f %f\nsum %f %f\n", 70.0, 10.0, 60.0, 10.0, 50.0, 10.0, 40.0, 20.0, 20.0), myCompiler.toString());
  }

  @Test
  public void forwardDiffInstructionTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String doubleInsn = "fd difference 100 30";
    myCompiler.getCommands(doubleInsn);
    assertEquals(String.format("forward %f\ndifference %f %f\n", 70.0, 100.0, 30.0), myCompiler.toString());
  }

  @Test
  public void forwardPowInstructionTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String doubleInsn = "fd power 5 2";
    myCompiler.getCommands(doubleInsn);
    assertEquals(String.format("forward %f\npower %f %f\n", 25.0, 5.0, 2.0), myCompiler.toString());
  }

  @Test
  public void backwardProductInstructionTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String doubleInsn = "bk product 2 25";
    myCompiler.getCommands(doubleInsn);
    assertEquals(String.format("back %f\nproduct %f %f\n", 50.0, 2.0, 25.0), myCompiler.toString());
  }

  @Test
  public void nestedInstructionTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String doubleInsn = "fd rt 100";
    myCompiler.getCommands(doubleInsn);
    assertEquals(String.format("forward %f\nright %f\n", 100.0, 100.0), myCompiler.toString());
  }

  @Test
  public void differentLanguageInstructionWithCommentTest()
      throws CompilerException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    myCompiler = new Compiler("Chinese", myModel);
    String chineseInsn = "# this is a chinese instruction\nht 50";
    myCompiler.getCommands(chineseInsn);
    assertEquals(String.format("back %f\n", 50.0), myCompiler.toString());
  }
  @Test
  public void wrongParamNumTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String wrongParamNumInsn = "fd";
    String expectedMessage = String.format(myErrorBundle.getString("numParamError"), "fd", 1);
    Exception expectedException = assertThrows(InvocationTargetException.class, ()->myCompiler.getCommands(wrongParamNumInsn));
    assertTrue(expectedException.getCause().getMessage().contains(expectedMessage));
  }

  @Test
  public void forwardWithVariableParameter()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String variableInsn = "make :test 70 fd :test";
    myCompiler.getCommands(variableInsn);
    assertEquals(String.format("make %s %f\nforward %f\n", ":test", 70.0, 70.0), myCompiler.toString());
  }
}
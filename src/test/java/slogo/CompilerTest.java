package slogo;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.CompilerExceptions.CompilerException;
import slogo.CompilerExceptions.NotAValueException;
import slogo.CompilerPackage.Compiler;
import slogo.Model.TurtleCollection;

class CompilerTest {

  public static final String DEFAULT_LANGUAGE = "English";
  private ResourceBundle myErrorBundle;
  private TurtleCollection myModel;

  Compiler myCompiler;
  @BeforeEach
  void setup() {
    myModel = new TurtleCollection();
    myCompiler = new Compiler(DEFAULT_LANGUAGE, myModel);
    myErrorBundle = ResourceBundle.getBundle(Compiler.ERROR_RESOURCE_PACKAGE+DEFAULT_LANGUAGE);
  }


  @Test
  public void simpleEnglishForwardTest()
      throws CompilerException {
    String forwardInsn = "fd 50";
    myCompiler.getCommands(forwardInsn);
    assertEquals(String.format("forward %f\n", 50.0), myCompiler.toString());
  }

  @Test
  public void simpleDoubleInstructionTest()
      throws CompilerException {
    String doubleInsn = "fd 50 rt 100";
    myCompiler.getCommands(doubleInsn);
    assertEquals(String.format("forward %f\nright %f\n", 50.0, 100.0), myCompiler.toString());
  }

  @Test
  public void forwardSumInstructionTest()
      throws CompilerException {
    String doubleInsn = "fd sum 10 sum 10 sum 10 sum 20 20";
    myCompiler.getCommands(doubleInsn);
    assertEquals(String.format("sum %f %f\nsum %f %f\nsum %f %f\nsum %f %f\nforward %f\n", 20.0, 20.0, 10.0, 40.0, 10.0, 50.0, 10.0, 60.0, 70.0), myCompiler.toString());
  }

  @Test
  public void forwardDiffInstructionTest()
      throws CompilerException {
    String doubleInsn = "fd difference 100 30";
    myCompiler.getCommands(doubleInsn);
    assertEquals(String.format("difference %f %f\nforward %f\n", 100.0, 30.0, 70.0), myCompiler.toString());
  }

  @Test
  public void forwardPowInstructionTest()
      throws CompilerException {
    String doubleInsn = "fd power 5 2";
    myCompiler.getCommands(doubleInsn);
    assertEquals(String.format("power %f %f\nforward %f\n", 5.0, 2.0, 25.0), myCompiler.toString());
  }

  @Test
  public void backwardProductInstructionTest()
      throws CompilerException {
    String doubleInsn = "bk product 2 25";
    myCompiler.getCommands(doubleInsn);
    assertEquals(String.format("product %f %f\nback %f\n", 2.0, 25.0, 50.0), myCompiler.toString());
  }

  @Test
  public void nestedInstructionTest()
      throws CompilerException {
    String doubleInsn = "fd rt 100";
    myCompiler.getCommands(doubleInsn);
    assertEquals(String.format("right %f\nforward %f\n", 100.0, 100.0), myCompiler.toString());
  }

  @Test
  public void differentLanguageInstructionWithCommentTest()
      throws CompilerException {
    myCompiler = new Compiler("Chinese", myModel);
    String chineseInsn = "# this is a chinese instruction\nht 50";
    myCompiler.getCommands(chineseInsn);
    assertEquals(String.format("back %f\n", 50.0), myCompiler.toString());
  }
  @Test
  public void wrongParamNumTest()
      throws NotAValueException, CompilerException {
    String wrongParamNumInsn = "fd";
    String expectedMessage = String.format(myErrorBundle.getString("numParamError"), "fd", 1);
    assertThrows(CompilerException.class, ()->myCompiler.getCommands(wrongParamNumInsn));
  }

  @Test
  public void forwardWithVariableParameterTest()
      throws NotAValueException, CompilerException {
    String variableInsn = "make :test 70 fd :test";
    myCompiler.getCommands(variableInsn);
    assertEquals(String.format("make %s %f\nforward %f\n", ":test", 70.0, 70.0), myCompiler.toString());
  }

  @Test
  public void changeVarTest()
      throws NotAValueException, CompilerException {
    String changeVariableInsn = "make :test 70 fd :test make :test 100 fd :test";
    myCompiler.getCommands(changeVariableInsn);
    assertEquals(String.format("make %s %f\nforward %f\nmake %s %f\nforward %f\n", ":test", 70.0, 70.0, ":test", 100.0, 100.0), myCompiler.toString());
  }

  @Test
  public void userCmdTest()
      throws NotAValueException, CompilerException {
    String userCmdInsn = "to line [ :distance ]\n[\nfd :distance\nrt :distance\n]\nline 30";
    myCompiler.getCommands(userCmdInsn);
    assertEquals(String.format("forward %f\nright %f\n", 30.0, 30.0), myCompiler.toString());
  }
}
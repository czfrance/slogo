package slogo;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.CompilerExceptions.CompilerException;
import slogo.CompilerExceptions.NotAValueException;

class CompilerTest {

  public static final String DEFAULT_LANGUAGE = "English";
  private ResourceBundle myErrorBundle;

  Compiler myCompiler;
  @BeforeEach
  void setup() {
    myCompiler = new Compiler(DEFAULT_LANGUAGE);
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
  public void nestedInstructionTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String doubleInsn = "fd rt 100";
    myCompiler.getCommands(doubleInsn);
    assertEquals(String.format("forward %f\nright %f\n", 100.0, 100.0), myCompiler.toString());
  }

  @Test
  public void wrongParamNumTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {
    String wrongParamNumInsn = "fd";
    String expectedMessage = String.format(myErrorBundle.getString("numParamError"), "fd", 1);
    Exception expectedException = assertThrows(InvocationTargetException.class, ()->myCompiler.getCommands(wrongParamNumInsn));
    assertTrue(expectedException.getCause().getMessage().contains(expectedMessage));
  }
}
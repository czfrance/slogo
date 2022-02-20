package slogo;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.CompilerExceptions.NotAValueException;

class CompilerTest {

  public static final String DEFAULT_LANGUAGE = "English";

  Compiler myCompiler;
  @BeforeEach
  void setup() {
    myCompiler = new Compiler(DEFAULT_LANGUAGE);
  }


  @Test
  public void simpleEnglishForwardTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    String forwardInsn = "fd 50";
    myCompiler.getCommands(forwardInsn);
    assertEquals(String.format("forward %f\n", 50.0), myCompiler.toString());
  }

  @Test
  public void simpleDoubleInstructionTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    String doubleInsn = "fd 50 rt 100";
    myCompiler.getCommands(doubleInsn);
    assertEquals(String.format("forward %f\nright %f\n", 50.0, 100.0), myCompiler.toString());
  }
}
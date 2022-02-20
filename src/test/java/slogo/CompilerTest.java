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
  public void forwardTest()
      throws ClassNotFoundException, InvocationTargetException, NotAValueException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    String forwardInsn = "fd 50";
    myCompiler.getCommands(forwardInsn);
    assertEquals("forward 50", myCompiler.toString());
  }
}
package slogo.CompilerExceptions;

public class CompilerException extends RuntimeException {

    /**
     * Create an exception based on an issue in our code.
     */
    public CompilerException (String message, Object ... values) {
      super(String.format(message, values));
    }
}

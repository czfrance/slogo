package slogo.CompilerExceptions;

public class CompilerException extends Exception {

    /**
     * Create an exception based on an issue in our code.
     */
    public CompilerException (String message, Object ... values) {
      super(String.format(message, values));
    }

    public CompilerException (Throwable cause, String message, Object ... values) {
      super(String.format(message, values), cause);
    }
}

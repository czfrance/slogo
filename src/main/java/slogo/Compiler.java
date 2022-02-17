package slogo;

import java.util.Stack;

public class Compiler {

  public static final String DELIMITER = "\\s+";

  private PatternParser syntaxParser = new PatternParser();
  private PatternParser languageParser = new PatternParser();
  private Stack<String> commandStack = new Stack<String>();
  private Stack<String> valueStack = new Stack<String>();


  public Compiler(String language) {
    syntaxParser.addPatterns("Syntax");
    languageParser.addPatterns(language);
  }

  public void populateStacks(String userInput) {
    for (String token : userInput.split(DELIMITER)) {
      String currStringType = syntaxParser.getSymbol(token);
      if(currStringType.equals("Comment")) continue;
      if(currStringType.equals("UserCommand")) {
        commandStack.push(token);
      }
      else{
        valueStack.push(token);
      }
    }
  }

}

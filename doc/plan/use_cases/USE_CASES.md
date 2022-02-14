# Use Cases
## Team 01

### Use Case #1
The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history.
Note, it is not necessary to understand exactly how parsing works in order to complete this example, just what the result of parsing the command will be.
Steps:
1) Console takes in user input as string
2) Compiler asks for console for any new strings and receives user inputted 'fd 50'
   1) Compiler checks syntax of 'fd 50' and concludes it is a valid instruction
3) InstructionModel asks for any new valid instructions which it receives from Compiler and inputs it to
   the instruction queue
4) The Animation class updates frame which calls for the TurtleModel to run its next instruction
5) TurtleModel updates its x-y value by moving 50 pixels in the direction it is pointing in
6) TurtleView updates its imageView with this new data which updates its position in the scene
~~~
String userInput = Console.newInsnInput("fd 50");
Compiler.validateString(userInput);
InstructionModel.pushNewInsn(Compiler.getValidInsn());
Animation.updateFrame();
TurtleModel.runNextInsn();
TurtleView.updateView();
~~~

### Use Case #2
The user types '50 fd' in the command window and sees an error message that the command was not formatted correctly.
1) Console takes in user input as string
2) Compiler asks for console for any new strings and receives user inputted '50 fd'
   1) Compiler checks syntax of 'fd 50' and concludes it is an invalid instruction
3) Animation class updatesFrame() which then would see the exception and show an error message to the user
~~~
String userInput = Console.newInsnInput("50 fd");
Compiler.validateString(userInput);
Animation.updateFrame();
~~~

### Use Case #3
The user sets a variable's value and sees it updated in the UI's Variable view.
1) For demonstration's sake, let the variable be speed
2) Console takes in the user input as a string (said in Design Plan how console handles these things)
3) Compiler checks the syntax of the string and parses for the Integer
4) Animation class updatesFrame(), which would then update this value
5) TurtleModel updates the speed in this case that it has
6) TurtleView updates its imageView with this new data which updates its speed
~~~
String userInput = Console.newInsnInput("50");
Compiler.validateString(userInput);
int turtleSpeed = Compiler.parseInt(userInput);
Animation.updateFrame();
TurtleModel.updateSpeed(turtleSpeed);
TurtleView.updateView();
~~~

### Use Case #4
The user sets the pen's color using the UI so subsequent lines drawn when the turtle moves use that color.
1) Console takes in the user input as a string (said in Design Plan how console handles these things)
2) Compiler checks the syntax of the string
3) Animation class updatesFrame(), which would then update this value for the color of the line
4) TurtleModel updates the color of the Pen object the turtle object in question has
5) TurtleView updates its imageView with this new data which updates the color
~~~
   String userInput = Console.newInsnInput("Blue");
   Compiler.validateString(userInput);
   Animation.updateFrame();
   TurtleModel.updateColor(userInput);
   TurtleView.updateView();
~~~

## Custom Use Cases

###Compiler Use Case #1
User defines a custom method "CustomInsn" in a text file and uploads it. Compiler saves method
1) Uploaded File is sent through the console and sent to the FileParser
2) Parser returns raw input string from file
3) Console sends input to compiler
4) Compiler recognizes token for new method definition and stores the custom method in its custom method map
~~~
customMethodString = FileParser.parseTxtFile(customMethodFile);
Console.newInsnInput(customMethodString);
Compiler.validateString(Console.getUserInput);
//validateString method will call helper methods that will save customMethod
Compiler.saveMethod() //helper method
~~~

###Compiler Use Case #2
User calls custom method "CustomInsn" after defining it previously.
1) Console receives user input of "CustomInsn" and saves it
2) Compiler asks console for any new inputs and receives "CustomInsn"
3) Compiler checks its previousInsn map for "CustomInsn" and finds it
4) Compiler sends the base instructions associated with "CustomInsn" to InstructionModel
~~~
Console.newInsnInput("CustomInsn");
String userInput = Console.getUserInput();
Compiler.validateString(userInput);
//Compiler checks for "CustomInsn" in its custom methods map and finds it
ValidInsn = CustomMethodMap.get("CustomInsn");
InstructionModel.pushNewInsn(Compiler.getValidInsn());
~~~

### Compiler Use Case #3
User calls custom method "CustomInsn2" but never defined it previously. Shows an error to the user
~~~
Console.newInsnInput("CustomInsn2");
String userInput = Console.getUserInput();
Compiler.validateString(userInput);
//Compiler finds that userInput is not in its known instruction map so throws exception
Animation.updateFrame(); //exception handled here
Animation.showError();
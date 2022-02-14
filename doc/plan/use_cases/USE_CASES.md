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

### Use Case #4
The user sets the pen's color using the UI so subsequent lines drawn when the turtle moves use that color.

## Custom Use Cases
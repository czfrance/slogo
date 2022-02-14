/**
 * The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history.
 * Note, it is not necessary to understand exactly how parsing works in order to complete this example, just what the result of parsing the command will be.
 */

String userInput = Console.newInsnInput("fd 50");
Compiler.validateString(userInput);
InstructionModel.pushNewInsn(Compiler.getValidInsn());
Animation.updateFrame();
TurtleModel.runNextInsn();
TurtleView.updateView();
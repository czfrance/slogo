# DESIGN Document for SLOGO
## Brandon Bae, Thivya Sivarajah, Prajwal Jagadish, Cynthia France


## Role(s)

* Brandon Bae
  * User command compiler/parser (Backend)
  * Command class hierarchy (Backend)
* Thivya Sivarajah

* Prajwal Jagadish

* Cynthia France
  * Turtle commands (backend)
  * Turtle movement & commands (frontend)


## Design Goals
###What Features are Easy to Add
* One of the biggest design goals of this project was to make it as flexible as possible. This meant we focused on allowing the addition of new features as easy as possible.
* Flexible Features:
  * New Commands
  * (Any front end features easy to add? such as buttons, new windows, panes, etc...)
  * (Any parts of the compiler easy to add? such as new stats to display, user defined variables or components)
  * (Any parts of the turtlemodel/view easy to add? such as new colors, images, turtle behavior)
* New Commands - New commands are easily developed by extending our Instruction class hierarchy. Here users simply need to define the new instructions returnValue() and getLambda().
  The returnValue() allows for users to define how the returnValue is calculated for the new command. This is especially useful for calculation type instructions that are used primarily
  for their return value such as Math type instructions (add, sub, and cos). The getLambda() function allows users to define any functionality to the instruction that affects the turtle.
  We decided to make the getLambda() function in order to maintain encapsulation of turtleModel data while also containing all parts of the instruction within its own class. This means that
  users do not need to redefine or add any methods into the turtleModel itself and only need to stick with their new Instruction subclass.


## High-Level Design
* Model:
  * The TurtleInsnModel class acts as the main external API for the backend with its runNextInsn() and addUserInput() methods
    * The Console class sends the String representation of the unparsed user input to the TurtleInsnModel using the addUserInput() method. The addUserInput() 
    then sends the user input string to the Compiler class.
      * The Compiler class acts as an internal API for the model by parsing any user input strings into individual InstructionClasses and returns a deque of InstructionClasses to the TurtleInsnClass which is stored in the combined parsed instructions deque. This is done through the getCommands() method.
      * The Instruction class hierarchy defines each of the instructions such as their return value and their functionality. Each instruction class has a reference to the current TurtleCollection and defines its own Functional 
      (lambda expression) which the Instruction class can send to the turtleCollection/Model for the turtle to run.
      * The TurtleCollection acts as a collection of turtleModels while also handling issues such as creating new turtleModels, organizing active turtles, and running recieved lambdas for each active turtle.
        * The turtleModel themselves acts as the main backend representation for an individual turtle on the screen. It receives lambdas that it uses to override its TurtleRecord (holds instance variables) using the runInsn() method
  * The SlogoView constantly asks TurtleInsnModel to runNextInsn(). This method pops the first thing in the combined instruction deque and calls its runInsn() method. If the Instruction is "not done" it is popped back onto the deque to be ran again (used for list commands, user commands, etc...).
####Core Classes
* Model
  * Compiler
  * Instruction class Hierarchy
  * TurtleModel, TurtleCollection, TurtleInsnModel
* Model (?)
  * Console
* View
  * SketchbookView
  * Simulation Display
  * SlogoView


## Assumptions or Simplifications
* Users will input instructions using proper format such as following the correct delimeter
  * This means we do not have exception handling for missing delimeters or wrong format
  * We do have error handling for unknown instructions, missing parameters, etc...
* Instructions will have a return value that can be calculated before running the program
  * This means that we can define the return value of each instruction in its instruction class representation
  * The only exception to this assumption is if return values depend on variables as returning a variable during runtime will return the most updated version of that variable due to our use of "passing by reference" when interacting with anything involving variables.
* (Any frontend assumptions ?)
* (Any console assumptions ?)
* Any command done on the turtle/program has been correctly executed in the backend TurtleModel
  * This means that the frontend Turtle simply reflects the changes of the backend, rather than computing
    the changes itself
* All instructions passed from compiler to TurtleModel are the basic functions and are syntactically 
  correct
  * This includes command names and number & type of parameters

## Changes from the Plan
* Instruction Classes
  * In the original plan, the Compiler returned a string representation of the parsed user input. This meant that the TurtleModel Class itself would have to parse strings itself using reflection to run private methods that were hardcoded representations of each instruction
    * We changed this into individual instruction classes that passed Functionals to the TurtleModel to run. This ensured that the turtleModel encapsulated its data and removed the need to hardcode methods into the TurtleModel for every new Instruction we want to add.
  * The compiler also changed to return a deque of instructions as well instead of a deque of strings in order to facilitate the new Instruction Class functionality.
* TurtleCollection
  * In the original plan, we didn't properly account for multiple turtles. Therefore we only had a turtleModel class to represent single models. Originally we were simply going to account for multiple turtles by keeping them in an arrayList or something similar
  * In the final version we have a TurtleCollection class now which essentially holds a hashmap of all existing turtles while also handling the creation of new turtles and the updating of active turtles, 


## How to Add New Features
* New Instructions
  * Extend the Instruction abstract super class.
    * Define methods returnValue() with the appropriate calculations for the instructions return value
    * Define methods getLambda() so that it returns a Functional that defines functionality of how the instruction alters a turtleModel's turtle record
      * The Functional takes in a turtle record and list of doubles representing the parameters to the Instruction. It then returns a new TurtleRecord with updated turtle values.
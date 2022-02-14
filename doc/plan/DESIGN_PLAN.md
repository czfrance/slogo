# SLogo Design Plan
### Brandon Bae, Cynthia France, Prajwal Jagadish, Thivya Sivarajah
### TEAM 01


#### Examples

Here is a graphical look at my design:

![This is cool, too bad you can't see it](Main.jpg "An initial UI")

made from [a tool that generates UML from existing code](http://staruml.io/).


Here is our amazing UI:

![This is cool, too bad you can't see it](29-sketched-ui-wireframe.jpg "An alternate design")

taken from [Brilliant Examples of Sketched UI Wireframes and Mock-Ups](https://onextrapixel.com/40-brilliant-examples-of-sketched-ui-wireframes-and-mock-ups/).


## Introduction
Our goal is to create a well-designed program that takes in user-defined commands in the form of 
.txt files and instructions from the console to create some sort of drawing. The user controls
a pen (turtle) and feeds it commands. As the turtle moves, its path is visualized, taking the form
of a picture/drawing.

The primary design goal is to make the program as flexible as possible so that new features can be
introduced with ease. In particular, we should be flexible in adding new languages, pen types, 
movement, etc., so classes that relate to these features (compiler, model(s), view(s)) should be 
open to extension. However, components that deal with the core functioning of the program (ie 
drawing, moving, reading input) should be closed for modification.

In general, the user will input commands either through the console or uploaded files that control 
how the turtle will move. From there, the commands are read and broken down into its base
components/commands. These will be executed one by one, which are visually reflected in the UI.

This continues until either the user quits or starts a new drawing, in which case the entire
process would be repeated.


## Overview

![](Main.jpg)

#### External APIs - user facing
Backend: Console
  * input commands
  * input files
  * define drawing "functions"

Frontend: View
  * obtain turtle's location (x, y coordinates)
  * direction turtle is facing (in degrees)
  * pen status (up/down)
  * turtle viewable status (showing/hiding)

#### Internal APIs
Backend
  * User instructions
  * Function definitions (name -> broken down commands)
  * Commands to execute (& their order)
Frontend
  * next location/status/status

## User Interface


## Design Details
- Controller External API
  - Console
    - Features Supported by this API:
      - Enter commands to turtle through console
      - User Sets turtle parameters (color, pen, turtle image, etc...)
    - Resources Used:
      - JavaFX (for visual display and getting input)
      - FileParser (used to handle file inputs instead of console inputs)
    - How it Serves other Components:
      - Console acts as the primary API that provides the user input for the model to work on
    - Extensions:
      - Different languages for input
      - multiple consoles for different instances of SLogo at once
    - Justification:
      - This class acts as the controller for our program. This allows for our program to have some separation between the 
      view and model which allows for further encapuslation of data and abstraction
- Controller Internal API
  - FileParser
    - Features Supported by this API:
      - Txt files with commands instead of only communicating through console
    - Resources Used:
      - File (File class used to navigate file)
      - FileChooser (allows for user to choose which files to open)
    - How it Serves other Components:
      - Translates files into string inputs which the console class is able to handle
    - Extensions:
      - Different file types beyond text files
      - C style header files
    - Justification:
      - This class acts like as an internal API for the main console external api. This internal api
      serves to provide file parsing capabilities for the console expanding its functionality. 
      - By having the file parsing functionality handled by a different internal API we allow for abstractions
      and encapsulation through the use of open closed principle, liskov substitution principle, etc...
- Model External API
  - TurtleModel
    - Features Supported by this API:
      - Turtle interactions
      - Turtle instruction execution (backend portion)
    - Resources Used:
      - InstructionModel (handles instruction ordering)
    - How it Serves other Components:
      - Handles storing and updating the variables that represent the turtle for our program
      - Works in conjunction with the TurtleView to handle all turtle functionalities
    - Extensions:
      - Different pen features (different lines, different shapes, pen offsets, etc...)
    - Justification:
      - This allows for us to better follow the single responsibility principle as it splits up the responsibility of the turtle functionality between its backend and frontend features
- Model Internal API
  - Compiler
    - Features Supported by this API:
      - see errors that result from entered commands in a user friendly way
      - User defined methods
    - Resources Used:
      - Console (gets unchecked user inputs from the console)
    - How it Serves other Components:
      - This class error checks and parses the user inputs into actual LOGO instructions which it returns for other model
      classes to utilize
    - Extensions:
      - Different coding languages
      - Logo in different languages
    - Justification:
      - Splits up responsibility of parsing user inputs into receiving input (console class) and parsing input (compiler class) 
      - We need to properly error check our code and the compiler allows for the perfect as it is the main entry point of user input
        (which can be rife with errors) and our model
  - InstructionModel
    - Features Supported by this API:
      - Line by Line instructions from the console for the turtle
    - Resources Used:
      - Queues (how instructions are stored)
      - Compiler (receives next instructions from this class)
    - How it Serves other Components:
      - Acts as an internal API for the TurtleModel by handling the ordering of a turtle instance's next instructions
    - Extensions:
      - Custom instructions in the queue
    - Justification:
      - This encapsulates the data of the instructions from the TurtleModel 
      - Further abstraction of the instruction handling which allows for potential exansion in the future
- View External API
  - Animation
    - Features Supported by this API:
      - Display turtle
      - Display variables
    - Resources Used:
      - TurtleView (turtleView handles all the turtle movements)
      - TurtleModel (to get the variables to display)
    - How it Serves other Components:
      - Combines all the backend data into a visual display for the users to see
      - Acts as an external api for the frontend to finally display the program to the user
    - Extensions:
      - multiple turtles at once
      - multiple instruction sets running at once (different grids)
    - Justification:
      - Separates frontend and backend which allows for encapsulation of sensitive data
- View Internal API
  - TurtleView
    - Features Supported by this API:
      - Display Turtle movement and pen drawings
    - Resources Used:
      - TurtleModel
    - How it Serves other Components:
      - Acts as an internal API that translates changes in turtleModel data into changes in displayed turtle
    - Extensions:
      - Turtle has multiple pens (multiple lines drawn at once)
      - Turtle reacts to each method differently (example oppositeTurtle would do the exact opposite of instruction recieved such as foward instruction making turtle go backwards)
    - Justification:
      - Splits up the responsibility of the turtle between the changing of state based off instructions (TurtleModel) and updating display (TurtleView)
      - Acts as an abstraction for the overall Animation API which allows for changes to the turtle behavior


## Design Considerations


## Test Plan


## Team Responsibilities

 * Team Member #1

 * Team Member #2

 * Team Member #3

 * Team Member #4

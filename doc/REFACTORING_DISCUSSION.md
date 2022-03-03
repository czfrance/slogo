## Refactoring Lab Discussion
### TEAM 01
### Brandon, Thivya, Cynthia, Prajwal


## Issues in Current Code

### Compiler
* Design Issue: Single Responsibility Principle
  * The class seems a bit overloaded as it handles EVERYTHING about compiling code. 
  * I feel like we should split up some of the compiling parts into separate class hierarchies such as a variable compiler or a method compiler etc...

* Too many conditionals
  * There are many conditionals that are used to handle branch cases such as if the input is a variable, constant, method, etc...
  * We should instead use reflection to handle these conditionals more cleanly

### SketchbookView
* Design issues: Single Responsibility Principle
  * Similar to above, this class is responsible for essentially everything
    related to creating turtle animations, creating turtle views, playing the animations, etc
  * This class would benefit from refactoring in the form of splitting up responsibiltiies
    into the categories I specified above. There are also opportunities for reflection
    and other design features.

* Design issue: Redundant functions
  * There are functions that do almost the same thing that can be consolidated into one
  * Regarding path and rotate transitions, both are created no matter which will
    be used (only one will ever be making a change), so this can be refactored to
    create only the Transition object that will make the change.



## Refactoring Plan

* What are the code's biggest issues?
  * A majority of our classes are overloaded and have too much functionality

* Which issues are easy to fix and which are hard?
  * refactoring simple tasks into separate classes are easy to fix such as moving constant compiling into its own compiler class hierarchy
  * However, some of our code is ingrained(hardcoded) into our classes to a great deal which can make refactoring those into different classes harder to do

* What are good ways to implement the changes "in place"?
  * We could possibly pull out small functional bits into their own class hierarchies to slim down on the responsibilties for our overloaded classes.



## Refactoring Work

* Issue chosen: Compiler class being overloaded
  * We can take out each type of method into its own compiler such as a variable compiler or a method compiler


* Issue chosen: Fix and Alternatives

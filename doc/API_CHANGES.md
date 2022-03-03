## SLogo API Changes
### TEAM 01
### Brandon, Thivya, Cynthia, Prajwal


## Changes to the Initial APIs

### Backend External

* Method changed: RunInsn() (In new TurtleInsnModel class)

    * Why was the change made?
      * We changed how our workflow was done from the turtle running the commands using its own private methods to insn classes providing lambdas to the turtle to run.
      This means that our main external api is no longer the turtle or the compiler but instead the instruction model that controls which instructions are being run.

    * Major or Minor (how much they affected your team mate's code)
      * This was an extremely big change as we were essentially shifting around what would be the external and internal API for the backend.

    * Better or Worse (and why)
      * While this did end up changing a lot of the code we had made with our initial API designs, this encapsulated the data of the turtleModel much better and allowed for greater flexibility.


* Method changed: addUserInput() (In new TurtleInsnModel class)

    * Why was the change made?
      * As mentioned previously we changed our main external API to the TurtleInsnModel class. This means that it handles userInputs by sending it to its own compiler.
      Now the console no longer sends directly to the compiler which is now a internal API.

    * Major or Minor (how much they affected your team mate's code)
      * This was not as big of an API change as all that needed to change is that the console sends the user input to the TurtleInsnModel instead of the compiler.

    * Better or Worse (and why)
      * This is better as this allowed for the compiler to not interact with any of the frontend api. Instead the frontend only communicates with the external TurtleInsnModel API.


### Backend Internal

* Method changed: runInsn() (in TurtleModel)

    * Why was the change made?
      * Originally the turtle took in a string and decoded that string to run internal private methods using reflection. However, we realized that this was not scalable as this would require new methods in turtle everytime new methods were implemented.
      Now the insn classes send lambdas to the turtle to run using a general runInsn() instead.

    * Major or Minor (how much they affected your team mate's code)
      * This is a major change as this invalidated all the private methods in the turtleModel. The functionality was instead moved to individual InstructionClasses.

    * Better or Worse (and why)
      * This was for better as it allowed for us to have scalable and easily implementable new methods.


* Method changed: run() (in Insn Classes)
    * Why was the change made?
      * As mentioned previously this method previously did not exist as all the turtle functionality was located in the turtleModel. However, now the functionality is defined in the Insn Classes which allows for flexibility and encapsulation of TurtleModel data.

    * Major or Minor (how much they affected your team mate's code)
      * This was a major change as we had to migrate all the functionality to the instruction classes.

    * Better or Worse (and why)
      * This was for better as it allowed for more flexibility when creating new methods for Slogo.


### Frontend External

* Method changed:

    * Why was the change made?

    * Major or Minor (how much they affected your team mate's code)

    * Better or Worse (and why)


* Method changed:

    * Why was the change made?

    * Major or Minor (how much they affected your team mate's code)

    * Better or Worse (and why)


### Frontend Internal

* Method changed:

    * Why was the change made?

    * Major or Minor (how much they affected your team mate's code)

    * Better or Worse (and why)


* Method changed:

    * Why was the change made?

    * Major or Minor (how much they affected your team mate's code)

    * Better or Worse (and why)


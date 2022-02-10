# Cell Society API Lab Discussion
### Brandon Bae, Cynthia France, Prajwal Jagadish, Thivya Sivarajah
### TEAM 01


## Current Simulation API (team 04)

### External

* Identified Classes/Methods
  * GridModel: goes through grid and sets the cell states

* Goals
  * Not extendable
  * Deals only with what the user sees
  * The only class that can modify the grid

* Contract
  * updates the grid with new cell states

* Services
  * update cells
  * render cells
  * returns cell at a location
  

### Internal

* Identified Classes/Methods
  * Simulations
  * Cell

* Goals
    * Simulation never directly changes the grid
    * keep it slim (not a lot of methods)

* Contract
    * Returns the next state of each cell
    * Doesn't directly set the next state, only returns it

* Services
    * initialize cells
    * getting next state for cells


## Wish Simulation API

### External

* Goals
  * maintain getNext state, initialize

* Contract
  * doesn't alter the state of the grid
  * only returns the state

* Services
  * returns the state of a cell
  * initializes cells


### Internal

* Goals
  * add more flexibility/depth in terms of cells (ie Wator, Ants, SugarScape)
  
* Contract
  * scalability doesn't interfere with other implementations

* Services
  * allows for multiple options in each cell
  * multiple states in one cell (sugarscape)
  * Ground (ants)
  * Fish and Shark (Wator)

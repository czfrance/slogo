# Collections API Lab Discussion
### NAMES
### TEAM 1


## In your experience using these collections, are they hard or easy to use?
- They are easy to use in the sense that they have a lot of common methods such as size(), sort(), etc… These methods are also very readable as well leading to little confusion about what method does what.
- However, there is also some confusion and difficulty that can arise especially between the different implementations of collections. For example, a lot of the data structures in collections have very fundamental differences between them that require different approaches to use. For example, for queues and deques you have to think about popping and pushing to add and remove from the queues. Lists and Sets have .add() and .remove(). However, Lists have the ability to add and remove at a certain index of the list while sets do not have this ability.


## In your experience using these collections, do you feel mistakes are easy to avoid?
- As mentioned previously methods are for the most part not extremely confusing due to them being very readable and many of them being shared. This prevents any confusion from a user’s perspective when using these methods.
- However, we also mentioned that there are a lot of implementation specific methods as well between different collection subclasses which can lead to confusion. It is important to note that exception and error handling is very robust in the collections class meaning that even if a fatal mistake is made the user can at least know where and how it happened.


## What methods are common to all collections (except Maps)?
- Size()_
- add()
- max()
- min()
- sort()
- .equals()

## What methods are common to all Deques?
- pollFirst/Last()
- removeFirst/Last()
- addFirst/last()
- peekFirst/Last()

## What is the purpose of each interface implemented by LinkedList?
- List:
  - You can also get, add, and remove at index specific locations as well.
- Dequeue:
  - You want to add, check, and get from the beginning and end of the list (head and tail nodes)
- Iterable:
  - So you can easily iterate through a linked list using an iterable like any other collections time datastructure
- It combines all these interfaces together to get the speed of a dequeue and the robust nature (in terms of accessing every node) of a list


## How many different implementations are there for a Set?
- Tree set
- Hashsets
- Linked hashsets
- 9 different total implementations
  - Yes I think 9 total implementations warrants Set to be different. This is especially true given the fact that each implementation of set is very unique from one another such as treeset allowing for sorted order while hashsets use hasing to store values.


## What is the purpose of each superclass of PriorityQueue?
- Object
  - Every new class extends object so PriorityQueue must also extend object
  - It also provides baseline methods such as .equals and .toString for the class as well
- AbstractCollection
  - AbstractCollection implements Collection to make Collection into an abstract class
  - It provides a skeletal implementation for collection with some predefined methods making it easier to implement in subclasses
- AbstractQueue
  - This also acts like abstract collection in the sense that it makes it easier the implement the queue interface
  - It also helps with exception handling

## Why does it make sense to have the utility classes (such as Collections) instead of adding that functionality to specific collection types themselves (such as List)?
- It makes sense to have a utility collections class as it allows different implementations of collection to interact with one another. 
- Also it helps standardize methods
- # Are there any overlapping methods (ones that are in both a specific collection and a utility class)?
  - Examples in the Deque:
    - add()/addFirst()
    - remove()/removeFirst()
- # If so, is there any guidance on which one you should use?
  - In the documentation it seems that these methods essentially do the same thing. I assume it is preferable for users to use the implementation specific methods to maintain consistency. But add and remove could be used to prevent any downcasting when a deque is assigned to a Collections variable.




## API Characterics applied to Collections API

* Easy to learn

* Provides for extension

* Leads to readable code

* Hard to misuse
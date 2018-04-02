# Lab 6: P.S. It's Just a Stack

## Useful Links
 * [Course Homepage](https://www.cs.williams.edu/~cs136/index.html) (with TA schedule)
 * [Lab Webpage](https://www.cs.williams.edu/~cs136/labs/postscript.html)
 * [Token](https://www.cs.williams.edu/~cs136/labs/javadoc/Token.html) documentation
 * [Reader](https://www.cs.williams.edu/~cs136/labs/javadoc/Reader.html) documentation
 * [SymbolTable](https://www.cs.williams.edu/~cs136/labs/javadoc/SymbolTable.html) documentation


## Repository Contents
This repository contains the starter files for writing and testing
your postscript interpreter.

 * In this lab, you should only need to modify `Interpreter.java.
 * All files that end in `.ps` are postscript files. You should eventually be able to run them using your interpreter.

## Using Test files
There are several testing programs to help you verify your code as you go.
(You can always run your program interactively and type commands from the
command line as well.)

Rather than wait to test until you have implemented all of the commands,
create a smaller version of the test (the comment character in postscript is `%`),
and add new commands as you implement them.
Start with (a subset of) `basics.ps`!

```
$ java Interpreter < basics.ps
```

## Lab 6 Questions (page 246)

* __10.3__: Suppose you wish to fill a stack with a copy of another, maintaining the order of elements. Using only Stack operations, describe how this would be done. How many additional stacks are necessary? _(Note: the original stack and its contents should be preserved. After completing the steps that you describe, there should be two identical stacks. You may create copies of elements.)_
  * Your answer here.

* __10.4__: Suppose you wish to reverse the order of elements of a stack. Using only Stack operations, describe how this would be done. Assuming you place the result in the original stack, how many additional stacks are necessary?
  * Your answer here.

* __10.5__: Suppose you wish to copy a queue into another, preserving the order of elements. Using only Queue operations, describe how this would be done. _Also indicate the number of additional queues needed._
  * Your answer here.

# Project 1 Grocery Bagging {#mainpage}

* Author: Jason Egbert
* Class: CS 457 [Intro to AI]
* Date: 09/30/2019

## Overview

This program is designed to take in an input list of bag and item data and
determine how, if at all, they can be bagged together using the given
constraints. The constraints include number of bags, maximum capacity of each
bag, constraints for what an item can and cannot be bagged with, and the sizes
of each of the items. The program is built to be run via shell script or command
line, and is written in Java.

## Manifest

The program includes the following files:
- GroceryBagging.java - the main driver file
- Bagger.java - the file with the majority of the logic
- StateStorage.java - an interface for storage systems
- BFS.java - a BFS implementation of StateStorage
- DFS.java - a DFS implementation of StateStorage
- Bag.java - Bag object to store items
- Grocery.java - Grocery object to create items
- Solution.java - Solution object to store current state data
- Makefile - file used to build this program
- bagit - a shell script to run this program
- README.md - this file

## Building the Program

There are two primary methods to building this program. The first, and simplest,
is to utilize the Makefile. To do this, simply enter the following command into
the command line from the source directory:
```
$ make
```
This will build all of the dependencies in the directory, and create an operable
program. 

Alternatively, the program can be built using the following command in the
source directory:
```
$ javac GroceryBagging.java
or
$ javac *.java
```
This will also build all of the dependencies and compile the program.


## Features and Usage

In order to utilize this program, two methods have been provided. The first, and
most basic, is to utilize the BASH shell script bagit using the following
syntax:
```
$ bagit filename [-depth | -breadth]
```
Where filename is the name of the test file to be run through the program, and
the flags determine the search method of depth first search or breadth first
search, respectively. The test file should take the following format:
```
3                   // number of bags available
7                   // maximum bag size is 7
bread 3 + rolls     // 1st unique item size constraint
rolls 2 + bread     // 2nd unique item size constraint
squash 3 - meat     // 3rd unique item size constraint
meat 5              // 4th unique item size
lima_beans 1 - meat // 5th unique item size constraint
```
Note that the file should not contain any blank lines.
The + constraint is used to indicate that the preceding item can ONLY be bagged
with the following item/items, and no others.
The - constraint is used to indicate that the preceding item CANNOT be bagged
with the following item/items and all others are acceptable.
No constraint indicates that the item can be bagged with anything.

Once the program has determined whether or not there is a solution, the output
to the screen will appear as follows:
```
success
bread rolls
meat
squash lima_beans
```
for success, or:
```
failure
```
if no solutions are found.
For a breadth first search, all possible solutions at the depth of the first
solution will be printed to the screen. For a depth first search, only the first
solution discovered will be printed.

## Design and Testing

This project went through several phases of design, and there were several
significant changes during implementation. In my initial design I was going to 
use hash sets to hold items, and treat my items as variables. As I began to
implement this design I ran into my first problem: how to keep track of which
items were in which bags, and whether or not they needed to be removed or left
in, and further, what to do in the event that I needed to backtrack. 

In trying to figure out a suitable solution to the problems that I was facing
with my variable as the groceries, I decided that it should be straightforward
to implement a binary index algorithm to track which items could and could not
be paired together. This presented its own problem set as I found it difficult
to figure out how to create a string of bits of arbitrary length that wouldn't
eventually be overrun given a list of items of sufficient length. The solution
that I first arrived at for this particular problem was to utilize Java's
BigInteger class, which consists of integers of variable sizes, and can be fully
controlled via bit manipulation operations. I eventually found that this was
particularly difficult to control, because one cannot simply print out the value
of the BigInteger in regular binary in an arbitrary number of bits, but must
first convert to a regular integer or a long, which defeats the whole purpose of
trying to print the BigInteger to see how many bits it has and what they look
like. 

After spending far too long on this particular problem, I gave up on it and
utilized ArrayLists to accomplish the task of storing my items, and hashsets to
store the list of possible pairings. I developed a boolean switch in the Grocery
class which, when set, meant that the items in the list were the only possible
pairings for the given item, and when not set meant that any items but the items
in the list were possible pairings, and set about making the rest of my logic. 

After attending class, I realised that they idea of creating a super-object to
hold my current list of unbagged items and bags with or without items wasn't
really such a bad idea after all, and decided to change up my implementation to
this method so that I could pass around these solution states instead of trying
to figure out my items. This seemed to make the primary logic of a BFS and DFS
much simpler, but on testing I discovered that the solution objects were all
using the same set of bags. Eventually I was able to resolve this issue by
creating a method within the solution class that creates a true copy of the
solution object passed in to it and was able to perform my unique manipulations
on these new bags and items. After figuring out how to accomplish this portion
of the project, it was all down to testing and cleaning up the code.

This project was tested both with and without the Makefile for compilation, and
with and without the BASH script for functionality. Use of generated test files
and the provided solution checker programs were also used to generate input, and
confirm output.

## Known Bugs
The breadth first search runs somewhat slowly, but otherwise there are no known
bugs at this time.

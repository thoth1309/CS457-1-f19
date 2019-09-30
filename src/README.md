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

## Testing

This file was tested both with and without the Makefile for compilation, and
with and without the BASH script for functionality. Use of generated test files
and the provided solution checker programs were also used to generate input, and
confirm output.

## Known Bugs
The breadth first search runs somewhat slowly, but otherwise there are no known
bugs at this time.

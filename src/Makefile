JFLAGS = -g
JC = javac
JVM = java

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  GroceryBagging.java \
		  Bag.java \
		  Bagger.java \
		  StateStorage.java \
		  BFS.java \
		  DFS.java \
		  Grocery.java \
		  Solution.java \
		  StateStorage.java

MAIN = GroceryBagging

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class

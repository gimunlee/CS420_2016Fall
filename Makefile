JFLAGS = -g -d . -cp .;./java_cup
JC = javac
.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $<

CLASSES = \
	Parse/*.java

default: all

cupc: 
	java -classpath .;.\java_cup java_cup.Main -package Parse -parser Grm Parse\Grm.cup
	copy Grm.java Parse\Grm.java
	copy sym.java Parse\sym.java
	del Grm.java
	del sym.java
lexc: 
	java JLex.Main Parse/miniC.lex
	del Parse\Yylex.java
	ren Parse\miniC.lex.java Yylex.java
all: lexc cupc
	$(JC) $(JFLAGS) Parse/*.java

clean:
	$(RM) *.class
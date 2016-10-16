JFLAGS = -g -d .   
JC = javac
.SUFFIXES: .java .class
.java.class:
  $(JC) $(JFLAGS) $*.java

CLASSES = \
        Parse/Main.java

default: all

cupc: Parse/Grm.cup
  java -classpath ..;../java_cup java_cup.Main -package Parse -parser Grm Grm.cup
lexc: Parse/miniC.lexc
  java -classpath .;./JLex JLex.Main Parse/miniC.lex
  mv Parse/miniC.lex.java Parse/Yylex.java
all: $(CLASSES:.java=.class)

clean: $(RM) *.class
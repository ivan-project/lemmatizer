JCC = javac
JVM = java

ifeq (run,$(firstword $(MAKECMDGOALS)))
  RUN_ARGS := $(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))
  $(eval $(RUN_ARGS):;@:)
endif

.SUFFIXES: .java .class

.java.class:
	$(JCC) -classpath ".:mongo-2.10.1.jar" $*.java

CLASSES = \
    Lemmatizer.java \
    MyException.java \
    MyLogger.java \
    Rooter.java \
    StopWord.java

MAIN = Lemmatizer

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) -classpath ".:mongo-2.10.1.jar" $(MAIN) $(RUN_ARGS)

clean: 
	$(RM) *.class

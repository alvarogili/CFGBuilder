#! /bin/sh
java -jar jflex-1.6.1/jflex-1.6.1.jar src/main/java/ar/edu/unrc/asp/cfgbuilder/parser/LexicalParser.flex

java -jar jflex-1.6.1/java-cup-11a.jar -package ar.edu.unrc.asp.cfgbuilder.parser -parser Parser src/main/java/ar/edu/unrc/asp/cfgbuilder/parser/SyntacticParser.cup
mv Parser.java src/main/java/ar/edu/unrc/asp/cfgbuilder/parser/
mv sym.java src/main/java/ar/edu/unrc/asp/cfgbuilder/parser/

mvn clean install

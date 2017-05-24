Comando flex:

 java -jar jflex-1.6.1/jflex-1.6.1.jar src/main/java/ar/edu/unrc/asp/cfgbuilder/parser/LexicalParser.flex 

Comando cup:

 java -jar jflex-1.6.1/java-cup-11a.jar -package ar.edu.unrc.asp.cfgbuilder.parser -parser SyntacticParser src/main/java/ar/edu/unrc/asp/cfgbuilder/parser/SyntacticParser.cup

Comando de ejemplo para generar el PDF con el grafo a partir de un archivo dot
ejecutar:

 cd files
 sh generate_pdf.sh <nombre de archivo sin .dot> // por ejemplo: sh generate_pdf.sh CFGBuilder_of_if_else_sentence

 
package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.cfgbuilder.parser.LexicalParser;
import ar.edu.unrc.asp.cfgbuilder.parser.Parser;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java_cup.runtime.Symbol;

/**
 *
 * @author agili
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        java_cup.runtime.Scanner scanner = new LexicalParser(new FileReader(new File("src/test/resources/do_while_sentence")));
        Parser parser = new Parser(scanner);
        Symbol s = parser.parse();
        Node startNode = (Node) s.value;
        CFGBuilder cFGBuilder = new CFGBuilder();
        cFGBuilder.setStartNode(startNode);
        System.out.println(cFGBuilder.getStartNode().getName());
    }

}

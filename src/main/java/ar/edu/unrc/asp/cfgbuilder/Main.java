package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.cfgbuilder.parser.LexicalParser;
import ar.edu.unrc.asp.cfgbuilder.parser.SyntacticParser;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author agili
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        java_cup.runtime.Scanner scanner = new LexicalParser(new FileReader(new File("src/test/resources/complejo1")));
        SyntacticParser parser = new SyntacticParser(scanner);
        parser.parse();            
    }

}

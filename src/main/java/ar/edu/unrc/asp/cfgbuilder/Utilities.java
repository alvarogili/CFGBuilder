package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.cfgbuilder.parser.LexicalParser;
import ar.edu.unrc.asp.cfgbuilder.parser.Parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java_cup.runtime.Symbol;

/**
 *
 * @author agili
 */
public class Utilities {
    
    public void generateCFGBuilder(Scanner reader) {
        System.out.print("Ingrese la ruta del archivo con el código fuente: ");
        String filePath = reader.next();
        File file = new File(filePath);
        CFGBuilder cFGBuilder = null;
        try {
            java_cup.runtime.Scanner scanner = new LexicalParser(new FileReader(file));
            Parser parser = new Parser(scanner);
            Symbol s = parser.parse();
            Node startNode = (Node) s.value;
            cFGBuilder = new CFGBuilder();
            cFGBuilder.setStartNode(startNode);
            cFGBuilder.setNodeList(parser.nodeList);
            System.out.println("\nArchivo procesado correctamente.");
        } catch (FileNotFoundException ex) {
            System.out.println("\nArchivo no encontrado: " + ex.getLocalizedMessage());
            return;
        } catch (Exception ex) {
            System.out.println("\nError ejecutando el parser: " + ex.getLocalizedMessage());
            return;
        }
        while (true) {
            System.out.println("\nElija la opción deseada:");
            System.out.println("\t1: generar un archivo .dot con el CFGBuilder");
            System.out.println("\t0: Para regresar al menú principal");

            String i = reader.next();

            if ("1".equals(i)) {
                File outputFile = new File(file.getParent(), "CFGBuilder_of_" + file.getName() + ".dot");
                try {
                    cFGBuilder.generateDotFile(outputFile);
                    System.out.println("\nCFG Builder guardado en " + outputFile.getAbsolutePath());
                } catch (IOException ex) {
                    System.out.println("\nError al guardar " + outputFile.getAbsolutePath());
                }
            } else if ("0".equals(i)) {
                break;
            }
        }

    }
}

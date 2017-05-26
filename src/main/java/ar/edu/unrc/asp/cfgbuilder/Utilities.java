package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.cfgbuilder.parser.LexicalParser;
import ar.edu.unrc.asp.cfgbuilder.parser.Parser;
import ar.edu.unrc.asp.model.CFG;
import ar.edu.unrc.asp.model.Graph;
import ar.edu.unrc.asp.model.PDT;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java_cup.runtime.Symbol;

/**
 *
 * @author agili
 */
public class Utilities {

    private File file;
    private Node startNode = null;
    private List<Node> nodeList = null;

    public void readSource(Scanner reader) {
        System.out.print("Ingrese la ruta del archivo con el código fuente: ");
        String filePath = reader.next();
        file = new File(filePath);

        try {
            java_cup.runtime.Scanner scanner = new LexicalParser(new FileReader(file));
            Parser parser = new Parser(scanner);
            Symbol s = parser.parse();

            startNode = new Node("entry", "entry");
            Node n0 = (Node) s.value;

            startNode.addNexts("next", n0);
            n0.addPrevious(startNode);
            nodeList = parser.nodeList;
            nodeList.add(0, startNode);

            System.out.println("\nArchivo procesado correctamente.");
        } catch (FileNotFoundException ex) {
            System.out.println("\nArchivo no encontrado: " + ex.getLocalizedMessage());
            return;
        } catch (Exception ex) {
            System.out.println("\nError ejecutando el parser: " + ex.getLocalizedMessage());
            return;
        }
    }

    public void operations(Scanner reader) {
        while (true) {
            printHelp();
            String i = reader.next();

            Graph graph = new Graph();
            graph.setStartNode(startNode);
            graph.setNodeList(nodeList);

            if ("1".equals(i)) {
                File outputFile = new File(file.getParent(), "CFGBuilder_of_" + file.getName() + ".dot");
                try {

                    CFGBuilder cFGBuilder = new CFGBuilder();
                    cFGBuilder.setCFG((CFG) graph);
                    cFGBuilder.generateDotFile(outputFile);
                    System.out.println("\nCFG Builder guardado en " + outputFile.getAbsolutePath());
                } catch (IOException ex) {
                    System.out.println("\nError al guardar " + outputFile.getAbsolutePath());
                }

            } else if ("2".equals(i) || "3".equals(i)) {

                PDTBuilder pDTBuilder = new PDTBuilder(graph, file);
                pDTBuilder.processOperations(i);
            } else if ("4".equals(i)) {
                CDGBuilder cDGBuilder = new CDGBuilder(graph);
                cDGBuilder.generateCDG();
            } else if ("0".equals(i)) {
                break;
            }
        }

    }

    private void printHelp() {
        System.out.println("\nElija la opción deseada:");
        System.out.println("\t1: generar un archivo .dot con el CFGBuilder");
        System.out.println("\t2: Calcular Postdominators");
        System.out.println("\t3: generar un archivo .dot con el PostDominator Tree");
        System.out.println("\t4: generar un archivo .dot con el Control Dependence Graph");
        System.out.println("\t0: Para regresar al menú principal");
    }
}

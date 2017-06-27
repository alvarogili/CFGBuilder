package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.model.Node;
import ar.edu.unrc.asp.cfgbuilder.parser.LexicalParser;
import ar.edu.unrc.asp.cfgbuilder.parser.Parser;
import ar.edu.unrc.asp.model.CFG;
import ar.edu.unrc.asp.model.Constants;
import ar.edu.unrc.asp.model.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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

        parseFile(file);
    }

    public void parseFile(File file) {
        try {
            java_cup.runtime.Scanner scanner = new LexicalParser(new FileReader(file));
            Parser parser = new Parser(scanner);
            Symbol s = parser.parse();

            startNode = new Node("entry", "entry");
            Node n0 = (Node) s.value;

            startNode.addNexts(Constants.NEXT, n0);
            n0.addPrevious(startNode);
            nodeList = parser.nodeList;
            nodeList.add(0, startNode);

            System.out.println("\nArchivo procesado correctamente.");
        } catch (FileNotFoundException ex) {
            System.out.println("\nArchivo no encontrado: " + ex.getLocalizedMessage());
        } catch (Exception ex) {
            System.out.println("\nError ejecutando el parser: " + ex.getLocalizedMessage());
        }
    }

    public void operations(Scanner reader) throws IOException {
        while (true) {
            printHelp();
            String i = reader.next();
            Graph graph = new CFG();
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
                CDGBuilder cDGBuilder = new CDGBuilder((CFG) graph);
                cDGBuilder.generateCDG();
                File outputFile = new File(file.getParent(), "CDGBuilder_of_" + file.getName() + ".dot");
                cDGBuilder.generateDotFile(outputFile, graph);
            } else if ("5".equals(i)) {
                DataFlowUtilities dataFlowUtilities = new DataFlowUtilities();
                dataFlowUtilities.reachingDefs(graph);
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
        // System.out.println("\t4: generar un archivo .dot con el Control Dependence Graph");
        System.out.println("\t5: ejecutar el algoritmo \"ReachingDefs\"");
        System.out.println("\t0: Para regresar al menú principal");
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void printMapStringString(Map<String, String> map) {
        for (int i = 0; i < map.size(); i++) {
            String key = (String) map.keySet().toArray()[i];
            System.out.println(key + ": " + map.get(key));
        }
    }

    public void printMapStringListStrings(Map<String, List<String>> map, Graph graph) {
        for (int i = 0; i < map.size(); i++) {
            String key = (String) map.keySet().toArray()[i];
            System.out.print(key + "[" + graph.getNodeFromList(key).getLabel() + "]: ");
            System.out.print("{");
            if (map.get(key) == null) {
                System.out.print("{}");
            } else {
                boolean first = true;
                for (int j = 0; j < map.get(key).size(); j++) {
                    String s = map.get(key).get(j);
                    if (s != null) {
                        if (!first) {
                            System.out.print(", ");
                        }
                        System.out.print(s);
                        first = false;
                    }
                }
            }
            System.out.println("}");
        }
    }

    public void printNodes(List<Node> nodes) {
        for (Node n : nodes) {
            System.out.println(n.getName() + ": " + n.getLabel());
        }
    }
}

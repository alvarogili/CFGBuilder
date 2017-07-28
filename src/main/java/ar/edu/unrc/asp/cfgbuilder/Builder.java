/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.model.Next;
import ar.edu.unrc.asp.model.Node;
import ar.edu.unrc.asp.model.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author agili
 */
public abstract class Builder {
    
    /**
     * Genera un archivo dot
     *
     * @param outputFile archivo de salida en donde se escribirá el resultado
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void generateDotFile(File outputFile, Graph graph) throws FileNotFoundException, IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            fileOutputStream.write("digraph{\n".getBytes());
            fileOutputStream.write("\tinic[shape=point];\n\n".getBytes());
            printLabels(graph,fileOutputStream);
            fileOutputStream.write(("\n\tinic->" + graph.getStartNode().getName() + ";\n").getBytes());
            fileOutputStream.write(graph.getStartNode().generateRelations().getBytes());
            graph.getStartNode().setPrinted(true);
            printChildrens(graph.getStartNode().getNexts(), fileOutputStream);
            fileOutputStream.write("}".getBytes());
        }
    }

    private void printChildrens(List<Next> nodes, FileOutputStream fileOutputStream) throws IOException {
        for (Next next : nodes) {
            Node node = next.getNode();
            if (!node.isPrinted()) {
                fileOutputStream.write(node.generateRelations().getBytes());
                node.setPrinted(true);
                printChildrens(node.getNexts(), fileOutputStream);
            }
        }
    }

    private void printLabels(Graph graph, FileOutputStream fileOutputStream) throws IOException {
        for (Node n : graph.getNodeList()) {
            n.setPrinted(false);
            fileOutputStream.write(("\t" + n.getName() + "[label=\"" + n.getLabel() + "\"]\n").getBytes());
        }
    }   
}

package ar.edu.unrc.asp.cfgbuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that given a file with a list of statements generates a CFG
 *
 * @author agili
 */
public class CFGBuilder {

    private Node startNode = null;
    
    private List<Node> nodeList = new LinkedList<>();

    public CFGBuilder() {
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }        

    /**
     * Genera un archivo dot
     *
     * @param outputFile archivo de salida en donde se escribirá el resultado
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void generateDotFile(File outputFile) throws FileNotFoundException, IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            fileOutputStream.write("digraph{\n".getBytes());
            fileOutputStream.write("\tinic[shape=point];\n\n".getBytes());
            printLabels(fileOutputStream);
            fileOutputStream.write(("\n\tinic->" + startNode.getName() + ";\n").getBytes());
            fileOutputStream.write(startNode.generateRelations().getBytes());
            startNode.setPrinted(true);
            printChildrens(startNode.getNexts(), fileOutputStream);
            fileOutputStream.write("}".getBytes());
        }
    }

    private void printChildrens(List<Next> nodes, FileOutputStream fileOutputStream) throws IOException {
        for (Next next : nodes) {            
            Node node = next.getNode();
            if(!node.isPrinted()){
                fileOutputStream.write(node.generateRelations().getBytes());
                node.setPrinted(true);
                printChildrens(node.getNexts(), fileOutputStream);            
            }
        }
    }

    private void printLabels(FileOutputStream fileOutputStream) throws IOException {
        for(Node n: nodeList){
            fileOutputStream.write(("\t" + n.getName()+"[label=\""+n.getLabel()+"\"]\n").getBytes());
        }
    }

}

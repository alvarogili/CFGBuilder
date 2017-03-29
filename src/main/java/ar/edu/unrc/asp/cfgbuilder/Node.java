package ar.edu.unrc.asp.cfgbuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Nodo genérico
 *
 * @author agili
 */
public class Node implements Comparable<Node>{

    ///Nombre del nodo
    private String name;
    ///Nodos siguientes, donde la clave representa el label
    private List<Next> nexts = new ArrayList<>();

    private boolean end = false;

    public Node() {
    }

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Next> getNexts() {
        return nexts;
    }

    public void setNexts(List<Next> nexts) {
        this.nexts = nexts;
    }
    
    /**
     * Given a label return all available nodes with it 
     * @param label label to search
     * @return List of available nodes
     */
    public List<Node> getNexts(String label){
        List<Node> nextNodes = new ArrayList<>();
        for(Next n : getNexts()){
            if(n.getLabel().equals(label)){
                nextNodes.add(n.getNode());
            }
        }
        return nextNodes;
    }

    /**
     * Add the next nodes with the label
     *
     * @param label Label
     * @param node next node
     */
    public void addNexts(String label, Node node) {

        Next next = new Next(label, node);
        nexts.add(next);

    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    /**
     * Hace el complemento del nodo y sus hijos
     */
    public void complementar() {
        end = !end;
        for (Next next : nexts) {
            next.getNode().complementar();
        }
    }

    public Boolean matchLabel(String stream) {
        if (stream.isEmpty()) {
            if (isEnd()){
                //la cadena terminó y es un nodo final            
                return true;
            } else {
                return false;
            }
        }

        String ch = stream.substring(0, 1);
        for (Next n : nexts) {
            if (n.getLabel().equals(ch)) {
                Node node = n.getNode();
                if (node.matchLabel(stream.substring(1, stream.length()))) {
                    return true;
                }
            }
        }
        //casos lambda
        for (Next n : nexts) {
            if (n.getLabel().equals("_")) {
                Node node = n.getNode();
                if (node.matchLabel(stream)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Genera las relaciones para el archivo dot entre el nodo y sus hijos
     *
     * @return un string en formato dot
     */
    public String generateRelations() {
        String result = "";
        for (Next next : nexts) {
            result += "\t" + getName() + "->" + next.getNode().getName() + " [label=\"" + next.getLabel() + "\"];\n";
        }
        if (isEnd()) {
            result += "\t" + getName() + "[shape=doublecircle];\n";
        }
        return result;
    }

    @Override
    public int compareTo(Node o) {
        return this.name.compareTo(o.getName());
    }
}

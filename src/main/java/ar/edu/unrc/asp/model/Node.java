package ar.edu.unrc.asp.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Nodo genérico
 *
 * @author agili
 */
public class Node implements Comparable<Node>{

    ///Nombre del nodo
    private String name;
    //Expresión a la que representa el nodo
    private String label;
    ///posicion
    private Integer position;
    ///Nodos siguientes, donde la clave representa el label
    private List<Next> nexts = new LinkedList<>();
    
    private List<Node> previous = new LinkedList<>();
    
    private boolean printed = false;

    private boolean end = true;
    
    private boolean start = false;

    private String declaredVariable = null;

    private List<String> usedVariables = new LinkedList<>();

    public Node() {
    }

    public Node(String name, String label) {
        this.name = name;
        setLabel(label);
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }        

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    private void calculateUsedVariables(String label) {
        if(specialNode(label))
            return;
        String expression = label;
        if (expression.contains(":=")) {
            //me quedo con la parte derecha de la asignación
            expression = expression.substring(expression.indexOf(":=") + 2).trim();
        }
        //si lo asignado es un número, no hay uso de variables
        if (isNumeric(expression)) {
            return;
        }
        String symbol = haveTwoParts(expression);
        if (symbol == null) {
            // es una sentencia de tipo a := b
            usedVariables.add(expression);
        }else{
            String firstPart = expression.substring(0, expression.indexOf(symbol)).trim();
            String secondPart = expression.substring(expression.indexOf(symbol) + symbol.length(), expression.length()).trim();
            if(!isNumeric(firstPart))
                usedVariables.add(firstPart);
            if(!isNumeric(secondPart))
                usedVariables.add(secondPart);
        }

    }

    private String haveTwoParts(String sentence) {
        if(sentence.contains("+"))
            return "+";
        if(sentence.contains("-"))
            return "-";
        if(sentence.contains("*"))
            return "*";
        if(sentence.contains("/"))
            return "/";
        if(sentence.contains(">"))
            return ">";
        if(sentence.contains("<"))
            return "<";
        if(sentence.contains("=="))
            return "==";
        return null;
    }

    private boolean isNumeric(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private boolean specialNode(String temp) {
        if (temp.contains("endIf") || temp.contains("endDo") ||
                temp.contains("entry") || temp.contains("start") || temp.contains("exit")) {
            return true;
        }
        return false;
    }


    /**
     * Dada una sentencia del tipo a := b, retorna a, sino retorna null
     *
     * @param label
     * @return
     */
    protected void extractVariable(String label) {
        int indexOfAssing = label.indexOf(":=");
        if (indexOfAssing != -1) {
            declaredVariable = label.substring(0, indexOfAssing).trim();
        }
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }        

    public List<Next> getNexts() {
        return nexts;
    }

    public void setNexts(List<Next> nexts) {
        this.nexts = nexts;
    }

    public List<Node> getPrevious() {
        return previous;
    }

    public void addPrevious(Node previous) {
        this.previous.add(previous);
    }        

    public boolean isPrinted() {
        return printed;
    }

    public void setPrinted(boolean printed) {
        this.printed = printed;
    }        
    
    /**
     * Given a label return all available nodes with it 
     * @param label label to search
     * @return List of available nodes
     */
    public List<Node> getNexts(String label){
        List<Node> nextNodes = new LinkedList<>();
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
        end = false;
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
    
     /**
     * Genera las relaciones para el archivo dot entre el nodo y sus padres
     *
     * @return un string en formato dot
     */
    public String generatePreviousRelations() {
        String result = "";
        for (Node n : previous) {
            result += "\t" + getName() + "->" + n.getName() + " [label=\"previous\"];\n";
        }
        return result;
    }

    @Override
    public int compareTo(Node o) {
        return this.name.compareTo(o.getName());
    }

    /**
     * Retorna el último nodo del trozo de código corriente
     *
     * @return
     */
    public Node getLast() {
        if (getNexts().isEmpty()) {
            return this;
        } else {
            return getNexts().get(0).getNode().getLast();
        }
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((Node)obj).name);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.name);
        return hash;
    }


    public String getDeclaredVariable() {
        extractVariable(label);
        return declaredVariable;
    }

    public List<String> getUsedVariables(){
        calculateUsedVariables(label);
        return usedVariables;
    }
}

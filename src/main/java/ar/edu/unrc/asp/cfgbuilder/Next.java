
package ar.edu.unrc.asp.cfgbuilder;

/**
 * Representa a un nodo siguiente
 * @author agili
 */
public class Next {
    
    //label de la arista
    private String label;
    //nodo destino
    private Node node;

    public Next(String label, Node node) {
        this.label = label;
        this.node = node;
    }        

    public String getLabel() {
        return label;
    }

    public Node getNode() {
        return node;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setNode(Node node) {
        this.node = node;
    }
    
    
}

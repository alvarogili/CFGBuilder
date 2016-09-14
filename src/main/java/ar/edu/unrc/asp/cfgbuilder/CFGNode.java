package ar.edu.unrc.asp.cfgbuilder;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agili
 */
class CFGNode {

    private String node;
    private String edge;

    public CFGNode() {
    }

    public CFGNode(String node, String edge) {
        this.node = node;
        this.edge = edge;
    }

    public String getEdge() {
        return edge;
    }

    public String getNode() {
        return node;
    }

    public void setEdge(String edge) {
        this.edge = edge;
    }

    public void setNode(String node) {
        this.node = node;
    }
    
    
    
}

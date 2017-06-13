/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.model.Graph;
import ar.edu.unrc.asp.model.Node;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author agili
 */
class DataFlowUtilities {
    private Graph graph;
    private Map<String,String> gen = new HashMap<>();
    private Map<String,List<Node>> kill = new HashMap<>();

    public DataFlowUtilities(Graph graph) {
        this.graph = graph;
    }
    
    /**
     * Dada una sentencia del tipo a := b, retorna a, sino retorna null
     * @param assignment
     * @return 
     */
    protected String getVariable(String assignment){
        int indexOfAssing = assignment.indexOf(":=");
        if(indexOfAssing == -1){
            return null;
        }else{
            return assignment.substring(0, indexOfAssing).trim();
        }
    }
    
    protected void calculateGen(List<Node> nodes){
        for(Node n: nodes){
            gen.put(n.getName(), getVariable(n.getLabel()));
        }
    }
    
}

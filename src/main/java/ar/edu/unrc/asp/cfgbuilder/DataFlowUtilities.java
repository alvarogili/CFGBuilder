/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.model.Graph;
import ar.edu.unrc.asp.model.Node;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author agili
 */
class DataFlowUtilities {

    private Graph graph;

    public DataFlowUtilities(Graph graph) {
        this.graph = graph;
    }

    /**
     * Dada una sentencia del tipo a := b, retorna a, sino retorna null
     *
     * @param assignment
     * @return
     */
    protected String getVariable(String assignment) {
        int indexOfAssing = assignment.indexOf(":=");
        if (indexOfAssing == -1) {
            return null;
        } else {
            return assignment.substring(0, indexOfAssing).trim();
        }
    }

    protected Map<String, String> calculateGen(List<Node> nodes) {
        Map<String, String> gen = new HashMap<>();
        for (Node n : nodes) {
            String variable = getVariable(n.getLabel());
            if (n.getPosition() != null) {
                if (variable != null) {
                    gen.put(n.getName(), n.getPosition().toString() + ":" + variable);
                } else {
                    gen.put(n.getName(), null);
                }
            }
        }
        return gen;
    }

    protected Map<String, List<String>> calculateKill(List<Node> nodes) {
        Map<String, List<String>> kill = new HashMap<>();
        for (Node n : nodes) {
            String label = n.getLabel();
            String variable = getVariable(label);
            String name = n.getName();
            if (variable != null) {
                List<String> variables = new LinkedList<>();
                for (Node n2 : nodes) {
                    String variable2 = getVariable(n2.getLabel());
                    if (variable2 != null) {
                        variables.add(n2.getPosition().toString() + ":" + getVariable(n2.getLabel()));
                    }
                }
                kill.put(name, variables);
            }
        }
        return kill;
    }

}

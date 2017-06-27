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
                    if (variable2 != null && variable.equals(variable2)) {
                        variables.add(n2.getPosition().toString() + ":" + getVariable(n2.getLabel()));
                    }
                }
                kill.put(name, variables);
            } else {
                kill.put(name, null);
            }
        }
        return kill;
    }

    public void reachingDefs(Graph cfg) {
        Map<String, String> gens = calculateGen(cfg.getNodeList());
        Map<String, List<String>> kills = calculateKill(cfg.getNodeList());
        Map<String, List<String>> ints = new HashMap<>();
        Map<String, List<String>> outs = new HashMap<>();

        for (Node n : cfg.getNodeList()) {
            List firstOut = new LinkedList();
            firstOut.add(gens.get(n.getName()));
            outs.put(n.getName(), firstOut);
        }
        boolean change = true;
        while (change) {
            change = false;
            for (Node n : cfg.getNodeList()) {
                String nodeName = n.getName();
                ints.put(nodeName, unionOutsP(n.getPrevious(), outs));
                List<String> oldOuts = outs.get(nodeName);
                List<String> substraction = calculateSubstraction(ints.get(nodeName), kills.get(nodeName));
                if(!substraction.contains(gens.get(nodeName))){
                    substraction.add(0, gens.get(nodeName));
                }
                outs.put(nodeName,substraction);
                if (!outs.get(nodeName).equals(oldOuts)) {
                    change = true;
                }
            }
        }
        
        Utilities utilities = new Utilities();
        System.out.println("\n\nLista de INTs:");
        utilities.printMapStringListStrings(ints, cfg);
        System.out.println("\n\nLista de OUTs:");
        utilities.printMapStringListStrings(outs, cfg);
    }

    private List<String> unionOutsP(List<Node> previous, Map<String, List<String>> outs) {
        List<String> union = new LinkedList<>();
        for (Node p : previous) {
            List<String> outP = outs.get(p.getName());
            for (String out : outP) {
                if (!union.contains(out)) {
                    union.add(out);
                }
            }
        }
        return union;
    }

    private List<String> calculateSubstraction(List<String> a, List<String> b) {
        List<String> substraction = new LinkedList<>();
        if(b == null){
            return a;
        }
        for (String n : a) {
            if (!b.contains(n)) {
                substraction.add(n);
            }
        }
        return substraction;
    }
        

}

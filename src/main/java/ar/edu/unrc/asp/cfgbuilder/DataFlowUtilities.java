/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.model.Graph;
import ar.edu.unrc.asp.model.Node;
import ar.edu.unrc.asp.model.DUPair;
import ar.edu.unrc.asp.model.Variable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author agili
 */
class DataFlowUtilities {

    Map<String, List<String>> ints = new HashMap<>();
    Map<String, List<String>> outs = new HashMap<>();


    protected Map<String, String> calculateGen(List<Node> nodes) {
        Map<String, String> gen = new HashMap<>();
        for (Node n : nodes) {
            String variable = n.getDeclaredVariable();
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
            String variable = n.getDeclaredVariable();
            String name = n.getName();
            if (variable != null) {
                List<String> variables = new LinkedList<>();
                for (Node n2 : nodes) {
                    String variable2 = n2.getDeclaredVariable();
                    if (variable2 != null && variable.equals(variable2)) {
                        variables.add(n2.getPosition().toString() + ":" + n2.getDeclaredVariable());
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
                if (!substraction.contains(gens.get(nodeName))) {
                    substraction.add(0, gens.get(nodeName));
                }
                outs.put(nodeName, substraction);
                if (!outs.get(nodeName).equals(oldOuts)) {
                    change = true;
                }
            }
        }
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
        if (b == null) {
            return a;
        }
        for (String n : a) {
            if (!b.contains(n)) {
                substraction.add(n);
            }
        }
        return substraction;
    }

    public Map<String, List<String>> getInts() {
        return ints;
    }

    public Map<String, List<String>> getOuts() {
        return outs;
    }

    public Map<Node, List<DUPair>> computeDefUsePairs(Graph cfg) {
        Map<Node, List<DUPair>> pairs = new HashMap<>();

        //genero la lista de variables que se definen y usan
        for (Node n : cfg.getNodeList()) {

            List<String> usedVariables = n.getUsedVariables();
            if (!usedVariables.isEmpty()) {
                List<DUPair> duPairs = processUsedVariables(usedVariables, cfg, n);
                pairs.put(n, duPairs);
            } else {
                pairs.put(n, null);
            }

        }

        return pairs;
    }

    private List<DUPair> processUsedVariables(List<String> usedVariables, Graph cfg, Node n) {
        List<DUPair> duPairs = new LinkedList<>();
        for (String usedVariable : usedVariables) {
            for (Node n2 : cfg.getNodeList()) {
                String declaredVariable = n2.getDeclaredVariable();
                if (declaredVariable != null && declaredVariable.equals(usedVariable)) {
                    DUPair duPair = new DUPair(n.getPosition(), usedVariable, n2.getPosition(), declaredVariable);
                    if (!duPairs.contains(duPair) && n2.getPosition() != n.getPosition())
                        duPairs.add(duPair);
                }
            }
        }
        return duPairs;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.model.Constants;
import ar.edu.unrc.asp.model.Node;
import ar.edu.unrc.asp.model.Graph;
import ar.edu.unrc.asp.model.PDT;
import ar.edu.unrc.asp.model.Pair;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author familia
 */
public class CDGBuilder extends Builder {

    private final Graph graph;

    CDGBuilder(Graph graph) {
        this.graph = graph;
    }

    /**
     * Retorna el CDG generado
     * @return
     * @throws IOException 
     */
    public Graph generateCDG() throws IOException {

        // paso 1
        Node startNode = new Node(Constants.START, Constants.START);

        Node oldStartNode = graph.getStartNode();
        startNode.addNexts(Constants.NEXT, oldStartNode);
        oldStartNode.addPrevious(startNode);
        graph.setStartNode(startNode);
        graph.getNodeList().add(0, startNode);

        Node endNode = graph.getEndNode();
        endNode.addPrevious(startNode);
        startNode.addNexts(Constants.NEXT, endNode);

        //paso 2
        PDTBuilder pDTBuilder = new PDTBuilder(graph, null);

        //paso 3.a
        List<Pair> pairs = generatePairs(graph.getNodeList());

        //paso 3.b
        generateAncestors(pairs, pDTBuilder.calculatePostDominators());
        
        List<Pair> pairsForCDG = new LinkedList<>();
        //paso 3.c
        for(Pair p: pairs){
            if(p.getA().equals(p.getAncestor())){
                pairsForCDG.add(p);
            }
        }        
        return buildCDG(pairsForCDG);
    }

    /**
     * Dada una lista de nodos genera todos los pares posibles tales que, dado
     * el par <A,B>, B no es antecesor de A
     *
     * @param nodes Lista de nodos
     * @return lista de pares
     */
    public List<Pair> generatePairs(List<Node> nodes) {
        List<Pair> pairs = new LinkedList<>();
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (i != j) {
                    Node A = nodes.get(i);
                    Node B = nodes.get(j);
                    if (!A.getPrevious().contains(B)) {
                        Pair p1 = new Pair(A, B);
                        if (!pairs.contains(p1)) {
                            pairs.add(p1);
                        }
                    }
                    if (!B.getPrevious().contains(A)) {
                        Pair p2 = new Pair(B, A);
                        if (!pairs.contains(p2)) {
                            pairs.add(p2);
                        }
                    }
                }
            }
        }
        return pairs;
    }

    /**
     * Obtiene los ansenstros comunes mas proximos del par
     *
     * @param pairs
     * @param postdominatos
     */
    protected void generateAncestors(List<Pair> pairs,  Map<String,List<Node>> postdominatos) {
        for (Pair pair : pairs) {
            Node A = pair.getA();
            Node B = pair.getB();
            List<Node> postDomA = postdominatos.get(A.getName());
            List<Node> postDomB = postdominatos.get(B.getName());
            for (Node n : postDomA) {
                //como se que están ordenados, el primero de A que encuentre en
                //B es el primer ansestro comun
                if (postDomB.contains(n)) {
                    pair.setAncestor(n);
                }
            }
        }
    }

    private Graph buildCDG(List<Pair> pairs) {
        Graph cdg = new Graph();
        Pair startPair = getPairWithStart(pairs);
        Node a = new Node(startPair.getA().getName(), startPair.getA().getLabel());
        Node b = new Node(startPair.getB().getName(), startPair.getB().getLabel());
        a.addNexts(Constants.NEXT, b);
        b.addPrevious(a);
        cdg.setStartNode(a);
        cdg.addNodeToList(a);
        cdg.addNodeToList(b);
        for(Pair p: pairs){
            a = new Node(p.getA().getName(), p.getA().getLabel());
            b = new Node(p.getB().getName(), p.getB().getLabel());
            int indexA = cdg.getNodeList().indexOf(a);
            int indexB = cdg.getNodeList().indexOf(b);
            if(indexA > -1){
                a = cdg.getNodeList().get(indexA);
            }
            if(indexB > -1){
                b = cdg.getNodeList().get(indexB);
            }
            a.addNexts(Constants.NEXT, b);
            b.addPrevious(a);
            if (indexA == -1) {
                cdg.getNodeList().add(a);
            }
            if (indexB == -1) {
                cdg.getNodeList().add(b);
            }
        }
        return cdg;
    }

    
    private Pair getPairWithStart(List<Pair> pairs){
        Pair result = null;
        for(Pair p: pairs){
            if(p.getA().getName().equals(Constants.START)){
                result = p;
                pairs.remove(p);
                break;
            }
        }
        return result;
    }
}

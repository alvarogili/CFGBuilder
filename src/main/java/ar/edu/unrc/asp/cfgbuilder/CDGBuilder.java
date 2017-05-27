/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.model.Node;
import ar.edu.unrc.asp.model.Graph;
import ar.edu.unrc.asp.model.PDT;
import ar.edu.unrc.asp.model.Pair;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author familia
 */
public class CDGBuilder extends Builder {

    private final Graph graph;

    CDGBuilder(Graph graph) {
        this.graph = graph;
    }

    public void generateCDG() throws IOException {

        // paso 1
        Node startNode = new Node("start", "start");

        Node oldStartNode = graph.getStartNode();
        startNode.addNexts("next", oldStartNode);
        oldStartNode.addPrevious(startNode);
        graph.setStartNode(startNode);
        graph.getNodeList().add(0, startNode);

        Node endNode = graph.getEndNode();
        endNode.addPrevious(startNode);

        //paso 2
        PDTBuilder pDTBuilder = new PDTBuilder(graph, null);
        PDT pdt = pDTBuilder.generatePostDomTree();

        //paso 3.a
        List<Pair> pairs = generatePairs(pdt.getNodeList());
        
        //paso 3.b
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
                        pairs.add(p1);
                    }
                    if (!B.getPrevious().contains(A)) {
                        Pair p2 = new Pair(B, A);
                        pairs.add(p2);
                    }
                }
            }
        }
        return pairs;
    }

}

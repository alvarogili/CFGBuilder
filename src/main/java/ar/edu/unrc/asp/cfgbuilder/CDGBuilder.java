/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.model.Graph;
import ar.edu.unrc.asp.model.PDT;
import java.io.IOException;

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
        
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.model;

import ar.edu.unrc.asp.cfgbuilder.Node;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author agili
 */
public class Graph {
    
    private Node startNode = null;
    
    private List<Node> nodeList = new LinkedList<>();
    
    private Node endNode = null;

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
        this.startNode.setStart(true);
    }

    public List<Node> getNodeList() {
        return nodeList;
    }   
    
    public void addNodeToList(Node node){
        this.nodeList.add(node);
    }
    
     public void setEndNode(Node exit) {
        endNode = exit;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
        generateExitNode();
    }
    
    private void generateExitNode() {
        //genero nodo final
        Node exit = new Node("exit", "exit");
        endNode = getEnd();
        endNode.addNexts("next", exit);
        exit.addPrevious(endNode);
        getNodeList().add(getNodeList().size(), exit);
        endNode = exit;
    }

    public Node getEnd() {
        Node last = null;
        for (Node n : getNodeList()) {
            if (n.isEnd()) {
                last = n;
                break;
            }
        }
        return last;
    }
}

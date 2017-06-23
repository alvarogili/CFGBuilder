/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.model.CFG;
import ar.edu.unrc.asp.model.Constants;
import ar.edu.unrc.asp.model.Node;
import ar.edu.unrc.asp.model.Graph;
import ar.edu.unrc.asp.model.Next;
import ar.edu.unrc.asp.model.PDT;
import ar.edu.unrc.asp.model.Pair;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author familia
 */
public class CDGBuilder extends Builder {

    private final Graph cfg;

    CDGBuilder(Graph cfg) {
        this.cfg = cfg;
    }

    /**
     * Retorna el CDG generado
     *
     * @return
     * @throws IOException
     */
    public Graph generateCDG() throws IOException {

        // paso 1
        Node startNode = new Node(Constants.START, Constants.START);

        Node oldStartNode = cfg.getStartNode();
        startNode.addNexts(Constants.NEXT, oldStartNode);
        oldStartNode.addPrevious(startNode);

        Node endNode = cfg.getEndNode();
        endNode.addPrevious(startNode);
        startNode.addNexts(Constants.NEXT, endNode);
        cfg.setStartNode(startNode);
        cfg.getNodeList().add(0, startNode);
        
        //paso 2
        PDTBuilder pDTBuilder = new PDTBuilder(cfg, null);
        PDT pdt = pDTBuilder.generatePostDomTree();

        //paso 3.a
        List<Pair> pairs = generatePairs(cfg, pdt);

        //paso 3.b
        generateAncestors(pairs);
        //paso 3.c
        return generateCDG(pairs, pdt);
    }

    /**
     * busca los pares (A,B) en CFG(ACFG) tales que B no es ansestro de A en PDT
     * el par <A,B>, B no es antecesor de A
     *
     * @param cfg
     * @param pdt
     * @return lista de pares
     */
    public List<Pair> generatePairs(Graph cfg, PDT pdt) {
        List<Pair> pairs = new LinkedList<>();
        Node A = cfg.getStartNode();
        generatePairsRecursive(A, pairs, pdt);
        return pairs;
    }

    private void generatePairsRecursive(Node A, List<Pair> pairs, PDT pdt) {
        for (Next n : A.getNexts()) {
            Node B = n.getNode();
            //verifico si B es ansestro de A en PDT
            Node aInPdt = pdt.getNodeFromList(A.getName());
            if (!aInPdt.getPrevious().contains(B)) {
                Pair p = new Pair(A, B);
                if (!pairs.contains(p)) {
                    pairs.add(p);
                }
            }
            generatePairsRecursive(B, pairs, pdt);
        }
    }

    /**
     * Obtiene los ansenstros comunes mas proximos del par
     *
     * @param pairs
     */
    protected void generateAncestors(List<Pair> pairs) {
        for (Pair pair : pairs) {
            Node A = pair.getA();            
            Node B = pair.getB();
            Node L = getCommonAnsestor(A, B);
//            no funciona, el start no queda como cabeza del PDT
//            List<Node> postDomA = postdominatos.get(A.getName());
//            List<Node> postDomB = postdominatos.get(B.getName());
//            for (Node n : postDomA) {
//                //como se que están ordenados, el primero de A que encuentre en
//                //B es el primer ansestro comun
//                if (postDomB.contains(n)) {
//                    pair.setL(n);
//                }
//            }
        }
    }
    
    private List<Node> getPathToStart(Node initial){
        List<Node> path = new LinkedList<>();
        path.add(initial);
        if(!initial.getName().equals(Constants.START)){
            path.addAll(getPathToStart(initial.getNexts().get(0).getNode()));
        }
        return path;
    }

    private Node getCommonAnsestor(Node A, Node B) {
        List<Node> ancestrosA = getPathToStart(A);
        List<Node> ancestrosB = getPathToStart(B);
        
        
        return null;
    }

    private Graph generateCDG(List<Pair> pairs, PDT pdt) {
        Graph cdg = new Graph();
        cdg.setStartNode(pdt.getNodeFromList("start"));
        cdg.addNodeToList(cdg.getStartNode());

        for (Pair p : pairs) {
            Node A = cdg.getNodeFromList(p.getA().getName());
            Node B = p.getB();
            Node L = p.getL();
            if (A == null) {
                A = p.getA();
                cdg.addNodeToList(A);
            }

        }

        return cdg;
    }
}

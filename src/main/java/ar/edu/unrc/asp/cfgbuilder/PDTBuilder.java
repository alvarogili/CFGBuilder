/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.model.Graph;
import ar.edu.unrc.asp.model.PDT;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * PostDominator tree builder
 *
 * @author agili
 */
public class PDTBuilder extends Builder{

    private final Graph graph;
    private final File source;
    private int nodeCounter = 0;

    public PDTBuilder(Graph graph, File source) {
        this.graph = graph;
        this.source = source;
    }

    public Map<String, List<Node>> calculatePostDominators() {
        Map<String, List<Node>> postdominators = new HashMap<>();
        List<Node> localNodeList = new LinkedList<>(graph.getNodeList());
        Collections.reverse(localNodeList);
        /**
         * ***** 1. D(exit) = {exit} ********
         */
        Node exit = localNodeList.get(0);
        List<Node> exitNodes = new LinkedList<>();
        exitNodes.add(exit);
        postdominators.put(exit.getName(), exitNodes);
        /**
         * ******************************
         */
        /**
         * ***** 2. for each node n in N -{exit} do D(n) = N ********
         */
        List<Node> N = new LinkedList<>(localNodeList.subList(1, localNodeList.size()));
        for (int i = 1; i < localNodeList.size(); i++) {
            postdominators.put(localNodeList.get(i).getName(), new LinkedList<>(N));
        }
        /**
         * ***** 3. while changes to any D(n) occurs do ********
         */
        boolean changes = true;
        while (changes) {
            changes = false;
            /**
             * ***** 4. for n in N -{exit} do ********
             */
            for (Node n : N) {
                /**
                 * ***** 5. D(n) = {n} ∪ (∩ D(p) for all inmediate predecessor
                 * p of n) ********
                 */
                List<Node> unionList = new LinkedList<>();
                unionList.add(n);
                if (!n.getNexts().isEmpty()) {
                    List<Node> intersection = calculatIntersection(n, postdominators);
                    unionList.addAll(intersection);
                }
                changes = (changes || haveChanges(postdominators.get(n.getName()), unionList));

                postdominators.put(n.getName(), unionList);

            }
        }
        return postdominators;
    }

    private List<Node> calculatIntersection(Node node, Map<String, List<Node>> postdominators) {
        List<Node> intersection = null;
        for (Next n : node.getNexts()) {
            if (intersection == null) {
                intersection = postdominators.get(n.getNode().getName());
            } else {
                List<Node> postd = postdominators.get(n.getNode().getName());
                intersection = intersection(intersection, postd);
            }
        }
        return intersection;
    }

    public List<Node> intersection(List<Node> list1, List<Node> list2) {
        List<Node> list = new LinkedList<>();

        for (Node n : list1) {
            if (list2.contains(n)) {
                list.add(n);
            }
        }

        return list;
    }

    /**
     * Verifica si hubo cambios entre {
     *
     * @param source} y {
     * @ inersection}
     * @param source
     * @param intersection
     * @return
     */
    private boolean haveChanges(List<Node> source, List<Node> intersection) {
        if (source.size() != intersection.size()) {
            return true;
        } else {
            for (Node n : source) {
                if (!intersection.contains(n)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Genera el Post dominator tree
     *
     * @param outputFile
     * @throws IOException
     */
    public PDT generatePostDomTree() throws IOException {
        List<Node> N = new LinkedList<>(graph.getNodeList());
        Collections.reverse(N);
        Node exit = N.get(0);
        //1. let n0 be the root of DT
        PDT pdt = new PDT();
        Node n0 = new Node(exit.getName(), exit.getLabel());
        pdt.setStartNode(n0);
        pdt.addNodeToList(n0);

        //2. put n0 on queue Q;
        Queue<Node> Q = new ArrayDeque<>();
        Q.add(exit);

        //3. for each node n in N do D(n) =D(n)−n enddo;
        Map<String, List<Node>> postdominators = calculatePostDominators();
        //borro cada n de su D(n)
        for(String key: postdominators.keySet()){
            List<Node> list = postdominators.get(key);
            for(Node n: list){
                if(n.getName().equals(key)){
                    list.remove(n);
                    break;
                }
            }
        }
        //4. while Q is not empty do
        while (!Q.isEmpty()) {
            //5. m = the next node on Q(remove it from Q)
            Node m = Q.remove();
            //6. for each node n in N such that D(n) is nonempty do
            for (Node n : N) {
                if (postdominators.get(n.getName()) == null || postdominators.get(n.getName()).isEmpty()) {
                    continue;
                }
                List<Node> Dn = postdominators.get(n.getName());
                //7. if D(n) contains m
                if (Dn.contains(m)) {
                    //8. D(n) = D(n) -m
                    Dn.remove(m);
                    postdominators.put(n.getName(), Dn);
                    //9. if D(n) is now empty
                    if (Dn.isEmpty()) {
                        Node mInPdt = pdt.getNodeFromList(m.getName());
                        Node nForPdt = new Node(n.getName(), n.getLabel());
                        mInPdt.addNexts("child", nForPdt);
                        pdt.addNodeToList(nForPdt);
                        Q.add(n);
                    }
                }
            }
        }
        return pdt;
    }

    public void processOperations(String option) {
        if ("2".equals(option)) {

            Map<String, List<Node>> postdominators = calculatePostDominators();
            printNodesMap(postdominators);
        } else if ("3".equals(option)) {
            File outputFile = new File(source.getParent(), "PostDomTree_of_" + source.getName() + ".dot");
            try {
                PDT pdt = generatePostDomTree();
                generateDotFile(outputFile, pdt);
                
                System.out.println("\nPostDominator Tree guardado en " + outputFile.getAbsolutePath());
            } catch (IOException ex) {
                System.out.println("\nError al guardar " + outputFile.getAbsolutePath());
            }
        }
    }

    public void printNodesMap(Map<String, List<Node>> nodesMap) {
        System.out.println("PostDominators list:");
        Set<String> keys = nodesMap.keySet();
        for (String key : keys) {
            System.out.print(key + ": {");
            List<Node> values = nodesMap.get(key);
            for (int i = 0; i < values.size(); i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(values.get(i).getName());
            }
            System.out.println("}");
        }
    }
    
    String getNodeName(){
        return "n" + nodeCounter++;
    }


}

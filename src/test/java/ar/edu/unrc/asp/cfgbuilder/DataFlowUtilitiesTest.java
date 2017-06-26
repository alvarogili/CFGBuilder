/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.model.Graph;
import ar.edu.unrc.asp.model.Node;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author familia
 */
public class DataFlowUtilitiesTest {

    public DataFlowUtilitiesTest() {
    }

    /**
     * Test of getVariable method, of class DataFlowUtilities.
     */
    @Test
    public void testGetVariable() {
        System.out.println("getVariable");
        String assignment = "a := b";
        String assignment2 = "a:=b";
        String assignment3 = "a:= b +q +q";
        String assignment4 = "a>q";
        DataFlowUtilities instance = new DataFlowUtilities(null);
        assertEquals("a", instance.getVariable(assignment));
        assertEquals("a", instance.getVariable(assignment2));
        assertEquals("a", instance.getVariable(assignment3));
        assertNull(instance.getVariable(assignment4));
    }

    /**
     * Test of calculateGen method, of class DataFlowUtilities.
     */
    @Test
    public void testCalculateGen() {
        Utilities utilities = new Utilities();
        utilities.parseFile(new File("files/sources/if_sentence"));

        Graph graph = new Graph();
        graph.setStartNode(utilities.getStartNode());
        graph.setNodeList(utilities.getNodeList());

        DataFlowUtilities dataFlowUtilities = new DataFlowUtilities(graph);
        Map<String, String> gen = dataFlowUtilities.calculateGen(graph.getNodeList());
        assertEquals("0:id", gen.get("n0"));
        assertEquals("1:id", gen.get("n1"));
        assertEquals("6:id", gen.get("n6"));
        assertEquals("7:id2", gen.get("n7"));
        assertNull(gen.get("n4"));
    }

    /**
     * Test of calculateKill method, of class DataFlowUtilities.
     */
    @Test
    public void testCalculateKill() {
        System.out.println("calculateKill");
        Utilities utilities = new Utilities();
        utilities.parseFile(new File("files/sources/if_sentence"));

        Graph graph = new Graph();
        graph.setStartNode(utilities.getStartNode());
        graph.setNodeList(utilities.getNodeList());
        DataFlowUtilities dataFlowUtilities = new DataFlowUtilities(graph);
        Map<String, List<String>> kill = dataFlowUtilities.calculateKill(utilities.getNodeList());
        assertTrue(kill.get("n0").contains("0:id"));
        assertTrue(kill.get("n1").contains("0:id"));
        assertTrue(kill.get("n1").contains("1:id"));
        assertTrue(kill.get("n6").contains("0:id"));
        assertTrue(kill.get("n6").contains("1:id"));
        assertTrue(kill.get("n6").contains("6:id"));
        assertNull(kill.get("n4"));
    }

}

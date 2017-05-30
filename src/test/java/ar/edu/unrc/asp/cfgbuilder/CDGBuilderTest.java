/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.model.Node;
import ar.edu.unrc.asp.model.PDT;
import ar.edu.unrc.asp.model.Pair;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author familia
 */
public class CDGBuilderTest {
    
    public CDGBuilderTest() {
    }
        

    /**
     * Test of generatePairs method, of class CDGBuilder.
     */
    @Test
    public void testGeneratePairs() {
        System.out.println("generatePairs");
        List<Node> nodes = null;
        CDGBuilder instance = null;
        List<Pair> expResult = null;
        List<Pair> result = instance.generatePairs(nodes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateAncestors method, of class CDGBuilder.
     */
    @Test
    public void testGenerateAncestors() {
        System.out.println("generateAncestors");
        List<Pair> pairs = null;
        PDT pdt = null;
        CDGBuilder instance = null;
        instance.generateAncestors(pairs, pdt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

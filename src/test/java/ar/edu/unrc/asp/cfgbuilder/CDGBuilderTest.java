/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.model.Node;
import ar.edu.unrc.asp.model.PDT;
import ar.edu.unrc.asp.model.Pair;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

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
        /*
        PDT:
              a
             / \
            b   c
            |
            d
        Pares: {a,c},{a,b},{b,c},{b,d},{c,d},{d,c},{a,d},{d,a},{c,b}
        */
        Node a = new Node("a", "a");        
        Node b = new Node("b", "b");
        Node c = new Node("c", "c");
        Node d = new Node("d", "d");
        b.addPrevious(a);
        c.addPrevious(a);
        d.addPrevious(b);
        List<Node> nodes = new LinkedList<>();
        nodes.add(a);
        nodes.add(b);
        nodes.add(c);
        nodes.add(d);
        CDGBuilder instance = new CDGBuilder(null);        
        List<Pair> result = instance.generatePairs(nodes);
        assertEquals(9, result.size());
        assertTrue(result.contains(new Pair(a,c)));
        assertTrue(result.contains(new Pair(a,b)));
        assertFalse(result.contains(new Pair(d,b)));
        assertFalse(result.contains(new Pair(c,a)));
        assertFalse(result.contains(new Pair(b,a)));
    }

    /**
     * Test of generateAncestors method, of class CDGBuilder.
     */
    @Test
    public void testGenerateAncestors() {
        System.out.println("generateAncestors");
        
        List<Pair> pairs = new LinkedList<>();
        PDT pdt = new PDT();
        Node a = new Node("a", "a");
        Node b = new Node("b", "b");
        Node c = new Node("c", "c");
        Node d = new Node("d", "d");
        List<Node> postDA = new LinkedList<>();
        postDA.add(0, a);
        postDA.add(1, c);
        postDA.add(2, d);
        
        List<Node> postDB = new LinkedList<>();
        postDB.add(0, b);
        postDB.add(1, d);
        
        Map<String, List<Node>> postD = new HashMap<>();
        postD.put("a", postDA);
        postD.put("b", postDB);
        pdt.setPostdominators(postD);
        Pair p = new Pair(a, b);
        pairs.add(p);

        CDGBuilder instance = new CDGBuilder(pdt);
        instance.generateAncestors(pairs, pdt);
        assertEquals("d", p.getAncestor().getName());
    }
    
}

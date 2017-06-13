/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.cfgbuilder;

import org.junit.Test;
import static org.junit.Assert.*;

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
    
}

/**
 * Copyright (c) 2016 Ascentio Technologies S.A All rights reserved.
 * 
* This program is confidential and proprietary to Ascentio Technologies S.A,
 * and may not be copied, reproduced, modified, disclosed to others, published
 * or used, in whole or in part, without the express prior written permission of
 * Ascentio Technologies S.A.
 */
package ar.edu.unrc.asp.cfgbuilder;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author agili
 */
public class CFGBuilderTest {
    
    public CFGBuilderTest() {
    }

    /**
     * Test of getBasicBlocks method, of class CFGBuilder.
     */
    @Test
    public void testGetBasicBlocks() throws Exception {
        CFGBuilder cFGBuilder = new CFGBuilder();
        cFGBuilder.getBasicBlocks("src/test/resources/do_while_sentence");
    }
    
}

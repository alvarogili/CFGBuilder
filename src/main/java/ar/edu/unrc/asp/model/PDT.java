/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.model;

import java.util.List;
import java.util.Map;

/**
 * PostDominator Tree
 * @author agili
 */
public class PDT extends Graph{
    
    Map<String, List<Node>> postdominators;

    public PDT() {
    }               

    public Map<String, List<Node>> getPostdominators() {
        return postdominators;
    }

    public void setPostdominators(Map<String, List<Node>> postdominators) {
        this.postdominators = postdominators;
    }        
}

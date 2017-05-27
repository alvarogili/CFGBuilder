/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.model;

/**
 * PostDominator Tree
 * @author agili
 */
public class PDT extends Graph{
    

    public PDT() {
    }        
   
    public Node getNodeFromList(String name){
        for(Node n:super.getNodeList()){
            if(n.getName().equals(name)){
                return n;               
            }
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.model;

/**
 * Representa un par de nodos
 * @author agili
 */
public class Pair {
    
    private Node A;
    private Node B;
    private Node ancestor;

    public Pair(Node A, Node B) {
        this.A = A;
        this.B = B;
    }

    public Node getA() {
        return A;
    }

    public void setA(Node A) {
        this.A = A;
    }

    public Node getB() {
        return B;
    }

    public void setB(Node B) {
        this.B = B;
    }

    public Node getAncestor() {
        return ancestor;
    }

    public void setAncestor(Node ancestor) {
        this.ancestor = ancestor;
    }
        
}

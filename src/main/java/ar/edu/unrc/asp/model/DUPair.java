/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.asp.model;

/**
 *
 * @author agili
 */
public class DUPair {
    private int usedPosition;
    private String used;
    private int declaredPosition;
    private String declared;
    //nodos para generar relaciones en DDG
    private Node declaredNode;
    private Node usedNode;

    public DUPair(int usedPosition, String used, int declaredPosition, String declared) {
        this.usedPosition = usedPosition;
        this.used = used;
        this.declaredPosition = declaredPosition;
        this.declared = declared;
    }

    public int getUsedPosition() {
        return usedPosition;
    }

    public void setUsedPosition(int usedPosition) {
        this.usedPosition = usedPosition;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public void setDeclared(String declared) {
        this.declared = declared;
    }

    public void setDeclaredPosition(int declaredPosition) {
        this.declaredPosition = declaredPosition;
    }

    public int getDeclaredPosition() {
        return declaredPosition;
    }

    public String getDeclared() {
        return declared;
    }

    public Node getDeclaredNode() {
        return declaredNode;
    }

    public void setDeclaredNode(Node declaredNode) {
        this.declaredNode = declaredNode;
    }

    public Node getUsedNode() {
        return usedNode;
    }

    public void setUsedNode(Node usedNode) {
        this.usedNode = usedNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DUPair)) return false;

        DUPair duPair = (DUPair) o;

        if (getUsedPosition() != duPair.getUsedPosition()) return false;
        if (getDeclaredPosition() != duPair.getDeclaredPosition()) return false;
        if (!getUsed().equals(duPair.getUsed())) return false;
        return getDeclared().equals(duPair.getDeclared());
    }

    @Override
    public int hashCode() {
        int result = getUsedPosition();
        result = 31 * result + getUsed().hashCode();
        result = 31 * result + getDeclaredPosition();
        result = 31 * result + getDeclared().hashCode();
        return result;
    }
}

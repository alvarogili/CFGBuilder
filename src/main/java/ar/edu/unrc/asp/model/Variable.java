package ar.edu.unrc.asp.model;

/**
 * Created by familia on 05/07/17.
 */
public class Variable {

    private int position;
    private String name;

    public Variable(int position, String name) {
        this.position = position;
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getName().equals(((Variable)obj).getName());
    }
}

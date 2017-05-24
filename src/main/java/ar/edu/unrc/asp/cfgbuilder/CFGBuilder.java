package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.model.CFG;
import ar.edu.unrc.asp.model.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Class that given a file with a list of statements generates a CFG
 *
 * @author agili
 */
public class CFGBuilder extends Builder{

    
    private CFG cfg;

    public CFGBuilder() {
    }
    
    public void setCFG(CFG cfg){
        this.cfg = cfg;
    }      
    
    public void generateDotFile(File outputFile) throws FileNotFoundException, IOException {
        super.generateDotFile(outputFile, cfg); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}

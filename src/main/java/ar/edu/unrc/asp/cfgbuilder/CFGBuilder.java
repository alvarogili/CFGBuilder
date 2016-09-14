package ar.edu.unrc.asp.cfgbuilder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class that given a file with a list of statements generates a CFG
 *
 * @author agili
 */
public class CFGBuilder {

    public CFGBuilder() {
    }

    /**
     *
     * @param inputFile
     * @return
     */
    public List<CFGNode> getBasicBlocks(String inputFile) throws IOException {
        List<CFGNode> basicBlocks = new ArrayList<>();
        List<String> statements = new ArrayList<>();        
        try (Stream<String> lines = Files.lines(Paths.get(inputFile), Charset.defaultCharset())) {
            lines.forEachOrdered(statements::add);            
        }
        CFGNode basicBlock = null;
        for(String statement: statements){
            statement = statement.trim();
            if(basicBlock == null 
                    || statement.toLowerCase().startsWith("if")
                    || statement.toLowerCase().startsWith("do")){
            }
            System.out.println(statement.trim());
        }
        return basicBlocks;
    }
}

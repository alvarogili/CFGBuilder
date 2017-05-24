package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.cfgbuilder.parser.LexicalParser;
import ar.edu.unrc.asp.cfgbuilder.parser.Parser;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java_cup.runtime.Symbol;

/**
 *
 * @author agili
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        System.out.println("\nAnálisis Estatico Programas");
        System.out.println("Proyecto Potenciar Graduación");
        Utilities utilities = new Utilities();
        Scanner reader = new Scanner(System.in);
        do {
            printHelp();

            String i = reader.next();

            if (null != i) {
                switch (i) {
                    case "1":
                        utilities.readSource(reader);
                        break;
                    case "2":
                        utilities.operations(reader);
                        break;
                    case "0":
                        reader.close();
                        return;
                    default:
                        printHelp();
                        break;
                }
            }
        } while (true);
    }

    private static void printHelp() {
        System.out.println("\nElija una opción:");
        System.out.println("\t1: Para procesar archivo con código fuente");
        System.out.println("\t2: Para realizar operaciones sobre el código fuente ingresado");
        System.out.println("\t0: para salir");
    }

}

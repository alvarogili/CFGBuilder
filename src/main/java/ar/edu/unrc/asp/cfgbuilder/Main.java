package ar.edu.unrc.asp.cfgbuilder;

import ar.edu.unrc.asp.model.CFG;
import ar.edu.unrc.asp.model.Graph;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author agili
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {

        Utilities utilities = new Utilities();
        Scanner reader = new Scanner(System.in);
        do {
            System.out.println("\nAnálisis Estatico Programas");
            System.out.println("Proyecto Potenciar Graduación\n\n\n");            

            printHelp();

            String i = reader.next();

            if (null != i) {
                switch (i) {
                    case "1":
                        utilities.readSource(reader);
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
        System.out.println("\t1: Para ingresar la ruta del código fuente");
        System.out.println("\t0: para salir");
    }

}

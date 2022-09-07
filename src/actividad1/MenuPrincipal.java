package actividad1;

import java.util.Formatter;
import java.util.Scanner;

public class MenuPrincipal {

    public void menuPrincipal() {

        Documento documento = new Documento();
        Scanner sn = new Scanner(System.in);
        String opcion;

        while (true) {
            System.out.flush();
            System.out.println("1.- Leer un archivo");
            System.out.println("2.- Leer todos los archivos");
            System.out.println("3.- Remover etiquetas HTML");
            System.out.println("4.- Extraer palabras");
            System.out.println("5.- Salir");

            System.out.print("\nIngresa una opcion: ");
            opcion = sn.nextLine();
            switch (opcion) {
                case "1":
                    documento.tiempoLeerArchivo(documento.obtenerNombre());
                    break;
                case "2":
                    Double tiempoTotal = 0.0;
                    for (int i = 2; i < 504; i++) {
                        Formatter fmt = new Formatter();
                        fmt.format("%03d", i);
                        tiempoTotal = tiempoTotal + documento.tiempoLeerArchivo(fmt.toString());
                        fmt.close();
                    }
                    tiempoTotal = tiempoTotal + documento.tiempoLeerArchivo("simple");
                    tiempoTotal = tiempoTotal + documento.tiempoLeerArchivo("medium");
                    tiempoTotal = tiempoTotal + documento.tiempoLeerArchivo("hard");
                    documento.generarLog(tiempoTotal);
                    break;
                case "3":
                    documento.limpiarHTML("003");
                    break;
                case "4":
                    break;
                case "5":
                    System.out.println("Esta seguro que desea salir? (S/N)");
                    opcion = sn.nextLine();
                    if(opcion.equals("S") || opcion.equals("s"))
                        System.exit(0);
                    break;
                default:
                    System.out.println("Ingresa una opcion valida.");
            }
        }
    }
}

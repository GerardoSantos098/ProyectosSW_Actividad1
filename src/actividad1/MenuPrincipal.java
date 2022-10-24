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
            System.out.println("4.- Listar palabras");
            System.out.println("5.- Consolidar palabras por alfabeto");
            System.out.println("6.- Generar token");
            System.out.println("7.- Tokenizar archivos");
            System.out.println("8.- Generar token general");
            System.out.println("9.- Generar Posting");
            System.out.println("10.- Eliminar StopList");
            System.out.println("11.- Weight tokens");
            System.out.println("12.- Salir");

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
                    documento.generarLog(tiempoTotal, 1);
                    break;
                case "3":
                    tiempoTotal = 0.0;
                    for (int i = 2; i < 504; i++) {
                        Formatter fmt = new Formatter();
                        fmt.format("%03d", i);
                        tiempoTotal = tiempoTotal + documento.limpiarHTML(fmt.toString());
                        fmt.close();
                    }
                    tiempoTotal = tiempoTotal + documento.limpiarHTML("simple");
                    tiempoTotal = tiempoTotal + documento.limpiarHTML("medium");
                    tiempoTotal = tiempoTotal + documento.limpiarHTML("hard");
                    documento.generarLog(tiempoTotal, 2);
                    break;
                case "4":
                    tiempoTotal = 0.0;
                    for (int i = 2; i < 504; i++) {
                        Formatter fmt = new Formatter();
                        fmt.format("%03d", i);
                        tiempoTotal = tiempoTotal + documento.acomodarPalabrasPorLista(fmt.toString());
                        fmt.close();
                    }
                    tiempoTotal = tiempoTotal + documento.acomodarPalabrasPorLista("simple");
                    tiempoTotal = tiempoTotal + documento.acomodarPalabrasPorLista("medium");
                    tiempoTotal = tiempoTotal + documento.acomodarPalabrasPorLista("hard");
                    documento.generarLog(tiempoTotal, 3);
                    break;
                case "5":
                    tiempoTotal = 0.0;
                    for (int i = 2; i < 504; i++) {
                        Formatter fmt = new Formatter();
                        fmt.format("%03d", i);
                        tiempoTotal = tiempoTotal + documento.consolidarArchivo(fmt.toString());
                        fmt.close();
                    }
                    tiempoTotal = tiempoTotal + documento.consolidarArchivo("simple");
                    tiempoTotal = tiempoTotal + documento.consolidarArchivo("medium");
                    tiempoTotal = tiempoTotal + documento.consolidarArchivo("hard");
                    documento.generarLog(tiempoTotal, 4);
                    break;
                case "6":
                    documento.tokens("tokens");
                    break;
                case "7":
                    tiempoTotal = 0.0;
                    for (int i = 2; i < 504; i++) {
                        Formatter fmt = new Formatter();
                        fmt.format("%03d", i);
                        tiempoTotal = tiempoTotal + documento.tokenizarArchivos(fmt.toString());
                        fmt.close();
                    }
                    tiempoTotal = tiempoTotal + documento.tokenizarArchivos("simple");
                    tiempoTotal = tiempoTotal + documento.tokenizarArchivos("medium");
                    tiempoTotal = tiempoTotal + documento.tokenizarArchivos("hard");
                    documento.generarLog(tiempoTotal, 5);
                    break;
                case "8":
                    tiempoTotal = documento.tokenGeneral("002");
                    documento.generarLog(tiempoTotal, 6);
                    break;
                case "9": //Actividad 7
                    tiempoTotal = documento.generarPosting("002");
                    documento.generarLog(tiempoTotal, 6);
                    break;
                case "10":
                    System.out.print("\nProcesando... ");

                    System.out.print("Se ha generado con exito! ");
                    break;
                case "11":
                    System.out.println("\nProcesando... ");

                    System.out.println("Se ha generado con exito! ");
                    break;
                case "12":
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

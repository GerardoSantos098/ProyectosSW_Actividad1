package actividad1;

import java.io.*;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collections;

public class Documento {

    private static final String PATH2  = "\\SinHTML\\";
    private static final String PATH3  = "\\Consolidados\\";
    long inicio = 0;
    long fin = 0;
    StringBuffer s;

    public double acomodarPalabrasPorLista(String nombreArchivo){
        calculaTiempo(1, nombreArchivo + ".html", 3);
        String documento = leerArchivo("\\SinHTML\\"+nombreArchivo, "html");
        // Genera nuevo archivo
        try {
            String ruta = "C:\\Files\\PorLista\\" + nombreArchivo + ".txt" ;
            File file = new File(ruta);
            file.delete();
            file.createNewFile();
            String[] split = documento.split("/n");
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            PrintWriter pw = new PrintWriter(fw);
            for (int i=0; i<split.length; i++) {
                if (!split[i].equals("")) {
                    pw.println(split[i]);
                    //System.out.println(split[i]);
                }
            }
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return calculaTiempo(2, nombreArchivo + ".html", 3);
    }

    public double limpiarHTML(String nombreArchivo){
        calculaTiempo(1, nombreArchivo + ".html", 2);
        String documento = leerArchivo(nombreArchivo, "html");
        //System.out.println("ANTES: " + documento);
        documento = documento.replaceAll("\\<.*?\\>", " ");
        documento = documento.replaceAll("/n", " ");
        documento = documento.replaceAll("\\[ ", " ");
        documento = documento.replaceAll("\\] ", " ");
        documento = documento.replaceAll("\\[", " ");
        documento = documento.replaceAll("\\]", " ");
        documento = documento.replaceAll("\\[ ", " ");
        documento = documento.replaceAll("\\] ", " ");
        documento = documento.replaceAll("\\p{Punct}", " ");
        documento = documento.replaceAll("\\p{IsPunctuation}", " ");
        String[] split = documento.split(" ");
        // Genera nuevo archivo
        try {
            String ruta = "C:\\Files\\SinHTML\\" + nombreArchivo + ".html" ;
            File file = new File(ruta);
            file.delete();
            file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            PrintWriter pw = new PrintWriter(fw);
            for (int i=0; i<split.length; i++) {
                if (!split[i].equals("")) {
                    pw.println(split[i]);
                    //System.out.println(split[i]);
                }
            }
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return calculaTiempo(2, nombreArchivo + ".html", 2);
    }

    public static Double tiempoLeerArchivo(String nombre){
        long inicio = System.currentTimeMillis();
        String path = "C:\\FIles\\"+nombre+".html";
        try{
            File rec = new File(path);
            FileReader lector = new FileReader(rec);
            BufferedReader buffer = new BufferedReader(lector);
            buffer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
        }
        long fin = System.currentTimeMillis();
        double tiempo = (double) ((fin - inicio));
        System.out.println(path + " ----- "+tiempo +" Milisegundos");
        generarLog(path, tiempo, 1);
        return tiempo;
    }

    public static String leerArchivo(String nombre, String tipo){
        String path = "C:\\Files\\"+nombre+"."+tipo;
        String texto="";
        try{
            File rec = new File(path);
            FileReader lector = new FileReader(rec);
            BufferedReader buffer = new BufferedReader(lector);
            String temporal = "";
            while((temporal = buffer.readLine()) != null){
                texto = texto + temporal +"/n";
            }
            return texto;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return texto;
    }

    public static void generarLog(String path, Double tiempo, int num) {
        try {
            String ruta = "C:\\FIles\\" +"a"+num+"_matricula.txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(path + " ----- "+tiempo +" Milisegundos");
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void generarLog(Double tiempoTotal, int num) {
        try {
            String ruta = "C:\\Files\\" +"a"+num+"_matricula.txt";
            File file = new File(ruta);
            //file.delete();
            //file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Tiempo total: " + " ----- "+tiempoTotal +" Milisegundos");
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String obtenerNombre(){
        System.out.print("Ingresa nombre del archivo: ");
        Scanner sn = new Scanner(System.in);
        return sn.nextLine();
     }

    public double consolidarArchivo(String nombreArchivo){
        calculaTiempo(1, nombreArchivo+".txt", 4);
        String documento = leerArchivo("PorLista\\"+nombreArchivo, "txt");
        String[] split = documento.split("/n");
        ArrayList<String> palabras = new ArrayList<>();
        for (int i=0; i<split.length; i++){
            split[i] = split[i].toLowerCase();
            palabras.add(split[i]);
        }
        Collections.sort(palabras);

        // Genera nuevo archivo
        try {
            String ruta = "C:\\Files\\Consolidados\\" + nombreArchivo + ".txt" ;
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            PrintWriter pw = new PrintWriter(fw);
            for (int i=0; i<palabras.size(); i++) {
                if (!palabras.get(i).equals("")) {
                    pw.println(palabras.get(i));
                    //System.out.println(palabras.get(i));
                }
            }
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
        }

        return calculaTiempo(2, nombreArchivo+".txt", 4);
    }

    public void tokens(String nombreArchivo){
        String documento = leerArchivo(PATH3+"simple", "txt");
        String documento2 = leerArchivo(PATH3+"medium", "txt");
        String documento3 = leerArchivo(PATH3+"hard", "txt");
        documento = documento + documento2 + documento3;
        String[] split = documento.split("/n");
        ArrayList lista = new ArrayList();
        for(String temporal3: split){
            lista.add(temporal3);
        }
        Collections.sort(lista);
        String temporal = lista.get(0).toString();
        int repeticiones = 0;

        // Genera nuevo archivo
        try {
            String ruta = "C:\\Files\\tokens.txt" ;
            File file = new File(ruta);
            file.delete();
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            PrintWriter pw = new PrintWriter(fw);
            for(int i=0; i< lista.size();i++){
                if(temporal.equals(lista.get(i))){
                    temporal = lista.get(i).toString();
                    repeticiones++;
                }else{
                    pw.println(temporal+" "+repeticiones);
                    temporal = lista.get(i).toString();
                    repeticiones = 0;
                    i--;
                }
            }
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double tokenizarArchivos(String nombreArchivo){
        calculaTiempo(1, nombreArchivo+".txt", 5);
        String documento = leerArchivo(PATH3+nombreArchivo, "txt");
        String[] split = documento.split("/n");
        String temporal = split[0];
        int repeticiones = 0;

        // Genera nuevo archivo
        try {
            String ruta = "C:\\Files\\Tokens\\"+nombreArchivo+".txt" ;
            File file = new File(ruta);
            file.delete();
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            PrintWriter pw = new PrintWriter(fw);
            for(int i=0; i< split.length;i++){
                if(temporal.equals(split[i])){
                    temporal = split[i];
                    repeticiones++;
                }else{
                    pw.println(temporal+" "+repeticiones);
                    temporal = split[i];
                    repeticiones = 0;
                    i--;
                }
            }
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return calculaTiempo(2, nombreArchivo+".txt", 5);
    }


    public double tokenGeneral(String nombreArchivo){
        calculaTiempo(1, nombreArchivo+".txt", 6);
        String documento = leerArchivo("Tokens\\"+nombreArchivo, "txt");
        String[] split1 = documento.split("/n");
        String[][] palabras = new String[split1.length][3];
        for (int i=0; i<split1.length; i++){
            String[] split2 = split1[i].split(" ");
            palabras[i][0] = split2[0];
            palabras[i][1] = split2[1];
            palabras[i][2] = "1";
        }
        for (int i = 3; i < 504; i++) {
            Formatter fmt = new Formatter();
            fmt.format("%03d", i);
            String documentoTemporal = leerArchivo("Tokens\\"+fmt.toString(), "txt");
            String[] split3 = documentoTemporal.split("/n");
            String[][] palabrasTemporal = new String[split3.length][2];
            for (int iterador=0; iterador<split3.length; iterador++){
                String[] splitTemporal = split3[iterador].split(" ");
                palabrasTemporal[iterador][0] = splitTemporal[0];
                palabrasTemporal[iterador][1] = splitTemporal[1];
            }
            fmt.close();

            for(int x=0; x<palabras.length;x++){
                for(int y=0; y<palabrasTemporal.length; y++){
                    if(palabras[x][0].equals(palabrasTemporal[y][0])){
                        palabras[x][1] = (Integer.parseInt(palabras[x][1]) + Integer.parseInt(palabrasTemporal[y][1]))+ "";
                        palabras[x][2] = (Integer.parseInt(palabras[x][2]) + 1) + "";
                    }
                }
            }
            try{
                String ruta = "C:\\Files\\\\"+"palabrasArchivo.txt" ;
                File file = new File(ruta);
                file.delete();
                file.createNewFile();
                FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                PrintWriter pw = new PrintWriter(fw);
                for(int intera = 0; intera<palabras.length; intera++){
                    pw.println(palabras[intera][0]+" "+palabras[intera][1]+" "+palabras[intera][2]);
                }
                pw.close();
            } catch (IOException ex) {
                Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return calculaTiempo(2, nombreArchivo+".txt", 6);
    }

    public double generarPosting(String nombreArchivo){
        calculaTiempo(1, nombreArchivo+".txt", 7); //Abre Calculo de tiempo

        String documento = leerArchivo("palabrasArchivo","txt"); //Archivo con todas las palabras
            String[] split1 = documento.split("/n"); //Separa por reglon

            //Separa registros por nombre, repeticiones, numero de archivos donde se encuentra
            String[] palabrasTemporal = new String[split1.length];
            for (int iterador=0; iterador<split1.length; iterador++){
                String[] splitTemporal = split1[iterador].split(" ");
                palabrasTemporal[iterador] = splitTemporal[0];
            }

            // Compara palabras con las del diccionario
            ArrayList<String> posting = new ArrayList<>();
            for(int x=0; x<palabrasTemporal.length; x++){ //Recorre el archivo de palabras
                for(int x2 = 2; x2<4 ; x2++){
                    Formatter fmt = new Formatter();
                    fmt.format("%03d", x2);
                    String documentoTemporal = leerArchivo("Tokens\\"+fmt.toString(), "txt");
                    String[] split3 = documentoTemporal.split("/n");
                    int repeticiones =1;
                    for(int x4=0; x4<split3.length;x4++){
                        if(palabrasTemporal[x].equals(split3[x4])){
                            repeticiones++;
                        }
                    }
                    posting.add(fmt +" "+ repeticiones);
                }
            }
            for(int a = 0; a<posting.size();a++){
                System.out.println(posting.get(a));
            }
            // Guarda en documento
        try{
            String ruta = "C:\\Files\\\\"+"posting.txt" ;
            File file = new File(ruta);
            file.delete();
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            PrintWriter pw = new PrintWriter(fw);
            for(int intera = 0; intera<posting.size(); intera++){
                pw.println(posting.get(intera));
            }
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
        }

        return calculaTiempo(2, nombreArchivo+".txt", 7); //Cierra Calculo de tiempo y retorna el tiempo que se llevo la funcion
    }

    public double calculaTiempo(int opcion, String path, int num){
        double tiempo = 0.0000;
        if(opcion == 1)
            inicio = System.currentTimeMillis();
        else if(opcion == 2){
            fin = System.currentTimeMillis();
            tiempo = (double) ((fin - inicio));
            System.out.println(path + " ----- "+tiempo +" Milisegundos");
            generarLog(path, tiempo, num);
        }
        else{
            System.out.println("Esta no es una opcion valida. 1 start o 2 end");
        }
        return tiempo;
    }
}

package actividad1;

import java.io.*;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Documento {

    StringBuffer s;

    public static void separarPalabras(){
        String documento = leerArchivo("003", "html");
        System.out.println("ANTES: " + documento);
        documento = documento.replaceAll("<html>/n", "");
        documento = documento.replaceAll("<head>/n", "");
        documento = documento.replaceAll("<title>", "");
        documento = documento.replaceAll("<title>/n", "");
        documento = documento.replaceAll("<body>/n", "");
        documento = documento.replaceAll("<center>/n", "");
        documento = documento.replaceAll("<h1>", "");
        documento = documento.replaceAll("<h1>/n", "");
        documento = documento.replaceAll("</center>/n<hr", "");
        documento = documento.replaceAll("size=7", "");
        documento = documento.replaceAll("width=75%>", "");
        documento = documento.replaceAll("<a", "");
        documento = documento.replaceAll("href=\"#followups\">", "");
        documento = documento.replaceAll("</a>", "");
        documento = documento.replaceAll("</title>/n", "");
        documento = documento.replaceAll("</head>/n", "");
        documento = documento.replaceAll("</h1>/n", "");
        documento = documento.replaceAll("/n<center>", "");
        documento = documento.replaceAll("href=\"#postfp\">", "");
        documento = documento.replaceAll("href=\"", "");
        documento = documento.replaceAll("<p>/n", "");
        documento = documento.replaceAll("<br>", "");
        documento = documento.replaceAll("<br>/n<br>/n<br><hr", "");
        documento = documento.replaceAll("<p>/n", "");
        documento = documento.replaceAll("\">", "");
        documento = documento.replaceAll("/n/n<hr", "");
        documento = documento.replaceAll("name=\"", "");
        documento = documento.replaceAll(":/n<ul><!--insert:", "");
        documento = documento.replaceAll("35-->/n<!--top:", "");
        documento = documento.replaceAll("9--><li>", "");
        documento = documento.replaceAll("39.html\">Re", "");
        documento = documento.replaceAll("<b>", "");

        String[] split = documento.split(" ");
        for (int i=0; i<split.length; i++)
            if(!split[i].equals(""))
                System.out.println( i + " - " + split[i]);

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
        generarLog(path, tiempo);
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

    public static void generarLog(String path, Double tiempo)
    {
        try {
            String ruta = "C:\\FIles\\" +"a1_matricula.txt";
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

    public static void generarLog(Double tiempoTotal)
    {
        try {
            String ruta = "C:\\FIles\\" +"a1_matricula.txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Tiempo total en abrir los archivos " + " ----- "+tiempoTotal +" Milisegundos");
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

}

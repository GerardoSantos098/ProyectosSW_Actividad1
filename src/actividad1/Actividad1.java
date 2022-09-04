package actividad1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gerardo
 */
public class Actividad1 {
    public static void main(String[] args) {
        System.out.println("Archivo: "+leerArchivo("a1_matricula", "txt"));
        generarLog("PRUEBA", 0.00);
        System.out.println("Archivo2: "+leerArchivo("a1_matricula", "txt"));
        menuPrincipal();
        
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
            Logger.getLogger(Actividad1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Actividad1.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Actividad1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Actividad1.class.getName()).log(Level.SEVERE, null, ex);
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
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(leerArchivo("a1_matricula", "txt")+path + " ----- "+tiempo +" Milisegundos");
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(Actividad1.class.getName()).log(Level.SEVERE, null, ex);
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
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(leerArchivo("a1_matricula", "txt") + "Tiempo total en abrir los archivos " + " ----- "+tiempoTotal +" Milisegundos");
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Actividad1.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    public static String obtenerNombre(){
        System.out.print("Ingresa nombre del archivo: ");
        Scanner sn = new Scanner(System.in);
        return sn.nextLine();
    }
    
    public static void menuPrincipal()
    {
        Scanner sn = new Scanner(System.in);
        String opcion;
        boolean bandera = true;
      
        while(bandera){
            System.out.flush();
            System.out.println("1.- Leer un archivo");
            System.out.println("2.- Leer todos los archivos");
        
            System.out.print("\nIngresa una opcion: ");
            opcion = sn.nextLine();
            switch(opcion){
            case "1":
                tiempoLeerArchivo(obtenerNombre());
                bandera = false;
            break;
            case "2":
                Double tiempoTotal = 0.0;
                for(int i=2; i<504; i++){
                    Formatter fmt = new Formatter();
                    fmt.format("%03d",i);
                    tiempoTotal = tiempoTotal + tiempoLeerArchivo(fmt.toString());
                    fmt.close();
                }
                tiempoTotal = tiempoTotal + tiempoLeerArchivo("simple");
                tiempoTotal = tiempoTotal + tiempoLeerArchivo("medium");
                tiempoTotal = tiempoTotal + tiempoLeerArchivo("hard");
                generarLog(tiempoTotal);
                bandera = false;
            break;
            default:
                System.out.println("Ingresa una opcion valida.");
          }
        }
        
    }    
}

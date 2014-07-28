import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Generador {
	public static void main(String[] args) {
        /* Codigo tomado de:
         * www.developerpages.gr
         * Publicado en abril del año 2012
         * Crea un archivo de text con los argumentos especificados.
         */
        
        FileWriter outFile;
		int num;

        try {
            outFile = new FileWriter("numeros.txt");
            PrintWriter out = new PrintWriter(outFile);
            /*Generacion de 2000 numeros aleatorios y escritura a archivo .txt*/
            for(int i = 0; i < 2000; i++){
            	Random generator= new Random();	
    			num = generator.nextInt(100);
    			System.out.println(num);
    			out.println(num);
            }
            out.close();        
        } catch (IOException ex) {
            Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class SortDriver {

	public static void main(String[] args) {
		File txt = null;
		ArrayList<String> lista = null;
				//Creacion del archivo para lectura.
				try{
					txt = new File("numeros.txt");
					lista = new ArrayList<String>();
				}
				//Excepcion que se lanza cuando el archivo no existe.
				catch(NullPointerException e){
					JOptionPane.showMessageDialog(null, "No existe el archivo.", "ERROR", 
							JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
			    try{
			    	//Creacion de scanner especializado para el archivo.
			    	Scanner s = new Scanner(txt);
					//Lee todo el archivo; almacena en la matriz. 
			        while (s.hasNextLine()) {
			            lista.add(s.nextLine());
			        }
			        //Se cierra el Scanner para evitar "leaks".
			        s.close();
			    } 
			    //Excepcion que se lanza cuando hay un error en lectura.
			    catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Archivo no encontrado.", "ERROR", 
							JOptionPane.ERROR_MESSAGE);
					System.exit(0);
			    }
	
			    for (String s : lista)
			    	System.out.println(s);	
	}
	


}

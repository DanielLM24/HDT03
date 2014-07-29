import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;


public class SortDriver<E> {

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
				lista.add(s.nextLine().toString());
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
		insertion(lista);
		bubble(lista);
		merge(lista);
	}

	public static void insertion(ArrayList<String> lista){
		ArrayList<Integer> coleccion = new ArrayList<Integer>();
		for(int i = 0; i < lista.size(); i++){
			int temp= Integer.parseInt(lista.get(i));
			coleccion.add(i, temp);
		}
		for(int j=1; j < coleccion.size(); j++){
			int valor = coleccion.get(j);
			while(j>0 && coleccion.get(j-1) > valor){
				coleccion.add(j-1, valor);
				coleccion.remove(j+1);
				j--;
			}
		}
		newFile(coleccion,"numeros1.txt");
	}
	public static void bubble(ArrayList<String> lista){
		int j = 0;
		int temp = 0;
		boolean intercambio = true;
		ArrayList<Integer> coleccion = new ArrayList<Integer>();
		for(int i = 0; i < lista.size(); i++){
			int num= Integer.parseInt(lista.get(i));
			coleccion.add(i, num);
		}
		while(intercambio){
			intercambio = false;
			j++;
			for (int i = 0; i < coleccion.size() - j; i++) {                                       
				if (coleccion.get(i) > coleccion.get(i+1)) {                          
					temp = coleccion.get(i);
					coleccion.add(i, coleccion.get(i+1));
					coleccion.remove(i+1);
					coleccion.remove(i+1);
					coleccion.add(i+1, temp);
					intercambio = true;
				}
			} 
		}

		newFile(coleccion,"numeros2.txt");
	}
	public static void merge(ArrayList<String> lista){
		ArrayList<Integer> coleccion = new ArrayList<Integer>();
		for(int i = 0; i < lista.size(); i++){
			int temp= Integer.parseInt(lista.get(i));
			coleccion.add(i, temp);
		}
		//Prueba
		Collections.sort(coleccion);
		newFile(coleccion, "numeros3.txt");
	}
	public static void newFile(ArrayList<Integer> lista, String nombre){
		try {
			FileWriter outFile;
			outFile = new FileWriter(nombre);
			PrintWriter out = new PrintWriter(outFile);
			for(int s:lista){
				String cadena = Integer.toString(s); 
				out.println(cadena);
			}
			out.close();        
		} catch (IOException ex) {
			Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}

/* Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * 
 * Peter Bennett 13243
 * Santiago González 13263
 * Daniel Lara Moir 13424
 * 
 * Seccion 20
 * SortDriver.java
 * Programa que toma un archivo de texto con números aleatorios y los ordena utilizando distintos algoritmos.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
		//Llamado a metodos con algoritmos para clasificar datos.
		insertionSort(lista);
		bubbleSort(lista);
		ArrayList<Integer> list = new ArrayList<Integer>();
		list = convert(lista);
		list = mergeSort(list);
		newFile(list, "numeros3.txt");
		ArrayList<Integer> list_1 = new ArrayList<Integer>();
		list_1 = convert(lista);
		list_1 = quickSort(list_1);
		newFile(list_1, "numeros4.txt");
	}

	public static void insertionSort(ArrayList<String> lista){
		//Convirtiendo los datos leidos a enteros para comparacion.
		ArrayList<Integer> coleccion = new ArrayList<Integer>();
		coleccion = convert(lista);
		//Iterando el conjunto de valores, empezando por el segundo.
		for(int j=1; j < coleccion.size(); j++){
			int valor = coleccion.get(j);
			//Si el valor evaluado es menor que el anterior, toma su lugar.
			while(j>0 && coleccion.get(j-1) > valor){
				coleccion.add(j-1, valor);
				coleccion.remove(j+1);
				j--;
			}
		}
		//Creacion del nuevo archivo con los números ordenados.
		newFile(coleccion,"numeros1.txt");
	}
	/*
 	* Referencia encontrada en:
 	* http://www.algolist.net
 	*/
	public static void bubbleSort(ArrayList<String> lista){
		int j = 0;
		int temp = 0;
		//Variable que indica si se hizo un intercambio en la posicion actual.
		boolean intercambio = true;
		ArrayList<Integer> coleccion = new ArrayList<Integer>();
		coleccion = convert(lista);
		while(intercambio){
			intercambio = false;
			j++;
			for (int i = 0; i < coleccion.size() - j; i++) { 
				//Intercambio de posiciones.
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
		//Creacion de nuevo archibvo.
		newFile(coleccion,"numeros2.txt");
	}
	/*
	 * Referencia encontrada en:
	 * http://softbase.ipfw.edu/~lubo/LearningJava/sourceCode/Chapter%2014/Merge%20Sort/V2/MergeSort.java
	 * "Java Structures" Duane Bailey
	 */
	public static ArrayList<Integer> mergeSort(ArrayList<Integer> lista){
		//Condicion para verificacion del momento en que se llega a la unidad.
		if (lista.size() <= 1) {
			return lista;
		}
		
		ArrayList<Integer> mitad_baja = new ArrayList<Integer>();
		ArrayList<Integer> mitad_alta = new ArrayList<Integer>();
		//Distribucion de valores.
		for (int i = 0; i < lista.size() / 2; i++) {
			mitad_baja.add(lista.get(i));
		}
		for (int i = lista.size() / 2; i < lista.size(); i++) {
			mitad_alta.add(lista.get(i));
		}
		//Retorna la lista unida y organizada.
		return unir(mergeSort(mitad_baja), mergeSort(mitad_alta));
	}
	public static ArrayList<Integer> unir(ArrayList<Integer> segmento_1, ArrayList<Integer> segmento_2) {
		//Condicion para cuando se llego al final de la lista en el merge. 
		if (segmento_1.size() == 0) {
			return segmento_2;
		}
		if (segmento_2.size() == 0) {
			return segmento_1;
		}
		//Para almacenar valores ordenados.
		ArrayList<Integer> resultante = new ArrayList<Integer>();
		int siguiente;
		if (segmento_1.get(0) > segmento_2.get(0)) {
			siguiente = segmento_2.get(0);
			segmento_2.remove(0);
		} else {
			siguiente = segmento_1.get(0);
			segmento_1.remove(0);
		}
		resultante.add(siguiente);
		//Recursion
		resultante.addAll(unir(segmento_1,segmento_2));
		return resultante;
	}
	//Metodo para unir las dos partes y el pivote en algoritmo de quickSort.
	public static ArrayList<Integer> mergeQuick(ArrayList<Integer> menor, int pivote, ArrayList<Integer> mayor){
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for (int i = 0; i < menor.size(); i++) {
			lista.add(menor.get(i));
		}
		lista.add(pivote);
		for (int i = 0; i < mayor.size(); i++) {
			lista.add(mayor.get(i));
		}
		return lista;
	}
	public static ArrayList<Integer> quickSort(ArrayList<Integer> entrada){
		//Regresa
		if(entrada.size() <= 1){
			return entrada;
		}
		int centro = (int) Math.ceil((double)entrada.size() / 2);
		int pivote = entrada.get(centro);
		ArrayList<Integer> menor = new ArrayList<Integer>();
		ArrayList<Integer> mayor = new ArrayList<Integer>();
		for (int i = 0; i < entrada.size(); i++) {
			if(entrada.get(i) <= pivote){
				//Salta el centro para utilizarlo como pivote.
				if(i == centro){
					continue;
				}
				menor.add(entrada.get(i));
			}
			else{
				mayor.add(entrada.get(i));
			}
		}
		//Recursions
		return mergeQuick(quickSort(menor), pivote, quickSort(mayor));
	}
	//Metodo para crear un nuevo archivo de texto partiendo de una coleccion de numeros.
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
	//Utilizado para convertir un arreglo de texto a uno de enteros.
	public static ArrayList<Integer> convert(ArrayList<String> lista){
		ArrayList<Integer> coleccion = new ArrayList<Integer>();
		for(String s:lista)
			coleccion.add(Integer.parseInt(s));
		return coleccion;
	}
}

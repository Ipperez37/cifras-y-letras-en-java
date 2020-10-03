package es.uned.lsi.eped.pract2019_2020;

import java.util.*;

import es.uned.lsi.eped.DataStructures.ListIF;
import es.uned.lsi.eped.DataStructures.List;
/* Palabras ordenadas del mismo tama�o  A�ADIR PALABRA DE ORDEN N*/
 
public class WordListN {
	
	private ListIF<String> lista;
	private int size;
	private String word;
	
	
	// Constructor 
	public WordListN(int size) {
		this.lista = new List<String>();
		this.size = size;
	}
	/* A�ade una palabra a la estructura */
	public void add(String word) {
		this.lista.insert(this.lista.size() + 1, word);		
	}
	/* Devuelve el tama�o de las palabras de la estructura*/
	public void setWordSize(String Words)
	{
		this.word=Words;
	}
	
	public int getWordSize() {
		return this.size;
	}
	
	public String toString() {
		StringBuilder salida = new StringBuilder();
		int numPalabras = this.lista.size(); /* Longitud de la secuencia de palabras */
		salida.append("-Palabras de ");
		salida.append(this.getWordSize());
		salida.append(" letra");
		
		if ( this.getWordSize() > 1 ) { salida.append('s'); }
		salida.append(": ");
		for (int pos = 1 ; pos <= numPalabras ; pos++) {
		/* Estas líneas dependen de la estructura escogida */
			String word = this.lista.get(pos);
			salida.append(word);
			if ( pos < numPalabras ) {
				salida.append(", ");
			}
		}
		salida.append('\n');
		return salida.toString();
	}
}


package es.uned.lsi.eped.pract2019_2020;


import java.util.Collections;
import java.util.LinkedList;

/*
 * agrupa el wordlisN con todas las palabras
 * de distinta longitud localizadas en el diccionario*/
import es.uned.lsi.eped.DataStructures.List;
import es.uned.lsi.eped.DataStructures.ListIF;
  
public class WordList {
	private ListIF<WordListN> wordList;
	
	public WordList() {
		this.wordList = new List<WordListN>();
	}
	
	/* M�todo que a�ade una nueva palabra a la estructura */
	public void add(String word) {
		
		WordListN listWordSize = null;
		int pos = 1;
		int wordSize = word.length();
		
		if (this.wordList.size() > 0) {			
			while (listWordSize == null && pos <= this.wordList.size() ) {
				if (wordSize >= this.wordList.get(pos).getWordSize()) {
					listWordSize = this.wordList.get(pos);
				}else
					pos++;
			}
		}
		
		if (listWordSize == null || wordSize != this.wordList.get(pos).getWordSize()) {
			WordListN newWordListN = new WordListN(wordSize);
			newWordListN.add(word);
			this.wordList.insert(pos, newWordListN);
		}else {
			listWordSize.add(word);
		}			
	}
	
	
	/* Construye un String con el contenido de la estructura */
	
	public String toString() {
		StringBuilder salida = new StringBuilder();
		for ( int pos = 1 ; pos <= this.wordList.size() ; pos++ ) {
			salida.append(this.wordList.get(pos).toString());
		}
		return salida.toString();
	}
}

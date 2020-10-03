package es.uned.lsi.eped.pract2019_2020;

import java.io.Console;

import es.uned.lsi.eped.DataStructures.GTree;
import es.uned.lsi.eped.DataStructures.GTreeIF;
import es.uned.lsi.eped.pract2019_2020.Node.NodeType;

public class Dictionary {

	private GTree<Node> dict; 
	private int pos = 0;
	/* Constructor de la clase */
	public Dictionary() {		
	
		this.dict =new GTree<Node>();
		dict.setRoot(new RootNode());
		
    }
	
	/* Método de inserción de una nueva palabra en el diccionario */
	public void insert(String word) {
			
			 insertInTree(word,this.dict);
	}
	
	
	/* Método privado llamado por el anterior */
	private void insertInTree(String word, GTreeIF<Node> dict) {		
	
	
		if (word.length() == 0 ) {
			
			if (dict.getChildren().size() == 0) {
				GTree<Node> newNode = new GTree<Node>();
				newNode.setRoot(new WordNode());
				dict.addChild(1, newNode);
			}			
		}else {			
			
			GTree<Node> newNode;
			char chart = word.charAt(0);
			String newWord = word.substring(1,word.length());
			if (dict.getChildren().size() == 0) {
				newNode= new GTree<Node>();
				newNode.setRoot(new LetterNode(chart));
				insertInTree(newWord, newNode);
	   			dict.addChild(1,newNode);
			}else {
				pos = getTreePosition(word.charAt(0), (GTree<Node>)dict);
				if (pos == -1) {
					newNode= new GTree<Node>();
					newNode.setRoot(new LetterNode(chart));
					insertInTree(newWord, newNode);
		   			dict.addChild(dict.getChildren().size() + 1,newNode);
				}else {					
					if (((Node)dict.getChild(pos).getRoot()).getNodeType() == Node.NodeType.LETTERNODE  &&
							((LetterNode)dict.getChild(pos).getRoot()).getLetra() == chart) {
						//Si la letra ya existe. añadimos por ese letra 
						newNode = (GTree<Node>)dict.getChild(pos);
						insertInTree(newWord, newNode);
					}else {
						//Si la letra no existe. Se crea un hijo y se sigue añadiendo por el
						newNode= new GTree<Node>();
						newNode.setRoot(new LetterNode(chart));
						insertInTree(newWord, newNode);
			   			dict.addChild(pos,newNode);
					}
				}
			}
   				
		} 
	}
	
	 private int getTreePosition(char a, GTree<Node> dict) {
		// TODO Auto-generated method stub
	
	   for (int next = 0 ; next< dict.getChildren().size(); next++) {		   
		   if (((Node)dict.getChild(next+1).getRoot()).getNodeType() == Node.NodeType.LETTERNODE )
			   if (((LetterNode)dict.getChild(next+1).getRoot()).getLetra() == a || ((LetterNode)dict.getChild(next+1).getRoot()).getLetra() > a)
			   return next + 1; 
	   }
	   return -1;
	
		
	}

	/* Método público de búsqueda de todas las palabras a partir de una secuencia */
	
	public WordList search(String sequence) {
		WordList salida = new WordList();           /* Variable donde construiremos la salida */
		searchInTree(sequence,"",this.dict,salida); /* Construimos la salida recursivamente */
		return salida;
	}
	
	
	
	/* Método privado llamado por el anterior */
	private void searchInTree(String sequence, String word,
							  GTreeIF<Node> node, WordList salida) {
		
		if (node.getRoot().getNodeType() == Node.NodeType.WORDNODE ) {
			salida.add(word);
		}else {
			boolean letterContains = true;
			if (node.getRoot().getNodeType() == Node.NodeType.LETTERNODE ) {
				int posSequence = 0;
				//recorre la sequence
				while (letterContains && posSequence < sequence.length()){
					if (((LetterNode)node.getRoot()).getLetra() == sequence.charAt(posSequence))
						letterContains = false;				
					else
						posSequence++;						
				}
				//la letra existe en sequencía
				if (!letterContains){
					word = word.concat("" + sequence.charAt(posSequence)); //añade la letra a la palabra
					sequence = new StringBuilder(sequence).deleteCharAt(posSequence).toString(); //se quita la letra de las sequencía					
				}				
			}
			if(node.getRoot().getNodeType() == Node.NodeType.ROOTNODE ||
					(node.getRoot().getNodeType() == Node.NodeType.LETTERNODE && !letterContains)){
				for (int i = 1; i <= node.getChildren().size(); i++) {
					this.searchInTree(sequence, word, node.getChild(i), salida);						
				}
			}
		}
		        
	
	}
	
	/* Método público de búsqueda de todas las palabras de tamaño size a partir de una secuencia */
	
	public WordListN search(String sequence, int size) {
		WordListN salida = new WordListN(size);           /* Variable donde construiremos la salida */
		searchInTreeN(sequence,"",this.dict,salida,size); /* Construimos la salida recursivamente */
		
		return salida;
	}
	
	/* Método privado llamado por el anterior */
	
	private void searchInTreeN(String sequence, String word,
							   GTreeIF<Node> node, WordListN salida,
							   int size) {		
		
		
		if (node.getRoot().getNodeType() == Node.NodeType.WORDNODE && word.length() == size ) {
			salida.add(word);
		}else {
			boolean letterContains = true;
			if (node.getRoot().getNodeType() == Node.NodeType.LETTERNODE ) {
				int posSequence = 0;
				//recorre la sequence
				while (letterContains && posSequence < sequence.length()){
					if (((LetterNode)node.getRoot()).getLetra() == sequence.charAt(posSequence))
						letterContains = false;				
					else
						posSequence++;						
				}
				//la letra existe en sequencía
				if (!letterContains){
					word = word.concat("" + sequence.charAt(posSequence)); //añade la letra a la palabra
					sequence = new StringBuilder(sequence).deleteCharAt(posSequence).toString(); //se quita la letra de las sequencía					
				}				
			}
			if(node.getRoot().getNodeType() == Node.NodeType.ROOTNODE ||
					(node.getRoot().getNodeType() == Node.NodeType.LETTERNODE && !letterContains)){
				for (int i = 1; i <= node.getChildren().size(); i++) {
					this.searchInTreeN(sequence, word, node.getChild(i), salida, size);						
				}
			}
		}	
	}	
}

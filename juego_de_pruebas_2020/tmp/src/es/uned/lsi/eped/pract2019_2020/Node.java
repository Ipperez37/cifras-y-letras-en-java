package es.uned.lsi.eped.pract2019_2020;

import java.util.ArrayList;

public abstract class Node {
	public enum NodeType {
		ROOTNODE, LETTERNODE, WORDNODE
	}
	
	
	/* Prescribe un getter que devuelve el tipo de nodo */
	public abstract NodeType getNodeType();
	
	
	public Node() {}
	
	public Node getROOTNODE() {
		return new RootNode();
	}

	public Node getWORDNODE() {
		return new WordNode();
	}

	public Node getLETTERNODE(char letter) {
		return new LetterNode(letter);
	}


}

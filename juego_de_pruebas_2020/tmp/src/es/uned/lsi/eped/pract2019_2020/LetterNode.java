package es.uned.lsi.eped.pract2019_2020;

import es.uned.lsi.eped.pract2019_2020.Node.NodeType;

/* nodo intermedio (letra)*/

public class LetterNode extends Node {
	
	private char letter;
	
	public LetterNode(char letter) {
		// TODO Auto-generated constructor stub
		this.letter = letter;
		
	}
	public char getLetra() {
		return letter;
	}
	public void setLetra(char letter) {
		this.letter = letter;
	}
	@Override
	public NodeType getNodeType() {
		// TODO Auto-generated method stub
		return NodeType.LETTERNODE;
	}
	
}

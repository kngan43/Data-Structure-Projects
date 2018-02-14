package hangman;

public class LLCharacterNode {
	private char letter;
	private LLCharacterNode link;
	private LLCharacterNode end = this;
	
	public LLCharacterNode(){};
	public LLCharacterNode(char letter){
			this.letter=letter;
	}
	
	public char getInfo(){
		return letter;
	}
	
	public LLCharacterNode getLink(){
		return link;
	}
	
	public void setLink(LLCharacterNode link){
		this.link = link;
		end = this.link;
	}
	
	public void setInfo(char letter){
		this.letter = letter;
	}
	
	public LLCharacterNode getEnd(){
		return end;
	}
	public void setEnd(LLCharacterNode end){
		this.end = end;
	}
}

package hangman;

public class LinkedListGameModel implements GameModel {
	private int guessCount = 0;
    private String guessWord;
    private LLCharacterNode guessWordNode;
    private LLCharacterNode priorGuessNode;
    private LLCharacterNode displayNode;
    private int state;
    
	public LinkedListGameModel(String guessWord){
		this.guessWord = guessWord;
		guessWordNode = new LLCharacterNode();
		LLCharacterNode curr = guessWordNode;
		for(int i = 0;i<guessWord.length();i++){
			curr.setInfo(guessWord.charAt(i));
			curr.setLink(new LLCharacterNode());
			curr = curr.getLink();
		}
		displayNode = new LLCharacterNode();	
		curr = displayNode;
		for(int j = 0;j<guessWord.length();j++){
			curr.setInfo('_');
			curr.setLink(new LLCharacterNode());
			curr= curr.getLink();
		}
	}
	@Override
	public boolean isPriorGuess(char guess) {
		LLCharacterNode curr = priorGuessNode;
		while(curr!=null){
        	if(curr.getInfo() == guess)
        		return true;
        	curr = curr.getLink();
        }
        return false;
	}

	@Override
	public int numberOfGuesses() {
		return guessCount;
	}

	@Override
	public boolean isCorrectGuess(char guess) {
		if(!isPriorGuess(guess)){
			LLCharacterNode curr = guessWordNode;
        	while(curr!=null){
        		if(guess == curr.getInfo()){
        			return true;
        		}
        		curr=curr.getLink();
        	}
        }
        return false;
	}
	
	public void setDisplayNode(char guess){
		LLCharacterNode curr1 = guessWordNode;
		 LLCharacterNode curr2 = displayNode;
       	while(curr1!=null && curr2!=null){
       		if(guess == curr1.getInfo()){
       			curr2.setInfo(guess);
       			}
       		curr1 = curr1.getLink();
       		curr2 = curr2.getLink();
       	}
	}
	public void setPriorGuessNode(char guess){
		if(priorGuessNode == null){
			 priorGuessNode = new LLCharacterNode(guess);
		 }
			 else {
				 LLCharacterNode curr = priorGuessNode;
				 while(curr!=null){
					 	if(curr.getLink() == null){
					 		curr.setLink(new LLCharacterNode(guess));
					 		break;
					 	}
					 	curr = curr.getLink();
		        		}
		        	}
			 
	}
	@Override
	public boolean doMove(char guess) {
		 if(isCorrectGuess(guess)){
			 setDisplayNode(guess);
			 setPriorGuessNode(guess);
		        	guessCount++;
		        	return true;
	        }
	        		
		 else if (!isPriorGuess(guess)){
			 state++;
			 setPriorGuessNode(guess);
			 guessCount++;
			 return false;
		}
	        return false;
	}

	@Override
	public boolean inWinningState() {
		LLCharacterNode curr = displayNode;
		while(curr!=null){
        	if(curr.getInfo() == '_')
        		return false;
        	curr = curr.getLink();
		}
        return true;
	}

	@Override
	public boolean inLosingState() {
		if(getState()==10)
        	return true;

        return false;
	}

	@Override
	public int getState() {
		return state;
	}

	@Override
	public String previousGuessString() {
		String prior ="";
		LLCharacterNode curr = priorGuessNode;
        while(curr!=null){
        		prior+=curr.getInfo() + ", ";
        		curr = curr.getLink();
        }

        return "["+prior.substring(0,prior.length()-2)+"]";
	}

	@Override
	public String getWord() {
		return guessWord;
	}
	
	 public String toString() {
	    	String word = "";
	    	LLCharacterNode curr = displayNode;
	        while(curr!=null){
	        	word+= curr.getInfo()+" ";
	        	curr = curr.getLink();
	        }

	        return word.substring(0, word.length()-3);
	    }

}

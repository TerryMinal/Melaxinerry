public class WOO{

    int numAi;
    int numRealPlayers;
    ArrayList<Player> allPlayers; 
    public void distribute(ArrayList<Deck> deck) {
	for (int i = 0; i < allPlayers.size(); i++) {
	    for (int n = 0; n < 7; n++) {
		allPlayers.get(i).add(); 
	    }
	}
    }
    
    public static void main(String[] args){
        //prints an ArrayList of the whole deck
	System.out.println(Deck.createDeck());
	
    }//end main
    
}//end class

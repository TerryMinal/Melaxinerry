
import java.util.*;

public class WOO{

    int numAi;
    int numRealPlayers;
    ArrayList<Player> allPlayers;
    ArrayList<Card> discardPile;
    Deck drawPile;
    //instantiate players and put them in arraylist  
    public WOO() {
	numAi = 0;
	numRealPlayers = 0;	
	allPlayers = new ArrayList<Player>();
	discardPile = new ArrayList<Card>();
	//drawPile = new ArrayList<Card>(108); 
	drawPile = new Deck();
    }

   public void distribute() {
       for (int i = 0; i < allPlayers.size(); i++) {
	    for (int n = 0; n < 7; n++) {
		allPlayers.get(i).draw(drawPile);
	    }
	}
   }

    //returns index of player that goes first;
    public void rollDice(){
       	for (int i=0; i < allPlayers.size(); i++){
	    allPlayers.set(i, allPlayers.set( (int) (Math.random() * allPlayers.size()), allPlayers.get(i) ) ); 
	}
    }

    public void playCards(Player pl, int index) {
	if (drawPile.size() == 0) {
	    drawPile.setDeck(discardPile);
	    drawPile.shuffle();
	}
	else {
	    pl.playCard(index, discardPile);
	}
    }

    //start screen etc goes here
    public void beginGame() {
	numAi = 0;
	numRealPlayers = 2;
	allPlayers.add(new Player("Terry"));
	allPlayers.add(new Player("Guan"));
	distribute();
    }

    //actual game methods go here
    public void game() {
	
    
    }
    
    public static void main(String[] args){
	WOO game = new WOO();
	game.beginGame();
    
    }//end main
    
}//end class


import java.util.*;
import cs1.Keyboard;

public class Woo {

    int numAi;
    int numRealPlayers;
    ArrayList<Player> allPlayers;
    ArrayList<Card> discardPile;
    Deck drawPile;
    //instantiate players and put them in arraylist  
    public Woo() {
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
	int typegame=0;
	//----STRINGS-----------
	String rules="How to Play:\n";
	rules+="A game of UNO consists of 2-5 players. Each player starts with 7 cards \n";
	rules+="A dice is rolled to see who goes first.\n";
	rules+="On a players turn, he/she must do ONE of the following: Draw or Play or Call UNO.\n";
	rules+="-play a card matching the discard in either color, number, or symbol\n";
	rules+="-play a Wild card, or a playable Wild Draw Four card\n";
	rules+="-draw a card from the deck.\n";
	rules+="-you must call UNO when you have one card left. If you didn��t call UNO before your turn is over, you will draw 2 cards. If you call UNO when you have more than one card, you will draw 4 cards.\n";
	rules+="How to Win:\n";
	rules+="The first player to get rid of his/her last card wins the round and scores points for the cards held by the other players.Number cards count their face value, all action cards count 20, and Wild and Wild Draw Four cards count 50. If a Draw Two or Wild Draw Four card is played to go out, the next player in sequence must draw the appropriate number of cards before the score is tallied. \n";
	rules+="First player to reach 500 points wins. \n";
	rules+="==============================================\n";

	String start="Lets start! \n"; 
	start+="Enter 1 for Solo! \n";
	start+="Enter 2 for multiplayer \n";
	start+="Enter 3 for rules \n";

	//-----------------------

	System.out.println("Welcome to UNO! \n");
	while (typegame< 1 || typegame > 3){
	    System.out.print(start);
	    typegame=Keyboard.readInt();
	    if (typegame==3){
		System.out.print(rules);
		typegame=0;
	    }
	}
	if (typegame==1){
	    /* this part prob goes somewhere else
	    System.out.print("How would thy like to be called: ");
	    name=Keyboard.readString();
	    System.out.print("Enter a PIN to access your account");
	    pass=Keyboard.readString();
	    */
	    numRealPlayers=1;
	    System.out.print("How many computers? (Max 4): "); //does not take into accunt when number is greater than 4
	    numAi=Keyboard.readInt();
	    numRealPlayers=1;
	}
	if (typegame==2){ 
	    System.out.print("How many players?:"); //does not take into account if # of players >5
	    numRealPlayers=Keyboard.readInt();
	    System.out.print("How many computers?:");
	    numAi=Keyboard.readInt();
	}

	allPlayers.add(new Player("hiya1"));
	allPlayers.add(new Player("hiya"));
	distribute();
    }

    //actual game methods go here
    public void game() {
	
    
    }
    
    public static void main(String[] args){
	Woo game = new Woo();
	game.beginGame();
    
    }//end main
    
}//end class

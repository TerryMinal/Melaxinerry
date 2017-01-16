import java.util.*;
import UNO.*;
import UNO.cs1.Keyboard;
public class Woo {

    int numAi;
    int numRealPlayers;
    ArrayList<Player> allPlayers;
    ArrayList<Card> discardPile;
    ArrayList<Card> drawPile;
    ArrayList<String> names; //all players' names
    ArrayList<Integer> pins; //all players' pins corresponding to the index of names
    //instantiate players and put them in arraylist  
    public Woo() {
	numAi = 0;
	numRealPlayers = 0;	
	allPlayers = new ArrayList<Player>();
	discardPile = new ArrayList<Card>();
   	drawPile = Card.createDeck();
	Card.shuffle(drawPile);
	names = new ArrayList <String>();
	pins  = new ArrayList <Integer>();
    }

   public void distribute() {
       for (int i = 0; i < allPlayers.size(); i++) {
	    for (int n = 0; n < 7; n++) {
		allPlayers.get(i).draw(drawPile);
	    }
	}
   }

    public void rollDice(){
       	for (int i=0; i < allPlayers.size(); i++){
	    allPlayers.set(i, allPlayers.set( (int) (Math.random() * allPlayers.size()), allPlayers.get(i) ) ); 
	}
    }

    public boolean  playCards(Player pl, int index) { //returns T/F if worked.
	//ADD RULES
    }

    public void draw(){
	if (drawPile.size() == 0) {
	    drawPile = discardPile;
	    Card.shuffle(drawPile);
	    discardPile = new ArrayList<Card>();
	}
	CurrentCards.add(drawPile.remove(drawPile.size()-1));
    }	

    public void beginGame() {
	//----STRINGS-----------
	String rules="How to Play:\n";
	rules+="A game of UNO consists of 2-5 players. Each player starts with 7 cards \n";
	rules+="A dice is rolled to see who goes first.\n";
	rules+="On a players turn, he/she must do ONE of the following: Draw or Play or Call UNO.\n";
	rules+="-play a card matching the discard in either color, number, or symbol\n";
	rules+="-play a Wild card, or a playable Wild Draw Four card\n";
	rules+="-draw a card from the deck.\n";
	rules+="-you must call UNO when you have one card left. If you didn't call UNO before your turn is over, you will draw 2 cards. If you call UNO when you have more than one card, you will draw 4 cards.\n";
	rules+="How to Win:\n";
	rules+="The first player to get rid of his/her last card wins the round and scores points for the cards held by the other players.Number cards count their face value, all action cards count 20, and Wild and Wild Draw Four cards count 50. If a Draw Two or Wild Draw Four card is played to go out, the next player in sequence must draw the appropriate number of cards before the score is tallied. \n";
	rules+="First player to reach 500 points wins. \n";
	rules+="==============================================\n";

	String start="Welcome to UNO!\n"; 
	start+="Enter 1 for starting the game! \n";
	start+="Enter 2 for rules \n";
	System.out.println(start);
	//-----------------------	
	int typegame=Keyboard.readInt();
	while (typegame < 1 || typegame >= 2){	   
	    if (typegame==2){
		System.out.print(rules);
		typegame=0;
	    }
	    System.out.print(start);
	    typegame=Keyboard.readInt();
	}
	/*
	if (typegame==1){
	    //this part prob goes somewhere else
	    System.out.print("How would thy like to be called: ");
	    name=Keyboard.readString();
	    System.out.print("Enter a PIN to access your account");
	    pass=Keyboard.readString();
	    
	    numRealPlayers=1;
	    System.out.print("How many computers? (Max 4): "); //does not take into accunt when number is greater than 4
	    numAi=Keyboard.readInt();
	    }*/
	if (typegame==1){ 
	    System.out.print("How many players?:"); //does not take into account if # of players >5
	    numRealPlayers=Keyboard.readInt();	    
	}
	String tempname;
	int temppin;
	for (int a = 1 ; a <= numRealPlayers; a++){
	    System.out.println("Enter player"+ a+" name:");
	    tempname=Keyboard.readString();
	    names.add(tempname);
	    System.out.println("Enter player"+ a+" pin (4-digits):");
	    temppin=Keyboard.readInt();
	    pins.add(temppin);
	}
	for(int i = 0; i < numRealPlayers; i++){
	    allPlayers.add(new Player(names.get(i),pins.get(i)));
	}
      	distribute();
    }

    //actual game methods go here
    public void playGame() {
	boolean gameCont = true;
	int move;
	while (gameCont) {
	    for (int turn = 0; turn < allPlayers.size(); turn++) {
		move=0;
		Player currentPlayer = allPlayers.get(turn);
		while (move!=-1){ //turn goes not end when player calls UNO! //-1 = turn is over
		System.out.println(currentPlayer.getCurrentCards());
		System.out.println("What would you like to do? \n 1. Play \n 2. Draw \n 3. Call UNO!(you may only call this at the start of your turn)");
		move= Keyboard.readInt();
		if (move==1){ //PLAY
		    System.out.println("enter the card you want to play by entering the index:"); 
		    int cardIndex = Keyboard.readInt(); 
		    if (playCards(currentPlayer, cardIndex)){
			System.out.println("current hand: " + currentPlayer.getCurrentCards());
			System.out.println("top most card played: " + discardPile.get(discardPile.size() - 1));
			move=-1
		    }
		    else{
			System.out.println("invalid card play. 1. Try Again \n 2.Draw");
			move=Keyboard.readInt();
		    }	
		}
		if (move==2){ //DRAW
		    currentPlayer.draw();
		}
		if (move==3){ //UNO 
		    if (currentPlayer.CurrentCards.size()==1){
			calledUNO=1; //IF 1 CARD-->CHECK OFF THEIR UNO STATUS!
		    }
		    else { //draw 2 cards
			currentPlayer.draw();
			currentPlayer.draw();
		    }
		}
		}
		if (currentPlayer.CurrentCards.size()==0) { 
		    System.out.println("WINNER!");
		    break;
		}
		else
		    System.out.println("\nnext player's turn: ");
	    }
	}
    }
    
    public static void main(String[] args){
	Woo game = new Woo();
	game.beginGame();
	game.playGame();
	//===========================================================
    }//end main
    
}//end class

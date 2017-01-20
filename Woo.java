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
	int first= (int)(Math.random()*(allPlayers.size()));
	//System.out.println(first);
	for(int i = 0; i < first; i++){
	    allPlayers.add(allPlayers.get(0));
	    //System.out.println(a);
	    allPlayers.remove(0);
	}				
    }

     public void reverse(int index){
	ArrayList<Player> temp = new ArrayList<Player>();
	temp.add(allPlayers.get(index));
	for (int x = index - 1; x >= 0; x --){
	    temp.add(allPlayers.get(x));
	}
	for (int i = allPlayers.size()-1; i > index; i --){
	    temp.add(allPlayers.get(i));
	}
	allPlayers = temp;
	System.out.println(allPlayers);
    }
    
    public void checkDiscardPile(){
	if (drawPile.size() == 0) {
	    drawPile = discardPile;
	    Card.shuffle(drawPile);
	    discardPile = new ArrayList<Card>();
	}
    }	

    public String draw(Player currentPlayer){
	checkDiscardPile();
	currentPlayer.draw(drawPile);
	return "You drew: "+currentPlayer.getCurrentCards().get(currentPlayer.getCurrentCards().size()-1);
    }
	
    public void firstCard(){
	System.out.println("The first card is: " + drawPile.get(drawPile.size() - 1));
	discardPile.add(drawPile.remove(drawPile.size() - 1));
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
	rollDice();
	System.out.println(allPlayers);
      	distribute();
    } 

    
    //actual game methods go here
    public void playGame() {
	boolean gameCont = true;
	int move = 0;
	while (gameCont) {
	    for (int turn = 0; turn < allPlayers.size(); turn++) {
		
		//if the last player played a skip
		if (move == 1){
		    Card lastCard = discardPile.get(discardPile.size()-1);
		    if( lastCard instanceof SpecialCard && (((SpecialCard)lastCard).getAction() == 2)){ 
			    turn ++;
		    }
		}
				
		Player currentPlayer = allPlayers.get(turn);

		//if the last player played a draw2 
		if (move == 1){
		    Card lastCard = discardPile.get(discardPile.size()-1);
		    if( lastCard instanceof SpecialCard &&( ( (SpecialCard)lastCard).getAction() == 3 )){ 
			draw(currentPlayer);
			draw(currentPlayer);
		    }
		}

		//if the second to last card was a wild draw 4 (played by last player)	
		if (move == 1){
		    Card secondToLastCard = discardPile.get(discardPile.size()-2);
		    if( secondToLastCard instanceof SpecialCard && ( ( (SpecialCard)secondToLastCard).getAction() == 5 ) ){ 
			draw(currentPlayer);
			draw(currentPlayer);
			draw(currentPlayer);
			draw(currentPlayer);
		    }
		}
	    
		
		System.out.println("Player "+ currentPlayer + "'s turn:");
	        currentPlayer.checkPin();
		System.out.println("============================================");		
		System.out.println("Top most card played: " + discardPile.get(discardPile.size() - 1));	
		System.out.println("Your current hand: " + currentPlayer.getCurrentCards());
		System.out.println("What would you like to do? \n 1. Play \n 2. Draw \n 3. Call UNO");
	
		move= Keyboard.readInt();
		if (move==1){ //PLAY
		    System.out.println("enter the card you want to play by entering the index:"); 
		    int cardIndex = Keyboard.readInt();
	
		    while( !(currentPlayer.playCard(cardIndex, discardPile)) ){
		        System.out.println("WRONG CARD PLAYED! the card must match in color, number or action! \n 1. Try again \n 2. Draw");
 			move=Keyboard.readInt();
 			if (move !=1){
 			    break;
 			}
 			System.out.println("Enter the card you want to play by entering the index:");
			cardIndex = Keyboard.readInt();
		    }

		    
		    Card thisCard = discardPile.get(discardPile.size()-1);
		    if( thisCard instanceof SpecialCard){
			//if the card played is a reverse
			if (((SpecialCard)thisCard).getAction() == 1){ 
			    reverse(turn);
			    turn = 0; //resets the turn bc if you reverse the allPlayer ArrayList, it will begin with the currentPlayer
			}
			
			//if the currentPlayer played a wild card, he/she must play another card
			if (((SpecialCard)thisCard).getAction() == 4 || ((SpecialCard)thisCard).getAction() == 5){ 
			    System.out.println("You just played a wild or wild draw 4 card! \n Enter the card you want to play by entering the index:");
			    cardIndex = Keyboard.readInt();
			    currentPlayer.playCard(cardIndex, discardPile);
			}
		    }
		}
	
		if (move==2){ //DRAW
		    System.out.println(draw(currentPlayer));
		    //if the card that is drawn can be played
		    if (Card.isMatch( discardPile.get(discardPile.size()-1),
				     (currentPlayer.getCurrentCards().get(currentPlayer.getCurrentCards().size()-1) ) 
				      )) {
			System.out.println("Current hand: " + currentPlayer.getCurrentCards());
			System.out.println("Do you want to play the card that you just drew? 1.YES 2.NO"); 
			int choice = Keyboard.readInt();
			if (choice == 1){
			    //add exception here
			    move = 1;  
			    int index = currentPlayer.getCurrentCards().size()-1;
			    currentPlayer.playCard(index, discardPile);
			    
			    Card thisCard = discardPile.get(discardPile.size()-1);
			    if( thisCard instanceof SpecialCard){
				//if the card played is a reverse
				if (((SpecialCard)thisCard).getAction() == 1){ 
				    reverse(turn);
				    turn = 0; //resets the turn bc if you reverse the allPlayer ArrayList, it will begin with the currentPlayer
				}
			
				//if the currentPlayer played a wild card, he/she must play another card
				if (((SpecialCard)thisCard).getAction() == 4 || ((SpecialCard)thisCard).getAction() == 5){ 
				    System.out.println("You just played a wild or wild draw 4 card! \n Enter the card you want to play by entering the index:");
				    int cardIndex = Keyboard.readInt();
				    currentPlayer.playCard(cardIndex, discardPile);
				}
			    }
			}
		    }
		}
		if (move==3){ //UNO 
		    if (currentPlayer.getCurrentCards().size()==1){ //add instance variable to player
		        currentPlayer.calledUNO=1; //IF 1 CARD-->CHECK OFF THEIR UNO STATUS!
		    }
		    else { //IF YOU DON'T HAVE UNO-->DRAW 2 CARDS
			System.out.println("\nEek! You don't have UNO! Draw 2 Cards. \n");
			System.out.println(draw(currentPlayer));
			System.out.println(draw(currentPlayer));
		    }
		}
		if (currentPlayer.getCurrentCards().size()==1){
		    if (currentPlayer.calledUNO!=1){ //they didn't call UNO --> draw 2
			System.out.println("\nSo close...yet so far. Remember to call UNO next time. Draw 2 \n");
			System.out.println(draw(currentPlayer));		
			System.out.println(draw(currentPlayer));
		    }
		}
			
		if (currentPlayer.getCurrentCards().size()==0) { 
		    System.out.println("WINNER!");
		    break;
		}

		System.out.println("Current hand: " + currentPlayer.getCurrentCards());
		System.out.println("Top most card played: " + discardPile.get(discardPile.size() - 1));	
		System.out.println("============================================");
	        System.out.println("\nNext player's turn: ");
		
	    }
	}
    }
    
    public static void main(String[] args){
      	Woo game = new Woo();
	game.beginGame();
	System.out.println("============================================");
	game.firstCard();
	System.out.println("============================================");
	game.playGame();
	//===========================================================
    }//end main
    
}//end class

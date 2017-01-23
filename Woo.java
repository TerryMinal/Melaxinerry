import java.util.*;
import UNO.*;
import UNO.cs1.Keyboard;
import java.io.Console; 

public class Woo {
	
    int numAi;
    int numRealPlayers;
    ArrayList<Player> allPlayers;
    ArrayList<Card> discardPile;
    ArrayList<Card> drawPile;

    public Woo() {
	numAi = 0;
	numRealPlayers = 0;	
	allPlayers = new ArrayList<Player>();
	discardPile = new ArrayList<Card>();
   	drawPile = Card.createDeck();
	Card.shuffle(drawPile);
    }

    //beginning of each game: each player gets 7 cards
   public void distribute() {
       for (int i = 0; i < allPlayers.size(); i++) {
	    for (int n = 0; n < 7; n++) {
		allPlayers.get(i).draw(drawPile);
	    }
	}
   }

    //determines who goes first and then the turns will go clockwise
    //ex: players 1,2,3 and the "dice" returns 2, the turns will go by [2,3,1]
    public void rollDice(){	
	int first= (int)(Math.random()*(allPlayers.size()));
	//System.out.println(first);
	for(int i = 0; i < first; i++){
	    allPlayers.add(allPlayers.get(0));
	    //System.out.println(a);
	    allPlayers.remove(0);
	}
	System.out.println("TURN ORDER: "+ allPlayers);
    }

    //reverse the order of the turns
    //ex. the current order [1,2,3] and player 2 plays a reverse card, the order will become [2,1,3]
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
	System.out.println("The new turn order: "+ allPlayers);
    }

    //if the drawPile is empty, it will turn the discardPile into the drawPile and shuffle it
    public void checkDiscardPile(){
	if (drawPile.size() == 0) {
	    drawPile = discardPile;
	    Card.shuffle(drawPile);
	    discardPile = new ArrayList<Card>();
	}
    }	
 
    public String mkPin() {
	Console pwd = System.console();
	char[] pass = pwd.readPassword();
 	String retStr = "";
 	for (char s : pass) {
     	    retStr += s; 
 	}
 	return retStr;
    }

    //the currentPlayer draws the last card from the drawPile
    public String draw(Player currentPlayer){
	checkDiscardPile();
	currentPlayer.draw(drawPile);
	return "You drew: "+currentPlayer.getCurrentCards().get(currentPlayer.getCurrentCards().size()-1);
    }

    //asks if the player wants to sort his current cards
    public static void sort(Player currentPlayer) {
	currentPlayer.sortCards();
	System.out.println("would you like to sort again: \n1:Yes \n2:No");
	int more = Keyboard.readInt();
	if (more < 1 || more > 2) {
	    System.out.println("please enter an acceptable number");
	    more = Keyboard.readInt();
	}
	//recursive statements
	if (more == 1)
	    sort(currentPlayer);
	else
	    return; 
    }

    //the first card of the game
    public void firstCard(){
	//if the last card (which becomes the first card) is a specialCard -> re-shuffle
	while (drawPile.get(drawPile.size() - 1) instanceof SpecialCard){
	    Card.shuffle(drawPile);
	}
	System.out.println("The first card is: " + drawPile.get(drawPile.size() - 1));
	discardPile.add(drawPile.remove(drawPile.size() - 1));
    }

    //clears the terminal screen and disabled scroll up
    public static void clearScreen() {
	String cls="printf \"\033c\"";
       	System.out.println(cls);  	
    }

    //displays the number of cards each player has left
    public void playerCardInfo(){
	String info = "==============================================\n";
	for (int i = 0; i < allPlayers.size(); i ++){
	    info = info +  allPlayers.get(i) + " has " + allPlayers.get(i).getCurrentCards.size()+" cards left.\n";
	}
	info += "==============================================\n";
	System.out.println(info);
    }

    //prints the start screen
    //players enter their corresponding names & pins
    public void beginGame() {
	String start="Welcome to UNO!\n"; 
	start+="Enter 1 for starting the game! \n";
	start+="Enter 2 for rules \n";
	System.out.println(start);
	int typegame=Keyboard.readInt();
	while (typegame < 1 || typegame >= 2){	   
	    if (typegame==2){
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

		System.out.print(rules);
		typegame=0;
	    }
	    System.out.print(start);
	    typegame=Keyboard.readInt();
	}

	//max number of players: 5
	if (typegame==1){ 
	    System.out.print("How many players?:"); 
	    numRealPlayers=Keyboard.readInt();
	    while (numRealPlayers < 2 || numRealPlayers > 5){
		 System.out.print("The max number of players is 5. Please enter an integer between 2 to 5 inclusive:"); 
		 numRealPlayers=Keyboard.readInt();
	    }
	}

	//creates the players
	for (int i = 0; i < numRealPlayers; i++) {
	    //set name
	    System.out.println("Enter player"+(i + 1) +" name:");	
	    String tempName=Keyboard.readString();
	    //set pin
	    System.out.println("Enter player"+(i + 1) +" pin (4-digits):");
	    boolean tempSizeTF = true;
	    String tempPin=mkPin();
	    while (tempSizeTF) {
		if (tempPin.length() == 4) { 
		    tempSizeTF = false;
		}
		else {
		    System.out.println("pin has to be 4 characters please re-enter");
		    tempPin = mkPin();
		}
	    }
	    allPlayers.add(new Player(tempName, tempPin));
	}
	rollDice();
	//System.out.println(allPlayers);
    }

    
    //actual game methods go here
    public void playGame() {
	distribute();
	boolean gameCont = true;
	int move = 0;
	while (gameCont) {
	    for (int turn = 0; turn < allPlayers.size(); turn++) {

		//if the last player played a skip
		if (move == 1){
		    Card lastCard = discardPile.get(discardPile.size()-1);
		    if( lastCard instanceof SpecialCard && (((SpecialCard)lastCard).getAction() == 2)){ 
			turn ++;
			System.out.println("***LAST PLAYER PLAYED A SKIP!***");
		    }
		}

		//if the last player played a reverse (only displays a message)
		if (move == 1){
		    Card lastCard = discardPile.get(discardPile.size()-1);
		    if( lastCard instanceof SpecialCard && (((SpecialCard)lastCard).getAction() == 1)){ 
			System.out.println("***LAST PLAYER PLAYED A REVERSE!***");
		    }
		    if (allPlayers.size() == 2){
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
			System.out.println("***LAST PLAYER PLAYED A DRAW2!***");
		    }
		}

		//if the second to last card was a wild draw 4 (played by last player)	
		if (move == 1){
		    Card secondToLastCard = discardPile.get(discardPile.size()-2);
		    if( secondToLastCard instanceof SpecialCard && ( ( (SpecialCard)secondToLastCard).getAction() == 5 ) ){
			System.out.println("***LAST PLAYER PLAYED A WILD DRAW4!***");
			for (int i = 0; i < 4; i++) {
			    draw(currentPlayer);
			}
		    }
		}

		//player need to enter their pin before allowed to play
		System.out.println("Player "+ currentPlayer + "'s turn:");
	        currentPlayer.checkPin();

		//displays how many cards each player has
		playerCardInfo();
		
		//displays the current player's info
		System.out.println("============================================");		
		System.out.println("Top most card played: " + discardPile.get(discardPile.size() - 1));	
		System.out.println("Your current hand: " + currentPlayer.getCurrentCards());
		System.out.println("What would you like to do? \n 1. Play \n 2. Draw \n");
		move= Keyboard.readInt();
		
		if (move==1){ //PLAY
		    System.out.println("Your current hand: " + currentPlayer.getCurrentCards());

		    //if the player wants to sort his/her cards
		    System.out.println("Would you like to sort: \n1:Yes \n2:No");
		    int sortQ = Keyboard.readInt();
		    while (sortQ < 1 || sortQ > 2) {
			System.out.println("please input an acceptable number");
			sortQ = Keyboard.readInt();
		    }
		    if (sortQ == 1) {
			sort(currentPlayer);
		    }
		    
		    System.out.println("enter the card you want to play by entering the index:"); 
		    int cardIndex = Keyboard.readInt();
		    //checks if the index would return an error
		    while (cardIndex > (currentPlayer.getCurrentCards() ).size() - 1 || cardIndex < 0) {
			System.out.println("the inputed index is out of bound. Re-enter an index");
			cardIndex = Keyboard.readInt();
		    }
		    while( !(currentPlayer.playCard(cardIndex, discardPile)) ){
			System.out.println("WRONG CARD PLAYED! the card must match in color, number or action! \n 1. Try again \n 2. Draw");
			move=Keyboard.readInt();
			if (move !=1){
			    break;
			}
			System.out.println("Enter the card you want to play by entering the index:");
			cardIndex = Keyboard.readInt();
		    }
		    
		    //if the card played is a special card
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

		    //if the player wants to call UNO
		    if (currentPlayer.isCallUNO()) {
			System.out.println("Call UNO! \n1.Yes \n2.No");
			int callUNO = Keyboard.readInt();
			if (callUNO == 1){
			    if (currentPlayer.getCurrentCards().size() !=1){ 
				//IF YOU DON'T HAVE UNO-->DRAW 2 CARDS
				System.out.println("\nEek! You don't have UNO! Draw 2 Cards. \n");
				System.out.println(draw(currentPlayer));
				System.out.println(draw(currentPlayer));
			    }
			}

			//if the currentPlayer only has one card left and he/she didnt call UNO
			if (currentPlayer.getCurrentCards().size()==1 && callUNO == 2){			  
			    System.out.println("\nSo close...yet so far. Remember to call UNO next time. Draw 2 \n");
			    System.out.println(draw(currentPlayer));		
			    System.out.println(draw(currentPlayer)+"\n");
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
			    move = 1;  
			    int index = currentPlayer.getCurrentCards().size()-1;
			    currentPlayer.playCard(index, discardPile);

			    //if the card played is a special card
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
		    }// end of draw and play
		}

		if (currentPlayer.getCurrentCards().size() == 0) { //if the player wins this round
		    System.out.println("WINNER!");
		    int score = 0; 
		    for (Player s : allPlayers) {
			for (Card x : s.getCurrentCards() ) {
			    score += x.getPoint(); 
			}
		    }
		    currentPlayer.setScore(score);
		    
		    //if the player has 500 pts then he/she won the whole game
		    if (currentPlayer.getScore() >= 500) {
			System.out.println("You won the whole game!");
			break;
		    }
		    else {
			playGame();
		    }
		}

		//displays the current player's info after he play or draw
		System.out.println("Current hand: " + currentPlayer.getCurrentCards());
		System.out.println("Top most card played: " + discardPile.get(discardPile.size() - 1));	
		System.out.println("============================================");
	        System.out.println("\nEnd Turn? \n 1.Yes");

		//ends the current player's turn
		int next = Keyboard.readInt();
		while (next != 1){
		    System.out.println("Your turn is over. End Turn? \n 1.Yes");
		    next = Keyboard.readInt();
		}

		clearScreen();
		
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

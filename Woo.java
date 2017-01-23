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
    public void checkDrawPile(){
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
	checkDrawPile();
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
	    info = info +  allPlayers.get(i) + " has " + allPlayers.get(i).getCurrentCards().size()+" cards left.\n";
	}
	info += "==============================================\n";
	System.out.println(info);
    }

    //prints the start screen + rules
    //players enter their corresponding names & pins
    public void beginGame() {
	String start="Welcome to UNO!\n"; 
	start+="Enter 1 for starting the game! \n";
	start+="Enter 2 for rules \n";
	System.out.println(start);
	int typegame=Keyboard.readInt();
	while (typegame < 1 || typegame >= 2){	   
	    if (typegame==2){
		String rules="\nHow to Play:\n";
		rules+="1. A game consists of 2-5 players. Each player starts with 7 cards and a dice is rolled \n";
		rules+="2. On your turn, you must either draw or play \n";
		rules+="3. To play a card, color, number, or action must match the last played card.\n";
		rules+="4. You must call UNO when you have one card left, or else you will draw 2 cards. If you call UNO when you have more than one card, you will draw 2 cards.\n";
		rules+="\n     Card Actions:\n    -Reverse: Reverses the turn order\n    -Skip: Skips the next players turn\n    -Draw2: Next player draws 2 cards\n    -Wild/Wild Draw4: Play again, with any card you want \n\n";
		rules+="How to Win:\n";
		rules+="1. The first player to get rid of his/her last card wins!\n";
		rules+="2. Cards from the other players hand are added to the winner's score: \n\n     Card Values:\n    -Number Cards: their face value \n    -Action Cards (skip, reverse, draw2): 20 \n    -Wild/Wild Draw 4: 50 \n\n";
		rules+="3. The first player to reach 500 points wins. \n\n";
		rules+="==============================================\n\n";

		System.out.print(rules);
		typegame=0;
	    }
	    System.out.print(start);
	    typegame=Keyboard.readInt();
	}

	//max number of players: 5
	if (typegame==1){ 
	    System.out.print("How many real players?:"); 
	    numRealPlayers=Keyboard.readInt();
	    while (numRealPlayers < 1 || numRealPlayers > 5){
		System.out.print("UNO games consist of 2-5 players. Enter a value between 1-5:"); 
		numRealPlayers=Keyboard.readInt();
	    }
	    System.out.print("How many computer/Ai players?:"); 
	    numAi=Keyboard.readInt();
	    while (numAi < 0 || numAi > (5 - numRealPlayers) ) {
		System.out.print("UNO games consist of 2-5 players. Enter a value less than or equal to " + (5 - numRealPlayers) + ":"); 
		numAi=Keyboard.readInt();
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
	for (int i = 0; i < numAi; i++) {
	    allPlayers.add(new Ai());
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
			if (allPlayers.size() == 2){
			    if (turn == 0){
				turn ++;
			    }
			    if (turn == 1){
				turn --;
			    }
			}
			//if skips the last person
			if (turn == allPlayers.size()-1){
			    turn = 0;
			}
			else{
			    turn ++;
			}
			System.out.println("***LAST PLAYER PLAYED A SKIP!***");
		    }
		}

		//if the last player played a reverse (only displays a message)
		if (move == 1){
		    Card lastCard = discardPile.get(discardPile.size()-1);
		    if( lastCard instanceof SpecialCard && (((SpecialCard)lastCard).getAction() == 1)){ 
			System.out.println("***LAST PLAYER PLAYED A REVERSE!***");
			if (allPlayers.size() == 2){
			    if (turn == 0){
				turn ++;
			    }
			    if (turn == 1){
				turn --;
			    }
			}
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
		if (!(currentPlayer instanceof Ai) ) {
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
		    while (move != 1 && move != 2){
			System.out.println("What would you like to do? \n 1. Play \n 2. Draw \n");
			move= Keyboard.readInt();
		    }
		    System.out.println("are you sure? \n 1:Yes \n 2:No  \n");
		    int mkSure = Keyboard.readInt();
		    if (mkSure < 1 || mkSure > 2) {
			System.out.println("please enter a correct resonse: ");
			mkSure = Keyboard.readInt(); 
		    }
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
				if (currentPlayer.getCurrentCards().size() != 0){
				    System.out.println("Current hand: " + currentPlayer.getCurrentCards());
				    System.out.println("You just played a wild or wild draw 4 card! \n Enter the card you want to play by entering the index:");
				    cardIndex = Keyboard.readInt();
				    while (cardIndex > (currentPlayer.getCurrentCards() ).size() - 1 || cardIndex < 0) {
					System.out.println("the inputed index is out of bound. Re-enter an index");
					cardIndex = Keyboard.readInt();
				    }
				    if (currentPlayer.getCurrentCards().size() != 1){
					Card temp = currentPlayer.getCurrentCards().get(cardIndex);
				   
					while(temp instanceof SpecialCard && (((SpecialCard)temp).getAction() ==  4 || ((SpecialCard)temp).getAction() ==  5)){
					    System.out.println("You cannot play a wild card after a wild card. Re-enter an index:");
					    cardIndex = Keyboard.readInt();
					    temp = currentPlayer.getCurrentCards().get(cardIndex);
					}
				    
				    }
				    currentPlayer.playCard(cardIndex, discardPile);
				}
			    }
			}

			//if the player wants to call UNO

			System.out.println("\nDo you want to call UNO? \n1.Yes \n2.No");
			int callUNO = Keyboard.readInt();
			while (callUNO != 1 && callUNO != 2){
			    System.out.println("\nDo you want to call UNO? \n1.Yes \n2.No");
			    callUNO= Keyboard.readInt();
			}
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
		    		
		    if (move==2){ //DRAW
			System.out.println(draw(currentPlayer));
			//if the card that is drawn can be played
			if (Card.isMatch( discardPile.get(discardPile.size()-1),
					  (currentPlayer.getCurrentCards().get(currentPlayer.getCurrentCards().size()-1) ) 
					  )) {
			    System.out.println("Current hand: " + currentPlayer.getCurrentCards());
			    System.out.println("Do you want to play the card that you just drew? 1.YES 2.NO");
			    int choice = Keyboard.readInt();
			    while (choice != 1 && choice != 2){
				System.out.println("Do you want to play the card that you just drew? 1.YES 2.NO");
				choice= Keyboard.readInt();
			    }
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
					if (currentPlayer.getCurrentCards().size() != 0){
					    System.out.println("Current hand: " + currentPlayer.getCurrentCards());
					    System.out.println("You just played a wild or wild draw 4 card! \n Enter the card you want to play by entering the index:");
					    int cardIndex = Keyboard.readInt();
					    while (cardIndex > (currentPlayer.getCurrentCards() ).size() - 1 || cardIndex < 0) {
						System.out.println("the inputed index is out of bound. Re-enter an index");
						cardIndex = Keyboard.readInt();
					    }
					    if (currentPlayer.getCurrentCards().size() != 1){
						Card temp = currentPlayer.getCurrentCards().get(cardIndex);
						while(temp instanceof SpecialCard && (((SpecialCard)temp).getAction() ==  4 || ((SpecialCard)temp).getAction() ==  5)){
						    System.out.println("You cannot play a wild card after a wild card. Re-enter an index:");
						    cardIndex = Keyboard.readInt();
						    temp = currentPlayer.getCurrentCards().get(cardIndex);
						}
					    }
					    currentPlayer.playCard(cardIndex, discardPile);
					}
				    }
				}

				if (currentPlayer.getCurrentCards().size() != 0){
				    //if the player wants to call UNO

				    System.out.println("\nDo you want to call UNO? \n1.Yes \n2.No");
				    int callUNO = Keyboard.readInt();
				    while (callUNO != 1 && callUNO != 2){
					System.out.println("\nDo you want to call UNO? \n1.Yes \n2.No");
					callUNO= Keyboard.readInt();
				    }
				    if (callUNO == 1){
					if (currentPlayer.getCurrentCards().size() >1){ 
					    //IF YOU DON'T HAVE UNO-->DRAW 2 CARDS
					    System.out.println("\nEek! You don't have UNO! Draw 2 Cards. \n");
					    System.out.println(draw(currentPlayer));
					    System.out.println(draw(currentPlayer));
					}
				    }

				    //if the currentPlayer only has one card left and he/she didnt call UNO
				    if (currentPlayer.getCurrentCards().size()<=1 && callUNO == 2){			  
					System.out.println("\nSo close...yet so far. Remember to call UNO next time. Draw 2 \n");
					System.out.println(draw(currentPlayer));		
					System.out.println(draw(currentPlayer)+"\n");
				    }
				}
			    }// end of draw and play
			}
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
		} //end of if not Ai
		clearScreen();
		if (currentPlayer instanceof Ai) {
		    ((Ai)currentPlayer).analyzeAndPlay(turn, allPlayers, drawPile, discardPile);
		    if (((Ai)currentPlayer).getCurrentCards().size() == 0) { //if the ai wins this round
			System.out.println("AI THE WINNER!");
			int score = 0; 
			for (Player s : allPlayers) {
			    for (Card x : s.getCurrentCards() ) {
				score += x.getPoint(); 
			    }
			}
			((Ai)currentPlayer).setScore(score);
		    
			//if the player has 500 pts then he/she won the whole game
			if (((Ai)currentPlayer).getScore() >= 500) {
			    System.out.println("AI won the whole game!");
			    break;
			}
			else {
			    playGame();
			}
		    }
		}
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

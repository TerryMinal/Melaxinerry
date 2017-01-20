package UNO;
import java.util.*;
public class Card {
    protected int color; // 1: red, 2: blue, 3: yellow, 4: green, 5:wild/black
    protected int pointVal; /*Number cards count their face value, all action cards count 20, and Wild and Wild Draw Four cards count 50. If a Draw Two or Wild Draw Four card is played to go out, the next player in sequence must draw the appropriate number of cards before the score is tallied.*/
     
    //creates a whole deck
    public static ArrayList<Card> createDeck(){
	//the end of the array will be the top side of the deck 
        ArrayList<Card> deck = new ArrayList <Card> (108); //total 108 cards
	
	//adds normal cards 0
	for (int col = 1; col <= 4; col++) {
	    deck.add(new NormalCard(0, col));
	}
	
	//adds normal cards 1-9 
	for (int num = 1; num <= 9; num++) {
	    for (int col = 1; col <= 4; col++) {
		for (int z = 0; z < 2; z++) {
		    deck.add(new NormalCard(num, col)); 
		}
	    }
	}

	//special cards (skip, reverse, draw2)
	for (int d = 1; d <= 3; d ++){
	    //cycling through colors
	    for (int col = 1; col <= 4; col++){
		for (int z = 0; z < 2; z++) {
		    deck.add(new SpecialCard(d, col));
		}
	    }
	}
	
	//wild & wild draw 4
	for (int f = 0; f < 4; f++){
	    deck.add(new SpecialCard(4, 5));
	    deck.add(new SpecialCard(5,5));
	}
	return deck;
    }
    
    //shuffles the deck
    public static void shuffle(ArrayList<Card> deck) {
	for (int i = 0; i < deck.size(); i++) {
	    deck.set(i, deck.set((int) (Math.random() * 108), deck.get(i)) ); 
	}
	//if the last card (which becomes the first card) is a specialCard -> re-shuffle
	while (deck.get(deck.size()-1) instanceof SpecialCard){
	    for (int x = 0; x < deck.size(); x++) {
		deck.set(x, deck.set((int) (Math.random() * 108), deck.get(x)) ); 
	    }
	}
    }
    
    //if the two cards match in number, color, or action
    public static boolean isMatch(Card lastCard, Card playCard){
	//match in color
	if (lastCard.color == playCard.color){
	    return true;
	}
	
	//if the lastCard is a wild or wild draw 4 card
	if (lastCard instanceof SpecialCard){
	    if (((SpecialCard)lastCard).getAction() == 4 || ((SpecialCard)lastCard).getAction() == 5){
		return true;
	    }
	}

	//if the playCard is a wild or wild draw 4 card
	if (playCard instanceof SpecialCard){
	    if (((SpecialCard)playCard).getAction() == 4 || ((SpecialCard)playCard).getAction() == 5){
		return true;
	    }
	}

	//match in number
	if (lastCard instanceof NormalCard && playCard instanceof NormalCard){
	    if (((NormalCard)lastCard).getNum() == ((NormalCard)playCard).getNum()){
		return true;
	    }
	}

	//match in action
	if (lastCard instanceof SpecialCard && playCard instanceof SpecialCard){
	    if (((SpecialCard)lastCard).getAction() == ((SpecialCard)playCard).getAction()){
		return true;
	    }
	}
	return false;
    }
    
    /* 
    public static void main(String[] args){
	ArrayList<Card> test = Card.createDeck();
	System.out.println(test);
    }//end main
    */
}

package UNO;
import java.util.*;
import UNO.cs1.Keyboard;
import java.io.Console; 

public class Player{
    protected String name;
    protected int score;
    protected String pin; //4-digit
    protected ArrayList <Card> currentCards;
    public int calledUNO=0;
    
    public Player(String name, String pin){
	this.name = name;
	score = 0;
	this.pin = pin;
	currentCards = new ArrayList<Card>();
    }//end constructor
    
    public boolean playCard(int index, ArrayList<Card> playedArea) {
	if( Card.isMatch( (playedArea.get(playedArea.size()-1)) ,currentCards.get(index) ) ) {	    
	    playedArea.add(currentCards.remove(index));
	    return true;
	}
	return false;	    
    }
    
    //takes card from end of the array 
    public Card draw(ArrayList<Card> deck) {
	Card drawn = deck.remove(deck.size() - 1);
	currentCards.add(drawn);
	return drawn;
    }

    public int setScore(int score) {
	int oldScore = this.score;
	this.score = score;
	return oldScore;
    }
    
    public boolean isCallUNO() {
	if (currentCards.size() == 1) {
	    return true;
	}
	else {
	    return false;
	}
    }

    public boolean win(){
	if (currentCards.size() == 0) {
	    return true;
	}
	else {
	    return false;
	}
    }
    
    void sortCards(int whichSort) {
    }

    void sortCardsColor() {
    }

    void sortCardsNum() {
    }

    void sortCardsWild() {
    }
   
    public ArrayList<Card> getCurrentCards() {
	return currentCards;
    }

    public int getScore(){
	return this.score;
    }

    public void checkPin(){
	System.out.println("Please enter the pin:");
	Console pwd = System.console();
	char[] pass = pwd.readPassword();
 	String pin = "";
 	for (char s : pass) {
     	    pin += s; 
 	}
	if ( !(this.pin.equals(pin)) ){
	    System.out.println("WRONG PIN! Please re-enter:");
	    checkPin();
	}
	else
	    return; 
    }
    
    public String toString(){
	return this.name;
    }

}//end class

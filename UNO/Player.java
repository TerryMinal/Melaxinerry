package UNO;
import java.util.*;
import UNO.cs1.Keyboard;

public class Player{
    protected String name;
    protected int score;
    protected int pin; //4-digit
    protected ArrayList <Card> currentCards;
    
    public Player(String name, int pin){
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
    public void draw(ArrayList<Card> deck) {
	currentCards.add(deck.remove(deck.size() - 1));
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

    public void UNOMiniGame() {
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
	int pin = Keyboard.readInt();
		
	while (pin != this.pin){
	    System.out.println("WRONG PIN! Please re-enter:");
	    pin = Keyboard.readInt();
	}
    }
    
    public String toString(){
	return this.name;
    }

}//end class

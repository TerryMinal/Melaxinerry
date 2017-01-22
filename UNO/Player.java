package UNO;
import java.util.*;
//import java.io.Console.*; 
import UNO.cs1.Keyboard;

public class Player {
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
    public void draw(ArrayList<Card> deck) {
	currentCards.add(deck.remove(deck.size() - 1));
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


    public String setName(String name) {
	String oldName = this.name;
	this.name = name;
	return oldName;
    }

    public String getName() {
	return name;
    }

    public String setPin(String pin) {
	String oldPin = this.pin;
	this.pin = pin;
	return oldPin;
    }

    public String getPin() {
	return pin; 
    }

    public void checkPin() {
	System.out.println("Please enter the pin:");
	String pin = Keyboard.readString();	
	while (!(this.pin.equals(pin)) ){
	    System.out.println("WRONG PIN! Please re-enter:");
	    pin = Keyboard.readString();
	}
    }

    public int setScore(int score) {
	int oldScore = this.score;
	this.score = score;
	return oldScore;
    }
    
    public int getScore(){
	return score;
    }
    
    public String toString(){
	return name;
    }

}//end class

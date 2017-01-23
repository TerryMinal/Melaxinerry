package UNO;
import java.util.*;
import UNO.cs1.Keyboard;
import java.io.Console; 

public class Player{
    protected String name;
    protected int score;
    protected String pin; //4-digit
    protected ArrayList <Card> currentCards;
    
    public Player(String name, String pin){
	this.name = name;
	score = 0;
	this.pin = pin;
	currentCards = new ArrayList<Card>();
    }//end constructor

    //check if the played card and the last card of the discardPile match
    //if they match, it will play the card and return true
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

    //if the current player has 0 current cards -> wins the round
    public boolean win(){
	if (currentCards.size() == 0) {
	    return true;
	}
	else {
	    return false;
	}
    }
    
    public void sortCards() {
	System.out.println("How would you like to sort it by: \n1:Color \n2:Numerical Order \n");
	int whichSort = Keyboard.readInt();
	while (whichSort < 1 || whichSort > 2) {
	    System.out.println("please input an acceptable number");
	    whichSort = Keyboard.readInt();
	}
	 
	if (whichSort == 1) {
	// 1: red, 2: blue, 3: yellow, 4: green
	System.out.println("Which color: \n1:red \n2:blue \n3:yellow \n4:green \n5:wild");
	int color = Keyboard.readInt();
	while (color < 1 || color > 6) {
	    System.out.println("wrong input. Please input an acceptable number");
	    color = Keyboard.readInt();
	}
	    sortCardsColor(color);
	    System.out.println(currentCards);
	}
	else {
	    sortCardsNum();
	    System.out.println(currentCards);
	}
    }

    public int sortCardsColor(int color) {
	int lastSortedCard = 0; 
	//selection sort
	for (int i = 0; i < currentCards.size()-1; i++) {
	    Card current = currentCards.get(i);
	    if(current.getColor() == color) {
		i++;
		lastSortedCard++; 
	    }
	    for (int n = i + 1;  n < currentCards.size(); n++) {
		if (currentCards.get(n).getColor() == color) {
		    currentCards.set(i, currentCards.set(n, current));
		    lastSortedCard++;
		    break;
		}
	    }
	}
	return lastSortedCard; 
    }

    //insertion sort
    public void sortCardsNum() {
	for (int x = 1; x < currentCards.size(); x++) {
	    int counter = x; 
	    for (int n = x - 1; n >= 0; n--) {
		System.out.println(currentCards.get(x) + ": (is being switched)  " + currentCards.get(x).getNum());
		if (currentCards.get(counter).getNum() < currentCards.get(n).getNum()  ) {
		     System.out.println(currentCards.get(n) + ": " + currentCards.get(n).getNum());
		    counter--;
		}
	    }
	}
    }

    public ArrayList<Card> getCurrentCards() {
	return currentCards;
    }

    public int getScore(){
	return this.score;
    }

       public int setScore(int score) {
	int oldScore = score;
	this.score = score;
	return oldScore;
    }

    //check if the pin entered is correct
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

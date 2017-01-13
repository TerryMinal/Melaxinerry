import java.util.ArrayList;
public class Deck{
    
    private ArrayList <Card> deck;
    private ArrayList <Card> DrawPile;
    private ArrayList <Card> DiscardPile;

    //creates a whole deck
    public Deck(){
        deck = new ArrayList <Card> (108); //total 108 cards
	
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

    }//end constructor

    public String toString(){
	return deck.toString();
    }

    //first shuffle: for 108 cards
    public void shuffle() {
	for (int i = 0; i < 108; i++) {
	    deck.set(i, deck.set((int) (Math.random() * 108), deck.get(i)) ); 
	}
    }

    //returns an shuffled ArrayList of a given ArrayList
     public static ArrayList <Card> shuffle(ArrayList <Card> input) {
	  ArrayList<Card> shuffledDeck = new ArrayList<Card>();
	for( Card x : input){
	    shuffledDeck.add(x);
	}
	for (int i = 0; i < shuffledDeck.size(); i++) {
	    shuffledDeck.set(i, shuffledDeck.set((int) (Math.random() * 108), shuffledDeck.get(i)) ); 
	}
	return shuffledDeck;
    }

    public static void main(String[] args){
	Deck one = new Deck();
	one.shuffle();
	System.out.println(one);
	System.out.println(shuffle(one.deck));
    }//end main
    
}//end class

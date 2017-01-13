import java.util.ArrayList;
public class Deck{
    
    private ArrayList <Card> deck; //the draw pile
  
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
    
    //shuffles the deck
     public void shuffle() {
	 for (int i = 0; i < deck.size(); i++) {
	     deck.set(i, deck.set((int) (Math.random() * 108), deck.get(i)) ); 
	 }
     }

    public Card get(int index) {
	return deck.get(index); 
    }

    public Card remove (int index) {
	return deck.remove(index);
    }
    
    public String toString(){
	return deck.toString();
    }    
    
    public static void main(String[] args){
	Deck one = new Deck();
        one.shuffle();
       	System.out.println(one);
    }//end main
    
}//end class

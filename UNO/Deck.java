import java.util.ArrayList;
public class Deck{
    protected int color; // 1: red, 2: blue, 3: yellow, 4: green, 5:wild/black
    protected int pointVal; /*Number cards count their face value, all action cards count 20, and Wild and Wild Draw Four cards count 50. If a Draw Two or Wild Draw Four card is played to go out, the next player in sequence must draw the appropriate number of cards before the score is tallied.*/
    
    private ArrayList <Deck> deck;
    private ArrayList <Deck> DrawPile;
    private ArrayList <Deck> DiscardPile;

    //creates a whole deck
    public Deck(){
        deck = new ArrayList <Deck> (108); //total 108 cards
	int color= 1;
	//normal cards
	for (int a = 0; a < 1; a ++){
	    color = 1;
	    for (int i = 0; i < 4; i++){
		deck.add(new NormalCard(a, color));
		color ++;		    
	    }
	}
	for (int b = 1; b < 10; b ++){
	    color = 1;
	    for (int i = 0; i < 4; i++){
		deck.add(new NormalCard(b, color));
		color ++;		    
	    }
	}
	for (int c = 1; c < 10; c ++){
	    color = 1;
	    for (int i = 0; i < 4; i++){
		deck.add(new NormalCard(c, color));
		color ++;		    
	    }
	}
	
	//special cards (skip, reverse, draw2)
	for (int d = 1; d < 4; d ++){
	    color = 1;
	    for (int i = 0; i < 4; i++){
		deck.add(new SpecialCard(d, color));
		color ++;		    
	    }
	}
	for (int e = 1; e < 4; e ++){
	    color = 1;
	    for (int i = 0; i < 4; i++){
		deck.add(new SpecialCard(e, color));
		color ++;		    
	    }
	}

	//wild
	for (int f = 0; f < 4; f++){
	    deck.add(new SpecialCard(4, 5));
  		    
	}

	//wild draw 4
	for (int g = 0; g < 4; g++){
	    deck.add(new SpecialCard(5, 5));
  		    
	}
    }//end constructor

    public String toString(){
	return deck.toString();
    }
    
    public void shuffle(ArrayList <Deck> deck){
	
    }
				       
    public static void main(String[] args){
	Deck one = new Deck();
	System.out.println(one);
	
    }//end main
    
}//end class

import java.util.ArrayList;
public class Deck{

    public static ArrayList <Card>  createDeck(){
	ArrayList <Card> deck = new ArrayList <Card> (108); //total 108 cards
	int color= 1;
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
	return deck;
    }
				       
    public static void main(String[] args){

	System.out.println(createDeck());
    }//end main
    
}//end class

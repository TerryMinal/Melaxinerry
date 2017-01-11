import java.util.ArrayList;
public class Deck{

    public ArrayList <Card>  createDeck(){
	ArrayList <Card> deck = new ArrayList <Card> (108); //total 108 cards
	int color= 1;
	for (int a = 0; a < 10; a ++){
	    color = 1;
	    for (int i = 0; i < 4; i++){
		deck.add(new NormalCard(a, color));
		color ++;
		    
	    }
	}
	return deck;
    }
				       
    public static void main(String[] args){

	System.out.println(createDeck());
    }//end main
    
}//end class

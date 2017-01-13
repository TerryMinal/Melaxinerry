
import java.util.*;

public class WOO{

    int numAi;
    int numRealPlayers;
    ArrayList<Player> allPlayers;
    ArrayList<Deck> discardPile;
    //instantiate players and put them in arraylist  
    public WOO() {
	numAi = 0;
	numRealPlayers = 0;	
	allPlayers = new ArrayList<Player>();
	discardPile = new ArrayList<Deck>();
    }

   public void distribute() {
       for (int i = 0; i < allPlayers.size(); i++) {
	    for (int n; n < 7; n++) {
		allPlayers.get(i).add(drawPile.get(n));
		drawPile.remove(n); 
	    }
	}
   }

    //returns index of player that goes first;
    public Player rollDice(){
        int first=Math.random()*(allPlayers.size()); //[0,size-1]
	//rearranges allPlayer order list (eg. 01234 -->2-->23401)
	for (int i=0; i<allPlayers.size()-first;i++){
	    allPlayers.add(i,allPlayers.remove(first+i));
	}
    }

    /*	
    public void game() {
	
    
    }
    */
    public static void main(String[] args){
	Deck drawPile = new Deck();
        //prints an ArrayList of the whole deck
	System.out.println(one);
	
	
    }//end main
    
}//end class

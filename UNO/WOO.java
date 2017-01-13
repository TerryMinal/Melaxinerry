
import java.util.*;

public class WOO{

    int numAi;
    int numRealPlayers;
    ArrayList<Player> allPlayers; 
    ArrayList<Deck> discarded;
 
    //instantiate players and put them in arraylist  
    public WOO() {
	allPlayers = new ArrayList<Player>(); 
	discarded = new ArrayList<Deck>();
    }

   public void distribute(ArrayList<Deck> deck) {
	for (int i = 0; i < allPlayers.size(); i++) {
	    for (int n = 0; n < 7; n++) {
		allPlayers.get(i).add(); 
	    }
	}
   }

    //returns index of player that goes first;
    public Player rolldice(){
        int first=Math.random()*(allPlayers.size()); //[0,size-1]
	return first;
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
	Deck one = new Deck();
        //prints an ArrayList of the whole deck
	System.out.println(one);
	
	
    }//end main
    
}//end class

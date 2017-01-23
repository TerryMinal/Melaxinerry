package UNO;
import java.util.*;

public class Ai extends Player {

    public Ai () {
	super.name = "JARVIS";
	super.score = 0;
	super.currentCards = new ArrayList<Card>();
    }

    public boolean drawOrNot(int turn, int allPlayers, ArrayList<Card> discarded) {
	int numPlayers = allPlayers.size(); 
	int nextP = turn + 1; 
	boolean tf = false;
	//checking for case where card cannot be played
	for (Card s : currentCards) {
	    if (s.getColor() == discarded.get(discarded.size() - 1).getColor() || s.getNum() == discarded.get(discarded.size() - 1).getNum() ) {
		tf = true;
		break; 
	    }
	}
	if (tf) {
	    return false; 
	}
	else {
	    return true;
	}
    }
    
    public Card determineNormalCards(ArrayList<Card> discarded) {
	int topColor = discarded.get(discarded.size() - 1).getColor();
	int topNum = discarded.get(discarded.size() - 1).getNum();
	ArrayList<Card> playable = new ArrayList<Card>();
	//determines all playable cards
	for (Card d : currentCards) {
	    if (d.getColor() == topColor || (d.getNum() == topNum && topNum < 10)  ) {
		playable.add(d); 
	    }
	}
	Card play;
	double Lprob = 1;
	double tempProb; 
	// calculate probability of other players having cards
	for (int i = 0; i < playable; i++) {
	    tempProb = calculateProb(discarded, playable.get(i) );
	    if ( tempProb < Lprob) {
		Lprob =tempProb;
		play = playable.get(i);
	    }
	}
	return play;
    }

    public Card determineSpecialCards(int turn, ArrayList<Player> players, ArrayList<Card> discarded) {
	Player nextP = players.get(turn + 1);
	if (currentCards.size() == 2) {
	    for (int i = 0; i < 2; i++ ) {
		if (currentCards.get(i).getNum() == 13) {
		    playCard(i, discarded); 
		}
		Card play = determineNormalCards(discarded); 
	    }
	}
	//prevent player from winning
 	if (nextP.getCurrentCards().size() <= 3) {
	    super.sortCardsNum();
	    if (currentCards.get(currentCards.size() - 1) > 10) {
		playCard(currentCards.size() - 1, discarded); 
	    }
	}
	Player nextP = players.get( ((turn + 1) % players.size() ) + players.size());
	Player RnextP = players.get( ((turn - 1) % players.size() ) + players.size());
	PHandSize = nextP.getCurrentCards().size();
	RPHandSize = RnextP.getCurrentCards().size();
	if (PHandSize < 5) {
	    if (PHandSize < RPHandSize) {
		
	    }
	}
    }
    
    //calculates probability of a card being played in the future by another player
    private double calcualteProb(ArrayList<Card> discarded, Card C) {	
	double denom = 108 - discarded.size() -  currentCards.size();
	// calculate probability of other players having cards
	    int colProp = C.getColor();
	    int numProp = C.getNum();
	    double numUsed = 0 ; 
	    for (Card s : currentCards) {
		if (s.getColor() == colProp || s.getNum() == numProp)
		    numUsed++;
		if (s.getColor() == colProp && s.getNum() == numProp)
		    numUsed--;
	    }
	    for (Card z : discarded) {
		if (s.getColor() == colProp || s.getNum() == numProp)
		    numUsed++;
		if (z.getColor() == colProp && z.getNum() == numProp)
		    numUsed--;
	    }
	    return (denom - numUsed)/denom;
	}

    //precondition: 0 implies that only the other property is being looked  for 
    private boolean haveCard(int color, int num) {
	if (num == 0) {
	    for (Card s : currentCards) {
		if (s.getColor() == color) {
		    return true; 
		}
	    }
	    return false; 
	}
	if (color == 0) {
	    for (Card s : currentCards) {
		if (s.getNum() == num) {
		    return true; 
		}
	    }
	    return false; 	    
	}
	for (Card s : currentCards) {
	    if (s.getNum() == num && s.getColor() == color) {
		return true; 
	    }
	}
	return false; 	    	
    }
    
} //class

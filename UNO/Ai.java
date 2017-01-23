package UNO;
import java.util.*;

public class Ai extends Player {

    public Ai () {
	super.name = "JARVIS";
	super.score = 0;
	super.currentCards = new ArrayList<Card>();
    }

    public void analyzeAndPlay(int turn, ArrayList<Player> allPlayers, ArrayList<Card> deck, ArrayList<Card> discarded) {
	System.out.println("Move aiside JARVIS is playing");
	if (drawOrNot(turn, deck, discarded))
	    System.out.println("JARVIS drew a card");
	else if (!(determineSpecialCards(turn, allPlayers, discarded)))
	    determineNormalCards(discarded);
	callUNO();
    }

    public boolean drawOrNot(int turn, ArrayList<Card> deck, ArrayList<Card> discarded) {
	boolean cardInHand = false;
	//checking for case where card cannot be played
	for (Card s : currentCards) {
	    if (s.getColor() == discarded.get(discarded.size() - 1).getColor() || s.getNum() == discarded.get(discarded.size() - 1).getNum() ) {
		cardInHand = true;
		break; 
	    }
	}
	//if there is a card in the hand that can be played
	if (cardInHand) {
	    return false; 
	}
	else {
	    super.draw(deck); 
	    return true;
	}
    }

    public boolean playCard(int index, ArrayList<Card> discarded) {
	if (currentCards.get(index).getNum() >= 13) {
	    int[] color = new int[5];
	    for (Card s : currentCards) {
		color[s.getColor() - 1] += 1;
	    }
	    int max = 0; 
	    for (int i = 0; i < 5; i++) {
		if (color[i] > max) {
		    max = i;
		}
	    }
	    currentCards.get(index).setColor(max);
	    if (currentCards.get(index).getNum() == 13) {
		super.sortCardsColor(max);
		System.out.println("JARVIS played: " + currentCards.get(0)); 
		return super.playCard(0, discarded);
	    }
	}
	System.out.println("JARVIS played: " + currentCards.get(index)); 
	return super.playCard(index, discarded);
    }
    
    private void determineNormalCards(ArrayList<Card> discarded) {
	int topColor = discarded.get(discarded.size() - 1).getColor();
	int topNum = discarded.get(discarded.size() - 1).getNum();
	ArrayList<Card> playable = new ArrayList<Card>();
	//determines all playable cards
	for (Card d : currentCards) {
	    if (d.getColor() == topColor || (d.getNum() == topNum && topNum < 10)  ) {
		playable.add(d); 
	    }
	}
	int index = 0;
	double Lprob = 1;
	double tempProb;
	// calculate probability of other players having cards
	for (int i = 0; i < playable.size(); i++) {
	    tempProb = calculateProb(discarded, playable.get(i) );
	    if ( tempProb < Lprob) {
		Lprob =tempProb;
		index = i; 
	    }
	}
	System.out.println("JARVIS played: " + playable.get(index));
	currentCards.remove(currentCards.indexOf(playable.get(index)));
	discarded.add(playable.get(index));
    }

    private boolean determineSpecialCards(int turn, ArrayList<Player> players, ArrayList<Card> discarded) {
	super.sortCardsNum();
	Player nextP = players.get( ((turn + 1) % (players.size() - 1)) + players.size() - 1);
	Player RnextP = players.get( ((turn - 1) % (players.size() - 1) ) + players.size() - 1);
	if (currentCards.size() == 2) {
	    for (int i = 0; i < 2; i++ ) {
		if (currentCards.get(i).getNum() == 13) {
		    return playCard(i, discarded);
		}
		
		return playCard(i, discarded);
	    }
	}
	//prevent player from winning
 	if (nextP.getCurrentCards().size() <= 3) {
	    if (currentCards.get(currentCards.size() - 1).getNum() > 9) {
		return playCard(currentCards.size() - 1, discarded); 
	    }
	}
	int PHandSize = nextP.getCurrentCards().size();
	int RPHandSize = RnextP.getCurrentCards().size();
	if (PHandSize < 5) {
	    return playCard(currentCards.size() - 1, discarded);
	}
	return false; 
    }

    //calculates probability of a card being played in the future by another player
    private double calculateProb(ArrayList<Card> discarded, Card C) {	
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
		if (z.getColor() == colProp || z.getNum() == numProp)
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

    private void callUNO() {
	if (currentCards.size() == 1) {
	    System.out.println("UNO -- from JARVIS"); 
	}
    }
} //class

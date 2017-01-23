public class Ai extends Player {

    public Ai () {
	super.name = "JARVIS";
	super.score = 0;
	super.currentCards = new ArrayList<Card>();
    }

    public void determineNormalCards(ArrayList<Card> discarded) {
	int topColor = discarded.get(discarded.size() - 1).getColor();
	int topNum = discarded.get(discarded.size() - 1).getNum();
	ArrayList<Card> playable = new ArrayList<Card>();
	for (Card d : currentCards) {
	    if (d.getColor() == topColor || d.getNum() == topNum  ) {
		
	    }
	}
    }
}

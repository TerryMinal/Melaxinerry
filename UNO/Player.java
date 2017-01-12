public class Player{
    protected String name;
    protected int score;
    //protected int pin; //4-digit
    protected ArrayList <Deck> currentCards;
    
    public Player(String name){
	this.name = name;
	score = 0;
	//pin = 0000;
	currentCards = new ArrayList<Deck>();
    }//end constructor
    
    public void playCard(int index) {
	
    }

    public int setScore(int score) {
	int oldScore = this.score;
	this.score = score;
	return oldScore;
    }
    
    public void draw(ArrayList<Deck> deck){
	currentCards.add(deck.get(0));
    }

    void sortCards(int whichSort) {
    }

    void sortCardsColor() {
    }

    void sortCardsNum() {
    }

    void sortCardsWild() {
    }
    
    public void callUNO(){
    }
    
}//end class

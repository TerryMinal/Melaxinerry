public class Card {
    protected int color; // 1: red, 2: blue, 3: yellow, 4: green, 5:wild/black
    protected int pointVal; /*Number cards count their face value, all action cards count 20, and Wild and Wild Draw Four cards count 50. If a Draw Two or Wild Draw Four card is played to go out, the next player in sequence must draw the appropriate number of cards before the score is tallied.*/
}

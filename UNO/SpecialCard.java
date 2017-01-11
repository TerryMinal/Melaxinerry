public class SpecialCard extends Card{ 
    private int action;
    //1: reverse, 2: skip, 3: draw2, 4: wild, 5: wild & draw4

    public SpecialCard(int num,int color1){
	color = color1; // 1: red, 2: blue, 3: yellow, 4: green, 5:wild/black
	action = num;

	// all action cards count 20, and Wild and Wild Draw Four cards count 50
	if (action == 1 || action == 2 || action == 3){
	    pointVal = 20;
	}
	if (action == 4 || action == 5){
	    pointVal = 50;
	}
    }//end overloaded constructor
    
}//end class

package UNO;
import java.util.*;

public class SpecialCard extends Card { 
    private int action;
    //1: reverse, 2: skip, 3: draw2, 4: wild, 5: wild & draw4
    private String name;
    
    public SpecialCard(int action1,int color1){
	color = color1; // 1: red, 2: blue, 3: yellow, 4: green, 5:wild/black
	action = action1;
	if (action == 1){
	    name = "Reverse";
	}
	if (action == 2){
	    name = "Skip";
	}
	if (action == 3){
	    name = "Draw2";
	}
	if (action == 4){
	    name = "Wild";
	}
	if (action == 5){
	    name = "Wild Draw4";
	}
	// all action cards count 20, and Wild and Wild Draw Four cards count 50
	if (action == 1 || action == 2 || action == 3){
	    pointVal = 20;
	}
	if (action == 4 || action == 5){
	    pointVal = 50;
	}
    }//end overloaded constructor

    public int getAction(){
	return this.action;
    }
    
    public static ArrayList<Player> reverse(int index, ArrayList<Player> allPlayers){
	ArrayList<Player> temp = new ArrayList<Player>();
	temp.add(allPlayers.get(index));
	for (int x = index - 1; x >= 0; x --){
	    temp.add(allPlayers.get(x));
	}
	for (int i = allPlayers.size()-1; i > index; i --){
	    temp.add(allPlayers.get(i));
	}
	return temp;
    }

    public String toString(){
	String retStr = "";
	if (color == 1){
	    retStr = "Red:";
	}
	if (color == 2){
	    retStr = "Blue:";
	}
	if (color == 3){
	    retStr = "Yellow:";
	}
	if (color == 4){
	    retStr = "Green:";
	}
	return retStr + name;
    }

}//end class

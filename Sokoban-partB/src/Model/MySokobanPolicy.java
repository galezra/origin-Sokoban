package Model;

import Items.Box;
import Items.Item;
import Items.Position;
import Items.Wall;
import levels.Level;

public class MySokobanPolicy
{
	public boolean goThrowWalls()
	{
		return false;
	}
	public boolean moveTwoBox()
	{
		return false;
		
	}
	public boolean canPullBox()
	{
		return false;
	}
	public boolean check(Level lev,Item A,Position posB,Position posC)
	{
		if (lev.getItemByPosition(posB) instanceof Wall)
			return this.goThrowWalls();
		if ((lev.getItemByPosition(posB) instanceof Box)&&(lev.getItemByPosition(posC) instanceof Box))
			return this.moveTwoBox();
		return true;
	}
	


}

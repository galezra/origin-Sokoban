package Model;

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

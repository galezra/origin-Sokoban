package Model.Data;

import java.io.Serializable;

public class Item implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Position pos;
	private boolean onDest;
	
	public void print()
	{
		System.out.print(" ");
	}
	public String toString()
	{
		return "";
	}
	public Position getPos()
	{
		return this.pos;
	}
	public void setPos(Position other)
	{
		this.pos=other;
	}
	public boolean isOnDest() {
		return onDest;
	}
	public void setOnDest(boolean onDest) {
		this.onDest = onDest;
	}
	public Item() {
		super();
		// TODO Auto-generated constructor stub
		onDest=false;
	}

}

package Model.Data;

import java.io.Serializable;

public class Character extends Item implements Serializable {
	
	
	public String toString()
	{
		if (this.isOnDest())
			return "a";
		return "A";
	}
	
	public void print()
	{
		System.out.print("A");
	}
	public Character() {
		super();
		// TODO Auto-generated constructor stub
	}


	


}

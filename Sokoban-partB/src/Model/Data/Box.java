package Model.Data;

import java.io.Serializable;

public class Box extends Item implements Serializable{
	public Box() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String toString()
	{
		if (this.isOnDest())
			return "$";
		return "@";
	}
	public void print()
	{
		System.out.print("@");
	}


}

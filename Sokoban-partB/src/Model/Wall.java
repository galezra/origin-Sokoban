package Model;

import java.io.Serializable;

public class Wall extends Item implements Serializable {

	public void print()
	{
		System.out.print("#");
	}
	public String toString()
	{
		return "#";
	}
	public Wall() {
		super();
		// TODO Auto-generated constructor stub
	}

}

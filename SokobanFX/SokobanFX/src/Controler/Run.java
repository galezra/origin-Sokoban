package Controler;


import java.io.FileNotFoundException;

import java.io.IOException;


public class Run {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		CLI GR=new CLI();
		try{
		GR.run();
		}
		catch(Exception e)
		{
			System.out.println("caught exception: "+e);
		}
	}

}

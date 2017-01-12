package Controler;

import java.io.FileNotFoundException;
import java.io.IOException;

import Model.Level;

public class FunctionalCommand implements Command {
	private	Level lev;
	private String str;
	public void getStringFromInput(String str)
	{
		this.str=str;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public Level getLev() {
		return lev;
	}

	public void setLev(Level lev) {
		this.lev = lev;
	}

	public FunctionalCommand(Level lev) {
		super();
		// TODO Auto-generated constructor stub
		this.setLev(lev);
		
	}

	@Override
	public void execute() throws FileNotFoundException, IOException, ClassNotFoundException, Exception {
		// TODO Auto-generated method stub

	}

}

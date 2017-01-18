package View;

import java.util.Observable;
import java.util.Observer;

import Controler.SokobanController;
import Model.Data.Level;

public class MyView extends Observable implements ViewInterface {


	private Level lev;
	private String userCommand;



		
	
	public MyView() {
		super();
	
		this.userCommand=null;
		this.lev=new Level();

	}
	public String getUserCommand() {
		
		return userCommand;
	}
	public void setUserCommand(String userCommand) {
		this.userCommand = userCommand;
	}
	public Level getLev() {
		return lev;
	}
	public void setLev(Level lev) {
		this.lev = lev;
		this.setChanged();
	}


	@Override
	public void reDraw() {
		// TODO Auto-generated method stub
		
	}

}

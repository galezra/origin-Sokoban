package Model;

import java.util.Observable;
import java.util.Observer;

import Controler.SokobanController;
import Model.Data.Level;

public class MyModel extends Observable implements ModelInterface {


	private Level CurrentLevel;
	
	
	public void UpdateLevel(Level lev)
	{
		this.setCurrentLevel(lev);
		this.setChanged();
		
	}
	public MyModel() {
		
		// TODO Auto-generated constructor stub
		
		CurrentLevel=new Level();
		
		
	}

	

	public Level getCurrentLevel() {
		return CurrentLevel;
	}
	public void setCurrentLevel(Level currentLevel) {
		CurrentLevel = currentLevel;
		this.setChanged();
		this.notifyObservers();
		
	}


}

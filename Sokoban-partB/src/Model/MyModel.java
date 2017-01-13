package Model;

import java.util.Observable;
import java.util.Observer;

import Model.Data.Level;

public class MyModel extends Observable implements ModelInterface {

	private Observer MC;
	private Level CurrentLevel;
	public MyModel() {
		
		// TODO Auto-generated constructor stub
		CurrentLevel=new Level();
		
	}
	public MyModel(Observer mC) {
		super();
		MC = mC;
		CurrentLevel=new Level();
	}
	public Level getCurrentLevel() {
		return CurrentLevel;
	}
	public void setCurrentLevel(Level currentLevel) {
		CurrentLevel = currentLevel;
	}
	public Observer getMC() {
		return MC;
	}
	public void setMC(Observer mC) {
		MC = mC;
	}

}

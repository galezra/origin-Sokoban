package Model;

import java.util.Observable;
import java.util.Observer;

public class MyModel extends Observable implements ModelInterface {

	Observer MC;
	public MyModel() {
		
		// TODO Auto-generated constructor stub
	}
	public MyModel(Observer mC) {
		super();
		MC = mC;
	}
	public Observer getMC() {
		return MC;
	}
	public void setMC(Observer mC) {
		MC = mC;
	}

}

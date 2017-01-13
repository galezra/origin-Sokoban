package View;

import java.util.Observable;
import java.util.Observer;

public class MyView extends Observable implements ViewInterface {

	private Observer MC;
	public MyView() {
		// TODO Auto-generated constructor stub
		
	}
	public MyView(Observer mC) {
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

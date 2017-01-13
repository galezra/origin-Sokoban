package Boot;

import java.util.Observable;

import Controler.MyControler;
import Model.MyModel;
import View.MyView;

public class Run {

	public Run() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Observable  MV=new MyView();
		Observable MM=new MyModel();
		MyControler MC=new MyControler(MV,MM); 
		MV.addObserver(MC);
		MM.addObserver(MC);
	
	

	}

}

package Controler;

import java.util.Observable;



public class MyControler implements ControlerInterface {
	Observable  MV;
	Observable MM;

	public Observable getMV() {
		return MV;
	}

	public void setMV(Observable mV) {
		MV = mV;
	}

	public Observable getMM() {
		return MM;
	}

	public void setMM(Observable mM) {
		MM = mM;
	}

	public MyControler() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}

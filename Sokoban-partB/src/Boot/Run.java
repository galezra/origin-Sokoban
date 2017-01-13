package Boot;

import Controler.MyControler;
import Model.MyModel;
import View.MyView;

public class Run {

	public Run() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyControler MC=new MyControler(); 
		MyView MV=new MyView(MC);
		MyModel MM=new MyModel(MC);
		MC.setMM(MM);
		MC.setMV(MV);

	}

}

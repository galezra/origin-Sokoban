package Boot;
import Controler.SokobanController;
import Model.MyModel;
import View.MyView;

public class Run {





	public Run() {
		// TODO Auto-generated constructor stub
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyView  MV=new MyView();
		MyModel MM=new MyModel();
		SokobanController MC=new SokobanController(MV,MM);
		MV.addObserver(MC);
		MM.addObserver(MC);



	}

}

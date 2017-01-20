package Controler;


import java.util.Observable;
import java.util.Observer;

import Model.MyModel;
import view.MyView;



public class SokobanController implements Observer {
	private MyView  MV;
	private MyModel MM;
	private Controller C;
	private CommandsFactory cF;
	
	
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
	
		CommandCreator cC;
		cF=new CommandsFactory();
		FunctionalCommand cmd;
		if (arg0==MM)
		{
			MV.setLev(MM.getCurrentLevel());
		}
		else if (arg0==MV)
		{
			
				
				//getting the specific command by string
			cC=cF.getCM().get(arg1);
			if (cC!=null)
			{
				cmd=cC.create();
				if (cmd!=null)
				{//add this command to my controller queue
				cmd.setStr((String)arg1);
				this.C.getmQ().add(cmd);
				/*get the first command in my queue,but in our case this will be
				the command i entered in the line above*/
				cmd=(FunctionalCommand)C.getCmd();
				
					cmd.setLev(this.MM.getCurrentLevel());
					try {
						
						cmd.execute();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.MM.setCurrentLevel(cmd.getLev());
					
				}
			}
			
			
			
			
			
			
		}
		

	}
	public Controller getC() {
		return C;
	}
	public void setC(Controller c) {
		C = c;
	}
	public CommandsFactory getcF() {
		return cF;
	}
	public void setcF(CommandsFactory cF) {
		this.cF = cF;
	}
	public void setMV(MyView mV) {
		MV = mV;
	}
	public void setMM(MyModel mM) {
		MM = mM;
	}
	public Observable getMV() {
		return MV;
	}

	public void setMV(Observable mV) {
		MV = (MyView) mV;
	}

	public Observable getMM() {
		return MM;
	}

	public void setMM(Observable mM) {
		MM = (MyModel) mM;
	}

	public SokobanController() {
		// TODO Auto-generated constructor stub
		this.MM=new MyModel();
		this.MV=new MyView();
		this.C=new Controller();
		this.cF=new CommandsFactory();
	}

	public SokobanController(MyView  mV, MyModel mM) {
		super();
		MV =  mV;
		MM =  mM;
		this.C=new Controller();
		this.cF=new CommandsFactory();
	}
	

	
	

}

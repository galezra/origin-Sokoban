package Controler;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import Model.MyModel;
import Model.Data.MyTextLevelLoader;
import view.MainWindowController;
import view.MyView;



public class SokobanController implements Observer {
	private MainWindowController MWC;
	private MyModel MM;
	private Controller controller;
	private CommandsFactory cF;
	
	
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
		CommandCreator cC;
		FunctionalCommand cmd;
		
		
		if (arg0==MM)
		{
			
			MWC.setArr((MM.getCurrentLevel().toCharArray()));
		}
		else if (arg0==MWC)
		{
			
				
				//getting the specific command by string
			
			String []s=MWC.getUserCommand().split(" ");

			cC=cF.getCM().get(s[0]);
			
			if (cC!=null)
			{
				
				cmd=cC.create();
				
				if (cmd!=null)
				{//add this command to my controller queue
					cmd.setStr(MWC.getUserCommand());
					this.controller.getmQ().add(cmd);
					/*get the first command in my queue,but in our case this will be
					the command i entered in the line above*/
					cmd=(FunctionalCommand)controller.getCmd();
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
	public Controller getController() {
		return controller;
	}
	public void setController(Controller c) {
		controller = c;
	}
	public CommandsFactory getcF() {
		return cF;
	}
	public void setcF(CommandsFactory cF) {
		this.cF = cF;
	}

	public void setMM(MyModel mM) {
		MM = mM;
	}

	
	public MainWindowController getMWC() {
		return MWC;
	}
	public void setMWC(MainWindowController mWC) {
		MWC = mWC;
	}
	public MyModel getMM() {
		return MM;
	}
	public SokobanController() {
		// TODO Auto-generated constructor stub
		this.MM=new MyModel();

		MM.addObserver(this);
		this.controller=new Controller();
		this.cF=new CommandsFactory();
	}

	public SokobanController(MainWindowController  mwc, MyModel mM) {
		super();
		
		MWC=  mwc;
		MM =  mM;
		this.controller=new Controller();
		this.cF=new CommandsFactory();
	}
	

	
	

}

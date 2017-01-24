package Controler;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import Controller.Server.MyServer;
import Model.MyModel;
import Model.Data.MyTextLevelLoader;
import view.MainWindowController;
import view.MyView;
import view.ViewInterface;



public class SokobanController implements Observer {
	private MainWindowController MWC;
	private MyModel MM;
	private Controller controller;
	private CommandsFactory cF;
	private MyServer ms;
	
	public void runServer()
	{
		try {
			ms.runServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean runUserCommand(String cmd)
	{
		CommandCreator cC;
		FunctionalCommand command;

		String[]s=cmd.split(" ");
		cC=cF.getCM().get(s[0]);
		if (cC!=null)
		{
			command=cC.create();
			
			if (command!=null)
			{//add this command to my controller queue
				command.setStr(cmd);
				this.controller.getmQ().add(command);
				/*get the first command in my queue,but in our case this will be
				the command i entered in the line above*/
				command=(FunctionalCommand)controller.getCmd();
				command.setLev(this.MM.getCurrentLevel());
					try {
						
						command.execute();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.MM.setCurrentLevel(command.getLev());
					return true;				
				
			}
		}
		return false;
	}
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub		
		boolean ifHappend=false;
		if (arg0==ms.getCh())
		{
			
			ifHappend=this.runUserCommand(this.ms.getUserCommand());
			ms.getCh().setIfHappend(ifHappend);
			this.getMWC().getSd().setDone(this.MM.getCurrentLevel().checkIfFinish());
		}
		if (arg0==MM)
		{
			MWC.getSd().setDone(MM.getCurrentLevel().checkIfFinish());
			MWC.setArr((MM.getCurrentLevel().toCharArray()));
			MWC.setSteps(MM.getCurrentLevel().getStepsCounter());
			ms.getCh().setIfHappend(true);
		}
		else if (arg0==MWC)
		{			
			this.runUserCommand(MWC.getUserCommand());
			
			
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

		
	
		this.controller=new Controller();
		this.cF=new CommandsFactory();
		MM.addObserver(this);
		this.ms=new MyServer();
		
		ms.getCh().addObserver(this);
		
	}

	public SokobanController(MainWindowController  mwc, MyModel mM) {
		super();
		
		MWC=  mwc;
		MM =  mM;
		this.controller=new Controller();
		this.cF=new CommandsFactory();
	}
	

	
	

}

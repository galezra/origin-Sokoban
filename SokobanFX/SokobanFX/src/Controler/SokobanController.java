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
	private boolean isFinish=false;
	public void runServer()
	{
		try {
			ms.runServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void closeAll()
	{
		this.controller.finishAllCommands();
		this.MWC.closeAllThreads();
		this.ms.closeAllSockets();
	}
	public boolean runUserCommand(String cmd)
	{
		CommandCreator cC;
		FunctionalCommand command;
		
		String[]s=cmd.split(" ");
		if(s[0].compareTo("load")==0)
		{
			this.isFinish=false;
		}
		else if (s[0].compareTo("exit")==0)
		{
			this.closeAll();
		}
		if(!this.isFinish)
		{		
		
			cC=cF.getCM().get(s[0]);
			if (cC!=null)
			{
					command=cC.create();
					
					if (command!=null)
					{//add this command to my controller queue
						
						command.setStr(cmd);
						//command.setLev(MM.getCurrentLevel());
						command.setmM(this.MM);
						this.controller.getmQ().add(command);
						return true;
						
					}
			
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
				this.getMWC().getSd().setDone(this.MM.getCurrentLevel().checkIfFinish());

				if(ifHappend)
				{
					if(this.ms.getUserCommand().compareTo("display".toLowerCase())==0)
					{
						System.out.println(this.ms.getUserCommand());
						this.ms.getCh().setMsgToUser(""+MWC.getArrByString());

					}
					else
						this.ms.getCh().setMsgToUser("succsses");
				}
				else
				{
					this.ms.getCh().setMsgToUser("fail");

				}
			}
			if (arg0==MM)
			{
				this.isFinish=MM.getCurrentLevel().checkIfFinish();
				MWC.getSd().setDone(isFinish);
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
		this.controller.getLastCommitedCommand().setmM(MM);
		this.cF=new CommandsFactory();
		MM.addObserver(this);
		this.ms=new MyServer();
		
		ms.getCh().addObserver(this);
		this.controller.start();
	}

	public SokobanController(MainWindowController  mwc, MyModel mM) {
		super();
		
		MWC=  mwc;
		MM =  mM;
		this.controller=new Controller();
		this.cF=new CommandsFactory();
	}
	

	
	

}

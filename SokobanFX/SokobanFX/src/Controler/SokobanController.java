package Controler;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import Controller.Server.MyServer;
import Model.ModelInterface;
import Model.MyModel;
import Model.Data.MyTextLevelLoader;
import view.MainWindowController;
import view.MyView;
import view.ViewInterface;



public class SokobanController implements Observer {
	private ViewInterface mv;
	private ModelInterface MM;
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
		this.mv.closeAllThreads();
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
					this.getMv().setDone(this.MM.getCurrentLevel().checkIfFinish());
	
					if(ifHappend)
					{
						if(this.ms.getUserCommand().compareTo("display".toLowerCase())==0)
						{
							this.ms.getCh().setMsgToUser(""+mv.getArrByString());
	
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
				mv.setDone(isFinish);
				mv.setArr((MM.getCurrentLevel().toCharArray()));
				mv.setSteps(MM.getCurrentLevel().getStepsCounter());
				ms.getCh().setIfHappend(true);
			}
			else if (arg0==mv)
			{			
				this.runUserCommand(mv.getUserCommand());
				
				
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

	public void setMM(ModelInterface mM) {
		MM = mM;
		this.controller.getLastCommitedCommand().setmM(mM);
	}

	
	public ViewInterface getMv() {
		return mv;
	}
	public void setMv(ViewInterface mV) {
		mv = mV;
	}
	public ModelInterface getMM() {
		return MM;
	}
	public SokobanController() {
		// TODO Auto-generated constructor stub

	
		
	
		this.controller=new Controller();
		
		this.cF=new CommandsFactory();
		
		this.ms=new MyServer();
		
		ms.getCh().addObserver(this);
		this.controller.start();
	}

	public SokobanController(ViewInterface  mV, ModelInterface mM) {
		super();
		
		mv=  mV;
		MM =  mM;
		this.controller=new Controller();
		this.cF=new CommandsFactory();
	}
	

	
	

}

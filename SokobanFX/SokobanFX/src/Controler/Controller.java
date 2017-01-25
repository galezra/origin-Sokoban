package Controler;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Controller implements ControlerInterface {
	private ArrayBlockingQueue<FunctionalCommand> mQ;
	private FunctionalCommand lastCommitedCommand;
	
	private boolean isStopped;
	public void insertNewCommand(FunctionalCommand cmd)
	{
		mQ.add(cmd);
	}
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
		mQ=new ArrayBlockingQueue<FunctionalCommand>(10);
		this.isStopped=false;
		this.lastCommitedCommand=new FunctionalCommand();
		
	}


	public Command getCmd() {
		return mQ.poll();
	}
	public Controller(ArrayBlockingQueue<FunctionalCommand> mQ) {
		super();
		this.mQ = mQ;
		this.isStopped=false;

	}
	public ArrayBlockingQueue<FunctionalCommand> getmQ() {
		return mQ;
	}
	public void setmQ(ArrayBlockingQueue<FunctionalCommand> mQ) {
		this.mQ = mQ;
	}
	public void finishAllCommands()
	{
		while(!this.mQ.isEmpty())
		{
			FunctionalCommand command;
			try {
				command = mQ.poll(1, TimeUnit.SECONDS);
				if(command!=null)
				{
					try {
						if(lastCommitedCommand!=null)
							command.updateCommand(lastCommitedCommand);
						command.execute();
						lastCommitedCommand.updateCommand(command);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
	}
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
			Thread t=new Thread (new Runnable()

			{

				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(!isStopped)
					{
						try {
							FunctionalCommand command=mQ.poll(1, TimeUnit.SECONDS);
							if(command!=null)
							{
								try {
									if(lastCommitedCommand!=null)
										command.updateCommand(lastCommitedCommand);
									command.execute();
									lastCommitedCommand.updateCommand(command);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
				}
			});
				
				
			t.start();	
			
			
	
		
	}
	
	public FunctionalCommand getLastCommitedCommand() {
		return lastCommitedCommand;
	}
	public void setLastCommitedcommand(FunctionalCommand lastCommitedcommand) {
		this.lastCommitedCommand = lastCommitedcommand;
	}
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("controller stop");
		
		this.isStopped=true;
		this.finishAllCommands();
	}
	
	

}

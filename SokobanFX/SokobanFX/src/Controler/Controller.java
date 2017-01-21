package Controler;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Controller implements ControlerInterface {
	private ArrayBlockingQueue<FunctionalCommand> mQ;
	private FunctionalCommand cmd;
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
							Command cmd=mQ.poll(1, TimeUnit.SECONDS);
							if(cmd!=null)
							{
								try {
									cmd.execute();
								
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
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		this.isStopped=true;
		
	}
	
	

}

package Controller.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import javafx.application.Platform;
import view.ViewInterface;


public class MyServer   {
	private MyClientHandler ch;
	private int port=6675;
	private boolean stop=false;


	public void closeAllSockets()
	{
		//this.ch.setMsgToUser("bye");
		//System.out.println("send bye to client");
		this.ch.setStop(true);
		this.stop=true;
	}
	public void runServer() throws IOException
	{
		ServerSocket server=new ServerSocket(port);
		server.setSoTimeout(1000);
		while(!stop)
		{
			try{
				Socket aClient=server.accept();
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {

							ch.handleClient(aClient.getInputStream(),aClient.getOutputStream());
							aClient.close();
							ch.setStop(false);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}).start();

			}
			catch (SocketTimeoutException e)
			{

			}

		}
		System.out.println("closing server");
		server.close();
	}

	public MyClientHandler getCh() {
		return ch;
	}

	public MyServer() {
		super();
		// TODO Auto-generated constructor stub
		ch=new MyClientHandler();
	}

	public void setCh(MyClientHandler ch) {
		this.ch = ch;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}


	public String getUserCommand() {
		// TODO Auto-generated method stub
		return this.ch.getCmd();
	}



}

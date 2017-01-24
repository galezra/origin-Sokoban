package Controller.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import javafx.application.Platform;
import view.ViewInterface;


public class MyServer  implements ViewInterface {
	private MyClientHandler ch;
	private int port=8871;
	private boolean stop=false;


	public void runServer() throws IOException
	{
		ServerSocket server=new ServerSocket(port);
		server.setSoTimeout(20000);
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
							
							aClient.getInputStream().close();
							aClient.getOutputStream().close();
							aClient.close();
							stop=true;
							//ch.setCmd("exit");
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

	@Override
	public String getUserCommand() {
		// TODO Auto-generated method stub
		return this.ch.getCmd();
	}



}

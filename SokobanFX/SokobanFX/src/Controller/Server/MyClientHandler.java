package Controller.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Observable;

import javafx.fxml.FXMLLoader;

public class MyClientHandler extends Observable implements ClientHandler {

	private boolean ifHappend;
	private String cmd;
	private String msgToUser;
	private boolean stop;
	@Override

	public void handleClient(InputStream in, OutputStream out) {
		// TODO Auto-generated method stub
		String s=new String();
		PrintStream bw=new PrintStream(out);

		System.out.println("start talking");
		while(!stop)
		{
			try {
				s=new BufferedReader(new InputStreamReader(in)).readLine();
				if (s.compareTo("exit".toLowerCase())==0)
				{
					
					stop=true;
				}
				else
				{
					this.setCmd(s);
					bw.println(msgToUser);

				}
				

				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		bw.println("bye");
		System.out.println("finish talking and waiting to another client");
		
	}
	
	public String getMsgToUser() {
		return msgToUser;
	}

	public void setMsgToUser(String msgToUser) {
		this.msgToUser = msgToUser;
	}

	public void saveLevelBeforExit(InputStream in,OutputStream out)
	{
		String s;
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(out));
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		try {
			bw.write("befor exit the game,do you want to save the level?");
			
			s=br.readLine();
			if (s.compareTo("yes".toLowerCase())==0)
			{
				bw.write("enter file name with extnesion /.txt/.xml/.obj/" );
				s=br.readLine();
				this.setCmd("save "+s);

			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean isIfHappend() {
		return ifHappend;
	}
	public void setIfHappend(boolean ifHappend) {
		this.ifHappend = ifHappend;
	}
	public MyClientHandler() {
		super();
		// TODO Auto-generated constructor stub
		this.stop=false;
		this.ifHappend=false;
	}
	public boolean isStop() {
		return stop;
	}
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
		this.setChanged();
		this.notifyObservers(cmd);
		
	}





}

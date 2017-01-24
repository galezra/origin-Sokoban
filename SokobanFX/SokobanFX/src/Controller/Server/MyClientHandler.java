package Controller.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Observable;

import javafx.fxml.FXMLLoader;

public class MyClientHandler extends Observable implements ClientHandler {

	private boolean ifHappend;
	private String cmd;
	private boolean stop;
	@Override
	public void handleClient(InputStream in, OutputStream out) {
		// TODO Auto-generated method stub
		String s=new String();
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(out));

		System.out.println("start talking");
		while(!stop)
		{
			try {
				s=new BufferedReader(new InputStreamReader(in)).readLine();
				System.out.println(s);
				if(s=="exit")
				{
					this.stop=true;
					bw.write("bye...");

				}
				
				else
				{
					this.setCmd(s);
					if (this.ifHappend)
					{
						bw.write("success...");
					}
					else
					{
						bw.write("fail...");
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
		this.notifyObservers();
		
	}





}

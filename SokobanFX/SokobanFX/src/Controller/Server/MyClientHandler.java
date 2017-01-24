package Controller.Server;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Observable;

import javafx.fxml.FXMLLoader;

public class MyClientHandler extends Observable implements ClientHandler {

	private String cmd;
	
	@Override
	public void handleClient(InputStream in, OutputStream outputStream) {
		// TODO Auto-generated method stub
		
		
		
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}





}

package Controller.Server;

import java.io.InputStream;
import java.io.OutputStream;

import java.util.Observable;




public interface ClientHandler  {
	public void handleClient(InputStream in,OutputStream outputStream);
	

}

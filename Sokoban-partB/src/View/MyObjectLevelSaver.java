package View;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import Model.Level;
public class MyObjectLevelSaver implements LevelSaver,Serializable {
	@Override
	public void SaveLevel(OutputStream out, Level lev) throws IOException {
		// TODO Auto-generated method stub
		
		
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(lev);
		
		
		
		
	}
}
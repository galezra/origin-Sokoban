package Model;


import java.io.IOException;
import java.io.InputStream;


public interface LevelLoader {
	Level loadLevel(InputStream in) throws IOException, ClassNotFoundException;
	
	
	
			
		


}

package View;


import java.io.IOException;
import java.io.InputStream;

import Model.Level;


public interface LevelLoader {
	Level loadLevel(InputStream in) throws IOException, ClassNotFoundException;
	
	
	
			
		


}

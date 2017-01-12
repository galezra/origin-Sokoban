package View;

import java.io.IOException;
import java.io.OutputStream;

import Model.Level;

public interface LevelSaver {
	public void SaveLevel(OutputStream out,Level lev) throws IOException;

}

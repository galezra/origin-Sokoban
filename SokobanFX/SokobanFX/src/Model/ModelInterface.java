package Model;

import Model.Data.Level;
/** our sokoban model interface
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *
 */
public interface ModelInterface {
	/**
	 * 
	 * @return the current level
	 */
	public Level getCurrentLevel();
	/**Sets the current level
	 * 
	 * @param lev out soiurce level
	 */
	public void setCurrentLevel(Level lev);

}

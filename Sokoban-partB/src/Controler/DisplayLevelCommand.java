package Controler;

import Model.Level;

public class DisplayLevelCommand extends FunctionalCommand{

	DisplayLevelCommand(Level lev) {
		super(lev);
		// TODO Auto-generated constructor stub
	}
	public void execute() {
		// TODO Auto-generated method stub
		this.getLev().print();

	}

}

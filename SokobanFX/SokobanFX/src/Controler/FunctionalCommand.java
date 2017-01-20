package Controler;

import java.io.FileNotFoundException;
import java.io.IOException;

import Model.MyModel;
import Model.Data.Level;
import view.MyView;

public class FunctionalCommand implements Command {
	private MyModel mM;
	private MyView mV;
	private String str;

	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public Level getLev() {
		return mM.getCurrentLevel();
	}

	public void setLev(Level lev) {
		mM.setCurrentLevel(lev);
		mV.setLev(lev);
	}

	public FunctionalCommand(Level lev) {
		super();
		// TODO Auto-generated constructor stub
		this.setLev(lev);
		this.mM=new MyModel();
		this.mV=new MyView();
	}

	public FunctionalCommand(MyModel mM, MyView mV, String str) {
		super();
		this.mM = mM;
		this.mV = mV;
		this.str = str;
	}
	@Override
	public void execute() throws FileNotFoundException, IOException, ClassNotFoundException, Exception {
		// TODO Auto-generated method stub

	}

}

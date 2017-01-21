package view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import Controler.SokobanController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class MainWindowController extends Observable  implements Initializable,ViewInterface{
	@FXML	private SokobanDisplayer sd=new SokobanDisplayer();
	private String userCommand;
	private char[][]arr;
	private SokobanController SC;
		

	public void exit()
	{
		this.setUserCommand("exit game");//without saving the game
	}
	public void saveFileMethod()
	{
		try {
			FileWriter fw=new FileWriter(new File("./resources"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void loadFileMethod()
	{
		 
	 FileChooser fc = new FileChooser();
	 fc.setTitle("open sokoban level file:");
	 fc.setInitialDirectory(new File("./resources/Levels"));
	 File chosen  = fc.showOpenDialog(null);
	 if (chosen!=null)
	 {
		String fileName= chosen.getName();
		this.setUserCommand("load "+fileName);
	
		
	 }
			    
	}
	public SokobanDisplayer getSd() {
		return sd;
	}

	public void setSd(SokobanDisplayer sd) {
		this.sd = sd;
	}

	public char[][] getArr() {
		if (arr!=null)
			return arr;
		return null;
	}

	public void setArr(char[][] arr) {
		this.arr = arr;
		this.sd.setLevelData(arr);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		SC=new SokobanController();
		
		this.arr=SC.getMM().getCurrentLevel().toCharArray();
		this.addObserver(SC);
		sd.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->sd.requestFocus());
		
		sd.setOnKeyPressed(new EventHandler<KeyEvent>() {
		
			@Override
			public void handle(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
				if (arg0.getCode()==KeyCode.UP)
				{
					
					setUserCommand("move up");
				}
				if (arg0.getCode()==KeyCode.DOWN)
					setUserCommand("move down");
				if (arg0.getCode()==KeyCode.LEFT)
					setUserCommand("move left");
				if (arg0.getCode()==KeyCode.RIGHT)
					setUserCommand("move right");
				

				
				
			}
		});
		sd.setLevelData(arr);
		this.setChanged();
		this.notifyObservers();
		SC.setMWC(this);
		
		
		
		
	}

	public String getUserCommand() {
		return userCommand;
	}

	public void setUserCommand(String userCommand) {
		this.userCommand = userCommand;
		System.out.println(userCommand);
		this.setChanged();
		this.notifyObservers(userCommand);
	}


	
	
	

}

package view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import Controler.SokobanController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainWindowController extends Observable  implements Initializable,ViewInterface{
	@FXML	private SokobanDisplayer sd=new SokobanDisplayer();
	private String userCommand;
	private char[][]arr;
	int count;
	int steps;
	@FXML
	Text myText;
	@FXML
	Text mySteps;
	StringProperty Counter;
	StringProperty stepCounter;
	//private SokobanController SC;



	public MainWindowController(){
	
	
	}


	public void startCounter()
	{
	
		
		Timer t=new  Timer();
		t.scheduleAtFixedRate(new TimerTask() {
					
					@Override
					public void run() {
						Counter.set(""+(++count));
					}
				}, 0, 1000);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Counter=new SimpleStringProperty();
		stepCounter=new SimpleStringProperty();
		steps=0;
		count=0;
		myText.textProperty().bind(Counter);
		mySteps.textProperty().bind(stepCounter);
		
		sd.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->sd.requestFocus());

		sd.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				// TODO Auto-generated method stub

				if(arr!=null)
				{
					if (arg0.getCode()==KeyCode.UP)
					{
						stepCounter.set(""+(++steps));
						setUserCommand("move up");
						
					}
					if (arg0.getCode()==KeyCode.DOWN)
					{
						stepCounter.set(""+(++steps));
						setUserCommand("move down");
					}
					if (arg0.getCode()==KeyCode.LEFT)
					{
						stepCounter.set(""+(++steps));
						setUserCommand("move left");
					}
					if (arg0.getCode()==KeyCode.RIGHT)
					{
						stepCounter.set(""+(++steps));
						setUserCommand("move right");
					}
				}




			}
		});
		sd.setLevelData(arr);
		
		this.setChanged();
		this.notifyObservers();
		//SC.setMWC(this);




	}

	public String getUserCommand() {
		return userCommand;
	}

	public void setUserCommand(String userCommand) {
		this.userCommand = userCommand;
		this.setChanged();
		this.notifyObservers(userCommand);
	}
	public void exit()
	{
		this.setUserCommand("exit game");//without saving the game
	}
	public void saveFileMethod()
	{
		if (arr!=null)
		{
			FileChooser fc=new FileChooser();
			fc.setInitialDirectory(new File("./resources/Levels"));
			fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text doc(*.txt)", "*.txt"));
			fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML doc(*.xml)", "*.xml"));
			fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Object file(*.obj)", "*.obj"));
	
			File chosen=fc.showSaveDialog(null);
			if (chosen!=null)
			{
				sd.requestFocus();
				String fileName=chosen.getName();
				this.setUserCommand("save "+fileName);
			}
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
		 this.startCounter();


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
	






}


package view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import org.omg.CORBA.portable.OutputStream;

import Controler.SokobanController;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	HashMap<String,String> hm;
	@FXML
	Text myText;
	
	


	@FXML
	Text mySteps;
	StringProperty Counter;
	StringProperty stepCounter;
	



	public void setDone(boolean b)
	{
		this.sd.setDone(b);
	}
	public String getArrByString()
	{
		if (arr!=null)
		{
			String s="";
			for (int i=0;i<arr.length;i++)
			{
				for(int j=0;j<arr[0].length;j++)
					s+=(arr[i][j]);
				s+="\n";
			}
			return s;
		}
		return "level is empty";
	}
	public void closeAllThreads()
	{
		
	}
	public void initializeDefaultKeys()
	{
		hm.put("UP", "move up");
		hm.put("DOWN", "move down");
		hm.put("LEFT", "move left");
		hm.put("RIGHT", "move right");

	}
	public void initializeLettersKeys()
	{
		hm.put("W", "move up");
		hm.put("S", "move down");
		hm.put("A", "move left");
		hm.put("D", "move right");
	}
	public void initializeNumbersKeys()
	{
		hm.put("8", "move up");
		hm.put("2", "move down");
		hm.put("4", "move left");
		hm.put("6", "move right");
	}
	public void readKeysFromXML()
	{
		XMLDecoder xd;
		try {
			xd = new XMLDecoder(new FileInputStream(new File("./resources/keys.xml")));
			hm.put((String) xd.readObject(), "move up");
			hm.put( (String) xd.readObject(), "move down");
			hm.put( (String) xd.readObject(), "move right");
			hm.put( (String) xd.readObject(), "move left");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void writeDefaultKeysToXML()
	{
		try {
			XMLEncoder xe=new XMLEncoder(new FileOutputStream(new File("./resources/Defaultkeys.xml")));
			xe.writeObject("UP");
			xe.writeObject("DOWN");
			xe.writeObject("RIGHT");
			xe.writeObject("LEFT");
			xe.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public MainWindowController(){
	
	
	}
	public int getSteps() {
		return steps;
	}


	public void setFocus()
	{
		this.sd.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean s) {
				// TODO Auto-generated method stub
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						sd.requestFocus();
						
					}
				});
				
			}
		});
	}
	public void setSteps(int steps) {
		this.steps = steps;
		stepCounter.set(""+steps);

	}

	public void startCounter()
	{
	
		setCount(0);
		Timer t=new  Timer();
		t.scheduleAtFixedRate(new TimerTask() {
					
					@Override
					public void run() {
						setCount(getCount()+1);
					}
				}, 0, 1000);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	

		hm=new HashMap<String,String>();
		this.readKeysFromXML();
		
		Counter=new SimpleStringProperty();
		stepCounter=new SimpleStringProperty();
		
		
		this.setSteps(0);
		this.setCount(0);
		myText.textProperty().bind(Counter);
		mySteps.textProperty().bind(stepCounter);
		
		sd.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->sd.requestFocus());
		//this.setFocus();
		sd.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				// TODO Auto-generated method stub
		

				if(arr!=null)
				{
				
					setUserCommand(hm.get(""+arg0.getCode()));
				}




			}
		});
		sd.setLevelData(arr);
		
		this.setChanged();
		this.notifyObservers();
		//SC.setMWC(this);




	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
		this.Counter.set(""+count);
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
		this.setUserCommand("exit");//without saving the game
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
		 this.steps=0;


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


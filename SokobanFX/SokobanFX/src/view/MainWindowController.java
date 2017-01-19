package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainWindowController implements Initializable{

	
	char[][]arr={
			{'A','o','#',' ','@'},
			{'A','o','#',' ','@'},
			{'A','o','#',' ','@'},
			{'A','o','#',' ','@'},
			{'A','o','#',' ','@'}};
	@FXML
	Maze2DDisplayer mazeData;
	public MainWindowController() {
		super();
		// TODO Auto-generated constructor stub
		this.mazeData=new Maze2DDisplayer();
		this.mazeData.setSokobanArr(arr);
		
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.mazeData=new Maze2DDisplayer();
		this.mazeData.setSokobanArr(arr);
	}
	
	

}

package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainWindowController implements Initializable{
	@FXML	private SokobanDisplayer sd=new SokobanDisplayer();
	private char[][]arr={
			{'@','#',' ','A','o'},
			{'@','#',' ','A','o'},
			{'@','#',' ','A','o'},
			{'@','#',' ','A','o'},
			{'@','#',' ','A','o'}};

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		sd.setLevelData(arr);
		
		
	}

	
	
	

}

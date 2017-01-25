package view;


import java.io.File;

import Controler.SokobanController;
import Controller.Server.MyServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fl=new FXMLLoader();
			BorderPane root = fl.load(getClass().getResource("MainWindow.fxml").openStream());
			MainWindowController mwc=fl.getController();
			SokobanController sc=new SokobanController();
			mwc.addObserver(sc);
			sc.setMWC(mwc);
			//sc.runServer();
			Scene scene = new Scene(root,650,650);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			Thread serverThread=new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					sc.runServer();
				}
			});
			Thread musicThread=new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							String musicFile="./resources/Music/song.mp3";

							Media song=new Media(new File(musicFile).toURI().toString());
							MediaPlayer mp=new MediaPlayer(song);
							mp.play();
							
						}
					});
		
			serverThread.start();
			musicThread.start();
			
			
			//start play the song
			primaryStage.show();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {



		launch(args);

	}
}

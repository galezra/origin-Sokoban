package view;


import Controler.SokobanController;
import Controller.Server.MyServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
			
			Thread t1=new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					sc.runServer();
				}
			});
		
			//t1.start();
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {



		launch(args);

	}
}

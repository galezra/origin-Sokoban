package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class MazeDisplayer extends Canvas {
	protected HashMap<String,Image> hM;

	public MazeDisplayer() {
		super();
		// TODO Auto-generated constructor stub
		try {
			hM.put("A", new Image(new FileInputStream(new File("./resources/character.jpg"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			hM.put("o", new Image(new FileInputStream(new File("./resources/destination.jpg"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			hM.put("@", new Image(new FileInputStream(new File("./resources/box.jpg"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			hM.put(" ", new Image(new FileInputStream(new File("./resources/floor.jpg"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			hM.put("#", new Image(new FileInputStream(new File("./resources/wall.jpg"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MazeDisplayer(double width, double height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}
	
	

}

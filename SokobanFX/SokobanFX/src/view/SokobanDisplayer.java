package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class SokobanDisplayer extends Canvas  {
	private char[][]levelData;
	private boolean isDone=false;
	private double cRow;
	private double cCol;
	private StringProperty wall;
	private StringProperty box;
	private StringProperty character;
	private StringProperty floor;
	private StringProperty destination;
	private StringProperty open;
	
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	public SokobanDisplayer() {
		super();
		// TODO Auto-generated constructor stub
		this.box=new SimpleStringProperty();
		this.wall=new SimpleStringProperty();
		this.character=new SimpleStringProperty();
		this.destination=new SimpleStringProperty();
		this.floor=new SimpleStringProperty();
		this.open=new SimpleStringProperty();

	}
	public SokobanDisplayer(double width, double height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}
	public void redraw()
	{
		
		HashMap<Character,Image> HM=new HashMap<Character,Image>();
		try {
			HM.put('A', new Image(new FileInputStream(this.getCharacter())));
			HM.put(' ', new Image(new FileInputStream(this.getFloor())));
			HM.put('@',  new Image(new FileInputStream(this.getBox())));
			HM.put('#', new Image(new FileInputStream(this.getWall())));
			HM.put('o', new Image(new FileInputStream(this.getDestination())));
			HM.put('X', new Image(new FileInputStream(this.getOpen())));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		double W=getWidth();
		double H=getHeight();
		double w=W/cCol;
		double h=H/cRow;
		GraphicsContext gc=this.getGraphicsContext2D();
		
		Image A=null;
		if (this.levelData!=null)
		{
			gc.clearRect(0, 0, W, H);

			if (this.isDone)
			{
				
			}
			
			else
			{
			for (int i=0;i<cRow;i++)
				for(int j=0;j<cCol;j++)
				{
					
					A=HM.get(this.levelData[i][j]);
					gc.drawImage(A, j*w, i*h, w, h);
					
				}
			}
		}
		else
		{
			A=HM.get('X');
			gc.drawImage(A, 0, 0, W, H);
		}
	}
	public char[][] getLevelData() {
		return levelData;
	}
	public void setLevelData(char[][] levelData) {
		if (levelData!=null)
		{
			cCol=levelData[0].length;
			cRow=levelData.length;
			this.levelData = levelData;
			
		}
		
		this.redraw();
	}
	public double getcRow() {
		return cRow;
	}
	public void setcRow(double cRow) {
		this.cRow = cRow;
	}
	public double getcCol() {
		return cCol;
	}
	public void setcCol(double cCol) {
		this.cCol = cCol;
	}
	
	public String getOpen() {
		return open.get();
	}
	public void setOpen(String open) {
		this.open.set(open);
	}
	public String getWall() {
		return wall.get();
	}
	public void setWall(String wall) {
		this.wall.set( wall);
	}
	public String getBox() {
		return box.get();
	}
	public void setBox(String box) {
		this.box.set(box);
	}
	public String getCharacter() {
		return character.get();
	}
	public void setCharacter(String character) {
		this.character.set(character);
	}
	public String getFloor() {
		return floor.get();
	}
	public void setFloor(String floor) {
		this.floor.set(floor);
	}
	public String getDestination() {
		return destination.get();
	}
	public void setDestination(String destination) {
		this.destination.set(destination);
	}

	
}

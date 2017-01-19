package view;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Maze2DDisplayer extends Canvas  {
	char[][] SokobanArr;
	HashMap <Character,Image>HM;

	public void initializeHM() throws FileNotFoundException
	{
		HM.put('A', new Image(new FileInputStream(new File("./resources/character.jpg"))));
		HM.put('@', new Image(new FileInputStream(new File("./resources/box.jpg"))));
		HM.put('#', new Image(new FileInputStream(new File("./resources/wall.jpg"))));
		HM.put(' ', new Image(new FileInputStream(new File("./resources/floor.jpg"))));
		HM.put('o', new Image(new FileInputStream(new File("./resources/destination.jpg"))));

	}
	public void setSokobanArr(char[][] otherArr)
	{
		this.SokobanArr=otherArr;
		this.reDrew();
	}
	public Maze2DDisplayer() {
		super();
		// TODO Auto-generated constructor stub
		
		this.HM=new HashMap<Character,Image>();
		try {
			this.initializeHM();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Maze2DDisplayer(double width, double height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		this.SokobanArr=null;
		try {
			this.HM=new HashMap<Character,Image>();

			this.initializeHM();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void reDrew()
	{
		char[][]arr={
				{'A','o','#',' ','@'},
				{'A','o','#',' ','@'},
				{'A','o','#',' ','@'},
				{'A','o','#',' ','@'},
				{'A','o','#',' ','@'}};
		Image ItemImage=null;
		//if (this.SokobanArr!=null)
		{
			this.setWidth(300);
			this.setHeight(300);
			this.SokobanArr=arr;
			double W=this.getWidth();
			double H=this.getHeight();
			double w=W/SokobanArr[0].length;	
			double h=H/SokobanArr.length;
			GraphicsContext GC=getGraphicsContext2D();
			
			for (int i=0;i<SokobanArr.length;i++)
				for (int j=0;j<SokobanArr[0].length;j++)
				{
					ItemImage =this.HM.get(this.SokobanArr[i][j]);
					if (ItemImage!=null)
					{
						GC.drawImage(ItemImage, j*w, i*h, w, h);
					}
				}
			
		}
	}
}

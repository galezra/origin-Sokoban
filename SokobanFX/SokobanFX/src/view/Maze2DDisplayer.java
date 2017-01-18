package view;
 
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Maze2DDisplayer extends MazeDisplayer  {
	String[][] SokobanArr;
	

	public void setSokobanArr(String[][] otherArr)
	{
		this.SokobanArr=otherArr;
		this.reDrew();
	}
	public Maze2DDisplayer() {
		super();
		// TODO Auto-generated constructor stub
		this.SokobanArr=new String[10][10];
	}
	public Maze2DDisplayer(double width, double height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}
	public void reDrew()
	{
		Image ItemImage=null;
		if (this.SokobanArr!=null)
		{
			
			double W=this.getWidth();
			double H=this.getHeight();
			double w=W/SokobanArr[0].length;	
			double h=H/SokobanArr.length;
			GraphicsContext GC=getGraphicsContext2D();
			
			for (int i=0;i<SokobanArr.length;i++)
				for (int j=0;j<SokobanArr[0].length;j++)
				{
					ItemImage =this.hM.get(this.SokobanArr[i][j]);
					if (ItemImage!=null)
					{
						GC.drawImage(ItemImage, j*w, i*h, w, h);
					}
				}
			
		}
	}
}

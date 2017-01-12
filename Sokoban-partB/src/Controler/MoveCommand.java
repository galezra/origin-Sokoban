package Controler;

import Model.Box;
import Model.Item;
import Model.Level;
import Model.MySokobanPolicy;
import Model.Position;
import Model.Wall;

public class MoveCommand extends FunctionalCommand implements Command {

	public MoveCommand(Level lev) {
		super(lev);
		// TODO Auto-generated constructor stub
	}
	public void execute() throws Exception
	{
		MySokobanPolicy MSP=new MySokobanPolicy();
		Item A=null;
		Item B=null;
		Item C=null;
		Position posB=null,posC=null;
		Position temp;
		int howManyMoves=0;
		String []s=this.getStr().split(" ");
		A=this.getLev().getCharacter();
		this.getLev().getCharacterList().remove(A);
		switch (s[1].toLowerCase())
		{
			case "up":
			{
				posB=A.getPos().getForward();
				posC=posB.getForward();		
				
				break;
			}
			case "down":
				posB=A.getPos().getBackward();
				posC=posB.getBackward();	
				
				break;
			case "right":
				posB=A.getPos().getRight();
				posC=posB.getRight();	
				break;
			case "left":
				posB=A.getPos().getLeft();
				posC=posB.getLeft();	
				break;
			default:
				throw new Exception("Undefined direction");
				
				
		}
		if (posB==null)
		{
			B=null;
		}
		if (!this.getLev().checkPosition(posB))
		{
			B=null;
			C=null;
		
		}
		else
			B=this.getLev().getItemByPosition(posB);
		 if (!this.getLev().checkPosition(posC))
				C=null;
		 else
			 C=this.getLev().getItemByPosition(posC);
		
			howManyMoves=this.checkIfPossible(A,B,C);
			if (howManyMoves==1)
				this.getLev().setInPlace(A, B.getPos());
				
			if (howManyMoves==2)
			{
				temp=B.getPos();
				this.getLev().setInPlace(B, C.getPos());
				this.getLev().setInPlace(A, temp);

			}
			if (howManyMoves==0)
			{
				
				System.out.println("you can't move to this direction");
			}
			
		
		
			
		
		this.getLev().getCharacterList().add(A);
		this.getLev().print();
		
	
	
	}
	
	public int checkIfPossible(Item A,Item B,Item C)
	{
		if (B==null)
			return 0;
		if (C==null)
		{
			if ((B instanceof Box)||
					(B instanceof Wall))
				return 0;
			else
				return 1;
		}
		if (B instanceof Box)
		{
			if (C instanceof Box)
				return 0;
			if (C instanceof Wall )
			{
				if (new MySokobanPolicy().goThrowWalls())
					return 1;
				else
					return 0;
			}
			return 2;
		}
		if (B instanceof Wall)
		{
			if (new MySokobanPolicy().goThrowWalls())
				return 1;
			else
				return 0;
		}
		return 1;
		
	}
}

	



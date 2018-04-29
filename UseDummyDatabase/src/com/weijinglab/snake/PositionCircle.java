package com.weijinglab.snake;
public class PositionCircle implements Direction
{
	public int x;
	public int y;
	public PositionCircle()
	{
		x=0;
		y=0;
	}
	public PositionCircle(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	public PositionCircle copy()
	{
		return (new PositionCircle(x,y));
	}
	public boolean equals(PositionCircle a)
	{
		return (a.x==x && a.y==y);
	}
	public void move(int direction)
	{
		switch(direction)
		{
			case left:x--;break;
			case right:x++;break;
			case up:y--;break;
			case down:y++;break;
		}
	}
}
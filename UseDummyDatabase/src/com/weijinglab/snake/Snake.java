package com.weijinglab.snake;
class Snake implements Direction
{
	public PositionCircle snake[];
	public int count;
	public Snake()
	{
		snake=new PositionCircle[100];
		count=0;
	}
	public void add(PositionCircle d)
	{
		snake[count++]=d;
	}
	public PositionCircle move(int direction)
	{
		if(direction==0)
			return null;
		PositionCircle pos=snake[0].copy();
		for(int i=1;i<count-1;i++)
		{
			snake[i-1]=snake[i];
		}
		snake[count-2]=snake[count-1].copy();
		snake[count-1].move(direction);
		return pos;
	}
	public void printSnake()
	{
		for(int i=0;i<count;i++)
		{
			System.out.println(snake[i].x+"   "+snake[i].y);
		}
		System.out.println();
	}
//	public static void main(String args[])
//	{
//		Snake a=new Snake();
//		for(int i=0;i<5;i++)
//		{
//			a.add(new PositionCircle(i,5));
//		}
//		a.move(right);
//		a.printSnake();
//	}
}
package com.weijinglab.snake;

import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Painter extends JPanel
{
	private int x,y,addx,addy;
	private Image snakeNode;
	private Snake s;
	private int state;
	private PositionCircle egg;
	private boolean hasEgg;
	private int score;
	public Painter()
	{
		score=0;
		hasEgg=false;
		state=0;
		s=new Snake();
		for(int i=0;i<3;i++)
			s.add(new PositionCircle(i,5));
		this.addKeyListener(new DirectionListener());
		this.setFocusable(true);
		x=0;y=0;addx=0;addy=0;
		setBackground(Color.WHITE);
		try
		{
			snakeNode=ImageIO.read(new File("SnakeNode.JPG"));
		}catch(IOException e){}
	}
	public void paintSnake(Graphics2D g)
	{
		for(int i=0;i<s.count;i++)
		{
			g.drawImage(snakeNode,s.snake[i].x*20,20*s.snake[i].y,null);		
		}
	}
	private boolean anotherCheck(PositionCircle c)
	{
		for(int i=0;i<s.count;i++)
		{
			if(s.snake[i].equals(c))
				return true;
		}
		return false;
	}
	public void drawEgg(Graphics2D g)
	{
		if(!hasEgg)
			do{
				int x=(int)(19*Math.random());
				int y=(int)(19*Math.random());
				egg=new PositionCircle(x,y);
			}while(anotherCheck(egg)||x>19||y>19);//|| x==0 || y==0 ||x>19 ||y>19);
		hasEgg=true;
		g.drawImage(snakeNode,egg.x*20,egg.y*20,null);
	}
	public void checkDead()
	{
		for(int i=0;i<s.count;i++)
		{
			
			if((s.snake[i].equals(s.snake[s.count-1]) && i!=(s.count-1) && i!=s.count-2)||s.snake[i].x>=20 || s.snake[s.count-1].x<=0 ||s.snake[i].y>=18 || s.snake[i].y<=0)
			{
				//System.out.println(s.snake[i].x+"  "+s.snake[i].y);
				//s.printSnake();
				JOptionPane.showMessageDialog(this,"Your score is "+score+",try again!!\nMade by Weijing Huang\nVersion 1.00","You lost", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}
	}
	public void eatEgg()
	{
		if(s.snake[s.count-1].equals(egg))
		{
			hasEgg=false;
			s.add(egg);
			score+=10;
		}
	}
	public void paintComponent(Graphics g)
	{
		checkDead();
		try{
		Thread.sleep(50);
		}catch(InterruptedException a){};
		Graphics2D g2=(Graphics2D)g;
		super.paintComponent(g);
		s.move(state);
		//s.printSnake();
		paintSnake(g2);
		drawEgg(g2);
		eatEgg();
		repaint();
	}
	class DirectionListener implements KeyListener
	{
		public void keyPressed (KeyEvent event)
		{
			if(Math.abs(event.getKeyCode()-state)!=2)
				state=event.getKeyCode();
       		repaint();
		}
      public void keyTyped (KeyEvent event) {}
      public void keyReleased (KeyEvent event) {}

	}
}

class MyFrame extends JFrame
{
	public MyFrame()
	{
		this.setLocation(300,300);
		try
		{
			this.setIconImage(ImageIO.read(new File("Snake.png")));
		}catch(IOException e){}
		setTitle("Snake Like Eating");
		setSize(430,420);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con=getContentPane();
		con.add(new Painter());
	}
}

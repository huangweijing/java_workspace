package com.weijinglab.snake;

import javax.swing.JFrame;


class Test
{
	public static void main(String args[])throws InterruptedException
	{
		Thread.sleep(300);
		JFrame.setDefaultLookAndFeelDecorated(true);
		MyFrame frame=new MyFrame();
		frame.setVisible(true);
	}
}
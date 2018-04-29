/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gizmodemo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author huangweijing
 */
public class GizmoPanel extends JPanel
        implements Runnable {
    DemoInterface demo;
    Thread t;
    public GizmoPanel(JFrame frame) {
        demo =  new FlipperDemo(this);
        demo.init();
        t = new Thread(this);
        t.start();
    }

    public void setDemo(String demoName){
        try{
        t.interrupt();
            try {
                if(demoName.equals("FlipperDemo"))
                    demo =  new FlipperDemo(this);
                else demo = (DemoInterface) Class.forName("gizmodemo."+demoName).newInstance();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GizmoPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(GizmoPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(GizmoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        demo.init();
        t.start();
        }catch(Exception ex){}
    }
    
    @Override
    public void paintComponent(Graphics g) {
        this.clearScreen(g);
        Graphics2D g2d = (Graphics2D) g;
        demo.drawDemo(g2d);
    }

    public void clearScreen(Graphics g){
        g.clearRect(0, 0, 800, 800);
        //System.out.println(WIDTH);
    }
    
    public void run() {
        while (true) {
            try {
                repaint();
                demo.nextFrame();
                //System.out.println("Hello");
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(GizmoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

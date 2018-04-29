/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gizmodemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author huangweijing
 */
public class GizmoFrame extends JFrame{
    GizmoPanel gizmoPanel;
    public GizmoFrame(){
        this.setTitle("GizmoDemo");
        this.createMenu();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gizmoPanel=new GizmoPanel(this);
        this.add(gizmoPanel);
        this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setVisible(true);
    }
    
    private JMenuItem createMenuItem(final String itemStr){
        JMenuItem item = new JMenuItem(itemStr);
        item.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                gizmoPanel.setDemo(itemStr);
            }
        });
        return item;
    }
    
    private void createMenu(){
        JMenuBar bar = new JMenuBar();
        JMenu demoMenu = new JMenu("Select Menu");
        for(int i=0;i<Constants.demoName.length;i++){
            demoMenu.add(createMenuItem(Constants.demoName[i]));
            bar.add(demoMenu);
        }
        this.setJMenuBar(bar);
    }
}

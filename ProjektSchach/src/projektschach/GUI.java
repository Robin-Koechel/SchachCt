/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektschach;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Robin
 */
public class GUI extends JFrame implements ActionListener{
    private JPanel panel;
    private JButton[][] felder = new JButton[8][8];
    
    private Spiellogik logik;
    
    public GUI(Spiellogik logik){
       initComponents();
        
        
    }

    
    public void initComponents (){
        panel = new JPanel();
        panel.setLayout(new GridLayout(8,8));
        this.add(panel);
        panel.setPreferredSize(new Dimension(800, 800));
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                felder[i][j] = new JButton();
                felder[i][j].setPreferredSize(new Dimension(100,100));
                panel.add(felder[i][j]);
                felder[i][j].setEnabled(true);
                felder[i][j].addActionListener(this);
                felder[i][j].setFont(new Font("Arial", Font.PLAIN, 30));
            }
        }
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void zeichneSpieler(){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++) {
                if(felder[i][j] == e.getSource()){
                    felder[i][j].setText("X");
                    felder[i][j].setEnabled(false);
                }
            }
        }
        
    }
    
    
}

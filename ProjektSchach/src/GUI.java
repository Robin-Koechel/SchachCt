import Figuren.Figur;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robin
 */
public class GUI extends JFrame implements ActionListener{
    private JPanel panel;
    private JButton[][] felder = new JButton[8][8];
    //Text shit
    private String textFont = "Sans";
    private int textGröße = 60;
    //Fenster shit
    private int anzahlZeilenSpalten = 8;
    private int breiteHöheFenster = 800;
    private int breiteHöheFelder = 800;
    
    //logik
    private Logik logik;
    
    //button input
    private boolean startknopfGedrückt;
    private int[] startKoordinate = null;
    private int[] zielKoordinate = null;
    
    public GUI(){
       logik = new Logik();
       initComponents();
       
       startknopfGedrückt = false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < anzahlZeilenSpalten; i++) {
                for (int j = 0; j < anzahlZeilenSpalten; j++) {
                    if (felder[i][j] == e.getSource()) {
                        if(!startknopfGedrückt){
                            startKoordinate = new int[]{j,i};
                            startknopfGedrückt = true;
                            
                        }else{
                            if(startKoordinate == new int[]{j,i}){
                                felder[i][j].setEnabled(true);
                                startKoordinate = null;
                            }else{
                                zielKoordinate = new int[]{j,i};
                            }
                        }
                    }
                }
            }
            if(startKoordinate != null && zielKoordinate != null){
                try {
                    logik.zugSetzen(startKoordinate, zielKoordinate);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex);
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                zeichneHintergrund();
                zeichneFiguren();
                
               startKoordinate = null;
               zielKoordinate = null;
               startknopfGedrückt = false;
            }
            
            //https://www.youtube.com/watch?v=U4ogK0MIzqk
            
    }
    
    //Hilfsmethoden
    private void initComponents() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(anzahlZeilenSpalten,anzahlZeilenSpalten));
        this.add(panel);
        panel.setPreferredSize(new Dimension(breiteHöheFenster, breiteHöheFenster));
        
        //init Buttons
        for (int i = 0; i < anzahlZeilenSpalten; i++) {
            for (int j = 0; j < anzahlZeilenSpalten; j++) {
                felder[i][j] = new JButton();
                felder[i][j].setPreferredSize(new Dimension(breiteHöheFelder,breiteHöheFelder));
                panel.add(felder[i][j]);
                felder[i][j].setEnabled(true);
                felder[i][j].addActionListener(this);
                felder[i][j].setFont(new Font(textFont, Font.PLAIN, textGröße));
            }
        }
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        zeichneHintergrund();
        zeichneFiguren();
    }
    private void zeichneHintergrund() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j+=2) {
                if(i%2==1){
                    felder[i][j].setBackground(Color.decode("#5B7B65"));
                }
                if(i%2==0){
                    felder[i][j+1].setBackground(Color.decode("#5B7B65"));
                }
                
            }
        }
    }
    private void zeichneFiguren() {
        ArrayList<Figur> lstFiguren = logik.getLstFiguren();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                felder[i][j].setText("");
            }
        }
        
        for (int k = 0; k < lstFiguren.size(); k++) {
            if(!lstFiguren.get(k).isBesiegt()){
                int x = lstFiguren.get(k).getPosX();
                int y = lstFiguren.get(k).getPosY();
                felder[y][x].setText(lstFiguren.get(k).getBuchstabe());
            }
        }
    }
    
    
}

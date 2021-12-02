/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektschach;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import projektschach.Figuren.Figur;

/**
 *
 * @author Robin
 */
public class GUI extends JFrame implements ActionListener{
    private JPanel panel;
    private JButton[][] felder = new JButton[8][8];
    private Spiellogik logik;
    
    public GUI(){
       this.logik = new Spiellogik();
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
                felder[i][j].setFont(new Font("Sans", Font.PLAIN, 40));
            }
        }
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        zeichneHintergrund();
        zeichneFiguren();
    }
    public void zeichneFiguren(){
        ArrayList<Figur> lstFiguren = logik.getLstFiguren();
        
        for (int k = 0; k < lstFiguren.size(); k++) {
            if(!lstFiguren.get(k).isBesiegt()){
                int x = lstFiguren.get(k).getPosition().getPosX();
                int y = lstFiguren.get(k).getPosition().getPosY();
                felder[y][x].setText(lstFiguren.get(k).getBuchstabe());
            }
        }
    }
    public void zeichneHintergrund(){
        boolean odd = true;
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
    public void spielfluss(int[] startKoordiante, int[] zielKoordiante){
        long start = System.currentTimeMillis();
        
        Figur fig = logik.getFigurAufFeld(startKoordiante);
        ArrayList<Feld> möglicheFelder = fig.getPossitionsAbleToMove(logik.getLstFiguren());
        boolean feldIstDabei = false;
                
        //ungültige Felder aussortieren
        for (int i = 0; i < möglicheFelder.size(); i++) {
            if(möglicheFelder.get(i).getPosX() < 0 || möglicheFelder.get(i).getPosX() > 7 ||
               möglicheFelder.get(i).getPosY() < 0 || möglicheFelder.get(i).getPosY() > 7){
                möglicheFelder.remove(i);
            }
        }
        
        if(fig.istWeiß()==logik.getSpielerWeiß().isAmZug()){ //prüfen ob Spieler figur aus seinem Team nutzt
            for (int i = 0; i < möglicheFelder.size(); i++) {
                if(!fig.istFigurImWeg(startKoordiante, zielKoordiante, logik.getLstFiguren())){
                    Feld feld = möglicheFelder.get(i);
                    if(feld.getPosX() == zielKoordiante[0] && feld.getPosY() == zielKoordiante[1]){
                        feldIstDabei = true;
                    }
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Du kannst nicht über andere Spielfiguren springen");
                    break;
                }
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "diese Figur gehört nicht zu deinem Team");
        }
        
        if(feldIstDabei){
            int anzahlMovesFig = logik.getFigurAufFeld(startKoordiante).getAnzahlGesetzt();
            logik.getFigurAufFeld(startKoordiante).setAnzahlGesetzt(anzahlMovesFig+=1);
            logik.setzeFigur(startKoordiante, zielKoordiante);
            
            zeichneHintergrund();
            zeichneFiguren();
            
            logik.spielerWechsel();

            if(logik.getSpielerWeiß().isAmZug()){
                //lblInfo.setText("Weiß ist am Zug"); 
            }
            if(logik.getSpielerSchwarz().isAmZug()){
                //lblInfo.setText("Schwarz ist am Zug");
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "diese Figur kann sich nicht auf diese Position bewegen");
        }
        
        long end = System.currentTimeMillis();
        float msec = end - start;
        System.out.println(msec + " millisecs");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++) {
                if(felder[i][j] == e.getSource()){
                    
                    
                }
            }
        }
        
    }
    
    
}

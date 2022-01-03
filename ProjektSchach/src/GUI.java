import Figuren.Figur;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
    
    //modi
    private boolean pvp = false;
    private boolean pve = true;
    private boolean online = false;
    
    
    public GUI(){
       logik = new Logik();
       initComponents();
       
       startknopfGedrückt = false;
    }
    class MenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {            
            System.out.println(e.getActionCommand() + " JMenuItem clicked.");
            String action = e.getActionCommand();
            if (action.equals("New Game")) {
                logik.logikReset();
                zeichneFiguren();
                zeichneHintergrund();
                
            }
            if (action.equals("Open")) {
            }
            if (action.equals("Save")) {
            }
            if (action.equals("Exit")) {
                System.exit(1);
            }
            if (action.equals("Player vs. Player")) {
                logik.logikReset();
                zeichneFiguren();
                zeichneHintergrund();
                
                pvp = true;
                pve = false;
                online = false;
            }
            if (action.equals("Player vs. AI")) {
                logik.logikReset();
                zeichneFiguren();
                zeichneHintergrund();
                
                pvp = false;
                pve = true;
                online = false;
            }
            if (action.equals("Online")) {
                logik.logikReset();
                zeichneFiguren();
                zeichneHintergrund();
                
                pvp = false;
                pve = false;
                online = true;
            }
            
            if(action.equals("Github")){
                Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        desktop.browse(new URI("https://github.com/itSchuleAccRobin/SchachCt"));
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        }    
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
        if(pvp){
            if(startKoordinate != null && zielKoordinate != null){
                try {
                    logik.zugSetzen(startKoordinate, zielKoordinate,logik.getLstFiguren());
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
        if(pve){
            boolean figurIstSchwarz = false;
            for (int i = 0; i < logik.getLstFiguren().size(); i++) {
                Figur figur = (Figur) logik.getLstFiguren().get(i);
                if(figur.getPosX() == startKoordinate[0] && figur.getPosY() == startKoordinate[1]){
                    if(figur.istWeiß()==false)figurIstSchwarz=true;break;
                }
            }
             
            if(startKoordinate != null && zielKoordinate != null){
                if(figurIstSchwarz){
                    try {
                        logik.zugSetzen(startKoordinate, zielKoordinate,logik.getLstFiguren());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, ex);
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    zeichneHintergrund();
                    zeichneFiguren();

                   startKoordinate = null;
                   zielKoordinate = null;
                   startknopfGedrückt = false;
                   //KI part
                   logik.miniMax(2, true);
                   
                }else{
                    JOptionPane.showMessageDialog(rootPane, "diese Figur wird vom Pc gesetzt");
                }
                
            }
        }
    }
    private void showMenuDemo(){
        //create a menu bar
        JMenuBar menuBar = new JMenuBar();

        //create menus
        JMenu fileMenu = new JMenu("File");
        JMenu spielmodiMenu = new JMenu("Spielmodi"); 
        JMenu aboutMenu = new JMenu("About");
        JMenu linkMenu = new JMenu("Links");

        //create menu items
        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.setActionCommand("New Game");

        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.setActionCommand("Open");

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setActionCommand("Save");

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setActionCommand("Exit");


        JMenuItem pvpMenuItem = new JMenuItem("Player vs. Player");
        pvpMenuItem.setActionCommand("Player vs. Player");

        JMenuItem pveItem = new JMenuItem("Player vs. AI");
        pveItem.setActionCommand("Player vs. AI");

        JMenuItem onlineMenuItem = new JMenuItem("Online");
        onlineMenuItem.setActionCommand("Online");
        
        JMenuItem gitMenuItem = new JMenuItem("Github");
        gitMenuItem.setActionCommand("Github");

        MenuItemListener menuItemListener = new MenuItemListener();

        newGameMenuItem.addActionListener(menuItemListener);
        openMenuItem.addActionListener(menuItemListener);
        saveMenuItem.addActionListener(menuItemListener);
        exitMenuItem.addActionListener(menuItemListener);
        pvpMenuItem.addActionListener(menuItemListener);
        gitMenuItem.addActionListener(menuItemListener);
        pveItem.addActionListener(menuItemListener);
        onlineMenuItem.addActionListener(menuItemListener);
        aboutMenu.addActionListener(menuItemListener);
        linkMenu.addActionListener(menuItemListener);
        

        //add menu items to menus
        fileMenu.add(newGameMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);        
      
        spielmodiMenu.add(pvpMenuItem);
        spielmodiMenu.add(pveItem);
        spielmodiMenu.add(onlineMenuItem);
        
        linkMenu.add(gitMenuItem);

        //add menu to menubar
        menuBar.add(fileMenu);
        menuBar.add(spielmodiMenu);
        menuBar.add(aboutMenu);       
        menuBar.add(linkMenu);

        //add menubar to the frame
        this.setJMenuBar(menuBar);
        this.setVisible(true); 
      
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
        //init menu
        showMenuDemo();
        
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

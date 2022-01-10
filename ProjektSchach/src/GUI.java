import Figuren.Figur;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUI extends JFrame implements ActionListener{
    private JPanel panel;
    private JButton[][] felder = new JButton[8][8];
    
    private JLabel lbl1;
    private JTextField txfName;
    private JButton btnFertig;
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
    private boolean pve = false;
    private boolean online = true;
    
    Datenbank db;
    
    FenNotation fen = new FenNotation();
    boolean farbeFestgelegt = false;
    boolean amZug = false;
    String farbe = "";
    
    
    public GUI(){
       logik = new Logik();
       
       userInformation();
       //initComponents();
       
       //System.out.println(fen.getFenNotation(logik.getLstFiguren(), "TPLKQLPT/BBBBBBBB/00000000/00000000/00000000/00000000/bbbbbbbb/tplkqlpt-b-0"));
       
       startknopfGedrückt = false;
    }
    class MenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {            
            zeichneHintergrund();
            zeichneFiguren();
            
            String action = e.getActionCommand();
            if (action.equals("New Game")) {
                logik.logikReset();
                zeichneFiguren();
                zeichneHintergrund();
                
            }
            if (action.equals("show IP")) {
                
            try(final DatagramSocket socket = new DatagramSocket()){
              socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
              String ip = socket.getLocalAddress().getHostAddress();
              JOptionPane.showMessageDialog(rootPane, ip);
            }   catch (SocketException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (action.equals("Refresh DB")) {
                db = logik.getDb();
                
                String fen = db.getNeustenFenStand();
                logik.listeDekodieren(fen);
                
                zeichneHintergrund();
                zeichneFiguren();
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
            if(action.equals("DB zurücksetzen")){
                Datenbank db = logik.getDb();
                db.purgeSpielstand(fen, logik);
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
                int[] zielPos = new int[2];
                int[] startPos = new int[2];
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
                    ArrayList<Figur> lstFig = logik.getLstFiguren();
                    int besterWert = 0;

                    for (int i = 0; i < lstFig.size(); i++) {
                        int ergebnisMiniMax = 0;                       
                        
                        if(logik.kannFigurSichBewegen(lstFig.get(i))){
                            continue;
                        }else
                            ergebnisMiniMax = logik.miniMax2(lstFig.get(i), 1, false);
                            if(ergebnisMiniMax > besterWert){
                                zielPos = logik.getBesteZielPos();
                                startPos[0] = lstFig.get(i).getPosX();
                                startPos[1] = lstFig.get(i).getPosY();
                            }
                        }
                    }
                    try {
                        logik.zugSetzen(startPos, zielPos, logik.getLstFiguren());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "Fehler bei KI Feld setzen!!");
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(rootPane, "diese Figur wird vom Pc gesetzt");
                }

        }
        if(online){
            db = logik.getDb();
            legeFarbeFest();
                 
            
            
            String fen = db.getNeustenFenStand();
            
            zeichneHintergrund();
            zeichneFiguren();
                

            if(startKoordinate != null && zielKoordinate != null){
                logik.listeDekodieren(fen);
                if(farbe.equals("weiß")){
                    logik.getSpielerWeiß().setAmZug(true);
                    logik.getSpielerSchwarz().setAmZug(false);
                }else{
                    logik.getSpielerWeiß().setAmZug(false);
                    logik.getSpielerSchwarz().setAmZug(true);
                }
                if(db.datenbankIstLeer()){
                    System.out.println("Datenbank ist leer");
                    
                    db.uploadSpielstand(this.fen.getFenNotation(logik.getLstFiguren(), "TPLKQLPT/AAAAAAAA/00000000/00000000/00000000/00000000/aaaaaaaa/tplkqlpt-b-0"), "w");
                }else{//Spiel hat begonnen -> DB ist nicht leer
                    System.out.println("Datenbank ist NICHT leer");
                    
                    //hole Daten
                    logik.listeDekodieren(db.getNeustenFenStand());//update lstFiguren
                    
                    if(this.fen.getFarbeAmZug(fen).equals("w") && farbe.equals("schwarz")){//sei schwarz bzw. uploade als schwarz                   
                        onlineFlow(db, logik.getSpielerSchwarz(), "schwarz");
                    }
                    else if(this.fen.getFarbeAmZug(fen).equals("b") && farbe.equals("weiß")){
                        onlineFlow(db, logik.getSpielerWeiß(), "weiß");
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Noch nicht am Zug!");
                    }
                }

                zeichneHintergrund();
                zeichneFiguren();
                
                startKoordinate = null;
                zielKoordinate = null;
                startknopfGedrückt = false;
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

        JMenuItem openMenuItem = new JMenuItem("show IP");
        openMenuItem.setActionCommand("show IP");

        JMenuItem saveMenuItem = new JMenuItem("Refresh DB");
        saveMenuItem.setActionCommand("Refresh DB");
        
        JMenuItem resetOnlineDbMenuItem = new JMenuItem("DB zurücksetzen");
        resetOnlineDbMenuItem.setActionCommand("DB zurücksetzen");

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
        resetOnlineDbMenuItem.addActionListener(menuItemListener);
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
        fileMenu.add(resetOnlineDbMenuItem);
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
    private void userInformation(){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        this.add(panel);
        
        
        lbl1 = new JLabel("Wie heißt du?");
        txfName = new JTextField();
        btnFertig = new JButton("fertig!");
         
        panel.add(lbl1);
        panel.add(txfName);
        panel.add(btnFertig);
        
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.pack();
        
        btnFertig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logik.getSpielerSchwarz().setName(txfName.getText());
                
                lbl1.setVisible(false);
                txfName.setVisible(false);
                txfName.setEditable(false);
                txfName.setEnabled(false);
                btnFertig.setVisible(false);
                btnFertig.setEnabled(false);
                
                initComponents();
            }
        });
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
    private void onlineFlow(Datenbank db, Spieler sp, String farbe){
        try {    
            logik.zugSetzen(startKoordinate, zielKoordinate,logik.getLstFiguren());
            
            zeichneHintergrund();
            zeichneFiguren();

            startKoordinate = null;
            zielKoordinate = null;
            startknopfGedrückt = false;

            db.uploadSpielstand(sp, logik.reverseFen(fen.getFenNotation(logik.getLstFiguren(), db.getNeustenFenStand())), farbe);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void legeFarbeFest(){
        db = logik.getDb();
        String fen = db.getNeustenFenStand();
        
        if(!farbeFestgelegt){
            if(this.fen.getFarbeAmZug(fen).equals("w")){
                //Sei schwarz
                farbe = "schwarz";
                logik.getSpielerSchwarz().setAmZug(true);
            }else{
                //Sei weiß
                farbe = "weiß";
                logik.getSpielerWeiß().setAmZug(true);
            }
        }
        farbeFestgelegt = true;
    }
}

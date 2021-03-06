
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robin
 */
public class Datenbank {
    private Connection verbindung;
    
    public Datenbank(String ip){
        try {
            verbindung = DriverManager.getConnection("jdbc:mysql://"+ip+":3306/schach", "root", "");
            System.out.println("Datenbank erfolgreich verbunden");
        } catch (SQLException ex) {
            Logger.getLogger(Datenbank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getNeustenFenStand(){
        ArrayList<String> listeStände = new ArrayList<>();
        try {
            Statement statement = verbindung.createStatement();
            ResultSet resSet = statement.executeQuery("SELECT * FROM currentmatch;");
            
            while(resSet.next()){
                listeStände.add(resSet.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Datenbank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeStände.get(listeStände.size()-1);
    }
    public boolean istFigurAmZug(String farbe){
       ArrayList<String> listeFarben = new ArrayList<>();
        try {
            Statement statement = verbindung.createStatement();
            ResultSet resSet = statement.executeQuery("SELECT * FROM currentmatch;");
            ResultSetMetaData metaDaten = resSet.getMetaData();
            int anzSpalten = metaDaten.getColumnCount();
            
            while(resSet.next()){
                listeFarben.add(resSet.getString(3));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Datenbank.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(listeFarben.get(listeFarben.size()-1).equals(farbe)){
            return false;
        }else{
            return true;
        }
    }
    public void uploadSpielstand(String spielerName, String value,String farbe){
        try {
            Statement statement = verbindung.createStatement();
            statement.executeUpdate("INSERT INTO currentmatch (Spieler,Spielstand, Farbe) VALUES ('"+spielerName+"' , '"+value+"', '"+farbe+"');");
        } catch (SQLException ex) {
            Logger.getLogger(Datenbank.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void uploadSpielstand(String value,String farbe){
        try {
            Statement statement = verbindung.createStatement();
            statement.executeUpdate("INSERT INTO currentmatch (Spieler,Spielstand, Farbe) VALUES ('-' , '"+value+"', '"+farbe+"');");
        } catch (SQLException ex) {
            Logger.getLogger(Datenbank.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void purgeSpielstand(FenNotation fen, Logik logik){
        try {
            Statement statement = verbindung.createStatement();
            statement.executeUpdate("TRUNCATE TABLE currentmatch;");
            uploadSpielstand(fen.getFenNotation(logik.getLstFiguren(), "TPLKQLPT/AAAAAAAA/00000000/00000000/00000000/00000000/aaaaaaaa/tplkqlpt-b-0"), "w");
        } catch (SQLException ex) {
            Logger.getLogger(Datenbank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void verbindungSchließen(){
        try {
            verbindung.close();
        } catch (SQLException ex) {
            Logger.getLogger(Datenbank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean datenbankIstLeer(){
        boolean res = true;
        try {
            Statement statement = verbindung.createStatement();
            ResultSet resSet = statement.executeQuery("SELECT count(*) FROM currentmatch;");
            
            while(resSet.next()){
                if(!resSet.getString(1).equals("0")){
                    res = false;
                    break;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Datenbank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}

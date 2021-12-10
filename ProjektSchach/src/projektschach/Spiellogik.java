/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektschach;
import java.util.ArrayList;
import projektschach.Figuren.*;
/**
 *
 * @author Robin
 */
public class Spiellogik {  
    private Spieler spielerWeiß;
    private Spieler spielerSchwarz;
    
    private ArrayList<Figur> lstFiguren = new ArrayList<Figur>();
    private ArrayList<Figur> lstToteFiguren = new ArrayList<Figur>();
    
    
    public Spiellogik(){
        spielerWeiß = new Spieler("Weiß",false);
        spielerSchwarz = new Spieler("Schwarz", true);
        //Weiß beginnt, Schwarz gewinnt
        spielerWeiß.setAmZug(false);
        spielerSchwarz.setAmZug(true);
        
        initFiguren();
        
    }
    
    private void initFiguren(){
        //figuren weiß
        for (int i = 0; i < 8; i++) {
            lstFiguren.add(new Bauer(true, new Feld(i, 1),"♙",10,false));
        }
        lstFiguren.add(new Turm(true, new Feld(0, 0),"♖",50, false));
        lstFiguren.add(new Turm(true, new Feld(7, 0),"♖",50,false));
        lstFiguren.add(new Springer(true, new Feld(1, 0),"♘",30, false));
        lstFiguren.add(new Springer(true, new Feld(6, 0),"♘",30, false));
        lstFiguren.add(new Läufer(true, new Feld(2, 0),"♗",30, false));
        lstFiguren.add(new Läufer(true, new Feld(5, 0),"♗",30, false));
        lstFiguren.add(new König(true, new Feld(3, 0),"♔",900, true));
        lstFiguren.add(new Dame(true, new Feld(4, 0),"♕",100, false));
        
        //figuren schwarz
        for (int i = 16; i < 24; i++) {
            lstFiguren.add(new Bauer(false, new Feld(i-16, 6),"♟",10, false));
        }
        lstFiguren.add(new Turm(false, new Feld(0, 7),"♜",50, false));
        lstFiguren.add(new Turm(false, new Feld(7, 7),"♜",50, false));
        lstFiguren.add(new Springer(false, new Feld(1, 7),"♞",30, false));
        lstFiguren.add(new Springer(false, new Feld(6, 7),"♞",30, false));
        lstFiguren.add(new Läufer(false, new Feld(2, 7),"♝",30, false));
        lstFiguren.add(new Läufer(false, new Feld(5, 7),"♝",30, false));
        lstFiguren.add(new König(false, new Feld(3, 7),"♚",900, true));
        lstFiguren.add(new Dame(false, new Feld(4, 7),"♛",100, false));
    }
    public ArrayList getLstFiguren(){
        return lstFiguren;
    }
    public void setLstFiguren(ArrayList<Figur> lstFiguren){
        this.lstFiguren = lstFiguren;
    }
    public void setLstToteFiguren(ArrayList<Figur> lstToteFiguren){
        this.lstToteFiguren = lstToteFiguren;
    }
    public Spieler getSpielerWeiß() {
        return spielerWeiß;
    }
    public Spieler getSpielerSchwarz() {
        return spielerSchwarz;
    }
    public ArrayList getLstToteFiguren(){
        return lstToteFiguren;
    }
    
    public void setzeFigur(int[] startKoordiante, int[] zielKoordiante) {
        for (int i = 0; i < lstFiguren.size(); i++) {
            if(lstFiguren.get(i).getPosition().getPosX() == startKoordiante[0] && 
                lstFiguren.get(i).getPosition().getPosY() == startKoordiante[1]){
                lstFiguren.get(i).getPosition().setPosX(zielKoordiante[0]);
                lstFiguren.get(i).getPosition().setPosY(zielKoordiante[1]);
            }
        }
    }
    
    public boolean istFeldBelegt(int[] zielKoordiante){
        boolean ergebnis = false;
        for (int i = 0; i < lstFiguren.size(); i++) {
            if(lstFiguren.get(i).getPosition().getPosX() == zielKoordiante[0] && 
                lstFiguren.get(i).getPosition().getPosY() == zielKoordiante[1]){
                ergebnis = true;
                break;
            }
        }
        return ergebnis;
    }
    
    public boolean istFeldBelegungGleicheFarbe(int[] startKoordiante,int[] zielKoordiante){
        boolean ergebnis = false;
        Figur startFig = null;
        Figur zielFig = null;
        for (int i = 0; i < lstFiguren.size(); i++) {
            if(lstFiguren.get(i).getPosition().getPosX() == zielKoordiante[0] && 
                lstFiguren.get(i).getPosition().getPosY() == zielKoordiante[1] ){
                zielFig = lstFiguren.get(i);
                break;
            }
        }
        for (int i = 0; i < lstFiguren.size(); i++) {
            if(lstFiguren.get(i).getPosition().getPosX() == startKoordiante[0] && 
                lstFiguren.get(i).getPosition().getPosY() == startKoordiante[1] ){
                startFig = lstFiguren.get(i);
                break;
            }
        }
        if((startFig.istWeiß() == zielFig.istWeiß()) ){
            ergebnis = true;
        }
        return ergebnis;
    }
    
    public void spielerWechsel(){
        spielerSchwarz.setAmZug(!spielerSchwarz.isAmZug());
        spielerWeiß.setAmZug(!spielerWeiß.isAmZug());
    }
    
    public Figur getFigurAufFeld(int[] koordiante){
        Figur ergebnis = null;
        for (int i = 0; i < lstFiguren.size(); i++) {
            if(lstFiguren.get(i).getPosition().getPosX() == koordiante[0] && 
                lstFiguren.get(i).getPosition().getPosY() == koordiante[1] ){
                ergebnis = lstFiguren.get(i);
                break;
            }
        }
        return ergebnis;
    }
    
    public String spielende(boolean königWeiß){
        if(königWeiß){
            spielerSchwarz.setGewonnen(true);
            spielerWeiß.setGewonnen(false);
            System.out.print("Spielende");
            return "spieler Schwarz hat gewonnen!";
        }else{
            spielerSchwarz.setGewonnen(false);
            spielerWeiß.setGewonnen(true);
            System.out.print("Spielende");
            return "spieler Weiß hat gewonnen!";
        }
        
    }
     public void miniMaxAlgo(int tiefe){
        int wert = 0;
        
        //durch alle spieler des Teams iterieren
        for (int i = 0; i < getFigurenEinesTeams(true).size(); i++) {
            Figur fig = (Figur) getFigurenEinesTeams(true).get(i);
            ArrayList<Feld> möglicheFelder= fig.getPossitionsAbleToMove(lstFiguren);
            
            //finde laufe durch alle möglichen Felder
            for (int j = 0; j < möglicheFelder.size(); j++) { 
                int[] zielfeld = new int[2];
                zielfeld[0]=möglicheFelder.get(j).getPosX();
                zielfeld[1]=möglicheFelder.get(j).getPosY();
                
                int[] startfeld = new int[2];
                startfeld[0]=fig.getPosition().getPosX();
                startfeld[1]=fig.getPosition().getPosY();
                //ist Feld belegt und ist auf Feld Gegner?
                if(istFeldBelegt(zielfeld) && istFeldBelegungGleicheFarbe(startfeld, zielfeld)){
                    //Wert für dieses Feld ist Punktestand von geschalgenem Gegner
                    wert = getFigurAufFeld(zielfeld).getWert();
                } else {
                    wert = 0;
                }
            }
            if(tiefe >= 1){
                miniMaxAlgo(tiefe-1);
            }
            
        }
    }
    
    public ArrayList getFigurenEinesTeams(boolean istTeamWeiß){
        ArrayList<Figur> lstFigurenAusTeam = new ArrayList<Figur>();
        for (int i = 0; i < lstFiguren.size(); i++) {
            if(lstFiguren.get(i).istWeiß() == istTeamWeiß){
                lstFigurenAusTeam.add(lstFiguren.get(i));
            }
        }
        
        
        return lstFigurenAusTeam;
    }
}

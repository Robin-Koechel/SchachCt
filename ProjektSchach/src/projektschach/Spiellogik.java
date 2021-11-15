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
    
    public Spiellogik(){
        spielerWeiß = new Spieler("Weiß");
        spielerSchwarz = new Spieler("Schwarz");
        //Weiß beginnt, Schwarz gewinnt
        spielerWeiß.setAmZug(true);
        spielerSchwarz.setAmZug(false);
        
        initFiguren();
        
    }
    
    public void initFiguren(){
        //figuren weiß
        for (int i = 0; i < 8; i++) {
            lstFiguren.add(new Bauer(true, new Feld(i, 1),"B"));
        }
        lstFiguren.add(new Turm(true, new Feld(0, 0),"T"));
        lstFiguren.add(new Turm(true, new Feld(7, 0),"T"));
        lstFiguren.add(new Springer(true, new Feld(1, 0),"S"));
        lstFiguren.add(new Springer(true, new Feld(6, 0),"S"));
        lstFiguren.add(new Läufer(true, new Feld(2, 0),"L"));
        lstFiguren.add(new Läufer(true, new Feld(5, 0),"L"));
        lstFiguren.add(new König(true, new Feld(3, 0),"K"));
        lstFiguren.add(new Dame(true, new Feld(4, 0),"D"));
        
        //figuren schwarz
        for (int i = 16; i < 24; i++) {
            lstFiguren.add(new Bauer(false, new Feld(i-16, 6),"B"));
        }
        lstFiguren.add(new Turm(false, new Feld(0, 7),"T"));
        lstFiguren.add(new Turm(false, new Feld(7, 7),"T"));
        lstFiguren.add(new Springer(false, new Feld(1, 7),"S"));
        lstFiguren.add(new Springer(false, new Feld(6, 7),"S"));
        lstFiguren.add(new Läufer(false, new Feld(2, 7),"L"));
        lstFiguren.add(new Läufer(false, new Feld(5, 7),"L"));
        lstFiguren.add(new König(false, new Feld(3, 7),"K"));
        lstFiguren.add(new Dame(false, new Feld(4, 7),"D"));
    }
    public ArrayList getLstFiguren(){
        return lstFiguren;
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
}

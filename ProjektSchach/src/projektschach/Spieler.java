/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektschach;

/**
 *
 * @author Robin
 */
public class Spieler {
    private final String seite;
    private boolean amZug;
    private boolean gewonnen;
    private int zaehlerVerloreneFiguren;
    
    public Spieler(String seite){
        this.seite = seite;
        this.gewonnen = false;
        zaehlerVerloreneFiguren = 0;
    }

    public boolean isAmZug() {
        return amZug;
    }
    public void setAmZug(boolean amZug) {
        this.amZug = amZug;
    }
    public boolean isGewonnen() {
        return gewonnen;
    }
    public void setGewonnen(boolean gewonnen) {
        this.gewonnen = gewonnen;
    }
    public int getZaehlerVerloreneFiguren() {
        return zaehlerVerloreneFiguren;
    }
    public void setZaehlerVerloreneFiguren(int zaehlerVerloreneFiguren) {
        this.zaehlerVerloreneFiguren = zaehlerVerloreneFiguren;
    }
    public String getSeite() {
        return seite;
    }
    
    
}

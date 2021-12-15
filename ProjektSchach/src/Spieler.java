/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Robin
 */
public class Spieler {
    private final boolean istSeiteWeiß;
    private boolean amZug;
    private boolean gewonnen;
    private int zaehlerVerloreneFiguren;
    
    public Spieler(boolean istSeiteWeiß, boolean amZug){
        //weiß bewginnt, schwarz gewinnt
        this.istSeiteWeiß = istSeiteWeiß;
        this.gewonnen = false;
        this.amZug = amZug;
        zaehlerVerloreneFiguren = 0;
    }

    public boolean istAmZug() {
        return amZug;
    }
    public void setAmZug(boolean amZug) {
        this.amZug = amZug;
    }
    public boolean hatGewonnen() {
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
    public boolean getSeite() {
        return istSeiteWeiß;
    }
    
    
}

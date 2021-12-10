/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektschach.Figuren;

import java.util.ArrayList;
import projektschach.Feld;

/**
 *
 * @author Robin
 */
public abstract class Figur {
    private boolean besiegt;
    private boolean istWeiß;
    private Feld position;
    private String buchstabe;
    private final int wertigkeit;
    private int anzahlGesetzt = 0;
    private boolean istKönig;
    
    public Figur(boolean istWeiß, Feld position,String buchstabe, int wert, boolean istKönig){
        this.istWeiß = istWeiß;
        this.position = position;
        besiegt = false;
        this.buchstabe = buchstabe;
        this.wertigkeit = wert;
        this.istKönig = istKönig;
    }
    
    public void setBesiegt(boolean besiegt){
        this.besiegt = besiegt;
    }
    
    public int getWert(){
        return this.wertigkeit;
    }
    
    public boolean isBesiegt() {
        return besiegt;
    }
    public boolean istWeiß() {
        return istWeiß;
    }
    public Feld getPosition() {
        return position;
    }

    public String getBuchstabe() {
        return buchstabe;
    }
    public abstract ArrayList<Feld> getPossitionsAbleToMove(ArrayList<Figur> lstFiguren);

    public int getAnzahlGesetzt() {
        return anzahlGesetzt;
    }
    public abstract boolean istFigurImWeg(int[] startKoordiante,int[] zielKoordiante, ArrayList<Figur> lstFiguren);

    public void setAnzahlGesetzt(int anzahlGesetzt) {
        this.anzahlGesetzt = anzahlGesetzt;
    }
    public boolean istKönig(){
        return istKönig;
    }
}

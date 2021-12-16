/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Figuren;

import java.util.ArrayList;


/**
 *
 * @author Robin
 */
public abstract class Figur {
    private boolean besiegt;
    private boolean istWeiß;
    
    private int posX;
    private int posY;
    
    private String buchstabe;
    private final int wertigkeit;
    private boolean istKönig;
    private int anzahlGesetzt;
    
    public Figur(boolean istWeiß, int x, int y,String buchstabe, int wert, boolean istKönig){
        this.istWeiß = istWeiß;
        this.posX = x;
        this.posY = y;
        besiegt = false;
        this.buchstabe = buchstabe;
        this.wertigkeit = wert;
        this.istKönig = istKönig;
        this.anzahlGesetzt = 0;
    }
    
    public void setBesiegt(boolean besiegt){
        this.besiegt = besiegt;
    }
    
    public int getWert(){
        return this.wertigkeit;
    }

    public void setAnzahlGesetzt(int anzahlGesetzt) {
        this.anzahlGesetzt = anzahlGesetzt;
    }
    
    public boolean isBesiegt() {
        return besiegt;
    }
    public boolean istWeiß() {
        return istWeiß;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    public int getAnzahlGesetzt() {
        return anzahlGesetzt;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    
    public String getBuchstabe() {
        return buchstabe;
    }
    public abstract ArrayList<int[]> getPossitionsAbleToMove(ArrayList<Figur> lstFiguren);

    public abstract boolean istFigurImWeg(int[] startKoordiante,int[] zielKoordiante, ArrayList<Figur> lstFiguren);

    public boolean istKönig(){
        return istKönig;
    }
}

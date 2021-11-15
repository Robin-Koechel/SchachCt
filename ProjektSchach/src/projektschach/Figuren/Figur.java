/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektschach.Figuren;

import projektschach.Feld;

/**
 *
 * @author Robin
 */
public class Figur {
    private boolean besiegt;
    private boolean istWeiß;
    private Feld position;
    private String buchstabe;
    
    public Figur(boolean istWeiß, Feld position,String buchstabe){
        this.istWeiß = istWeiß;
        this.position = position;
        besiegt = false;
        this.buchstabe = buchstabe;
    }
    
    public void setBesiegt(boolean besiegt){
        this.besiegt = besiegt;
    }
    public void setTeam(String team) {
        this.istWeiß = istWeiß;
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
    
}

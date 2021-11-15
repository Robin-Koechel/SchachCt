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
    private String team;
    private Feld position;
    private String buchstabe;
    
    public Figur(String team, Feld position,String buchstabe){
        this.team = team;
        this.position = position;
        besiegt = false;
        this.buchstabe = buchstabe;
    }
    
    public void setBesiegt(boolean besiegt){
        this.besiegt = besiegt;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public boolean isBesiegt() {
        return besiegt;
    }
    public String getTeam() {
        return team;
    }
    public Feld getPosition() {
        return position;
    }

    public String getBuchstabe() {
        return buchstabe;
    }
    
}

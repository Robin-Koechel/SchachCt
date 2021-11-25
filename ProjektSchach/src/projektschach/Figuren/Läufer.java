/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektschach.Figuren;

import java.util.ArrayList;
import projektschach.Feld;
import projektschach.Figuren.Figur;

/**
 *
 * @author Robin
 */
public class Läufer extends Figur{

    public Läufer(boolean istWeiß, Feld position, String buchstabe, int wert) {
        super(istWeiß, position, buchstabe, wert);
    }

    @Override
    public ArrayList<Feld> getPossitionsAbleToMove(ArrayList<Figur> lstFiguren) {
        ArrayList<Feld> möglichePositionImNächstenZug = new ArrayList<Feld>();
        
        for (int i = 0; i < 10; i++) {
            möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()-(i+1), getPosition().getPosY()-(i+1)));
            möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()+(i+1), getPosition().getPosY()-(i+1)));
            möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()-(i+1), getPosition().getPosY()+(i+1)));
            möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()+(i+1), getPosition().getPosY()+(i+1)));
        }
        
        return möglichePositionImNächstenZug;
    }

    @Override
    public boolean istFigurImWeg(int[] startKoordiante, int[] zielKoordiante, ArrayList<Figur> lstFiguren) {
        boolean istFigurImWeg = false;
        
        return istFigurImWeg;
    }
    
    public boolean istFeldBelegt(int[] zielKoordiante,ArrayList<Figur> lstFiguren){
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
}

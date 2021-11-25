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
public class Turm extends Figur{

    public Turm(boolean istWeiß, Feld position, String buchstabe, int wert) {
        super(istWeiß, position, buchstabe, wert);
    }

    @Override
    public ArrayList<Feld> getPossitionsAbleToMove(ArrayList<Figur> lstFiguren) {
        ArrayList<Feld> möglichePositionImNächstenZug = new ArrayList<Feld>();
        ArrayList<Figur> figurenImWeg = new ArrayList<Figur>();
        
        for (int i = 0; i < 9; i++) {
            möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX(), getPosition().getPosY()+i));
            möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX(), getPosition().getPosY()-i));
            möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()+i, getPosition().getPosY()));
            möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()-i, getPosition().getPosY()));
        }
        
        for (int i = 0; i < lstFiguren.size(); i++) {
            if(lstFiguren.get(i).getPosition().getPosX() == getPosition().getPosX()){
                figurenImWeg.add(lstFiguren.get(i));
            }
        }
        for (int i = 0; i < figurenImWeg.size(); i++) {
            if(figurenImWeg.get(i).getPosition().getPosX() == getPosition().getPosX() &&
               figurenImWeg.get(i).getPosition().getPosY() == getPosition().getPosY()){
                figurenImWeg.remove(i);
            }
        }
        System.out.print("hey there");
        for (int i = 0; i < möglichePositionImNächstenZug.size(); i++) {
            for (int j = 0; j < figurenImWeg.size(); j++) {
                if(möglichePositionImNächstenZug.get(i).getPosX() >= figurenImWeg.get(j).getPosition().getPosX()&&
                   möglichePositionImNächstenZug.get(i).getPosX() >= getPosition().getPosX()){
                    möglichePositionImNächstenZug.remove(i);
                }
                if(möglichePositionImNächstenZug.get(i).getPosY() >= figurenImWeg.get(j).getPosition().getPosY()&&
                   möglichePositionImNächstenZug.get(i).getPosY() >= getPosition().getPosY()){
                    möglichePositionImNächstenZug.remove(i);
                }
            }
        }
        
        
        return möglichePositionImNächstenZug;
    }

    @Override
    public boolean istFigurImWeg(int[] startKoordiante,int[] zielKoordiante, ArrayList<Figur> lstFiguren) {
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

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
public class Springer extends Figur{

    public Springer(boolean istWeiß, Feld position, String buchstabe, int wert) {
        super(istWeiß, position, buchstabe, wert);
    }

    @Override
    public ArrayList<Feld> getPossitionsAbleToMove(ArrayList<Figur> lstFiguren) {
        ArrayList<Feld> möglichePositionImNächstenZug = new ArrayList<Feld>();
        //oben rechts
        möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()+1, getPosition().getPosY()-2));
        möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()+2, getPosition().getPosY()-1));
        //oben links
        möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()-1, getPosition().getPosY()-2));
        möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()-2, getPosition().getPosY()-1));
        //unten rechts
        möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()+1, getPosition().getPosY()+2));
        möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()+2, getPosition().getPosY()+1));
        //unten links
        möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()-1, getPosition().getPosY()+2));
        möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()-2, getPosition().getPosY()+1));
        
        
        return möglichePositionImNächstenZug;
    }

    @Override
    public boolean istFigurImWeg(int[] startKoordiante,int[] zielKoordiante) {
        return false;
    }
}

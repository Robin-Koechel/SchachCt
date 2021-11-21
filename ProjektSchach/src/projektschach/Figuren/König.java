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
public class König extends Figur{

    public König(boolean istWeiß, Feld position, String buchstabe, int wert) {
        super(istWeiß, position, buchstabe, wert);
    }

    @Override
    public ArrayList<Feld> getPossitionsAbleToMove(ArrayList<Figur> lstFiguren) {
        ArrayList<Feld> möglichePositionImNächstenZug = new ArrayList<Feld>();
        
        möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()+1, getPosition().getPosY()));
        möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()-1, getPosition().getPosY()));
        möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX(), getPosition().getPosY()+1));
        möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX(), getPosition().getPosY()-1));
            
        möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()+1, getPosition().getPosY()+1));
        möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()-1, getPosition().getPosY()-1));
        möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()-1, getPosition().getPosY()+1));
        möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX()+1, getPosition().getPosY()-1));    

        return möglichePositionImNächstenZug;
    }

}

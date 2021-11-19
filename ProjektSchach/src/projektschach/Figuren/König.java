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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

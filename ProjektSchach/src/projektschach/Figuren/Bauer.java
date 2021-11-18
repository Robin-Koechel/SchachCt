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
public class Bauer extends Figur{
    
    public Bauer(boolean istWeiß, Feld position, String buchstabe, int wert) {
        super(istWeiß, position, buchstabe, wert);
    }
    @Override
    public ArrayList<Feld> getPossitionsAbleToMove(ArrayList<Figur> lstFiguren){
        ArrayList<Feld> möglichePositionImNächstenZug = new ArrayList<Feld>();
        if(istWeiß()){
            if(getAnzahlGesetzt()==0){
                möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX(), getPosition().getPosY()+2));
            }
            möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX(), getPosition().getPosY()+1));
            
            for (int i = 0; i < lstFiguren.size(); i++) {
                if((lstFiguren.get(i).getPosition().getPosX() == getPosition().getPosX()+1 && 
                    lstFiguren.get(i).getPosition().getPosY() == getPosition().getPosY()+1) || 
                    lstFiguren.get(i).getPosition().getPosX() == getPosition().getPosX()-1 && 
                    lstFiguren.get(i).getPosition().getPosY() == getPosition().getPosY()+1){
                    
                    if(lstFiguren.get(i).istWeiß()){
                        möglichePositionImNächstenZug.add(new Feld(lstFiguren.get(i).getPosition().getPosX(), lstFiguren.get(i).getPosition().getPosY()));
                    }
                }
            }
            
        }else{
            if(getAnzahlGesetzt()==0){
                möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX(), getPosition().getPosY()-2));
            }
            möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX(), getPosition().getPosY()-1));
            
            for (int i = 0; i < lstFiguren.size(); i++) {
                if((lstFiguren.get(i).getPosition().getPosX() == getPosition().getPosX()+1 && 
                    lstFiguren.get(i).getPosition().getPosY() == getPosition().getPosY()-1) || 
                    lstFiguren.get(i).getPosition().getPosX() == getPosition().getPosX()-1 && 
                    lstFiguren.get(i).getPosition().getPosY() == getPosition().getPosY()-1){
                    
                    if(lstFiguren.get(i).istWeiß()){
                        möglichePositionImNächstenZug.add(new Feld(lstFiguren.get(i).getPosition().getPosX(), lstFiguren.get(i).getPosition().getPosY()));
                    }
                }
            }
        }
        
        return möglichePositionImNächstenZug;
    }
    
}

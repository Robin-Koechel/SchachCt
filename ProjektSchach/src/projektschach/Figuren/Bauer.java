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
        if(istWeiß()){ //Weiße Bauern
            //nächstes mögliches Feld finden
            if(getAnzahlGesetzt()==0){
                möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX(), getPosition().getPosY()+2));
            }
            möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX(), getPosition().getPosY()+1));
            
            //schräg schlagen
            for (int i = 0; i < lstFiguren.size(); i++) {
                if((lstFiguren.get(i).getPosition().getPosX() == getPosition().getPosX()+1 && 
                    lstFiguren.get(i).getPosition().getPosY() == getPosition().getPosY()+1) || 
                    (lstFiguren.get(i).getPosition().getPosX() == getPosition().getPosX()-1 && 
                    lstFiguren.get(i).getPosition().getPosY() == getPosition().getPosY()+1)) {
                    
                    if(!lstFiguren.get(i).istWeiß()){
                        möglichePositionImNächstenZug.add(new Feld(lstFiguren.get(i).getPosition().getPosX(), lstFiguren.get(i).getPosition().getPosY()));
                    }
                }
            }
            //gegenüberliegende nicht schlagen
            for (int i = 0; i < lstFiguren.size(); i++) {
                if(lstFiguren.get(i).getPosition().getPosX() == getPosition().getPosX() &&
                   lstFiguren.get(i).getPosition().getPosY() == getPosition().getPosY()+1){
                    if(!lstFiguren.get(i).istWeiß()){
                        for (int j = 0; j < möglichePositionImNächstenZug.size(); j++) {
                            if(möglichePositionImNächstenZug.get(j).getPosX() == lstFiguren.get(i).getPosition().getPosX() &&
                                möglichePositionImNächstenZug.get(j).getPosY() == lstFiguren.get(i).getPosition().getPosY()){
                                möglichePositionImNächstenZug.remove(j);
                            }
                        }
                    }
                }
            }
            
        }else{ //schwarze Bauern
            //nächstes mögliches Feld finden
            if(getAnzahlGesetzt()==0){
                möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX(), getPosition().getPosY()-2));
            }
            möglichePositionImNächstenZug.add(new Feld(getPosition().getPosX(), getPosition().getPosY()-1));
            //schräg schlagen
            for (int i = 0; i < lstFiguren.size(); i++) {
                if(((lstFiguren.get(i).getPosition().getPosX() == getPosition().getPosX()+1 && 
                    lstFiguren.get(i).getPosition().getPosY() == getPosition().getPosY()-1)) || 
                    (lstFiguren.get(i).getPosition().getPosX() == getPosition().getPosX()-1 && 
                    lstFiguren.get(i).getPosition().getPosY() == getPosition().getPosY()-1)){
                    
                    if(lstFiguren.get(i).istWeiß()){
                        möglichePositionImNächstenZug.add(new Feld(lstFiguren.get(i).getPosition().getPosX(), lstFiguren.get(i).getPosition().getPosY()));
                    }
                }
            }
            
        }
        //gegenüberliegende nicht schlagen
            for (int i = 0; i < lstFiguren.size(); i++) {
                if(lstFiguren.get(i).getPosition().getPosX() == getPosition().getPosX() &&
                   lstFiguren.get(i).getPosition().getPosY() == getPosition().getPosY()-1){
                    if(!lstFiguren.get(i).istWeiß()){
                        for (int j = 0; j < möglichePositionImNächstenZug.size(); j++) {
                            if(möglichePositionImNächstenZug.get(j).getPosX() == lstFiguren.get(i).getPosition().getPosX() &&
                                möglichePositionImNächstenZug.get(j).getPosY() == lstFiguren.get(i).getPosition().getPosY()){
                                möglichePositionImNächstenZug.remove(j);
                            }
                        }
                    }
                }
            }
        return möglichePositionImNächstenZug;
    }

    @Override
    public boolean istFigurImWeg(int[] startKoordiante, int[] zielKoordiante, ArrayList<Figur> lstFiguren) {
        return false;
    }

    
}

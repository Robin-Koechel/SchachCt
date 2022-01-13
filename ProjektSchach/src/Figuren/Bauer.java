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
public class Bauer extends Figur{

    public Bauer(boolean istWeiß,int x,int y , String buchstabe, int wert, boolean istKönig) {
        super(istWeiß, x,y, buchstabe, wert, istKönig);
    }

    @Override
    public ArrayList<int[]> getPossitionsAbleToMove(ArrayList<Figur> lstFiguren){
        ArrayList<int[]> möglichePositionImNächstenZug = new ArrayList<int[]>();
        if(istWeiß()){ //Weiße Bauern
            //nächstes mögliches Feld finden
            if(getAnzahlGesetzt()==0){
                möglichePositionImNächstenZug.add(new int[]{getPosX(),getPosY()+2});
                möglichePositionImNächstenZug.add(new int[]{getPosX(),getPosY()-2});
            }
            möglichePositionImNächstenZug.add(new int[]{getPosX(),getPosY()+1});
            möglichePositionImNächstenZug.add(new int[]{getPosX(),getPosY()-1});
            
            //schräg schlagen
            for (int i = 0; i < lstFiguren.size(); i++) {
                if((lstFiguren.get(i).getPosX() == getPosX()+1 && 
                    lstFiguren.get(i).getPosY() == getPosY()+1) || 
                    (lstFiguren.get(i).getPosX() == getPosX()-1 && 
                    lstFiguren.get(i).getPosY() == getPosY()+1)) {
                    
                    if(!lstFiguren.get(i).istWeiß()){
                        möglichePositionImNächstenZug.add(new int[]{lstFiguren.get(i).getPosX(), lstFiguren.get(i).getPosY()});
                    }
                }
                if((lstFiguren.get(i).getPosX() == getPosX()+1 && 
                    lstFiguren.get(i).getPosY() == getPosY()-1) || 
                    (lstFiguren.get(i).getPosX() == getPosX()-1 && 
                    lstFiguren.get(i).getPosY() == getPosY()-1)) {
                    
                    if(!lstFiguren.get(i).istWeiß()){
                        möglichePositionImNächstenZug.add(new int[]{lstFiguren.get(i).getPosX(), lstFiguren.get(i).getPosY()});
                    }
                }
            }
            //gegenüberliegende nicht schlagen
            for (int i = 0; i < lstFiguren.size(); i++) {
                if(lstFiguren.get(i).getPosX() == getPosX() &&
                   lstFiguren.get(i).getPosY() == getPosY()+1){
                    if(!lstFiguren.get(i).istWeiß()){
                        for (int j = 0; j < möglichePositionImNächstenZug.size(); j++) {
                            if(möglichePositionImNächstenZug.get(j)[0] == lstFiguren.get(i).getPosX() &&
                                möglichePositionImNächstenZug.get(j)[1] == lstFiguren.get(i).getPosY()){
                                möglichePositionImNächstenZug.remove(j);
                            }
                        }
                    }
                }
                if(lstFiguren.get(i).getPosX() == getPosX() &&
                   lstFiguren.get(i).getPosY() == getPosY()-1){
                    if(!lstFiguren.get(i).istWeiß()){
                        for (int j = 0; j < möglichePositionImNächstenZug.size(); j++) {
                            if(möglichePositionImNächstenZug.get(j)[0] == lstFiguren.get(i).getPosX() &&
                                möglichePositionImNächstenZug.get(j)[1] == lstFiguren.get(i).getPosY()){
                                möglichePositionImNächstenZug.remove(j);
                            }
                        }
                    }
                }
            }
            
        }else{ //schwarze Bauern
            //nächstes mögliches Feld finden
            if(getAnzahlGesetzt()==0){
                möglichePositionImNächstenZug.add(new int[]{getPosX(),getPosY()-2});
            }
            möglichePositionImNächstenZug.add(new int[]{getPosX(),getPosY()-1});
            //schräg schlagen
            for (int i = 0; i < lstFiguren.size(); i++) {
                if(((lstFiguren.get(i).getPosX() == getPosX()+1 && 
                    lstFiguren.get(i).getPosY() == getPosY()-1)) || 
                    (lstFiguren.get(i).getPosX() == getPosX()-1 && 
                    lstFiguren.get(i).getPosY() == getPosY()-1)){
                    
                    if(lstFiguren.get(i).istWeiß()){
                        möglichePositionImNächstenZug.add(new int[]{lstFiguren.get(i).getPosX(), lstFiguren.get(i).getPosY()});
                    }
                }
            }
            
        }
        //gegenüberliegende nicht schlagen
            for (int i = 0; i < lstFiguren.size(); i++) {
                if(lstFiguren.get(i).getPosX() == getPosX() &&
                   lstFiguren.get(i).getPosY() == getPosY()-1){
                    if(!lstFiguren.get(i).istWeiß()){
                        for (int j = 0; j < möglichePositionImNächstenZug.size(); j++) {
                            if(möglichePositionImNächstenZug.get(j)[0] == lstFiguren.get(i).getPosX() &&
                                möglichePositionImNächstenZug.get(j)[1] == lstFiguren.get(i).getPosY()){
                                möglichePositionImNächstenZug.remove(j);
                            }
                        }
                    }
                }
            }
            
        for (int i = 0; i < möglichePositionImNächstenZug.size(); i++) {
            if(möglichePositionImNächstenZug.get(i)[0] < 0 || möglichePositionImNächstenZug.get(i)[0] > 7 ||
               möglichePositionImNächstenZug.get(i)[1] < 0 || möglichePositionImNächstenZug.get(i)[1] > 7){
                möglichePositionImNächstenZug.remove(i);
            }
        }
        return möglichePositionImNächstenZug;
    }

    @Override
    public boolean istFigurImWeg(int[] startKoordiante, int[] zielKoordiante, ArrayList<Figur> lstFiguren) {
        return false;
    }
    
}

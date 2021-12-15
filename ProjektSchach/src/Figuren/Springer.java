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
public class Springer extends Figur{

    public Springer(boolean istWeiß, int x,int y, String buchstabe, int wert, boolean istKönig) {
        super(istWeiß, x,y, buchstabe, wert, istKönig);
    }


    @Override
    public ArrayList<int[]> getPossitionsAbleToMove(ArrayList<Figur> lstFiguren) {
        ArrayList<int[]> möglichePositionImNächstenZug = new ArrayList<int[]>();
        //oben rechts
        möglichePositionImNächstenZug.add(new int[]{getPosX()+1, getPosY()-2});
        möglichePositionImNächstenZug.add(new int[]{getPosX()+2, getPosY()-1});
        //oben links
        möglichePositionImNächstenZug.add(new int[]{getPosX()-1, getPosY()-2});
        möglichePositionImNächstenZug.add(new int[]{getPosX()-2, getPosY()-1});
        //unten rechts
        möglichePositionImNächstenZug.add(new int[]{getPosX()+1, getPosY()+2});
        möglichePositionImNächstenZug.add(new int[]{getPosX()+2, getPosY()+1});
        //unten links
        möglichePositionImNächstenZug.add(new int[]{getPosX()-1, getPosY()+2});
        möglichePositionImNächstenZug.add(new int[]{getPosX()-2, getPosY()+1});
        
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

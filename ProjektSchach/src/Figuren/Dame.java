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
public class Dame extends Figur{

    public Dame(boolean istWeiß, int x,int y, String buchstabe, int wert, boolean istKönig) {
        super(istWeiß, x,y, buchstabe, wert, istKönig);
    }

    @Override
    public ArrayList<int[]> getPossitionsAbleToMove(ArrayList<Figur> lstFiguren) {
        ArrayList<int[]> möglichePositionImNächstenZug = new ArrayList<int[]>();
        
        for (int i = 0; i < 10; i++) {
            möglichePositionImNächstenZug.add(new int[]{getPosX()-(i+1), getPosY()-(i+1)});
            möglichePositionImNächstenZug.add(new int[]{getPosX()+(i+1), getPosY()-(i+1)});
            möglichePositionImNächstenZug.add(new int[]{getPosX()-(i+1), getPosY()+(i+1)});
            möglichePositionImNächstenZug.add(new int[]{getPosX()+(i+1), getPosY()+(i+1)});
            
            möglichePositionImNächstenZug.add(new int[]{getPosX(), getPosY()+i});
            möglichePositionImNächstenZug.add(new int[]{getPosX(), getPosY()-i});
            möglichePositionImNächstenZug.add(new int[]{getPosX()+i, getPosY()});
            möglichePositionImNächstenZug.add(new int[]{getPosX()-i, getPosY()});
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
        boolean istFigurImWeg = false;
        int anzahlFelderAufWeg;
        //ist x gleich?
        if(startKoordiante[0] == zielKoordiante[0]){
            anzahlFelderAufWeg = startKoordiante[1]-zielKoordiante[1];
            for (int i = 1; i < Math.abs(anzahlFelderAufWeg); i++) {
                int[] koordinate = {startKoordiante[0],startKoordiante[1] + i};
                if(istFeldBelegt(koordinate, lstFiguren)){
                    istFigurImWeg = true;
                    break;
                }
            }
        }
        else if(startKoordiante[1] == zielKoordiante[1]){//y gleich
            anzahlFelderAufWeg = startKoordiante[0]-zielKoordiante[0];
            for (int i = 1; i < Math.abs(anzahlFelderAufWeg); i++) {
                
                int[] koordinate = {startKoordiante[0]+i,startKoordiante[1]};
                if(istFeldBelegt(koordinate, lstFiguren)){
                    istFigurImWeg = true;
                    break;
                }
                
            }
        }
        else{
            anzahlFelderAufWeg = startKoordiante[1]-zielKoordiante[1];
            for (int i = 1; i < Math.abs(anzahlFelderAufWeg); i++) {
                int[] koordinate = {startKoordiante[0]+i,startKoordiante[1] + i};
                if(istFeldBelegt(koordinate, lstFiguren)){
                    istFigurImWeg = true;
                    break;
                }
            }
        }
        return istFigurImWeg;
    }
    
    public boolean istFeldBelegt(int[] zielKoordiante,ArrayList<Figur> lstFiguren){
        boolean ergebnis = false;
        for (int i = 0; i < lstFiguren.size(); i++) {
            if(lstFiguren.get(i).getPosX() == zielKoordiante[0] && 
                lstFiguren.get(i).getPosY() == zielKoordiante[1]){
                ergebnis = true;
                break;
            }
        }
        return ergebnis;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektschach;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * @author robin.koechel
 */
public class FeldUmwandler {
    
    public int[] FeldInZahl(String feld){
        int ergebnis[] = new int[2];
        //get Y
        ergebnis[1] = Integer.parseInt(feld.substring(1))-1;
        
        //get X
        switch(feld.substring(0, 1)){
            case "A":
                ergebnis[0] = 0;
                break;
            case "B":
                ergebnis[0] = 1;
                break;
            case "C":
                ergebnis[0] = 2;
                break;
            case "D":
                ergebnis[0] = 3;
                break;
            case "E":
                ergebnis[0] = 4;
                break;
            case "F":
                ergebnis[0] = 5;
                break;
            case "G":
                ergebnis[0] = 6;
                break;
            case "H":
                ergebnis[0] = 7;
                break;
        }
        return ergebnis;
    }
    
    public String NummerInBuchstabe(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
    }
}

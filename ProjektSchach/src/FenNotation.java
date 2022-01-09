
import Figuren.Figur;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robin
 */
public class FenNotation {
    //TPLKQLPT/AAAAAAAA/00000000/00000000/00000000/00000000/aaaaaaaa/tplkqlpt-b-0
    
    public String getFenNotation(ArrayList<Figur> lstFiguren, String fenAlt){
        return figurenstellung(lstFiguren)+"-"+zugrecht(fenAlt)+"-"+nummerNächsterZug(fenAlt);
    }
    private String figurenstellung(ArrayList<Figur> lst) {
        String res = "";
        
        int counterX = 0;
        int counterY = 0;
        boolean istFeld = false;
        
        for (int i = 0; i < 8; i++) {//Y
            for (int j = 0; j < 10; j++) {//X
                for (int k = 0; k < lst.size(); k++){
                    if(lst.get(k).getPosX() == j && lst.get(k).getPosY() == i){
                        switch(lst.get(k).getWert()){
                            case 10://Bauer
                                if(lst.get(k).getAnzahlGesetzt()>0){
                                    if(lst.get(k).istWeiß()){
                                    res +=  "B";
                                    }else{
                                        res +=  "b";
                                    }
                                
                                }else{
                                    if(lst.get(k).istWeiß()){
                                    res +=  "A";
                                    }else{
                                        res +=  "a";
                                    }
                                
                                }
                                istFeld = true;
                                break;
                            case 50://Turm
                                if(lst.get(k).istWeiß()){
                                    res +=  "T";
                                }else{
                                    res +=  "t";
                                }
                                
                                istFeld = true;
                                break;
                            case 30://Pferd
                                if(lst.get(k).istWeiß()){
                                    res +=  "P";
                                }else{
                                    res +=  "p";
                                }
                                
                                istFeld = true;
                                break;
                            case 40://Läufer
                                if(lst.get(k).istWeiß()){
                                    res +=  "L";
                                }else{
                                    res +=  "l";
                                }
                                
                                istFeld = true;
                                break;
                            case 900://König
                                if(lst.get(k).istWeiß()){
                                    res +=  "K";
                                }else{
                                    res +=  "k";
                                }
                                
                                istFeld = true;
                                break;
                            case 100://Dame
                                if(lst.get(k).istWeiß()){
                                    res +=  "Q";
                                }else{
                                    res +=  "q";
                                }
                                istFeld = true;
                                break;
                        }
                    }
                }
                if(!istFeld){
                    res+="0";
                }
                istFeld = false;
            }
            res+="/";
        } 
        res = res.replaceAll("00/", "/");
        res = res.replaceAll("/-", "-");
        
        return res;
    }
    private String zugrecht(String fen) {
        String res = "";
        res = fen.substring(fen.indexOf("-")+1, fen.indexOf("-")+2);
        
        if(res.equals("w"))res="b";
        else res="w";
        
        return res;
    }
    private String nummerNächsterZug(String fen) {
        String res = "";
        
        res = fen.substring(fen.length()-1);
        res = Integer.toString(Integer.parseInt(res)+1);
        
        return res;
    }
    
    public boolean weißAmZug(String fen){
        String temp = "";
        temp = fen.substring(fen.indexOf("-")+1, fen.indexOf("-")+2);
        
        if(temp.equals("w"))return false;
        else return true;
    }
    public String getFarbeAmZug(String fen){
        return fen.substring(fen.indexOf("-")+1, fen.indexOf("-")+2);
    }
}

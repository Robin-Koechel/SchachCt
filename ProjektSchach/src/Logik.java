
import Figuren.Bauer;
import Figuren.Dame;
import Figuren.Figur;
import Figuren.König;
import Figuren.Läufer;
import Figuren.Springer;
import Figuren.Turm;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Robin
 */
public class Logik {
    private ArrayList<Figur> lstFiguren = new ArrayList<Figur>();
    private ArrayList<Figur> lstToteFiguren = new ArrayList<Figur>();
    
    private Spieler spielerWeiß;
    private Spieler spielerSchwarz;
    
    public Logik(){
        
        initFiguren();
        
        spielerWeiß = new Spieler(true, false);
        spielerSchwarz = new Spieler(false, true);
    }

    private void initFiguren() {
        //figuren weiß
        for (int i = 0; i < 8; i++) {
            lstFiguren.add(new Bauer(true, i,1,"♙",10,false));
        }
        lstFiguren.add(new Turm(true, 0, 0,"♖",50, false));
        lstFiguren.add(new Turm(true, 7, 0,"♖",50,false));
        lstFiguren.add(new Springer(true, 1, 0,"♘",30, false));
        lstFiguren.add(new Springer(true, 6, 0,"♘",30, false));
        lstFiguren.add(new Läufer(true, 2, 0,"♗",30, false));
        lstFiguren.add(new Läufer(true, 5, 0,"♗",30, false));
        lstFiguren.add(new König(true, 3, 0,"♔",900, true));
        lstFiguren.add(new Dame(true, 4, 0,"♕",100, false));
        
        //figuren schwarz
        for (int i = 16; i < 24; i++) {
            lstFiguren.add(new Bauer(false,i-16, 6,"♟",10, false));
        }
        lstFiguren.add(new Turm(false, 0, 7,"♜",50, false));
        lstFiguren.add(new Turm(false, 7, 7,"♜",50, false));
        lstFiguren.add(new Springer(false, 1, 7,"♞",30, false));
        lstFiguren.add(new Springer(false, 6, 7,"♞",30, false));
        lstFiguren.add(new Läufer(false, 2, 7,"♝",30, false));
        lstFiguren.add(new Läufer(false, 5, 7,"♝",30, false));
        lstFiguren.add(new König(false, 3, 7,"♚",900, true));
        lstFiguren.add(new Dame(false, 4, 7,"♛",100, false));
    }
    public ArrayList getLstFiguren(){
        return lstFiguren;
    }
    public void prüfen(int[]startKoordinate,int[]zielKoordinate)throws Exception{
        Figur fig = getFigurAufFeld(startKoordinate);
        ArrayList<int[]> möglicheFelder = fig.getPossitionsAbleToMove(lstFiguren);        
        
        
        //ungültige Felder sind schon aussortiert
        if(fig.istWeiß() == spielerWeiß.istAmZug()){//Benutzt der Spieler die richtige Farbe?
            if(!istFeldLeer(zielKoordinate)){
                if((getFigurAufFeld(zielKoordinate).istWeiß() == fig.istWeiß())){//ist das Feld belegt?
                    throw new Exception("Feld ist durch Figur gleicher Farbe belegt!");
                }else{
                    for (int i = 0; i < möglicheFelder.size(); i++) {//ist eine Figur im Weg?
                    if(!fig.istFigurImWeg(startKoordinate, zielKoordinate, lstFiguren)){
                        int x = möglicheFelder.get(i)[0];
                        int y = möglicheFelder.get(i)[1];
                        if(x == zielKoordinate[0] && y == zielKoordinate[1]){
                            break;
                        }else{
                            throw new Exception("Felder stimmen nicht überein!");
                        }
                    }else{
                        throw new Exception("Da ist ne Figur im Weg!");
                    }
                }
                }
            }else{
                for (int i = 0; i < möglicheFelder.size(); i++) {//ist eine Figur im Weg?
                    if(!fig.istFigurImWeg(startKoordinate, zielKoordinate, lstFiguren)){
                        int x = möglicheFelder.get(i)[0];
                        int y = möglicheFelder.get(i)[1];
                        if(x == zielKoordinate[0] && y == zielKoordinate[1]){
                            break;
                        }else{
                            
                        }
                    }else{
                        throw new Exception("Da ist ne Figur im Weg!");
                    }
                }
            }

        }else{
            throw new Exception("Diese Figur gehört nicht zu deinem Team!");
        }
    }
    public Figur getFigurAufFeld(int[]feldKoordinate){
        Figur res = null;
        for (int i = 0; i < lstFiguren.size(); i++) {
            if(feldKoordinate[0] == lstFiguren.get(i).getPosX()){
                if(feldKoordinate[1] == lstFiguren.get(i).getPosY()){
                    res = lstFiguren.get(i);
                    break;
                }
            }
        }
        return res;
    }
    public boolean istFeldLeer(int[]feldKoordinate){
        boolean res = true;
        for (int i = 0; i < lstFiguren.size(); i++) {
            if(lstFiguren.get(i).getPosX()==feldKoordinate[0]&&lstFiguren.get(i).getPosY()==feldKoordinate[1]){
                res = false;
                break;
            }
        }
        return res;
    }
    public void spielfluss(int[]startKoordinate,int[]zielKoordinate) throws Exception{
        
        prüfen(startKoordinate,zielKoordinate);

        //figuren schlagen
        for (int i = 0; i < lstFiguren.size(); i++) {
            if(lstFiguren.get(i).getPosX() == zielKoordinate[0] && lstFiguren.get(i).getPosY() == zielKoordinate[1]){
                lstFiguren.get(i).setBesiegt(true);
                lstToteFiguren.add(lstFiguren.get(i));
                lstFiguren.remove(i);
                if(lstFiguren.get(i).istKönig()){
                    System.exit(2);
                }
                break;
            }
        }
        
        //anzahl züge für Figur erhöhen
        int anzahlMovesFig = getFigurAufFeld(startKoordinate).getAnzahlGesetzt();
        getFigurAufFeld(startKoordinate).setAnzahlGesetzt(anzahlMovesFig+=1);

        //Figur setzen
        for (int i = 0; i < lstFiguren.size(); i++) {
            if(lstFiguren.get(i).getPosX() == startKoordinate[0] && lstFiguren.get(i).getPosY() == startKoordinate[1]){
                lstFiguren.get(i).setPosX(zielKoordinate[0]);
                lstFiguren.get(i).setPosY(zielKoordinate[1]);
            }
                
        }
  
        //spielerwechsel
        spielerwechsel();
        if(spielerWeiß.istAmZug()){
            System.out.println("Weiß ist am Zug"); 
        }
        if(spielerSchwarz.istAmZug()){
            System.out.println("Schwarz ist am Zug");
        }
    }
    private void spielerwechsel() {
        spielerSchwarz.setAmZug(!spielerSchwarz.istAmZug());
        spielerWeiß.setAmZug(!spielerWeiß.istAmZug());
    }
}

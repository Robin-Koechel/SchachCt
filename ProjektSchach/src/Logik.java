
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
    //wichtige Methoden
    public void zugSetzen(int[]startKoordinate,int[]zielKoordinate)throws Exception{
          Figur fig = null;
          ArrayList<int[]> möglicheFelder;


          //***************************************
          //ist das Startdfeld leer?
          //***************************************
          boolean startfeldLeer = true;
          for (int i = 0; i < lstFiguren.size(); i++) {
              if(startKoordinate[0] == lstFiguren.get(i).getPosX() && startKoordinate[1] == lstFiguren.get(i).getPosY()){ //Startfeld ist nicht leer
                  startfeldLeer = false;
                  fig = lstFiguren.get(i);
                  break;
              }
          }
          if(startfeldLeer == true){//Startfeld ist leer
              throw new Exception("Leeres Feld wurde ausgewählt!");
          }
          else{ //Startfeld ist belegt
              //***************************************
              //benutzt der Spieler die richtige Farbe?
              //***************************************
              if(fig.istWeiß() == spielerWeiß.istAmZug()){
                  //***************************************
                  //Darf die Figur auf dieses Feld?
                  //***************************************
                  boolean darfFigurAufDiesesFeld = false;
                  möglicheFelder = fig.getPossitionsAbleToMove(lstFiguren);
                  for (int i = 0; i < möglicheFelder.size(); i++) {
                      if(zielKoordinate[0] == möglicheFelder.get(i)[0] && zielKoordinate[1] == möglicheFelder.get(i)[1]){
                          darfFigurAufDiesesFeld = true;
                          break;
                      }
                  }
                  if(darfFigurAufDiesesFeld == false){//Startfeld ist leer
                      throw new Exception("Diese Figur darf nicht auf dieses Feld!");
                  }
                  else{
                      //***************************************
                      //Ist eine Figur im Weg?
                      //***************************************
                      if(fig.istFigurImWeg(startKoordinate, zielKoordinate, lstFiguren)){
                          throw new Exception("Da ist eine Figur im Weg!");
                      }else{
                          //***************************************
                          //Ist eine Figur auf dem Zielfeld?
                          //***************************************
                          boolean zielfeldLeer = true;
                          Figur figurAufZielfeld = null;
                          for (int i = 0; i < lstFiguren.size(); i++) {
                              if(zielKoordinate[0] == lstFiguren.get(i).getPosX() && zielKoordinate[1] == lstFiguren.get(i).getPosY()){ //Zielfeld ist nicht leer
                                  zielfeldLeer = false;
                                  figurAufZielfeld = lstFiguren.get(i);
                                  break;
                              }
                          }
                          if(zielfeldLeer == true){//Zielfeld ist leer
                              //++++++++++++++++++++++
                              //setzen
                              //++++++++++++++++++++++
                              fig.setPosX(zielKoordinate[0]);
                              fig.setPosY(zielKoordinate[1]);

                              //anzahl züge für Figur erhöhen
                              int anzahlMovesFig = getFigurAufFeld(zielKoordinate).getAnzahlGesetzt();
                              getFigurAufFeld(zielKoordinate).setAnzahlGesetzt(anzahlMovesFig+=1);

                              //spielerwechsel
                              spielerwechsel();
                              if(spielerWeiß.istAmZug()){
                                  System.out.println("Weiß ist am Zug");
                              }
                              if(spielerSchwarz.istAmZug()){
                                  System.out.println("Schwarz ist am Zug");
                              }
                          }
                          else{//Zielfeld ist belegt
                              //***************************************
                              //ist Figur auf Zielfeld weiß?
                              //***************************************
                              if(figurAufZielfeld.istWeiß() == spielerWeiß.istAmZug()){//Figur ist vom selben Team
                                  throw new Exception("Man kann nicht auf Felder springen, die schon von nem Teammate belegt sind!");
                              }else{

                                  figurAufZielfeld.setBesiegt(true);
                                  lstToteFiguren.add(figurAufZielfeld);
                                  lstFiguren.remove(figurAufZielfeld);
                                  //++++++++++++++++++++++
                                  //setzen
                                  //++++++++++++++++++++++
                                  fig.setPosX(zielKoordinate[0]);
                                  fig.setPosY(zielKoordinate[1]);

                                  //anzahl züge für Figur erhöhen
                                  int anzahlMovesFig = getFigurAufFeld(zielKoordinate).getAnzahlGesetzt();
                                  getFigurAufFeld(zielKoordinate).setAnzahlGesetzt(anzahlMovesFig+=1);

                                  //spielerwechsel
                                  spielerwechsel();
                                  if(spielerWeiß.istAmZug()){
                                      System.out.println("Weiß ist am Zug");
                                  }
                                  if(spielerSchwarz.istAmZug()){
                                      System.out.println("Schwarz ist am Zug");
                                  }
                                  //Wurde König besiegt
                                  for (int i = 0; i < lstToteFiguren.size(); i++) {
                                      if(lstToteFiguren.get(i).istKönig()){
                                          throw new Exception("Das Spiel ist zuende!");
                                      }
                                  }
                              }

                          }
                      }
                  }
              }else{
                  throw new Exception("Figur hat gehört nicht zum Team!");
              }
          }
      }
    
    //Hilfsmethoden
    private void spielerwechsel() {
        spielerSchwarz.setAmZug(!spielerSchwarz.istAmZug());
        spielerWeiß.setAmZug(!spielerWeiß.istAmZug());
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
    
    //getter und setter
    public ArrayList getLstFiguren(){
        return lstFiguren;
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
    public int getWertEinesFeldes(int[] zielfeld){
        if(istFeldLeer(zielfeld)){
            return 0;
        }else{
            return getFigurAufFeld(zielfeld).getWert();
        }
    }
}

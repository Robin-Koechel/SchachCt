/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektschach;
import projektschach.Figuren.*;
/**
 *
 * @author Robin
 */
public class Spiellogik {
    private Spieler spielerWeiß;
    private Spieler spielerSchwarz;
    private Figur[] lstFiguren = new Figur[32];
    
    public Spiellogik(){
        spielerWeiß = new Spieler("Weiß");
        spielerSchwarz = new Spieler("Schwarz");
        //Weiß beginnt, Schwarz gewinnt
        spielerWeiß.setAmZug(true);
        spielerSchwarz.setAmZug(false);
        
        initFiguren();
        
    }
    
    public void initFiguren(){
        //figuren weiß
        for (int i = 0; i < 8; i++) {
            lstFiguren[i] = new Bauer("weiß", new Feld(i, 1),"B");
        }
        lstFiguren[8] = new Turm("weiß", new Feld(0, 0),"T");
        lstFiguren[9] = new Turm("weiß", new Feld(7, 0),"T");
        lstFiguren[10] = new Springer("weiß", new Feld(1, 0),"S");
        lstFiguren[11] = new Springer("weiß", new Feld(6, 0),"S");
        lstFiguren[12] = new Läufer("weiß", new Feld(2, 0),"L");
        lstFiguren[13] = new Läufer("weiß", new Feld(5, 0),"L");
        lstFiguren[14] = new König("weiß", new Feld(3, 0),"K");
        lstFiguren[15] = new Dame("weiß", new Feld(4, 0),"D");
        
        //figuren schwarz
        for (int i = 16; i < 24; i++) {
            lstFiguren[i] = new Bauer("schwarz", new Feld(i-16, 6),"B");
        }
        lstFiguren[24] = new Turm("schwarz", new Feld(0, 7),"T");
        lstFiguren[25] = new Turm("schwarz", new Feld(7, 7),"T");
        lstFiguren[26] = new Springer("schwarz", new Feld(1, 7),"S");
        lstFiguren[27] = new Springer("schwarz", new Feld(6, 7),"S");
        lstFiguren[28] = new Läufer("schwarz", new Feld(2, 7),"L");
        lstFiguren[29] = new Läufer("schwarz", new Feld(5, 7),"L");
        lstFiguren[30] = new König("schwarz", new Feld(3, 7),"K");
        lstFiguren[31] = new Dame("schwarz", new Feld(4, 7),"D");
    }
    public Figur[] getLstFiguren(){
        return lstFiguren;
    }
}

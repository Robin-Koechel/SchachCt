/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektschach;

/**
 *
 * @author Robin
 */
public class Feld {
    
    private int posX;
    private int posY;
    
    
    public Feld(int x, int y){
        this.posX = x;
        this.posY = y;
    }
    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
}

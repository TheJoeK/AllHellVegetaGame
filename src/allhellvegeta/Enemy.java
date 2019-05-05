/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allhellvegeta;

import java.awt.Image;
import java.util.Random;


public class Enemy {
    
     int badDude;
    Image im;
    int sx;
    int sy;
    int zoneTop;
    int zoneBottom;
    int moveDir;
    int moveRate;
    int dropRate;
    int enemyType;
    boolean detectedPlayer;
    
    public Enemy(Image i) {
        Random random = new Random();

        //Select type
        badDude = 1;
        enemyType = 10; //designates enemy type including bosses(5-9)
        im = i;
        
        //Set starting position CHANGE ACCORDING TO SCREEN RESOLUTIION
        int stp = random.nextInt(3) + 1;
        if (stp == 1) {
            zoneTop = random.nextInt(70) + 10;
            zoneBottom = random.nextInt(70) + 300;
        }
        if (stp == 2) {
            zoneTop = random.nextInt(70) + 70;
            zoneBottom = random.nextInt(70) + 350;
        }
        if (stp == 3) {
            zoneTop = random.nextInt(70) + 100;
            zoneBottom = random.nextInt(70) + 380;
        }
        
        //Set movement rate
        moveRate = random.nextInt(4) + 2;
        dropRate = random.nextInt(2) + 1;
        
        //Position Bad guy
        sx = 680; //CHANGE AFTER DETERMINING HOW BIG THE SCREEN SIZE WILL BE (START OUTSIDE GAME SCREEN)
        int dir = random.nextInt(2);
        if (dir == 0) {
            sy = zoneTop;
            moveDir = 1;
        }
        else {
            sy = zoneBottom;
            moveDir = 2;
        }
    }
    
    public int getX() {
        return sx;
    }
       
    public int getY() {
        return sy;
    }
    
    public void setX(int x) {
        sx = x;
    }

    public void setY(int y) {
        sy = y;
    }

    public Image getImage() {
        return im;
    }
    
    public int getCRX1() {
        return sx;
    }
    
    public int getCRY1() {
        return sy;
    }

    public int getCRX2() {
        //if (shipType == 1)
            return sx + 40; //CHANGE AFTER DETERMINING ENEMY SHIP SIZE
    }
    
    public int getCRY2() {
        //if (shipType == 1)
            return sy + 40; //CHANGE AFTER DETERMINING ENEMY SHIP SIZE
    }

    public int getDropRate() {
        return dropRate;
    }
    
    public void setDropRate(int dropRate) {
        this.dropRate = dropRate;
    }
    
    public int getMoveRate() {
        return moveRate;
    }
    
    public void setMoveRate(int moveRate) {
        this.moveRate = moveRate;
    }
    
    public int getMoveDir() {
        return moveDir;
    }
    
    public void setMoveDir(int dir) {
        moveDir = dir;
    }
    
    public int getZoneTop() {
        return zoneTop;
    }

    public int getZoneBottom() {
        return zoneBottom;
    }
    
    public int getShipValue() {
        return enemyType;
    }
    
    public boolean getDetected(){
        return detectedPlayer;
    }
    
    public void setDetected(){
        this.detectedPlayer = true;
    }
    
    
    
}

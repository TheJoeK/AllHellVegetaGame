/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allhellvegeta;

import java.awt.Image;

public class EnemyAttack {
    
    int sx;
    int sy;
    Image im;
    public EnemyAttack(Image i, int x, int y) {
            im = i;
            sx = x;
            sy = y;
    }
    public int getX() {
        return sx;
    }
    public void setX(int x) {
        sx = x;
    }
    public int getY() {
        return sy;
    }
    public void setY(int y) {
        sy = y;
    }
    public Image getImage() {
        return im;
    }
    public int getCRX() {
        return sx + 2;
    }
    public int getCRY() {
        return sy + 8;
    }
    
}

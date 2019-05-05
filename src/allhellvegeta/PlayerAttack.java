
package allhellvegeta;

import java.awt.Image;

public class PlayerAttack {
    
int sy;
int sx;
Image im;

public PlayerAttack(Image i, int y, int x) {
im = i;
sy = x;
sx = y;
}

public int getY() {
return sy;
}

public void setY(int y) {
sy = y;
}

public int getX() {
return sx;
}

public void setX(int x) {
sx = x;
}

public Image getImage() {
return im;
}

//Shot radius
public int getCRY() {
return sy;
}

public int getCRX() {
return sx + 2;
}
    
}

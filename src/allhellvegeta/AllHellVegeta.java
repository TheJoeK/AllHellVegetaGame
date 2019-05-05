/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allhellvegeta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class AllHellVegeta extends JPanel {

    
    //Main Game Variables
    int cx = 10;
    int cy = 250;
    int lives = 7;
    int gameScore = 0;
    int gameOver = 0;
    
    ArrayList<PlayerAttack> playerAttacks;
    ArrayList<Enemy> enemies;
    ArrayList<EnemyAttack> enemyAttacks;
    
    //Image Variables
    //Image vegeta;
    //Image vegetaFireStill;
    Image bkgrnd,bkgrnd2,bkgrnd3,bkgrnd4;
    ImageIcon enemyIm;
    ImageIcon playerAttackIm;
    ImageIcon enemyAttackIm;
    Image [] VegetaImg;

    //Speed Variables
    int attackSpeed = 11;
    int enemyAttackSpeed = 6;
    
    //User input variables
    int leftArrow = 0;
    int rightArrow = 0;
    int upArrow = 0;
    int downArrow = 0;
    int spaceBar = 0;
    int useImage = 0;
    
    //Timer and delay variables
    Timer timerEv;
    Random rNum;
    int nextEnemy;
    int nextEnemyAttack;
    int attackDelay = 0;
    int usingAlt = 0;
    int deathScene =0;
    
    public static void main(String[] args) {
        AllHellVegeta mainPanel = new AllHellVegeta();
        
        JFrame window = new JFrame();
        window.setTitle("Alien Swarm");
        window.setSize(640, 480);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.add(mainPanel);
        window.setVisible(true);
    }
    
    public AllHellVegeta() {
        setBackground(Color.BLACK);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyPressedEvent(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyReleasedEvent(e);
            }
        });
        setFocusable(true);
        setDoubleBuffered(true);
        
        //background images
        ImageIcon bg1 = new ImageIcon(getClass().getResource("/images/backgrounds/GokuHouseBG.png"));
        ImageIcon bg2 = new ImageIcon(getClass().getResource("/images/backgrounds/DBZEarthBG.png"));
        ImageIcon bg3 = new ImageIcon(getClass().getResource("/images/backgrounds/NamekBG.png"));
        ImageIcon bg4 = new ImageIcon(getClass().getResource("/images/backgrounds/KaiPlanetBG.png"));
        bkgrnd = bg1.getImage().getScaledInstance(640, 480, Image.SCALE_DEFAULT);
        bkgrnd2 = bg2.getImage().getScaledInstance(640, 480, Image.SCALE_DEFAULT);
        bkgrnd3 = bg3.getImage().getScaledInstance(640, 480, Image.SCALE_DEFAULT);
        bkgrnd4 = bg4.getImage().getScaledInstance(640, 480, Image.SCALE_DEFAULT);
        
        //Vegeta images
        //finish array of images (12 total)
        
        
        VegetaImg = new Image[7];
        
        
        //still block
        ImageIcon ii = new ImageIcon(getClass().getResource("/images/SPRITES/vegeta/v-still.png"));
        VegetaImg[0] = ii.getImage();
        ImageIcon ii2 = new ImageIcon(getClass().getResource("/images/SPRITES/vegeta/v-still-fire.png"));
        VegetaImg[1] = ii2.getImage();
        
        //right block
        ImageIcon ii3 = new ImageIcon(getClass().getResource("/images/SPRITES/vegeta/V-right.png"));
        VegetaImg[2] = ii3.getImage();
        ImageIcon ii4 = new ImageIcon(getClass().getResource("/images/SPRITES/vegeta/V-right-fire.png"));
        VegetaImg[3] = ii4.getImage();
        
        //left block
        ImageIcon ii5 = new ImageIcon(getClass().getResource("/images/SPRITES/vegeta/V-left.png"));
        VegetaImg[4] = ii5.getImage();
        ImageIcon ii6 = new ImageIcon(getClass().getResource("/images/SPRITES/vegeta/V-left-fire.png"));
        VegetaImg[5] = ii6.getImage();
        
        //Death image
        ImageIcon ii7 = new ImageIcon(getClass().getResource("/images/SPRITES/vegeta/V-hit.png"));
        VegetaImg[6] = ii7.getImage();
        
        
        
        


        //enemyIm = new ImageIcon(getClass().getResource("/images/SPRITES/frieza/f-left-fire.png"));
        playerAttackIm = new ImageIcon(getClass().getResource("/images/SPRITES/projectiles/v-proj1.png"));
        enemyAttackIm = new ImageIcon(getClass().getResource("/images/SPRITES/projectiles/p1.png"));

        playerAttacks = new ArrayList<PlayerAttack>();
        enemies = new ArrayList<Enemy>();
        enemyAttacks = new ArrayList<EnemyAttack>();
        
        rNum = new Random();
        nextEnemy = rNum.nextInt(20) + 10;
        nextEnemyAttack = rNum.nextInt(20) + 120;
        
        timerEv = new Timer(25, new TimerClass());
        timerEv.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        
        //TEMPORARY BACKGROUND CONDITIONALS
       super.paintComponent(g);
        if(gameScore < 100){
            g.drawImage(bkgrnd, 0, 0, this);
        }
        else if(gameScore >= 100 && gameScore <150){
            g.drawImage(bkgrnd2, 0, 0, this);
        }
        else if(gameScore >= 150&& gameScore <200){
            g.drawImage(bkgrnd3, 0, 0, this);
        }
        else if(gameScore >= 200){
            g.drawImage(bkgrnd4, 0, 0, this);
        }
        if(deathScene == 0){
            g.drawImage(VegetaImg[useImage],cx, cy, this);
        }
        else{
            g.drawImage(VegetaImg[6],cx, cy, this);
        }
        for (int i = 0; i < playerAttacks.size(); i++) {
            PlayerAttack pa = playerAttacks.get(i);
            Image a = pa.getImage();
            int ahx = pa.getX();
            int ahy = pa.getY();
            g.drawImage(a, ahx, ahy, this);
        }
        
        for (int j = 0; j < enemies.size(); j++) {
            Enemy e = enemies.get(j);
            Image ei = e.getImage();
            int ex = e.getX();
            int ey = e.getY();
            g.drawImage(ei, ex, ey, this);
        }
        
        for (int j = 0; j < enemyAttacks.size(); j++) {
            EnemyAttack ea = enemyAttacks.get(j);
            Image eai = ea.getImage();
            int eax = ea.getX();
            int eay = ea.getY();
            g.drawImage(eai, eax, eay, this);
        }

        String curScore = "Score: " + Integer.toString(gameScore);
        String curLives = Integer.toString(lives) + " Lives";
        g.setColor(Color.RED);
        g.setFont(new Font("SansSerif", Font.BOLD, 36));
        g.drawString(curScore, 30, 40);
        g.drawString(curLives, 450, 40);
        
        if (gameOver == 1) {
            g.setFont(new Font("SansSerif", Font.BOLD, 72));
            g.drawString("Game", 220, 180);
            g.drawString("Over.", 235, 280);
            g.drawString("YOU SUCK!", 125, 380);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public void eventFrame() {
        //Decrement timing counters
        if (attackDelay > 0)
            attackDelay--;
        if (nextEnemy > 0)
            nextEnemy--;
        if (nextEnemyAttack > 0)
            nextEnemyAttack--;
        if (usingAlt > 0)
            usingAlt--;
        if(deathScene > 0)
            deathScene--;
        
        //Move
        if(deathScene==0){
            //still
            if(leftArrow == 0 && rightArrow == 0 && upArrow == 0 && downArrow == 0)
                {
                  if (spaceBar == 1)  
                  {
                      useImage =1;
                  }else
                      useImage = 0;
                }

            //up
            if (leftArrow == 0 && rightArrow == 0 && upArrow == 1 && downArrow == 0){
                cy = cy - 7;
                if (cy < 50)
                    cy = 50;
                if(spaceBar == 1)
                {
                    useImage = 1;
                }
                else
                    useImage = 0;
            }
            //down
            if (leftArrow == 0 && rightArrow == 0 && upArrow == 0 && downArrow == 1){
                cy = cy + 7;
                if (cy > 400)
                    cy = 400;
                if(spaceBar == 1)
                {
                    useImage = 1;
                }
                else
                    useImage = 0;

            }
            //left
            if (leftArrow == 1 && rightArrow == 0 && upArrow == 0 && downArrow == 0){
                cx = cx - 7;
                if(cx < 5)    
                {
                   cx = 5; 
                }
                if(spaceBar == 1)
                {
                    useImage = 5;
                }
                else
                    useImage = 4;
            }
            //right
            if (leftArrow == 0 && rightArrow == 1 && upArrow == 0 && downArrow == 0){
                cx = cx + 7;
                if(cx > 160)    
                {
                   cx = 160; 
                }
                if(spaceBar == 1)
                {
                    useImage = 3;
                }
                else
                    useImage = 2;

            }
            //diagonal up-right
            if (leftArrow == 0 && rightArrow == 1 && upArrow == 1 && downArrow == 0)
            {
                cx = cx +4;
                cy = cy -4;
                if(cx > 160)    
                   cx = 160; 
                if (cy < 50)
                    cy = 50;
                if(spaceBar == 1)
                {
                    useImage = 3;
                }
                else
                    useImage = 2;
            }
            //diagonal down-right
            if (leftArrow == 0 && rightArrow == 1 && upArrow == 0 && downArrow == 1)
            {
                cx = cx +4;
                cy = cy +4;
                if(cx > 160)    
                   cx = 160;
                if (cy > 400)
                    cy = 400;
                if(spaceBar == 1)
                {
                    useImage = 3;
                }
                else
                    useImage = 2;
            }
            //diagonal up-left
            if (leftArrow == 1 && rightArrow == 0 && upArrow == 1 && downArrow == 0)
            {
               cx = cx -4;
               cy = cy -4;
               if (cy < 50)
                   cy = 50;
                if (cx < 5)    
                    cx = 5;
                if(spaceBar == 1)
                {
                    useImage = 5;
                }
                else
                    useImage = 4;
            }
            //diagonal down-left
            if (leftArrow == 1 && rightArrow == 0 && upArrow == 0 && downArrow == 1)
            { 
                cx = cx -4;
                cy = cy +4;
                if (cy > 400)
                    cy = 400;
                if (cx < 5)    
                    cx = 5; 
                if(spaceBar == 1)
                {
                    useImage = 5;
                }
                else
                    useImage = 4;
            } 
        }
        
        
        
        //Move alien ships
        int als = enemies.size() - 1;
        while (als >= 0) {
            Enemy curS = enemies.get(als);
            int newy = 0;
            int cursx = curS.getX();
            int cursy = curS.getY();
            int mvr = curS.getMoveRate();
            int drr = curS.getDropRate();
            int newx = cursx;
            if (cursx > 400) {
            newx = cursx - drr;
            }
            int mdir = curS.getMoveDir();
            if (mdir == 1) {
                newy = cursy + mvr;
                int zr = curS.getZoneBottom();
                if (newy >= zr)
                    curS.setMoveDir(2);
            }
            else {
                newy = cursy - mvr;
                int zl = curS.getZoneTop();
                if (newy <= zl)
                    curS.setMoveDir(1);
            }
            if ((java.lang.Math.abs(cursy - cy) < 50) && cursx <= 400){
                curS.setMoveRate(0);
                curS.setDetected();
            }
            
            if (curS.getDetected() == true) {
                newx = cursx - (drr * 12);
            }
            curS.setX(newx);
            curS.setY(newy);
            if (newx < 0)
                enemies.remove(als);
            als--;
        }
        
        //Add new alien ship
                if (nextEnemy == 0 && enemies.size() < 3) {
            ImageIcon enemyIm;
            Random rn = new Random();
            int randomEnemy = rn.nextInt(16)+1;
            switch(randomEnemy){
                case 1: enemyIm=new ImageIcon(getClass().getResource("/images/SPRITES/enemy/e1-left-fire.png"));break;
                case 2: enemyIm=new ImageIcon(getClass().getResource("/images/SPRITES/enemy/e2-left-fire.png"));break;
                case 3: enemyIm=new ImageIcon(getClass().getResource("/images/SPRITES/enemy/e3-left-fire.png"));break;
                case 4: enemyIm=new ImageIcon(getClass().getResource("/images/SPRITES/enemy/e4-left-fire.png"));break;
                case 5: enemyIm=new ImageIcon(getClass().getResource("/images/SPRITES/enemy/e5-left-fire.png"));break;
                case 6: enemyIm=new ImageIcon(getClass().getResource("/images/SPRITES/enemy/e6-left-fire.png"));break;
                case 7: enemyIm=new ImageIcon(getClass().getResource("/images/SPRITES/enemy/e7-left-fire.png"));break;
                case 8: enemyIm=new ImageIcon(getClass().getResource("/images/SPRITES/enemy/e8-left-fire.png"));break;
                case 9: enemyIm=new ImageIcon(getClass().getResource("/images/SPRITES/enemy/e9-left-fire.png"));break;
                case 10:enemyIm=new ImageIcon(getClass().getResource("/images/SPRITES/enemy/e10-left-fire.png"));break;
                case 11:enemyIm=new ImageIcon(getClass().getResource("/images/SPRITES/enemy/e11-left-fire.png"));break;
                case 12:enemyIm=new ImageIcon(getClass().getResource("/images/SPRITES/enemy/e12-left-fire.png"));break;
                case 13:enemyIm=new ImageIcon(getClass().getResource("/images/SPRITES/piccolo/p-left-fire.png"));break;
                case 14:enemyIm=new ImageIcon(getClass().getResource("/images/SPRITES/frieza/f-left-fire.png"));break;
                case 15:enemyIm=new ImageIcon(getClass().getResource("/images/SPRITES/cell/c-left-fire.png"));break;
                default:enemyIm=new ImageIcon(getClass().getResource("/images/SPRITES/buu/b-left-fire.png"));break;
            }            
            Image newShip = enemyIm.getImage();
            Enemy news = new Enemy(newShip);
            enemies.add(news);
            nextEnemy = rNum.nextInt(30) + 80;
        }
        
        //Move my shots
        int shi = playerAttacks.size() - 1;
        while (shi >= 0) {
            PlayerAttack phs = playerAttacks.get(shi);
            int curx = phs.getX();
            curx += attackSpeed;
            if (curx > 640)
                playerAttacks.remove(shi);
            else
                phs.setX(curx);
            shi--;
        }
        
        //Fire new shot while still
        if (attackDelay == 0 && spaceBar == 1 && deathScene == 0) {
            Image newShot = playerAttackIm.getImage();
            int ny = cy + 23;
            int nx = cx + 40;
            PlayerAttack ps = new PlayerAttack(newShot, nx, ny);
            playerAttacks.add(ps);
            attackDelay = 8;
        }
        
        //Move alien shots
        shi = enemyAttacks.size() - 1;
        while (shi >= 0) {
            EnemyAttack ash = enemyAttacks.get(shi);
            int curx = ash.getX();
            curx -= enemyAttackSpeed;
            if (curx < 0)
                enemyAttacks.remove(shi);
            else
                ash.setX(curx);
            shi--;
        }
        
        //Fire new alien shot
        if (nextEnemyAttack == 0) {
            int nEnemy = enemies.size();
            if (nEnemy > 0) {
                Image newShot = enemyAttackIm.getImage();
                int pickEnemy = rNum.nextInt(nEnemy);
                Enemy alsh = enemies.get(pickEnemy);
                int crx1 = alsh.getCRX1();
                int cry2 = alsh.getCRY1();
                EnemyAttack ash = new EnemyAttack(newShot, crx1, cry2);
                enemyAttacks.add(ash);
            }
            nextEnemyAttack = rNum.nextInt(30) + 40;     
        }
        
        //Kill aliens and remove shots
        shi = playerAttacks.size() - 1;
        while (shi >= 0){
            PlayerAttack phs2 = playerAttacks.get(shi);
            int critx = phs2.getCRX();
            int crity = phs2.getCRY();
            int ali = enemies.size() - 1;
            while (ali >= 0) {
                Enemy curals = enemies.get(ali);
                int crity2 = curals.getCRY2();
                int crity1 = curals.getCRY1();
                if (crity <= crity2  && crity > crity1) {
                    int critx1 = curals.getCRX1();
                    int critx2 = curals.getCRX2();
                    if (critx >= critx1 && critx <= critx2) {
                        int val = curals.getShipValue();
                        gameScore += val;
                        playerAttacks.remove(shi);
                        enemies.remove(ali);
                    }
                }
                ali--;
            }
            shi--;
        }
        
        //Check for alien crashes
        int ali = enemies.size() - 1;
        while (ali >= 0) {
            Enemy curals = enemies.get(ali);
            int crity = curals.getCRY1();
            if (crity >= cy && crity <= cy+53) {
                int critx = curals.getCRX1();
                int myx1 = cx - 40;
                int myx2 = cx + 50;
                if (critx >= myx1 && critx <= myx2) {
                    lives--;
                    deathScene = 10;
                    
                    
                    usingAlt = 10;
                    attackDelay += 30;
                    enemies.remove(ali);
                }
            }
            ali--;
        }
        
        //Check for alien shots hitting me
        shi = enemyAttacks.size() - 1;
        while (shi >= 0) {
            EnemyAttack curalsh = enemyAttacks.get(shi);
            int crity = curalsh.getCRY();
            int critx = curalsh.getCRX();
            //if (crity >= 590 && crity <= 630) {
                int myYloc1 = cy;
                int myYloc2 = cy + 53;
                int myx1 = cx;
                int myx2 = cx + 41;
                if (crity >= myYloc1 && crity <= myYloc2){
                    if (critx >= myx1 && critx <= myx2) {
                    lives--;
                    deathScene = 10;
                    
                    //Alt attack
                    //usingAlt = 10;
                    //attackDelay += 30;
                    enemyAttacks.remove(shi);
                    }
                }
                
            //}
            shi--;
        }
        
        //Check for Game Over
        if (lives < 1) {
            gameOver = 1;
            timerEv.stop();
        }
        repaint();
    }
    
    public void keyPressedEvent(KeyEvent e)
    {
        int cd = e.getKeyCode();
      
        if (cd == KeyEvent.VK_UP)
            upArrow = 1;
        if (cd == KeyEvent.VK_DOWN)
            downArrow = 1;
        if (cd == KeyEvent.VK_SPACE)
            spaceBar = 1;
        if (cd == KeyEvent.VK_LEFT)
            leftArrow = 1;
        if (cd == KeyEvent.VK_RIGHT)
            rightArrow = 1;
            
    }

    public void keyReleasedEvent(KeyEvent e)
    {
        int cd = e.getKeyCode();

        if (cd == KeyEvent.VK_UP)
            upArrow = 0;
        if (cd == KeyEvent.VK_DOWN)
            downArrow = 0;
        if (cd == KeyEvent.VK_SPACE)
            spaceBar = 0;
        if (cd == KeyEvent.VK_LEFT)
            leftArrow = 0;
        if (cd == KeyEvent.VK_RIGHT)
            rightArrow = 0;
    }

    class TimerClass implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            eventFrame();
        }
    }
    
}
    
    


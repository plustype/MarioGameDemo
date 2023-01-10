package com.project;

import javazoom.jl.decoder.JavaLayerException;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame implements KeyListener, Runnable {
    private List<BackGround> allBg = new ArrayList<>();
    private BackGround nowBg = new BackGround();
    private Image offScreenImage = null;
    //mario object
    private Mario mario = new Mario();
    //thread for let mario move
    private Thread thread = new Thread(this);
    public MyFrame() {
        //set window size
        this.setSize(900, 600);
        //set window relative location (center)
        this.setLocationRelativeTo(null);
        //set window visible
        this.setVisible(true);
        //set click close button to close window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set window cannot be resized
        this.setResizable(false);
        //add key listener to window
        this.addKeyListener(this);
        //set window title
        this.setTitle("Super Mario Demo");
        //initialize images
        StaticValue.init();
        //init mario
        mario = new Mario(10,400);
        //Create all background
        for (int i = 1; i <=3; i++) {
            allBg.add(new BackGround(i, i ==3));
        }
        //set first page background
        nowBg = allBg.get(0);
        mario.setBackGround(nowBg);
        //paint
        repaint();
        thread.start();
        try {
            new Music();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void paint(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(900,600);
        }
        Graphics graphics = offScreenImage.getGraphics();
        graphics.fillRect(0,0,900,600);

//        //draw for buffer graphic
        graphics.drawImage(nowBg.getBgImage(), 0,0,this);

        // draw all enemies
        for (Enemy enemy : nowBg.getEnemyList()) {
            graphics.drawImage(enemy.getShow(), enemy.getX(), enemy.getY(),this);
        }

        //draw obstacles
        for (Obstacle ob : nowBg.getObstacleList()) {
            graphics.drawImage(ob.getShow(),ob.getX(), ob.getY(), this);
        }
        //draw mario
        graphics.drawImage(mario.getShow(),mario.getX(),mario.getY(),this);

        //add score
        Color c = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Calibri",Font.BOLD,25));
        graphics.drawString("Score: " + mario.getScore(),400, 50);
        graphics.setColor(c);
        //draw buffer image to window
        g.drawImage(offScreenImage,0,0,this);

    }




    public static void main(String[] args) {
        var myFrame = new MyFrame();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //press keyboard to call
    @Override
    public void keyPressed(KeyEvent e) {
        //move to right
        if (e.getKeyCode() == 39) {
            mario.rightMove();
        }

        //move to left
        if (e.getKeyCode() == 37) {
            mario.leftMove();
        }

        //jump
        if (e.getKeyCode() == 38) {
            mario.jump();
        }


    }

    //release keyboard to call
    @Override
    public void keyReleased(KeyEvent e) {
        //stop right
        if (e.getKeyCode() ==39) {
            mario.rightStop();
        }

        //stop left
        if (e.getKeyCode() == 37) {
            mario.leftStop();
        }

    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(50);

                if (mario.getX() >= 880) {
                    nowBg = allBg.get(nowBg.getSort());
                    mario.setBackGround(nowBg);
                    mario.setX(10);
                    mario.setY(400);
                }

                if (mario.isDeath()) {
                    JOptionPane.showMessageDialog(this,"Game Over !");
                    System.exit(0);
                }

                if (mario.isAtTower()) {
                    Thread.sleep(100);
                    JOptionPane.showMessageDialog(this,"Game Clear !");
                    System.exit(0);

                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


package com.project;

import java.awt.image.BufferedImage;

public class Obstacle implements Runnable {
    //obstacle coordinate

    private int x;
    private int y;
    //obstacle type
    private int type; // type = the index of obstacles list

    //display image

    private BufferedImage show = null;
    //current background
    private BackGround bg = null;
    private Thread thread = new Thread(this);
    public Obstacle(int x, int y, int type, BackGround bg) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.bg = bg;
        show = StaticValue.obstacles.get(type);
        if (type == 9) {
            thread.start();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public BufferedImage getShow() {
        return show;
    }


    @Override
    public void run() {
        while (true) {
            if (this.bg.isReachPole()) {
                if (this.y < 420) {
                    this.y += 5;
                }else {
                    this.bg.setFlagAtGround(true);
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


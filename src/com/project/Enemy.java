package com.project;

import java.awt.image.BufferedImage;

public class Enemy implements Runnable {
    //current coordinate

    private int x;
    private  int y;
    //enemy type

    private int type;

    //enemy move direction
    private boolean face_to = true;
    //enemy display
    private BufferedImage show;
    //background ob
    private BackGround bg;
    //flower move limits
    private int max_up = 0;
    private int max_down = 0;
    Thread thread = new Thread(this);

    //current enemy display
    private int image_type = 0;

    public Enemy(int x, int y, int type, boolean face_to, BackGround bg) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.face_to = face_to;
        this.bg = bg;
        show = StaticValue.mushroom.get(0);
        thread.start();
    }
    public Enemy(int x, int y, int type, boolean face_to, BackGround bg, int max_up, int max_down) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.face_to = face_to;
        this.bg = bg;
        this.max_up = max_up;
        this.max_down = max_down;
        show = StaticValue.flower.get(0);
        thread.start();
    }


    //enemy dead method
    public void death() {
        show = StaticValue.mushroom.get(2);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.bg.getEnemyList().remove(this);
    }

    @Override
    public void run() {
        while (true) {
            //check enemy type, 1 = mushroom
            if (type == 1) {        // if enemy = mushroom?
                if (face_to) {      //face_to = true : move to left
                    this.x -= 2;
                } else {
                    this.x += 2;
                }
                image_type = image_type == 1 ? 0 : 1;

                show = StaticValue.mushroom.get(image_type);


            //check if mushroom can go left or go right
            boolean canLeft = true;
            boolean canRight = true;

            for (int i = 0; i < bg.getObstacleList().size(); i++) {
                Obstacle ob1 = bg.getObstacleList().get(i);
                // can go left ?
                if ((ob1.getX() < this.x + 60 && ob1.getX() > this.x - 30) && (ob1.getY() > this.y - 60 && ob1.getY() < this.y + 60)) {
                    canRight = false;
                }

                // can go left ?
                if ((ob1.getX() < this.x + 30 && ob1.getX() > this.x - 60) && (ob1.getY() > this.y - 60 && ob1.getY() < this.y + 60)) {
                    canLeft = false;
                }

            }

            if (face_to && !canLeft || this.x == 0) {
                face_to = false;
            } else if ((!face_to) && (!canRight) || this.x == 840) {
                face_to = true;
            }
        }

            if (type == 2) {         // if enemy = flower ?
                if (face_to) {       // true = move up
                    this.y -= 2;
                } else {
                    this.y += 2;
                }

                image_type = image_type == 1 ? 0 : 1;

                //check if flower move to limits
                if (face_to && (this.y == max_up)) {
                    face_to = false;
                }

                if ((!face_to) && (this.y == max_down)) {
                    face_to = true;
                }

                show = StaticValue.flower.get(image_type);
                }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getShow() {
        return show;
    }

    public int getType() {
        return type;
    }
}

package com.project;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
    //current background
    private BufferedImage bgImage = null;
    //record the current page number
    private int sort;
    //check if it is last stage
    private boolean flag;

    //store all the obstacles

    private List<Obstacle> obstacleList = new ArrayList<>();

    //store all the enemies

    private List<Enemy> enemyList = new ArrayList<>();
    private BufferedImage pole = null;

    private boolean isReachPole = false;

    private boolean flagAtGround = false;

    public BackGround() {

    }

    public BackGround(int sort, boolean flag) {
        this.sort = sort;
        this.flag = flag;

        if (flag) {
            bgImage = StaticValue.bg2;
        } else bgImage = StaticValue.bg;

        //check if it is first stage
        if (sort == 1) {
            //draw 1st stage ground, upper type = 1, lower type = 2, pic size= 60*60
            for (int i = 0; i < 15; i++)
                obstacleList.add(new Obstacle(i * 60, 480, 2, this));

            for (int j = 0; j < 60; j += 60) {
                for (int i = 0; i < 15; i++) {
                    obstacleList.add(new Obstacle(i * 60, 600 - 60 - j, 3, this));
                }
            }

            //draw bricks for 1st stage
            obstacleList.add(new Obstacle(120, 300, 0, this));

            for (int i = 300; i <= 540; i += 60) {
                if (i == 300 || i == 420 || i == 540) {
                    obstacleList.add(new Obstacle(i, 300, 8, this));
                } else if (i == 360) {
                    obstacleList.add(new Obstacle(i, 300, 1, this));

                } else
                    obstacleList.add(new Obstacle(i, 300, 0, this));
            }

            obstacleList.add(new Obstacle(420, 180, 0, this));


            //draw pipe for 1st stage
            obstacleList.add(new Obstacle(690, 360, 4, this));
            obstacleList.add(new Obstacle(750, 360, 5, this));
            obstacleList.add(new Obstacle(690, 420, 6, this));
            obstacleList.add(new Obstacle(750, 420, 7, this));

            //draw mushroom enemy for 1st stage
            enemyList.add(new Enemy(540, 420, 1, true, this));

            //draw flower enemy for 1st stage
            enemyList.add(new Enemy(720, 360, 2, true, this, 300, 420));
        }

        //check if it is 2nd stage
        if (sort == 2) {
            //draw ground for 2nd stage
            for (int i = 0; i < 15; i++)
                obstacleList.add(new Obstacle(i * 60, 480, 2, this));

            for (int j = 0; j < 60; j += 60) {
                for (int i = 0; i < 15; i++) {
                    obstacleList.add(new Obstacle(i * 60, 600 - 60 - j, 3, this));
                }
            }

            //draw block hill
            obstacleList.add(new Obstacle(120, 360, 8, this));
            obstacleList.add(new Obstacle(120, 420, 8, this));

            for (int i = 300; i <= 420; i += 60) {
                obstacleList.add(new Obstacle(180, i, 8, this));
            }
            obstacleList.add(new Obstacle(240, 420, 8, this));

            //draw pipe for 2nd stage
            obstacleList.add(new Obstacle(330, 240, 4, this));
            obstacleList.add(new Obstacle(390, 240, 5, this));
            for (int i = 300; i <= 420; i += 60) {
                obstacleList.add(new Obstacle(330, i, 6, this));
                obstacleList.add(new Obstacle(390, i, 7, this));
            }

            //draw bricks for 2nd stage
            for (int i = 540; i <= 780; i += 120) {
                if (i != 660) {
                    obstacleList.add(new Obstacle(i, 300, 0, this));
                }
            }

            //draw mushroom enemy for 2nd stage
            enemyList.add(new Enemy(540, 420, 1, true, this));

            //draw flower enemy for 2nd stage
            enemyList.add(new Enemy(360, 300, 2, true, this, 180, 300));

        }
        //check if it is 3rd (final) stage
        if (sort == 3) {
            //draw ground for 2nd stage
            for (int i = 0; i < 15; i++)
                obstacleList.add(new Obstacle(i * 60, 480, 2, this));

            for (int j = 0; j < 60; j += 60) {
                for (int i = 0; i < 15; i++) {
                    obstacleList.add(new Obstacle(i * 60, 600 - 60 - j, 3, this));
                }
            }

            //draw brick hill for 3rd stage
            for (int i = 90; i <= 420; i += 60) {
                if (i != 210 & i != 270) {
                    obstacleList.add(new Obstacle(i, 420, 8, this));
                }
            }

            for (int i = 150; i <= 420; i += 60) {
                if (i != 210 & i != 270) {
                    obstacleList.add(new Obstacle(i, 360, 8, this));
                }
            }

            obstacleList.add(new Obstacle(330, 300, 8, this));

            //draw flag
            obstacleList.add(new Obstacle(485, 180, 9, this));

            //draw mushroom enemy for 2nd stage
            enemyList.add(new Enemy(300, 420, 1, true, this));
        }
    }

    public BufferedImage getBgImage() {
        return bgImage;
    }
    public int getSort() {
        return sort;
    }

    public boolean isFlag() {
        return flag;
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public boolean isReachPole() {
        return isReachPole;
    }

    public void setReachPole(boolean reachPole) {
        isReachPole = reachPole;
    }

    public boolean isFlagAtGround() {
        return flagAtGround;
    }

    public void setFlagAtGround(boolean flagAtGround) {
        this.flagAtGround = flagAtGround;
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }
}


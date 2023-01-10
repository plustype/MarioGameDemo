package com.project;

import java.awt.image.BufferedImage;

public class Mario implements Runnable {
    //mario's coordinate
    private int x;
    private int y;

    //mario's status

    private String status;
    //display mario
    private BufferedImage show = null;

    //obtain obstacle info

    private BackGround backGround = new BackGround();
    //mario's movement
    private Thread thread = null;
    //mario move speed
    private int xSpeed;
    //mario jump speed
    private int ySpeed;
    //index for displaying move
    private int index;
    //mario's go-up time
    private int upTime = 0;

    private boolean isAtTower;

    private boolean isDeath = false;

    private int score = 0;



    public Mario() {

    }

    public Mario(int x, int y) {
        this.x = x;
        this.y = y;
        show = StaticValue.stand_R;
        this.status = "stand_right";
        thread = new Thread(this);
        thread.start();

    }


    //mario death
    public void death() {
        isDeath = true;
    }

    //method: mario go left
    public void leftMove() {
        //change speed
        xSpeed = -7;

        if (backGround.isReachPole()) {
            xSpeed = 0;
        }
        //check if mario is in the air
        if (status.indexOf("jump") != -1) {
            status = "jump_left";
        }else {
            status = "move_left";
        }
    }

    //method: mario go right
    public void rightMove() {
        //change speed
        xSpeed = 7;

        if (backGround.isReachPole()) {
            xSpeed = 0;
        }

        //check if mario is in the air
        if (status.indexOf("jump") != -1) {
            status = "jump_right";
        }else {
            status = "move_right";
        }
    }

    //method: mario stop left
    public void leftStop() {
        //change speed
        xSpeed = 0;
        //check if mario is in the air
        if (status.indexOf("jump") != -1) {
            status = "jump_left";
        }else {
            status = "stop_left";
        }
    }

    //method: mario stop right
    public void rightStop() {
        //change speed
        xSpeed = 0;
        //check if mario is in the air
        if (status.indexOf("jump") != -1) {
            status = "jump_right";
        }else {
            status = "stop_right";
        }
    }

    public void jump() {
        if (status.indexOf("jump") == -1) {
            if (status.indexOf("left") != -1) {
                status = "jump_left";
            } else {
                status = "jump_right";
            }
            ySpeed = -10;
            upTime = 12;
        }

        if (backGround.isReachPole()) {
            ySpeed = 0;
        }
    }
    public void fall() {
        if (status.indexOf("left") != -1) {
            status = "jump_left";
        } else {
            status = "jump_right";
        }
        ySpeed = 10;
    }

    @Override
    public void run() {
        while (true) {
            //boolean of mario is on an obstacle
            boolean onObstacle = false;

            //boolean of mario can go right
            boolean canRight = true;

            //boolean of mario can go left
            boolean canLeft = true;

            //check mario is in flag pole
            if (backGround.isFlag() && this.x >= 500) {
                this.backGround.setReachPole(true);

                if (this.backGround.isFlagAtGround()) {
                    status = "move_right";
                    if (x < 815) {
                        x += 5;
                    }else {
                        isAtTower = true;
                    }
                }else {
                    if (y < 420) {
                        xSpeed = 0;
                        this.y += 5;
                        status = "jump_right";
                    }
                    if (y >420) {
                        y = 420;
                        status = "move_right";
                    }
                }

            } else {

            }

            //iterate all the obstacles
            for (int i = 0; i < backGround.getObstacleList().size(); i++) {
                Obstacle ob = backGround.getObstacleList().get(i);
                if (ob.getY() == this.y + 60 && (ob.getX() > this.x - 60 && ob.getX() < this.x + 60)) {
                    onObstacle = true;
                }
                //check if mario jump to reach an obstacle

                if (ob.getY() == this.y - 60 && (ob.getX() > this.x - 60 && ob.getX() < this.x + 60)) {
                    if (ob.getType() == 0) {
                        backGround.getObstacleList().remove(ob);
                        score += 100;
                    }
                    upTime = 0;
                }

                //check if mario can go right
                if ((ob.getX() < this.x + 60 && ob.getX() > this.x - 30) && (ob.getY() > this.y - 60 && ob.getY() < this.y + 60)) {
                    canRight = false;
                }

                //check if mario can go left
                if ((ob.getX() < this.x + 30 && ob.getX() > this.x - 60) && (ob.getY() > this.y - 60 && ob.getY() < this.y + 60)) {
                    canLeft = false;
                }
            }

            //mario kill enemy or touch enemy to death
            for (int i = 0; i < backGround.getEnemyList().size(); i++) {
                Enemy enemy = backGround.getEnemyList().get(i);

                if (enemy.getY() == this.y + 60 && (enemy.getX() > this.x - 60 && enemy.getX() < this.x + 60)) {
                    if (enemy.getType() == 1) {
                        enemy.death();
                        score += 200;
                        upTime = 3;
                        ySpeed = -10;
                    } else if (enemy.getType() == 2) {
                        //mario death
                        death();
                    }
                }

                if ((enemy.getX() + 60 > this.x && enemy.getX() - 60 < this.x) && (enemy.getY() +60 > this.y && enemy.getY() - 60 < this.y)) {
                    //mario death
                    death();
                }
            }


            //mario jump
            if (onObstacle && upTime == 0) {
                if (status.indexOf("left") != -1) {
                    if (xSpeed != 0) {
                        status = "move_left";
                    } else {
                        status = "stop_left";
                    }
                } else {
                    if (xSpeed != 0) {
                        status = "move_right";
                    } else {
                        status = "stop_right";
                    }
                }
            } else {
                if (upTime != 0) {
                    upTime--;
                } else {
                    fall();
                }
                y += ySpeed;
            }



            if ((canLeft && xSpeed < 0) || (canRight && xSpeed >0)) {
                x += xSpeed;
                //check if maro move to the very left end
                if (x < 0) {
                    x = 0;
                }
            }
            //check if mario is moving
            if (status.contains("move")) {
                //index = index ==0 ? 1 :0;
                if (index == 0) {
                    index = 1;
                } else if (index == 1) {
                    index = 2;
                } else if (index == 2) {
                    index = 0;
                }
            }

            //check if mario is moving left
            if ("move_left".equals(status)) {
                show = StaticValue.run_L.get(index);
            }

            //check if mario is moving right
            if ("move_right".equals(status)) {
                show = StaticValue.run_R.get(index);
            }

            //check if mario is stop left
            if ("stop_left".equals(status)) {
                show = StaticValue.stand_L;
            }

            //check if mario is stop right
            if ("stop_right".equals(status)) {
                show = StaticValue.stand_R;

            }

            //check if mario jump left
            if ("jump_left".equals(status)) {
                show = StaticValue.jump_L;
            }

            //check if mario jump right
            if ("jump_right".equals(status)) {
                show = StaticValue.jump_R;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }



    //
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getShow() {
        return show;
    }

    public void setShow(BufferedImage show) {
        this.show = show;
    }

    public void setBackGround(BackGround backGround) {
        this.backGround = backGround;
    }

    public boolean isAtTower() {
        return isAtTower;
    }

    public boolean isDeath() {
        return isDeath;
    }

    public int getScore() {
        return score;
    }
}

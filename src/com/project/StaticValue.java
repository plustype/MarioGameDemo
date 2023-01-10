package com.project;

import javax.imageio.ImageIO;
import javax.management.InstanceAlreadyExistsException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaticValue {
    //background
    public static BufferedImage bg = null;
    public static BufferedImage bg2 = null;
    //Mario left jump
    public static BufferedImage jump_L = null;
    //Mario right jump
    public static BufferedImage jump_R = null;
    //Mario left stand
    public static BufferedImage stand_L = null;
    //Mario right stand
    public static BufferedImage stand_R = null;
    //tower
    public static BufferedImage tower = null;
    //flag
    public static BufferedImage pole = null;
    //Obstacle
    public static List<BufferedImage> obstacles = new ArrayList<>();
    //Mario move left
    public static List<BufferedImage> run_L = new ArrayList<>();
    //Mario move right
    public static List<BufferedImage> run_R = new ArrayList<>();
    //Mushroom Enemy
    public static List<BufferedImage> mushroom = new ArrayList<>();
    //Turtle Enemy
    public static List<BufferedImage> turtle = new ArrayList<>();
    //flower enemy
    public static List<BufferedImage> flower = new ArrayList<>();
    //set default path for images
    public static String path = "/Users/yuzhao/IdeaProjects/MarioGame/src/images/";

    //initial methods
    public static void init () {
            //load background
        try{
            bg = ImageIO.read(new File(path + "firststage.jpg"));
            bg2 = ImageIO.read(new File(path + "firststageend.jpg"));
            //load mario
            stand_L = ImageIO.read(new File(path + "mario_stand_L.png"));
            stand_R = ImageIO.read(new File(path + "mario_stand_R.png"));
            jump_L = ImageIO.read(new File(path + "mario_jump_L.png"));
            jump_R = ImageIO.read(new File(path + "mario_jump_R.png"));
            //load flag pole
            pole = ImageIO.read(new File(path + "pole.png"));
            //load tower
    } catch (IOException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }

        //load mario run
        for (int i = 1; i < 4; i++) {
            try {
                run_L.add(ImageIO.read(new File(path + "mario_run_L_" + i + ".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 1; i < 4; i++) {
            try {
                run_R.add(ImageIO.read(new File(path + "mario_run_R_" + i + ".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            //load obstacle
            obstacles.add(ImageIO.read(new File(path + "brick_breakable1.png")));
            obstacles.add(ImageIO.read(new File(path + "brick_question_mark.png")));
            obstacles.add((ImageIO.read(new File(path +"ground_upper.png"))));
            obstacles.add(ImageIO.read(new File(path + "ground_lower.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 1; i <= 4; i++) {
            //load pipe
            try {
                obstacles.add(ImageIO.read(new File(path + "pipe_" + i + ".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            //load brick and flag
            obstacles.add(ImageIO.read(new File(path + "brick.png")));
            obstacles.add(ImageIO.read(new File(path + "flag.png")));
        } catch (IOException e) {
                throw new RuntimeException(e);
        }

        //load triangle enemy
        for (int i = 1; i <= 3; i++) {
            try {
                mushroom.add(ImageIO.read(new File(path + "triangle" + i + ".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //load flower enemy
        for (int i = 1; i <= 2; i++) {
            try {
                flower.add(ImageIO.read(new File(path + "flower" + i + ".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}

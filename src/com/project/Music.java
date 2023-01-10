package com.project;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Music {

    public Music() throws FileNotFoundException, JavaLayerException {
        Player player;
        String str = "/Users/yuzhao/IdeaProjects/MarioGame/src/music/mario.wav";
        BufferedInputStream name = new BufferedInputStream(new FileInputStream(str));
        player = new Player(name);
        player.play();
    }
}

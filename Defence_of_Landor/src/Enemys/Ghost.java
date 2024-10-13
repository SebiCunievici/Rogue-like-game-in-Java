package Enemys;

import Defence_of_Landor.Graphics.Assets;
import Defence_of_Landor.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ghost {

    private final int WIDTH = 56;

    private final int HEIGHT = 56;

    public float GHOST_X_COORDINATE;

    public float GHOST_Y_COORDINATE;

    protected BufferedImage img = Assets.ghost;

    public Ghost(int x, int y)
    {
        GHOST_X_COORDINATE = x;
        GHOST_Y_COORDINATE = y;
    }
    public void Update()
    {
        if(GHOST_X_COORDINATE > Player.PLAYER_X_COORDINATE)
            GHOST_X_COORDINATE -= 0.5;
        else if (GHOST_X_COORDINATE < Player.PLAYER_X_COORDINATE)
            GHOST_X_COORDINATE += 0.5;

        if(GHOST_Y_COORDINATE > Player.PLAYER_Y_COORDINATE)
            GHOST_Y_COORDINATE -= 0.5;
        else if (GHOST_Y_COORDINATE < Player.PLAYER_Y_COORDINATE)
            GHOST_Y_COORDINATE += 0.5;

    }
    public void Draw(Graphics g)
    {
        g.drawImage(img, (int)GHOST_X_COORDINATE + (int)(Player.PLAYER_START_X_COORDINATE - Player.PLAYER_X_COORDINATE), (int)GHOST_Y_COORDINATE + (int)(Player.PLAYER_START_Y_COORDINATE - Player.PLAYER_Y_COORDINATE), WIDTH, HEIGHT, null);
    }
}

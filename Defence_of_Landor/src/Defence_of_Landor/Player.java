package Defence_of_Landor;

import Defence_of_Landor.Graphics.Assets;

import java.awt.*;


public class Player {

    public static Player player = new Player();

    public static final int PLAYER_WIDTH = 56;
    public static final int PLAYER_HEIGHT = 56;

    public static final double PLAYER_START_X_COORDINATE = 372;

    public static final double PLAYER_START_Y_COORDINATE = 272;

    public static final double REAL_PLAYER_START_X_COORDINATE = 1100;

    public static final double REAL_PLAYER_START_Y_COORDINATE = 1120;

    public static double PLAYER_X_COORDINATE = 1110;
    public static double PLAYER_Y_COORDINATE = 1120;

    public static double PLAYER_X_SPEED = 0;
    public static double PLAYER_Y_SPEED = 0;

    public static boolean isMoving = false;


    public static void Update()
    {
        PLAYER_X_COORDINATE+=PLAYER_X_SPEED;
        PLAYER_Y_COORDINATE+=PLAYER_Y_SPEED;
    }

    public static void Draw(Graphics g, int x, int y)
    {
        g.drawImage(Assets.player, x, y, PLAYER_WIDTH, PLAYER_HEIGHT, null);
    }

    public static void Attack(Graphics g, int x, int y)
    {
        g.drawImage(Assets.attack, x, y, 44, 56, null);
    }


}

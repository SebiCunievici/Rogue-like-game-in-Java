package Defence_of_Landor.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    private  static final int NO_TILES = 32;
    public static Tile[] tiles = new Tile[NO_TILES];

    //tiles

    public static Tile grassTile = new GrassTile(0);

    public static Tile waterTile = new WaterTile(1);

    public static Tile treeTile = new TreeTile(2);

    public static Tile gravelPathTile = new GravelPathTile(3);

    public static Tile portalTile = new PortalTile(4);

    public static Tile dungeonWall = new DungeonWall(5);

    public static Tile dungeonFloor = new DungeonFloor(6);

    public static Tile dungeonWall2 = new DungeonWall2(7);

    public static Tile dungeonFloor2 = new DungeonFloor2(8);

    public static Tile dungeonWall3 = new DungeonWall3(9);

    public static Tile dungeonFloor3 = new DungeonFloor3(10);

    //end tiles

    public static final int TILE_WIDTH = 56;
    public static final int TILE_HEIGHT = 56;
    protected BufferedImage img;
    protected final int id;

    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        tiles[id] = this;
    }

    public void Update()
    {

    }

    public void Draw(Graphics g, int x, int y)
    {
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean IsSolid() { return false; }

    public boolean IsPortal() { return false; }

    public int GetId()
    {
        return id;
    }

}

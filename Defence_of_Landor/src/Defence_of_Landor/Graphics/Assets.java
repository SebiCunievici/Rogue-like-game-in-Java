package Defence_of_Landor.Graphics;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage player;

    public static BufferedImage attack;

    public static BufferedImage ghost;

    public static BufferedImage redGhost;

    public static BufferedImage purpleGhost;

    public static BufferedImage grass;

    public static BufferedImage water;

    public static BufferedImage tree;

    public static BufferedImage gravelPath;

    public static BufferedImage portal;

    public static BufferedImage dungeonWall;

    public static BufferedImage dungeonFloor;

    public static BufferedImage dungeonWall2;

    public static BufferedImage dungeonFloor2;

    public static BufferedImage dungeonWall3;

    public static BufferedImage dungeonFloor3;

    public static BufferedImage house;

    public static BufferedImage menu;


    public static void Init()
    {
        //player
        SpriteSheet player_sheet = new SpriteSheet(ImageLoader.LoadImage("/Textures/knight.png"));
        player = player_sheet.crop(0, 0);

        //attack
        SpriteSheet attack_sheet = new SpriteSheet(ImageLoader.LoadImage("/Textures/attack.png"));
        attack = attack_sheet.crop(0, 0, 44, 56);

        //ghost
        SpriteSheet ghost_sheet = new SpriteSheet(ImageLoader.LoadImage("/Textures/ghost.png"));
        ghost = ghost_sheet.crop(0, 0);

        SpriteSheet redGhost_sheet = new SpriteSheet(ImageLoader.LoadImage("/Textures/red_ghost.png"));
        redGhost = redGhost_sheet.crop(0, 0);

        SpriteSheet purpleGhost_sheet = new SpriteSheet(ImageLoader.LoadImage("/Textures/purple_ghost.png"));
        purpleGhost = purpleGhost_sheet.crop(0, 0);

        //tiles
        SpriteSheet sheet_GrassTile = new SpriteSheet(ImageLoader.LoadImage("/Textures/grass_tile.png"));
        grass = sheet_GrassTile.crop(0, 0);

        SpriteSheet sheet_WaterTile = new SpriteSheet(ImageLoader.LoadImage("/Textures/water_tile.png"));
        water = sheet_WaterTile.crop(0, 0);

        SpriteSheet sheet_TreeTile = new SpriteSheet(ImageLoader.LoadImage("/Textures/tree_tile.png"));
        tree = sheet_TreeTile.crop(0, 0);

        SpriteSheet sheet_GravelPathTile = new SpriteSheet(ImageLoader.LoadImage("/Textures/gravel_path_tile.png"));
        gravelPath = sheet_GravelPathTile.crop(0, 0);

        SpriteSheet sheet_PortalTile = new SpriteSheet(ImageLoader.LoadImage("/Textures/portal.png"));
        portal = sheet_PortalTile.crop(0, 0);

        SpriteSheet sheet_DungeonWall = new SpriteSheet(ImageLoader.LoadImage("/Textures/dungeon_wall.png"));
        dungeonWall = sheet_DungeonWall.crop(0, 0);

        SpriteSheet sheet_DungeonFloor = new SpriteSheet(ImageLoader.LoadImage("/Textures/dungeon_floor.png"));
        dungeonFloor = sheet_DungeonFloor.crop(0, 0);

        SpriteSheet sheet_DungeonWall2 = new SpriteSheet(ImageLoader.LoadImage("/Textures/dungeon_wall2.png"));
        dungeonWall2 = sheet_DungeonWall2.crop(0, 0);

        SpriteSheet sheet_DungeonFloor2 = new SpriteSheet(ImageLoader.LoadImage("/Textures/dungeon_floor2.png"));
        dungeonFloor2 = sheet_DungeonFloor2.crop(0, 0);

        SpriteSheet sheet_DungeonWall3 = new SpriteSheet(ImageLoader.LoadImage("/Textures/dungeon_wall3.png"));
        dungeonWall3 = sheet_DungeonWall3.crop(0, 0);

        SpriteSheet sheet_DungeonFloor3 = new SpriteSheet(ImageLoader.LoadImage("/Textures/dungeon_floor3.png"));
        dungeonFloor3 = sheet_DungeonFloor3.crop(0, 0);

        //structures
        SpriteSheet sheet_House = new SpriteSheet(ImageLoader.LoadImage("/Textures/house.png"));
        house = sheet_House.crop(0, 0, 286, 413);

        //menu
        SpriteSheet sheet_Menu = new SpriteSheet(ImageLoader.LoadImage("/Textures/Menu.png"));
        menu = sheet_Menu.crop(0, 0, 800, 600);

    }

}

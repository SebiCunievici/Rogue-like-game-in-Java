package Defence_of_Landor.Tiles;

import Defence_of_Landor.Graphics.Assets;

public class DungeonWall2 extends Tile {

    public DungeonWall2(int id)
    {
        super(Assets.dungeonWall2, id);
    }

    @Override
    public boolean IsSolid() { return true; }

}

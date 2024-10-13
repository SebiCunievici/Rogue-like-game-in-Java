package Defence_of_Landor.Tiles;

import Defence_of_Landor.Graphics.Assets;

public class DungeonWall extends Tile {

    public DungeonWall(int id)
    {
        super(Assets.dungeonWall, id);
    }

    @Override
    public boolean IsSolid() { return true; }

}

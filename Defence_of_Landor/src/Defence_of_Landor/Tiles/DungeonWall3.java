package Defence_of_Landor.Tiles;

import Defence_of_Landor.Graphics.Assets;

public class DungeonWall3 extends Tile {

    public DungeonWall3(int id)
    {
        super(Assets.dungeonWall3, id);
    }

    @Override
    public boolean IsSolid() { return true; }

}

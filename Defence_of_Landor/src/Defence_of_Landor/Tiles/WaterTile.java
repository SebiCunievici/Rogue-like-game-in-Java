package Defence_of_Landor.Tiles;

import Defence_of_Landor.Graphics.Assets;

public class WaterTile extends Tile {

    public WaterTile(int id)
    {
        super(Assets.water, id);
    }

    @Override
    public boolean IsSolid() { return true; }

}

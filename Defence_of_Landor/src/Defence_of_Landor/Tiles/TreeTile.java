package Defence_of_Landor.Tiles;

import Defence_of_Landor.Graphics.Assets;

public class TreeTile extends Tile {

    public TreeTile(int id)
    {
        super(Assets.tree, id);
    }

    @Override
    public boolean IsSolid() { return true; }

}

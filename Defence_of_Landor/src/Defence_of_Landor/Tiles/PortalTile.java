package Defence_of_Landor.Tiles;

import Defence_of_Landor.Graphics.Assets;

public class PortalTile extends Tile {

    public PortalTile(int id)
    {
        super(Assets.portal, id);
    }

    @Override
    public boolean IsSolid() { return true; }

    @Override
    public boolean IsPortal() { return true; }

}

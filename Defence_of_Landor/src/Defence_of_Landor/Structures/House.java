package Defence_of_Landor.Structures;

import Defence_of_Landor.Graphics.Assets;

public class House extends Structure {

    public static final int width = 286;
    public static final int height = 413;

    public static final int X_OFFSET = 1000;

    public static final int Y_OFFSET = 600;

    public House(int id)
    {
        super(Assets.house, id);
    }

}

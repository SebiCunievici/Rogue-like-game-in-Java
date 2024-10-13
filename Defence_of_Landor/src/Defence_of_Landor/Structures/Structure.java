package Defence_of_Landor.Structures;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Structure {

    private  static final int NO_STRUCTURES = 32;
    public static Structure[] Structures = new Structure[NO_STRUCTURES];

    //structures

    public static Structure house = new House(0);

    //end structures

    protected BufferedImage img;
    protected final int id;

    public Structure(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        Structures[id] = this;
    }

    public void Update()
    {

    }

    public void Draw(Graphics g, int x, int y, int width, int height)
    {
        g.drawImage(img, x, y, width, height, null);
    }

    public boolean IsSolid() { return true; }

    public int GetId()
    {
        return id;
    }

}

package Defence_of_Landor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.sql.*;

public class Main {

    public static int MapSize = 41;

    public static int DungeonSize = 11;

    public static int[][] Map = new int[MapSize][MapSize];

    public static int[][] Dungeon = new int[DungeonSize][DungeonSize];

    public static void main(String[] args) throws FileNotFoundException
    {
        File file = new File("A:\\Defence of Landor\\Defence_of_Landor\\src\\Defence_of_Landor\\Map.txt");
        Scanner sc = new Scanner(file);

        int i = 0, j = 0;
        while(sc.hasNextInt())
        {
            Map[i][j] = sc.nextInt();
            j++;
            if(j == MapSize) {
                i++;
                j = 0;
            }
        }

        file = new File("A:\\Defence of Landor\\Defence_of_Landor\\src\\Defence_of_Landor\\Dungeon.txt");
        sc = new Scanner(file);

        i = 0; j = 0;
        while(sc.hasNextInt())
        {
            Dungeon[i][j] = sc.nextInt();
            i++;
            if(i == DungeonSize) {
                j++;
                i = 0;
            }
        }

        sc.close();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "UPDATE SAVEDGAMES set Valid = 0;";
            stmt.executeUpdate(sql);
            c.commit();

            sql = "UPDATE SAVEDGAMES set GameLevel = 0;";
            stmt.executeUpdate(sql);
            c.commit();

            sql = "UPDATE SAVEDGAMES set PlayerXCoord = 0;";
            stmt.executeUpdate(sql);
            c.commit();

            sql = "UPDATE SAVEDGAMES set PlayerYCoord = 0;";
            stmt.executeUpdate(sql);
            c.commit();

            sql = "UPDATE SAVEDGAMES set GameTimeSec = 0;";
            stmt.executeUpdate(sql);
            c.commit();

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }



        Game Defence_of_Landor = new Game("Defence of Landor", 800, 600);
        Defence_of_Landor.StartGame();
    }

}

package Defence_of_Landor;

import Defence_of_Landor.GameWindow.GameWindow;
import Defence_of_Landor.Graphics.Assets;
import Defence_of_Landor.Structures.House;
import Defence_of_Landor.Structures.Structure;
import Defence_of_Landor.Tiles.Tile;
import Enemys.Ghost;
import Enemys.redGhost;
import Enemys.purpleGhost;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import java.sql.*;
import java.util.ArrayList;

import static Defence_of_Landor.Main.*;


public class Game implements Runnable  {
    private GameWindow wnd;
    private boolean runState;
    private Thread gameThread;
    private BufferStrategy bs;
    private Graphics g;

    private int currentSave = 0;

    private long currentTime;

    private int currentLevel = 0;

    private ArrayList<Ghost> ghosts = new ArrayList<>();
    private int created = 0;

    private ArrayList<redGhost> redGhosts = new ArrayList<>();
    private int redCreated = 0;

    private ArrayList<purpleGhost> purpleGhosts = new ArrayList<>();
    private int purpleCreated = 0;

    enum GameState {Menu, NewGame, LoadGame, MainMap, FirstLevel, SecondLevel, ThirdLevel}
    private GameState gameState = GameState.Menu;

    private Tile tile;

    public Game(String title, int width, int height)
    {
        wnd = new GameWindow(title, width, height);
        runState = false;
    }

    private void InitGame()
    {
        wnd = new GameWindow("Defence of Landor", 800, 600);
        wnd.BuildGameWindow();
        Assets.Init();
    }

    public void run()
    {
        InitGame();
        long oldTime = System.nanoTime();
        long currentTime;
        final int framesPerSecond = 60;
        final double timeFrame = 1000000000 / framesPerSecond;

        while(runState == true)
        {
            currentTime = System.nanoTime();

            if( (currentTime - oldTime) > timeFrame )
            {
                Update();
                Draw();
                oldTime = currentTime;
            }
        }
    }

    public synchronized void StartGame()
    {
        if(runState == false)
        {
            runState = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
        else
        {
            return;
        }
    }

    public synchronized void StopGame()
    {
        if(runState == true)
        {
            runState = false;

            try
            {
                gameThread.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            return;
        }
    }

    private void Update()
    {
        if(gameState == GameState.Menu)
        {
            wnd.GetCanvas().addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //System.out.println(e.getLocationOnScreen().x + " " + e.getLocationOnScreen().y);
                    if(e.getLocationOnScreen().x > 892 && e.getLocationOnScreen().x < 1042)
                        if(e.getLocationOnScreen().y > 447 && e.getLocationOnScreen().y < 494)
                        {
                            //System.out.println("new game");
                            gameState = GameState.NewGame;
                        }

                    if(e.getLocationOnScreen().x > 892 && e.getLocationOnScreen().x < 1042)
                        if(e.getLocationOnScreen().y > 547 && e.getLocationOnScreen().y < 594)
                        {
                            //System.out.println("load game");
                            gameState = GameState.LoadGame;
                        }

                    if(e.getLocationOnScreen().x > 892 && e.getLocationOnScreen().x < 1042)
                        if(e.getLocationOnScreen().y > 647 && e.getLocationOnScreen().y < 694)
                        {
                            System.out.println("Exit game");
                            System.exit(0);
                        }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        } else if (gameState == GameState.NewGame) {

            wnd.GetCanvas().addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    Connection c = null;
                    try {
                        Class.forName("org.sqlite.JDBC");
                        c = DriverManager.getConnection("jdbc:sqlite:database.db");
                        c.setAutoCommit(false);
                        Statement stmt = c.createStatement();

                        if(e.getLocationOnScreen().x > 892 && e.getLocationOnScreen().x < 1042)
                            if(e.getLocationOnScreen().y > 397 && e.getLocationOnScreen().y < 446) {
                                String sql = "UPDATE SAVEDGAMES set Valid = 1 where ID=1;";
                                stmt.executeUpdate(sql);
                                c.commit();
                                currentSave = 1;
                                Player.PLAYER_X_COORDINATE = Player.REAL_PLAYER_START_X_COORDINATE;
                                Player.PLAYER_Y_COORDINATE = Player.REAL_PLAYER_START_Y_COORDINATE;
                                currentTime = System.currentTimeMillis();
                                gameState = GameState.MainMap;
                            }
                        if(e.getLocationOnScreen().x > 892 && e.getLocationOnScreen().x < 1042)
                            if(e.getLocationOnScreen().y > 497 && e.getLocationOnScreen().y < 546) {
                                String sql = "UPDATE SAVEDGAMES set Valid = 1 where ID=2;";
                                stmt.executeUpdate(sql);
                                c.commit();
                                currentSave = 2;
                                Player.PLAYER_X_COORDINATE = Player.REAL_PLAYER_START_X_COORDINATE;
                                Player.PLAYER_Y_COORDINATE = Player.REAL_PLAYER_START_Y_COORDINATE;
                                currentTime = System.currentTimeMillis();
                                gameState = GameState.MainMap;
                            }

                        if(e.getLocationOnScreen().x > 892 && e.getLocationOnScreen().x < 1042)
                            if(e.getLocationOnScreen().y > 597 && e.getLocationOnScreen().y < 646) {
                                String sql = "UPDATE SAVEDGAMES set Valid = 1 where ID=3;";
                                stmt.executeUpdate(sql);
                                c.commit();
                                currentSave = 3;
                                Player.PLAYER_X_COORDINATE = Player.REAL_PLAYER_START_X_COORDINATE;
                                Player.PLAYER_Y_COORDINATE = Player.REAL_PLAYER_START_Y_COORDINATE;
                                currentTime = System.currentTimeMillis();
                                gameState = GameState.MainMap;
                            }

                        if(e.getLocationOnScreen().x > 892 && e.getLocationOnScreen().x < 1042)
                            if(e.getLocationOnScreen().y > 697 && e.getLocationOnScreen().y < 746)
                            {
                                //System.out.println("back");
                                gameState = GameState.Menu;
                            }

                        stmt.close();
                        c.close();
                    } catch ( Exception ex ) {
                        System.err.println( e.getClass().getName() + ": " + ex.getMessage() );
                        System.exit(0);
                    }

                    //System.out.println(e.getLocationOnScreen().x + " " + e.getLocationOnScreen().y);




                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

        } else if (gameState == GameState.LoadGame) {

            wnd.GetCanvas().addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //System.out.println(e.getLocationOnScreen().x + " " + e.getLocationOnScreen().y);
                    Connection c = null;
                    try {
                        Class.forName("org.sqlite.JDBC");
                        c = DriverManager.getConnection("jdbc:sqlite:database.db");
                        c.setAutoCommit(false);
                        Statement stmt = c.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM SAVEDGAMES;");

                        rs.next();
                        int valid = rs.getInt("Valid");

                        if(e.getLocationOnScreen().x > 892 && e.getLocationOnScreen().x < 1042)
                            if(e.getLocationOnScreen().y > 397 && e.getLocationOnScreen().y < 446)
                                if(valid == 1) {
                                    Player.PLAYER_X_COORDINATE = rs.getInt("PlayerXCoord");
                                    Player.PLAYER_Y_COORDINATE = rs.getInt("PlayerYCoord");
                                    currentSave = 1;
                                    currentTime = System.currentTimeMillis();
                                    int level = rs.getInt("GameLevel");
                                    if(level == 0)
                                        gameState = GameState.MainMap;
                                    else if (level == 1)
                                        gameState = GameState.FirstLevel;
                                    else if (level == 2)
                                        gameState = GameState.SecondLevel;
                                    else
                                        gameState = GameState.ThirdLevel;
                                }

                        rs.next();
                        valid = rs.getInt("Valid");

                        if(e.getLocationOnScreen().x > 892 && e.getLocationOnScreen().x < 1042)
                            if(e.getLocationOnScreen().y > 497 && e.getLocationOnScreen().y < 546)
                                if(valid == 1)
                                {
                                    Player.PLAYER_X_COORDINATE = rs.getInt("PlayerXCoord");
                                    Player.PLAYER_Y_COORDINATE = rs.getInt("PlayerYCoord");
                                    currentSave = 2;
                                    currentTime = System.currentTimeMillis();
                                    gameState = GameState.MainMap;
                                }

                        rs.next();
                        valid = rs.getInt("Valid");

                        if(e.getLocationOnScreen().x > 892 && e.getLocationOnScreen().x < 1042)
                            if(e.getLocationOnScreen().y > 597 && e.getLocationOnScreen().y < 646)
                                if(valid == 1)
                                {
                                    Player.PLAYER_X_COORDINATE = rs.getInt("PlayerXCoord");
                                    Player.PLAYER_Y_COORDINATE = rs.getInt("PlayerYCoord");
                                    currentSave = 3;
                                    currentTime = System.currentTimeMillis();
                                    gameState = GameState.MainMap;
                                }

                        if(e.getLocationOnScreen().x > 892 && e.getLocationOnScreen().x < 1042)
                            if(e.getLocationOnScreen().y > 697 && e.getLocationOnScreen().y < 746)
                            {
                                gameState = GameState.Menu;
                            }

                        rs.close();
                        stmt.close();
                        c.close();
                    } catch ( Exception ex ) {
                        System.err.println( e.getClass().getName() + ": " + ex.getMessage() );
                        System.exit(0);
                    }

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

        } else if(gameState == GameState.MainMap) {

            Player.Update();

            wnd.GetCanvas().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_SPACE:
                            Player.Attack(g, (int)Player.PLAYER_START_X_COORDINATE, (int)Player.PLAYER_START_Y_COORDINATE);

                            for(int i = 0; i < ghosts.size(); ++i)
                                if ((int) ghosts.get(i).GHOST_X_COORDINATE - (int) (Player.PLAYER_X_COORDINATE) < -80 || (int) ghosts.get(i).GHOST_X_COORDINATE - (int) (Player.PLAYER_X_COORDINATE) < 80)
                                    if ((int) ghosts.get(i).GHOST_Y_COORDINATE - (int) (Player.PLAYER_Y_COORDINATE) < -80 || (int) ghosts.get(i).GHOST_Y_COORDINATE - (int) (Player.PLAYER_Y_COORDINATE) < 80)
                                    {
                                        ghosts.remove(i);
                                    }

                            break;

                        case KeyEvent.VK_W:

                            if (Tile.tiles[Map[(int) (Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if (Tile.tiles[Map[(int) (Player.PLAYER_X_COORDINATE - 25 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if ((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 18 && (int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 10)
                                if ((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 17 && (int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 23)
                                    break;

                            Player.PLAYER_Y_SPEED = -2;
                            Player.isMoving = true;
                            break;
                        case KeyEvent.VK_A:

                            if (Tile.tiles[Map[(int) (Player.PLAYER_X_COORDINATE - 1) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 25) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if (Tile.tiles[Map[(int) (Player.PLAYER_X_COORDINATE - 1) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE - 25 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if ((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 17 && (int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 10)
                                if ((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 17 && (int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 23)
                                    break;

                            Player.PLAYER_X_SPEED = -2;
                            Player.isMoving = true;
                            break;
                        case KeyEvent.VK_S:

                            if (Tile.tiles[Map[(int) (Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if (Tile.tiles[Map[(int) (Player.PLAYER_X_COORDINATE - 25 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if ((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 17 && (int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 9)
                                if ((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 17 && (int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 23)
                                    break;

                            Player.PLAYER_Y_SPEED = 2;
                            Player.isMoving = true;
                            break;
                        case KeyEvent.VK_D:

                            if (Tile.tiles[Map[(int) (Player.PLAYER_X_COORDINATE + 1 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 25) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if (Tile.tiles[Map[(int) (Player.PLAYER_X_COORDINATE + 1 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE - 25 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if ((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 17 && (int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 10)
                                if ((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 16 && (int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 21)
                                    break;

                            Player.PLAYER_X_SPEED = 2;
                            Player.isMoving = true;
                            break;

                        case KeyEvent.VK_Q:
                            System.out.print((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT + " ");
                            System.out.println((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH);
                            break;

                        case KeyEvent.VK_E:
                            if (Tile.tiles[Map[(int) (Player.PLAYER_X_COORDINATE + 1 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE - 25 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsPortal())
                            {
                                if(currentLevel == 0) {
                                    currentLevel = 1;
                                    Player.PLAYER_X_COORDINATE = Player.REAL_PLAYER_START_X_COORDINATE / 4;
                                    Player.PLAYER_Y_COORDINATE = Player.REAL_PLAYER_START_Y_COORDINATE / 4;
                                    gameState = GameState.FirstLevel;
                                }

                                if(currentLevel == 1) {
                                    currentLevel = 2;
                                    Player.PLAYER_X_COORDINATE = Player.REAL_PLAYER_START_X_COORDINATE / 4;
                                    Player.PLAYER_Y_COORDINATE = Player.REAL_PLAYER_START_Y_COORDINATE / 4;
                                    gameState = GameState.SecondLevel;
                                }

                                if(currentLevel == 2) {
                                    currentLevel = 3;
                                    Player.PLAYER_X_COORDINATE = Player.REAL_PLAYER_START_X_COORDINATE / 4;
                                    Player.PLAYER_Y_COORDINATE = Player.REAL_PLAYER_START_Y_COORDINATE / 4;
                                    gameState = GameState.ThirdLevel;
                                }
                            }

                            else if ((Tile.tiles[Map[(int) (Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsPortal()))
                            {
                                if(currentLevel == 0) {
                                    currentLevel = 1;
                                    Player.PLAYER_X_COORDINATE = Player.REAL_PLAYER_START_X_COORDINATE / 4;
                                    Player.PLAYER_Y_COORDINATE = Player.REAL_PLAYER_START_Y_COORDINATE / 4;
                                    gameState = GameState.FirstLevel;
                                }

                            }

                            else if (Tile.tiles[Map[(int) (Player.PLAYER_X_COORDINATE - 1) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 25) / Tile.TILE_HEIGHT]].IsPortal())
                            {
                                if(currentLevel == 0) {
                                    currentLevel = 1;
                                    Player.PLAYER_X_COORDINATE = Player.REAL_PLAYER_START_X_COORDINATE / 4;
                                    Player.PLAYER_Y_COORDINATE = Player.REAL_PLAYER_START_Y_COORDINATE / 4;
                                    gameState = GameState.FirstLevel;
                                }

                            }

                            else if (Tile.tiles[Map[(int) (Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1) / Tile.TILE_HEIGHT]].IsPortal())
                            {
                                if(currentLevel == 0) {
                                    currentLevel = 1;
                                    Player.PLAYER_X_COORDINATE = Player.REAL_PLAYER_START_X_COORDINATE / 4;
                                    Player.PLAYER_Y_COORDINATE = Player.REAL_PLAYER_START_Y_COORDINATE / 4;
                                    gameState = GameState.FirstLevel;
                                }
                            }

                            break;

                        case KeyEvent.VK_ESCAPE:

                            Connection c = null;
                            try {
                                Class.forName("org.sqlite.JDBC");
                                c = DriverManager.getConnection("jdbc:sqlite:database.db");
                                c.setAutoCommit(false);
                                Statement stmt = c.createStatement();

                                String sql = "UPDATE SAVEDGAMES set GameLevel = " + currentLevel + " where ID=" + currentSave + ";";
                                stmt.executeUpdate(sql);
                                c.commit();

                                sql = "UPDATE SAVEDGAMES set PlayerXCoord = " + Player.PLAYER_X_COORDINATE + " where ID=" + currentSave + ";";
                                stmt.executeUpdate(sql);
                                c.commit();

                                sql = "UPDATE SAVEDGAMES set PlayerYCoord = " + Player.PLAYER_Y_COORDINATE + " where ID=" + currentSave + ";";
                                stmt.executeUpdate(sql);
                                c.commit();

                                ResultSet rs = stmt.executeQuery("SELECT * FROM SAVEDGAMES;");
                                for(int i = 1; i < currentSave; ++i)
                                    rs.next();

                                long time = ((System.currentTimeMillis() - currentTime) / 1000);// + rs.getInt("GameTimeSec");

                                sql = "UPDATE SAVEDGAMES set GameTimeSec = " + (int)(time + rs.getInt("GameTimeSec")) + " where ID=" + currentSave + ";";
                                stmt.executeUpdate(sql);
                                c.commit();


                                rs.close();
                                stmt.close();
                                c.close();

                                System.out.println("Saved game");
                                gameState = GameState.Menu;
                            } catch ( Exception ex ) {
                                System.err.println( e.getClass().getName() + ": " + ex.getMessage() );
                                System.exit(0);
                            }

                            break;
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_W:
                            Player.PLAYER_Y_SPEED = 0;
                            Player.isMoving = false;
                            break;
                        case KeyEvent.VK_A:
                            Player.PLAYER_X_SPEED = 0;
                            Player.isMoving = false;
                            break;
                        case KeyEvent.VK_S:
                            Player.PLAYER_Y_SPEED = 0;
                            Player.isMoving = false;
                            break;
                        case KeyEvent.VK_D:
                            Player.PLAYER_X_SPEED = 0;
                            Player.isMoving = false;
                            break;
                    }
                }
            });
        } else if (gameState == GameState.FirstLevel) {

            Player.Update();

            wnd.GetCanvas().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_SPACE:
                            Player.Attack(g, (int)Player.PLAYER_START_X_COORDINATE, (int)Player.PLAYER_START_Y_COORDINATE);

                            for(int i = 0; i < ghosts.size(); ++i) {
                                if ((int) ghosts.get(i).GHOST_X_COORDINATE - (int) (Player.PLAYER_X_COORDINATE) < -80 || (int) ghosts.get(i).GHOST_X_COORDINATE - (int) (Player.PLAYER_X_COORDINATE) < 80)
                                    if ((int) ghosts.get(i).GHOST_Y_COORDINATE - (int) (Player.PLAYER_Y_COORDINATE) < -80 || (int) ghosts.get(i).GHOST_Y_COORDINATE - (int) (Player.PLAYER_Y_COORDINATE) < 80)
                                    {
                                        ghosts.remove(i);
                                    }
                            }

                            break;

                        case KeyEvent.VK_W:

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE - 25 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if ((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 18 && (int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 10)
                                if ((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 17 && (int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 23)
                                    break;

                            Player.PLAYER_Y_SPEED = -2;
                            Player.isMoving = true;
                            break;
                        case KeyEvent.VK_A:

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE - 1) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 25) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE - 1) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE - 25 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if ((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 17 && (int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 10)
                                if ((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 17 && (int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 23)
                                    break;

                            Player.PLAYER_X_SPEED = -2;
                            Player.isMoving = true;
                            break;
                        case KeyEvent.VK_S:

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE - 25 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if ((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 17 && (int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 9)
                                if ((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 17 && (int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 23)
                                    break;

                            Player.PLAYER_Y_SPEED = 2;
                            Player.isMoving = true;
                            break;
                        case KeyEvent.VK_D:

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE + 1 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 25) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE + 1 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE - 25 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if ((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 17 && (int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 10)
                                if ((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 16 && (int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 21)
                                    break;

                            Player.PLAYER_X_SPEED = 2;
                            Player.isMoving = true;
                            break;

                        case KeyEvent.VK_Q:
                            System.out.print((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT + " ");
                            System.out.println((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH);
                            break;


                        case KeyEvent.VK_ESCAPE:

                            Connection c = null;
                            try {
                                Class.forName("org.sqlite.JDBC");
                                c = DriverManager.getConnection("jdbc:sqlite:database.db");
                                c.setAutoCommit(false);
                                Statement stmt = c.createStatement();

                                String sql = "UPDATE SAVEDGAMES set GameLevel = " + currentLevel + " where ID=" + currentSave + ";";
                                stmt.executeUpdate(sql);
                                c.commit();

                                sql = "UPDATE SAVEDGAMES set PlayerXCoord = " + Player.PLAYER_X_COORDINATE + " where ID=" + currentSave + ";";
                                stmt.executeUpdate(sql);
                                c.commit();

                                sql = "UPDATE SAVEDGAMES set PlayerYCoord = " + Player.PLAYER_Y_COORDINATE + " where ID=" + currentSave + ";";
                                stmt.executeUpdate(sql);
                                c.commit();

                                ResultSet rs = stmt.executeQuery("SELECT * FROM SAVEDGAMES;");
                                for(int i = 1; i < currentSave; ++i)
                                    rs.next();

                                long threshold = System.currentTimeMillis();


                                long time = ((System.currentTimeMillis() - currentTime) / 1000);// + rs.getInt("GameTimeSec");

                                sql = "UPDATE SAVEDGAMES set GameTimeSec = " + (int)(time + rs.getInt("GameTimeSec")) + " where ID=" + currentSave + ";";
                                stmt.executeUpdate(sql);
                                c.commit();



                                rs.close();
                                stmt.close();
                                c.close();

                                System.out.println("Saved game");
                                gameState = GameState.Menu;
                            } catch ( Exception ex ) {
                                System.err.println( e.getClass().getName() + ": " + ex.getMessage() );
                                System.exit(0);
                            }

                            break;
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_W:
                            Player.PLAYER_Y_SPEED = 0;
                            Player.isMoving = false;
                            break;
                        case KeyEvent.VK_A:
                            Player.PLAYER_X_SPEED = 0;
                            Player.isMoving = false;
                            break;
                        case KeyEvent.VK_S:
                            Player.PLAYER_Y_SPEED = 0;
                            Player.isMoving = false;
                            break;
                        case KeyEvent.VK_D:
                            Player.PLAYER_X_SPEED = 0;
                            Player.isMoving = false;
                            break;
                    }
                }
            });

        } else if (gameState == GameState.SecondLevel) {

            Player.Update();

            wnd.GetCanvas().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_SPACE:
                            Player.Attack(g, (int)Player.PLAYER_START_X_COORDINATE, (int)Player.PLAYER_START_Y_COORDINATE);

                            for(int i = 0; i < redGhosts.size(); ++i) {
                                if ((int) redGhosts.get(i).GHOST_X_COORDINATE - (int) (Player.PLAYER_X_COORDINATE) < -80 || (int) redGhosts.get(i).GHOST_X_COORDINATE - (int) (Player.PLAYER_X_COORDINATE) < 80)
                                    if ((int) redGhosts.get(i).GHOST_Y_COORDINATE - (int) (Player.PLAYER_Y_COORDINATE) < -80 || (int) redGhosts.get(i).GHOST_Y_COORDINATE - (int) (Player.PLAYER_Y_COORDINATE) < 80)
                                    {
                                        redGhosts.remove(i);
                                    }
                            }

                            break;

                        case KeyEvent.VK_W:

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE - 25 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if ((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 18 && (int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 10)
                                if ((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 17 && (int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 23)
                                    break;

                            Player.PLAYER_Y_SPEED = -2;
                            Player.isMoving = true;
                            break;
                        case KeyEvent.VK_A:

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE - 1) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 25) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE - 1) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE - 25 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if ((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 17 && (int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 10)
                                if ((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 17 && (int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 23)
                                    break;

                            Player.PLAYER_X_SPEED = -2;
                            Player.isMoving = true;
                            break;
                        case KeyEvent.VK_S:

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE - 25 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if ((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 17 && (int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 9)
                                if ((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 17 && (int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 23)
                                    break;

                            Player.PLAYER_Y_SPEED = 2;
                            Player.isMoving = true;
                            break;
                        case KeyEvent.VK_D:

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE + 1 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 25) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE + 1 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE - 25 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if ((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 17 && (int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 10)
                                if ((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 16 && (int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 21)
                                    break;

                            Player.PLAYER_X_SPEED = 2;
                            Player.isMoving = true;
                            break;

                        case KeyEvent.VK_Q:
                            System.out.print((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT + " ");
                            System.out.println((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH);
                            break;


                        case KeyEvent.VK_ESCAPE:

                            Connection c = null;
                            try {
                                Class.forName("org.sqlite.JDBC");
                                c = DriverManager.getConnection("jdbc:sqlite:database.db");
                                c.setAutoCommit(false);
                                Statement stmt = c.createStatement();

                                String sql = "UPDATE SAVEDGAMES set GameLevel = " + currentLevel + " where ID=" + currentSave + ";";
                                stmt.executeUpdate(sql);
                                c.commit();

                                sql = "UPDATE SAVEDGAMES set PlayerXCoord = " + Player.PLAYER_X_COORDINATE + " where ID=" + currentSave + ";";
                                stmt.executeUpdate(sql);
                                c.commit();

                                sql = "UPDATE SAVEDGAMES set PlayerYCoord = " + Player.PLAYER_Y_COORDINATE + " where ID=" + currentSave + ";";
                                stmt.executeUpdate(sql);
                                c.commit();

                                ResultSet rs = stmt.executeQuery("SELECT * FROM SAVEDGAMES;");
                                for(int i = 1; i < currentSave; ++i)
                                    rs.next();

                                long threshold = System.currentTimeMillis();


                                long time = ((System.currentTimeMillis() - currentTime) / 1000);// + rs.getInt("GameTimeSec");

                                sql = "UPDATE SAVEDGAMES set GameTimeSec = " + (int)(time + rs.getInt("GameTimeSec")) + " where ID=" + currentSave + ";";
                                stmt.executeUpdate(sql);
                                c.commit();



                                rs.close();
                                stmt.close();
                                c.close();

                                System.out.println("Saved game");
                                gameState = GameState.Menu;
                            } catch ( Exception ex ) {
                                System.err.println( e.getClass().getName() + ": " + ex.getMessage() );
                                System.exit(0);
                            }

                            break;
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_W:
                            Player.PLAYER_Y_SPEED = 0;
                            Player.isMoving = false;
                            break;
                        case KeyEvent.VK_A:
                            Player.PLAYER_X_SPEED = 0;
                            Player.isMoving = false;
                            break;
                        case KeyEvent.VK_S:
                            Player.PLAYER_Y_SPEED = 0;
                            Player.isMoving = false;
                            break;
                        case KeyEvent.VK_D:
                            Player.PLAYER_X_SPEED = 0;
                            Player.isMoving = false;
                            break;
                    }
                }
            });

        } else if (gameState == GameState.ThirdLevel) {

            Player.Update();

            wnd.GetCanvas().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_SPACE:
                            Player.Attack(g, (int)Player.PLAYER_START_X_COORDINATE, (int)Player.PLAYER_START_Y_COORDINATE);

                            for(int i = 0; i < purpleGhosts.size(); ++i) {
                                if ((int) purpleGhosts.get(i).GHOST_X_COORDINATE - (int) (Player.PLAYER_X_COORDINATE) < -80 || (int) purpleGhosts.get(i).GHOST_X_COORDINATE - (int) (Player.PLAYER_X_COORDINATE) < 80)
                                    if ((int) purpleGhosts.get(i).GHOST_Y_COORDINATE - (int) (Player.PLAYER_Y_COORDINATE) < -80 || (int) purpleGhosts.get(i).GHOST_Y_COORDINATE - (int) (Player.PLAYER_Y_COORDINATE) < 80)
                                    {
                                        purpleGhosts.remove(i);
                                    }
                            }

                            break;

                        case KeyEvent.VK_W:

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE - 25 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if ((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 18 && (int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 10)
                                if ((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 17 && (int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 23)
                                    break;

                            Player.PLAYER_Y_SPEED = -2;
                            Player.isMoving = true;
                            break;
                        case KeyEvent.VK_A:

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE - 1) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 25) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE - 1) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE - 25 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if ((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 17 && (int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 10)
                                if ((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 17 && (int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 23)
                                    break;

                            Player.PLAYER_X_SPEED = -2;
                            Player.isMoving = true;
                            break;
                        case KeyEvent.VK_S:

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE - 25 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 1 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if ((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 17 && (int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 9)
                                if ((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 17 && (int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 23)
                                    break;

                            Player.PLAYER_Y_SPEED = 2;
                            Player.isMoving = true;
                            break;
                        case KeyEvent.VK_D:

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE + 1 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE + 25) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if (Tile.tiles[Dungeon[(int) (Player.PLAYER_X_COORDINATE + 1 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int) (Player.PLAYER_Y_COORDINATE - 25 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
                                break;
                            }

                            if ((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 17 && (int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 10)
                                if ((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 16 && (int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 21)
                                    break;

                            Player.PLAYER_X_SPEED = 2;
                            Player.isMoving = true;
                            break;

                        case KeyEvent.VK_Q:
                            System.out.print((int) Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT + " ");
                            System.out.println((int) Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH);
                            break;


                        case KeyEvent.VK_ESCAPE:

                            Connection c = null;
                            try {
                                Class.forName("org.sqlite.JDBC");
                                c = DriverManager.getConnection("jdbc:sqlite:database.db");
                                c.setAutoCommit(false);
                                Statement stmt = c.createStatement();

                                String sql = "UPDATE SAVEDGAMES set GameLevel = " + currentLevel + " where ID=" + currentSave + ";";
                                stmt.executeUpdate(sql);
                                c.commit();

                                sql = "UPDATE SAVEDGAMES set PlayerXCoord = " + Player.PLAYER_X_COORDINATE + " where ID=" + currentSave + ";";
                                stmt.executeUpdate(sql);
                                c.commit();

                                sql = "UPDATE SAVEDGAMES set PlayerYCoord = " + Player.PLAYER_Y_COORDINATE + " where ID=" + currentSave + ";";
                                stmt.executeUpdate(sql);
                                c.commit();

                                ResultSet rs = stmt.executeQuery("SELECT * FROM SAVEDGAMES;");
                                for(int i = 1; i < currentSave; ++i)
                                    rs.next();

                                long threshold = System.currentTimeMillis();


                                long time = ((System.currentTimeMillis() - currentTime) / 1000);// + rs.getInt("GameTimeSec");

                                sql = "UPDATE SAVEDGAMES set GameTimeSec = " + (int)(time + rs.getInt("GameTimeSec")) + " where ID=" + currentSave + ";";
                                stmt.executeUpdate(sql);
                                c.commit();



                                rs.close();
                                stmt.close();
                                c.close();

                                System.out.println("Saved game");
                                gameState = GameState.Menu;
                            } catch ( Exception ex ) {
                                System.err.println( e.getClass().getName() + ": " + ex.getMessage() );
                                System.exit(0);
                            }

                            break;
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_W:
                            Player.PLAYER_Y_SPEED = 0;
                            Player.isMoving = false;
                            break;
                        case KeyEvent.VK_A:
                            Player.PLAYER_X_SPEED = 0;
                            Player.isMoving = false;
                            break;
                        case KeyEvent.VK_S:
                            Player.PLAYER_Y_SPEED = 0;
                            Player.isMoving = false;
                            break;
                        case KeyEvent.VK_D:
                            Player.PLAYER_X_SPEED = 0;
                            Player.isMoving = false;
                            break;
                    }
                }
            });

        }
    }

    private void MainMap()
    {
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        wnd.GetCanvas().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    Player.Attack(g, (int)Player.PLAYER_START_X_COORDINATE, (int)Player.PLAYER_START_Y_COORDINATE);                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        //Opearatii de desenare
        for(int i = 0; i < MapSize; ++i)
            for(int j = 0; j < MapSize; ++j)
            {
                switch (Map[i][j]) {
                    case 0:
                        Tile.tiles[0].Draw(g, i * Tile.TILE_WIDTH + (int)(Player.PLAYER_START_X_COORDINATE - Player.PLAYER_X_COORDINATE), j * Tile.TILE_HEIGHT + (int)(Player.PLAYER_START_Y_COORDINATE - Player.PLAYER_Y_COORDINATE));
                        break;

                    case 1:
                        Tile.tiles[1].Draw(g, i * Tile.TILE_WIDTH + (int)(Player.PLAYER_START_X_COORDINATE - Player.PLAYER_X_COORDINATE), j * Tile.TILE_HEIGHT + (int)(Player.PLAYER_START_Y_COORDINATE - Player.PLAYER_Y_COORDINATE));
                        break;

                    case 2:
                        Tile.tiles[2].Draw(g, i * Tile.TILE_WIDTH + (int)(Player.PLAYER_START_X_COORDINATE - Player.PLAYER_X_COORDINATE), j * Tile.TILE_HEIGHT + (int)(Player.PLAYER_START_Y_COORDINATE - Player.PLAYER_Y_COORDINATE));
                        break;

                    case 3:
                        Tile.tiles[3].Draw(g, i * Tile.TILE_WIDTH + (int)(Player.PLAYER_START_X_COORDINATE - Player.PLAYER_X_COORDINATE), j * Tile.TILE_HEIGHT + (int)(Player.PLAYER_START_Y_COORDINATE - Player.PLAYER_Y_COORDINATE));
                        break;

                    case 4:
                        Tile.tiles[4].Draw(g, i * Tile.TILE_WIDTH + (int)(Player.PLAYER_START_X_COORDINATE - Player.PLAYER_X_COORDINATE), j * Tile.TILE_HEIGHT + (int)(Player.PLAYER_START_Y_COORDINATE - Player.PLAYER_Y_COORDINATE));
                        break;
                }

            }


        Structure.house.Draw(g, House.X_OFFSET + (int)(Player.PLAYER_START_X_COORDINATE - Player.PLAYER_X_COORDINATE), House.Y_OFFSET + (int)(Player.PLAYER_START_Y_COORDINATE - Player.PLAYER_Y_COORDINATE), House.width, House.height);

        //Coliziuni dinamice cu obiecte

        //Up
        if (Tile.tiles[Map[(int)(Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 1) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_Y_SPEED = 0;
        }

        if (Tile.tiles[Map[(int)(Player.PLAYER_X_COORDINATE - 25 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 1) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_Y_SPEED = 0;
        }

        //Left
        if (Tile.tiles[Map[(int)(Player.PLAYER_X_COORDINATE - 1) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 25) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_X_SPEED = 0;
        }

        if (Tile.tiles[Map[(int)(Player.PLAYER_X_COORDINATE - 1) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE - 25 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_X_SPEED = 0;
        }

        //Down
        if (Tile.tiles[Map[(int)(Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 1 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_Y_SPEED = 0;
        }

        if (Tile.tiles[Map[(int)(Player.PLAYER_X_COORDINATE - 25 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 1 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_Y_SPEED = 0;
        }

        //Right
        if (Tile.tiles[Map[(int)(Player.PLAYER_X_COORDINATE + 1 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 25) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_X_SPEED = 0;
        }

        if (Tile.tiles[Map[(int)(Player.PLAYER_X_COORDINATE + 1 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE - 25 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_X_SPEED = 0;
        }

        //Coliziuni dinamice cu structuri

        //Up
        if((int)Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 18 && (int)Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 10)
            if((int)Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 17 && (int)Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 23)
                Player.PLAYER_Y_SPEED = 0;

        //Down
        if((int)Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 17 && (int)Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 9)
            if((int)Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 17 && (int)Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 23)
                Player.PLAYER_Y_SPEED = 0;

        //Left
        if((int)Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 17 && (int)Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 10)
            if((int)Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 17 && (int)Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 23)
                Player.PLAYER_X_SPEED = 0;

        //Right
        if((int)Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT < 17 && (int)Player.PLAYER_Y_COORDINATE / Tile.TILE_HEIGHT > 10)
            if((int)Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH > 16 && (int)Player.PLAYER_X_COORDINATE / Tile.TILE_WIDTH < 21)
                Player.PLAYER_X_SPEED = 0;


        Player.Draw(g, (int)Player.PLAYER_START_X_COORDINATE, (int)Player.PLAYER_START_Y_COORDINATE);


        //end opearatii de desenare

        bs.show();
        g.dispose();
    }

    private void Menu()
    {
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        g.drawImage(Assets.menu, 0, 0, wnd.GetWndWidth(), wnd.GetWndHeight(), null);

        g.drawRect(325, 200, 150, 50);
        g.setColor(Color.white);
        g.fillRect(325, 200, 150, 50);

        g.drawRect(325, 300, 150, 50);
        g.fillRect(325, 300, 150, 50);

        g.drawRect(325, 400, 150, 50);
        g.fillRect(325, 400, 150, 50);

        g.setColor(Color.black);
        Font font = new Font("Courier", Font.BOLD, 25);
        g.setFont(font);

        g.drawString( "New Game", 337, 232);

        g.drawString("Load Game", 337, 332);

        g.drawString("Exit", 375, 432);

        bs.show();
        g.dispose();
    }

    private void NewGame()
    {
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        g.drawImage(Assets.menu, 0, 0, wnd.GetWndWidth(), wnd.GetWndHeight(), null);

        g.drawRect(325, 150, 150, 50);
        g.setColor(Color.white);
        g.fillRect(325, 150, 150, 50);

        g.drawRect(325, 250, 150, 50);
        g.fillRect(325, 250, 150, 50);

        g.drawRect(325, 350, 150, 50);
        g.fillRect(325, 350, 150, 50);

        g.drawRect(325, 450, 150, 50);
        g.fillRect(325, 450, 150, 50);

        g.setColor(Color.black);
        Font font = new Font("Courier", Font.BOLD, 25);
        g.setFont(font);

        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
            c.setAutoCommit(false);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SAVEDGAMES;");

            rs.next();
            int valid = rs.getInt("Valid");

            if(valid == 0)
                g.drawString( "Empty Save", 337, 172);
            else
                g.drawString( "Save 1", 337, 172);

            rs.next();
            valid = rs.getInt("Valid");

            if(valid == 0)
                g.drawString("Empty Save", 337, 272);
            else
                g.drawString("Save 2", 337, 272);

            rs.next();
            valid = rs.getInt("Valid");

            if(valid == 0)
                g.drawString("Empty Save", 337, 372);
            else
                g.drawString("Save 3", 337, 372);

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        g.drawString("Back", 337, 472);

        bs.show();
        g.dispose();
    }

    private void LoadGame()
    {
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        g.drawImage(Assets.menu, 0, 0, wnd.GetWndWidth(), wnd.GetWndHeight(), null);

        g.drawRect(325, 150, 150, 50);
        g.setColor(Color.white);
        g.fillRect(325, 150, 150, 50);

        g.drawRect(325, 250, 150, 50);
        g.fillRect(325, 250, 150, 50);

        g.drawRect(325, 350, 150, 50);
        g.fillRect(325, 350, 150, 50);

        g.drawRect(325, 450, 150, 50);
        g.fillRect(325, 450, 150, 50);

        g.setColor(Color.black);
        Font font = new Font("Courier", Font.BOLD, 25);
        g.setFont(font);

        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
            c.setAutoCommit(false);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SAVEDGAMES;");

            rs.next();
            int valid = rs.getInt("Valid");

            if(valid == 0)
                g.drawString( "Empty Save", 337, 172);
            else
                g.drawString( "Save 1", 337, 172);

            rs.next();
            valid = rs.getInt("Valid");

            if(valid == 0)
                g.drawString("Empty Save", 337, 272);
            else
                g.drawString("Save 2", 337, 272);

            rs.next();
            valid = rs.getInt("Valid");

            if(valid == 0)
                g.drawString("Empty Save", 337, 372);
            else
                g.drawString("Save 3", 337, 372);

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        g.drawString("Back", 337, 472);

        bs.show();
        g.dispose();
    }

    private void FirstLevel()
    {
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        //Opearatii de desenare
        for(int i = 0; i < DungeonSize; ++i)
            for(int j = 0; j < DungeonSize; ++j)
            {
                switch (Dungeon[i][j]) {
                    case 0:
                        Tile.tiles[6].Draw(g, i * Tile.TILE_WIDTH + (int)(Player.PLAYER_START_X_COORDINATE - Player.PLAYER_X_COORDINATE), j * Tile.TILE_HEIGHT + (int)(Player.PLAYER_START_Y_COORDINATE - Player.PLAYER_Y_COORDINATE));
                        break;

                    case 1:
                        Tile.tiles[5].Draw(g, i * Tile.TILE_WIDTH + (int)(Player.PLAYER_START_X_COORDINATE - Player.PLAYER_X_COORDINATE), j * Tile.TILE_HEIGHT + (int)(Player.PLAYER_START_Y_COORDINATE - Player.PLAYER_Y_COORDINATE));
                        break;

                }

            }


        if(created == 0)
            for (int i = 0; i < 5; i++) {
                ghosts.add( new Ghost((int)Player.PLAYER_X_COORDINATE + i*30 + 80, (int)Player.PLAYER_Y_COORDINATE + i*80 + 50));
                created = 1;
            }

        for (int i = 0; i < ghosts.size(); i++) {
            ghosts.get(i).Update();
            ghosts.get(i).Draw(g);
        }



        //Coliziuni dinamice cu obiecte

        //Up
        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 1) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_Y_SPEED = 0;
        }

        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE - 25 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 1) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_Y_SPEED = 0;
        }

        //Left
        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE - 1) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 25) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_X_SPEED = 0;
        }

        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE - 1) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE - 25 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_X_SPEED = 0;
        }

        //Down
        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 1 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_Y_SPEED = 0;
        }

        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE - 25 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 1 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_Y_SPEED = 0;
        }

        //Right
        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE + 1 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 25) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_X_SPEED = 0;
        }

        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE + 1 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE - 25 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_X_SPEED = 0;
        }

        //Coliziuni cu inamici
        for(int i = 0; i < ghosts.size(); ++i)
        {
            if((int)ghosts.get(i).GHOST_X_COORDINATE - (int)(Player.PLAYER_X_COORDINATE) < -40 || (int)ghosts.get(i).GHOST_X_COORDINATE - (int)(Player.PLAYER_X_COORDINATE) < 40)
                if((int)ghosts.get(i).GHOST_Y_COORDINATE - (int)(Player.PLAYER_Y_COORDINATE) < -40 || (int)ghosts.get(i).GHOST_Y_COORDINATE - (int)(Player.PLAYER_Y_COORDINATE) < 40)
                {
                    Connection c = null;
                    try {
                        Class.forName("org.sqlite.JDBC");
                        c = DriverManager.getConnection("jdbc:sqlite:database.db");
                        c.setAutoCommit(false);
                        Statement stmt = c.createStatement();

                        String sql = "UPDATE SAVEDGAMES set Valid = 0 where ID=" + currentSave;
                        stmt.executeUpdate(sql);
                        c.commit();

                        stmt.close();
                        c.close();

                        gameState = GameState.Menu;
                    } catch ( Exception e ) {
                        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                        System.exit(0);
                    }
                }
        }

        if(ghosts.isEmpty())
        {

            Connection c = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:database.db");
                c.setAutoCommit(false);
                Statement stmt = c.createStatement();

                String sql = "UPDATE SAVEDGAMES set GameLevel = " + currentLevel + " where ID=" + currentSave + ";";
                stmt.executeUpdate(sql);
                c.commit();

                sql = "UPDATE SAVEDGAMES set PlayerXCoord = " + Player.PLAYER_X_COORDINATE + " where ID=" + currentSave + ";";
                stmt.executeUpdate(sql);
                c.commit();

                sql = "UPDATE SAVEDGAMES set PlayerYCoord = " + Player.PLAYER_Y_COORDINATE + " where ID=" + currentSave + ";";
                stmt.executeUpdate(sql);
                c.commit();

                ResultSet rs = stmt.executeQuery("SELECT * FROM SAVEDGAMES;");
                for(int i = 1; i < currentSave; ++i)
                    rs.next();

                long time = ((System.currentTimeMillis() - currentTime) / 1000);// + rs.getInt("GameTimeSec");

                sql = "UPDATE SAVEDGAMES set GameTimeSec = " + (int)(time + rs.getInt("GameTimeSec")) + " where ID=" + currentSave + ";";
                stmt.executeUpdate(sql);
                c.commit();


                rs.close();
                stmt.close();
                c.close();

                System.out.println("Saved game");
                gameState = GameState.Menu;
            } catch ( Exception ex ) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
                System.exit(0);
            }

            gameState = GameState.SecondLevel;
            //ystem.out.println(gameState);
        }


        Player.Draw(g, (int)Player.PLAYER_START_X_COORDINATE, (int)Player.PLAYER_START_Y_COORDINATE);


        //end opearatii de desenare

        bs.show();
        g.dispose();
    }

    private void SecondLevel()
    {
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        //Opearatii de desenare
        for(int i = 0; i < DungeonSize; ++i)
            for(int j = 0; j < DungeonSize; ++j)
            {
                switch (Dungeon[i][j]) {
                    case 0:
                        Tile.tiles[8].Draw(g, i * Tile.TILE_WIDTH + (int)(Player.PLAYER_START_X_COORDINATE - Player.PLAYER_X_COORDINATE), j * Tile.TILE_HEIGHT + (int)(Player.PLAYER_START_Y_COORDINATE - Player.PLAYER_Y_COORDINATE));
                        break;

                    case 1:
                        Tile.tiles[7].Draw(g, i * Tile.TILE_WIDTH + (int)(Player.PLAYER_START_X_COORDINATE - Player.PLAYER_X_COORDINATE), j * Tile.TILE_HEIGHT + (int)(Player.PLAYER_START_Y_COORDINATE - Player.PLAYER_Y_COORDINATE));
                        break;

                }

            }


        if(redCreated == 0)
            for (int i = 0; i < 10; i++) {
                redGhosts.add( new redGhost((int)Player.PLAYER_X_COORDINATE + i*100 + 150, (int)Player.PLAYER_Y_COORDINATE + i*100 + 250));
                redCreated = 1;
            }

        for (int i = 0; i < redGhosts.size(); i++) {
            redGhosts.get(i).Update();
            redGhosts.get(i).Draw(g);
        }



        //Coliziuni dinamice cu obiecte

        //Up
        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 1) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_Y_SPEED = 0;
        }

        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE - 25 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 1) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_Y_SPEED = 0;
        }

        //Left
        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE - 1) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 25) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_X_SPEED = 0;
        }

        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE - 1) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE - 25 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_X_SPEED = 0;
        }

        //Down
        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 1 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_Y_SPEED = 0;
        }

        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE - 25 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 1 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_Y_SPEED = 0;
        }

        //Right
        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE + 1 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 25) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_X_SPEED = 0;
        }

        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE + 1 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE - 25 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_X_SPEED = 0;
        }

        //Coliziuni cu inamici
        for(int i = 0; i < redGhosts.size(); ++i)
        {
            if((int)redGhosts.get(i).GHOST_X_COORDINATE - (int)(Player.PLAYER_X_COORDINATE) < -40 || (int)redGhosts.get(i).GHOST_X_COORDINATE - (int)(Player.PLAYER_X_COORDINATE) < 40)
                if((int)redGhosts.get(i).GHOST_Y_COORDINATE - (int)(Player.PLAYER_Y_COORDINATE) < -40 || (int)redGhosts.get(i).GHOST_Y_COORDINATE - (int)(Player.PLAYER_Y_COORDINATE) < 40)
                {
                    Connection c = null;
                    try {
                        Class.forName("org.sqlite.JDBC");
                        c = DriverManager.getConnection("jdbc:sqlite:database.db");
                        c.setAutoCommit(false);
                        Statement stmt = c.createStatement();

                        String sql = "UPDATE SAVEDGAMES set Valid = 0 where ID=" + currentSave;
                        stmt.executeUpdate(sql);
                        c.commit();

                        stmt.close();
                        c.close();

                        gameState = GameState.Menu;
                    } catch ( Exception e ) {
                        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                        System.exit(0);
                    }
                }
        }

        if(redGhosts.isEmpty())
        {

            Connection c = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:database.db");
                c.setAutoCommit(false);
                Statement stmt = c.createStatement();

                String sql = "UPDATE SAVEDGAMES set GameLevel = " + currentLevel + " where ID=" + currentSave + ";";
                stmt.executeUpdate(sql);
                c.commit();

                sql = "UPDATE SAVEDGAMES set PlayerXCoord = " + Player.PLAYER_X_COORDINATE + " where ID=" + currentSave + ";";
                stmt.executeUpdate(sql);
                c.commit();

                sql = "UPDATE SAVEDGAMES set PlayerYCoord = " + Player.PLAYER_Y_COORDINATE + " where ID=" + currentSave + ";";
                stmt.executeUpdate(sql);
                c.commit();

                ResultSet rs = stmt.executeQuery("SELECT * FROM SAVEDGAMES;");
                for(int i = 1; i < currentSave; ++i)
                    rs.next();

                long time = ((System.currentTimeMillis() - currentTime) / 1000);// + rs.getInt("GameTimeSec");

                sql = "UPDATE SAVEDGAMES set GameTimeSec = " + (int)(time + rs.getInt("GameTimeSec")) + " where ID=" + currentSave + ";";
                stmt.executeUpdate(sql);
                c.commit();


                rs.close();
                stmt.close();
                c.close();

                System.out.println("Saved game");
                gameState = GameState.Menu;
            } catch ( Exception ex ) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
                System.exit(0);
            }

            gameState = GameState.ThirdLevel;
            System.out.println(gameState);
        }


        Player.Draw(g, (int)Player.PLAYER_START_X_COORDINATE, (int)Player.PLAYER_START_Y_COORDINATE);


        //end opearatii de desenare

        bs.show();
        g.dispose();
    }

    private void ThirdLevel()
    {

        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        //Opearatii de desenare
        for(int i = 0; i < DungeonSize; ++i)
            for(int j = 0; j < DungeonSize; ++j)
            {
                switch (Dungeon[i][j]) {
                    case 0:
                        Tile.tiles[10].Draw(g, i * Tile.TILE_WIDTH + (int)(Player.PLAYER_START_X_COORDINATE - Player.PLAYER_X_COORDINATE), j * Tile.TILE_HEIGHT + (int)(Player.PLAYER_START_Y_COORDINATE - Player.PLAYER_Y_COORDINATE));
                        break;

                    case 1:
                        Tile.tiles[9].Draw(g, i * Tile.TILE_WIDTH + (int)(Player.PLAYER_START_X_COORDINATE - Player.PLAYER_X_COORDINATE), j * Tile.TILE_HEIGHT + (int)(Player.PLAYER_START_Y_COORDINATE - Player.PLAYER_Y_COORDINATE));
                        break;

                }

            }


        if(purpleCreated == 0)
            for (int i = 0; i < 20; i++) {
                purpleGhosts.add( new purpleGhost((int)Player.PLAYER_X_COORDINATE + i*100 + 150, (int)Player.PLAYER_Y_COORDINATE + i*100 + 250));
                purpleCreated = 1;
            }

        for (int i = 0; i < purpleGhosts.size(); i++) {
            purpleGhosts.get(i).Update();
            purpleGhosts.get(i).Draw(g);
        }



        //Coliziuni dinamice cu obiecte

        //Up
        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 1) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_Y_SPEED = 0;
        }

        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE - 25 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 1) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_Y_SPEED = 0;
        }

        //Left
        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE - 1) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 25) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_X_SPEED = 0;
        }

        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE - 1) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE - 25 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_X_SPEED = 0;
        }

        //Down
        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE + 25) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 1 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_Y_SPEED = 0;
        }

        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE - 25 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 1 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_Y_SPEED = 0;
        }

        //Right
        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE + 1 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE + 25) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_X_SPEED = 0;
        }

        if (Tile.tiles[Dungeon[(int)(Player.PLAYER_X_COORDINATE + 1 + Player.PLAYER_WIDTH) / Tile.TILE_WIDTH][(int)(Player.PLAYER_Y_COORDINATE - 25 + Player.PLAYER_HEIGHT) / Tile.TILE_HEIGHT]].IsSolid()) {
            Player.PLAYER_X_SPEED = 0;
        }

        //Coliziuni cu inamici
        for(int i = 0; i < purpleGhosts.size(); ++i)
        {
            if((int)purpleGhosts.get(i).GHOST_X_COORDINATE - (int)(Player.PLAYER_X_COORDINATE) < -40 || (int)purpleGhosts.get(i).GHOST_X_COORDINATE - (int)(Player.PLAYER_X_COORDINATE) < 40)
                if((int)purpleGhosts.get(i).GHOST_Y_COORDINATE - (int)(Player.PLAYER_Y_COORDINATE) < -40 || (int)purpleGhosts.get(i).GHOST_Y_COORDINATE - (int)(Player.PLAYER_Y_COORDINATE) < 40)
                {
                    Connection c = null;
                    try {
                        Class.forName("org.sqlite.JDBC");
                        c = DriverManager.getConnection("jdbc:sqlite:database.db");
                        c.setAutoCommit(false);
                        Statement stmt = c.createStatement();

                        String sql = "UPDATE SAVEDGAMES set Valid = 0 where ID=" + currentSave;
                        stmt.executeUpdate(sql);
                        c.commit();

                        stmt.close();
                        c.close();

                        gameState = GameState.Menu;
                    } catch ( Exception e ) {
                        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                        System.exit(0);
                    }
                }
        }

        if(purpleGhosts.isEmpty())
        {

            Connection c = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:database.db");
                c.setAutoCommit(false);
                Statement stmt = c.createStatement();

                ResultSet rs = c.createStatement().executeQuery("SELECT * FROM SAVEDGAMES;");
                for(int i = 1; i < currentSave; ++i)
                    rs.next();

                long time = ((System.currentTimeMillis() - currentTime) / 1000);// + rs.getInt("GameTimeSec");

                String sql = "UPDATE SAVEDGAMES set GameTimeSec = " + (int)(time + rs.getInt("GameTimeSec")) + " where ID=" + currentSave + ";";
                stmt.executeUpdate(sql);
                c.commit();

                sql = "UPDATE SAVEDGAMES set Valid = 0" +  " where ID=" + currentSave + ";";
                stmt.executeUpdate(sql);
                c.commit();


                System.out.println("Time: " + rs.getInt("GameTimeSec"));

                rs.close();
                stmt.close();
                c.close();


                System.out.println("Win");
                gameState = GameState.Menu;
            } catch ( Exception ex ) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
                System.exit(0);
            }

        }


        Player.Draw(g, (int)Player.PLAYER_START_X_COORDINATE, (int)Player.PLAYER_START_Y_COORDINATE);


        //end opearatii de desenare

        bs.show();
        g.dispose();

    }

    private void Draw()
    {
        bs = wnd.GetCanvas().getBufferStrategy();

        if(bs == null)
        {
            try
            {
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        if(gameState == GameState.Menu) {
            Menu();
        } else if(gameState == GameState.MainMap){
            MainMap();
        } else if(gameState == GameState.NewGame){
            NewGame();
        } else if(gameState == GameState.LoadGame){
            LoadGame();
        } else if(gameState == GameState.FirstLevel){
            FirstLevel();
        } else if(gameState == GameState.SecondLevel){
            SecondLevel();
        } else if(gameState == GameState.ThirdLevel){
            ThirdLevel();
        }


    }

}

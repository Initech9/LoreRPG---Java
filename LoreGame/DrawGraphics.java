import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

// public LoadRooms loadRooms = new LoadRooms();

public class DrawGraphics extends JFrame {

    private static final int ROOM_SIZE = 800; // Room size in pixels
    private static final int CELL_SIZE = 40; // Cell size in pixels
    private static final int PLAYER_SIZE = 20; // Player size in pixels

    public Player player = new Player();

    private char[][] grid;

    private int numRows;
    private int numCols;

    private final int INVISIBLE = 0;

    private JPanel gamePanel;
    private int playerX;
    private int playerY;
    private int playY = playerX;
    private int playX = playerY;

    // MidiPlayer playerMusic = new MidiPlayer("opera.mid");
    // Thread thread = new Thread(playerMusic);
    
    

    public Room room[][] = new Room[3][3];

    public BufferedImage currentNorthWall;
    public BufferedImage currentEastWall;
    public BufferedImage currentSouthWall;
    public BufferedImage currentWestWall;

    LoadRooms loadRooms = new LoadRooms(room);



    ImageIcon icon;
    Image image;

    public DrawGraphics() {

        // Set player starting position
        playerX = 0;
        playerY = 0;

        this.numRows = 3;
        this.numCols = 3;

        grid = new char[numRows][numCols];
        // ImageIcon icon = new ImageIcon("00N.png");
        // Image image = icon.getImage();
        // room[0][0].northWall = image;

        // Set up the game panel
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Draw the dungeon walls based on player position
                // int roomNumber = playerX * 3 + playerY + 1;
                // ImageIcon icon = new ImageIcon("room" + roomNumber + ".png");
                // Image image = icon.getImage();
                // g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

                // int roomNumber = playerX * 3 + playerY + 1;
                icon = new ImageIcon("sky.png");
                image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

                if (player.direction == 'e') {
                    currentNorthWall = room[playerY][playerX].getImage(room[playerY][playerX].westWall);
                    currentEastWall = room[playerY][playerX].getAngledImage(room[playerY][playerX].southWall);
                    currentSouthWall = room[playerY][playerX].getImage(0);
                    currentWestWall = room[playerY][playerX].getAngledImage(room[playerY][playerX].northWall);
                }



                drawWallsFacingNorth(g);

                // if (player.direction == 'n')
                //     drawWallsFacingNorth(g);
                // else if (player.direction == 'e')
                //     drawWallsFacingNorth(g);
                //     else if (player.direction == 's')
                //     drawWallsFacingNorth(g);
                //     else if (player.direction == 'w')
                //     drawWallsFacingNorth(g);    


               


                // Draw the GUI
                image = room[playerY][playerX].gui;
                g.drawImage(image, 0, 0, 800, 900, this);

            }
        };
        gamePanel.setPreferredSize(new Dimension(ROOM_SIZE, ROOM_SIZE));
        add(gamePanel);

        setFocusable(true);
        requestFocus();

        // COLLAPSED KEY LISTENER

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (player.direction == 'n') {
                    switch (key) {
                        case KeyEvent.VK_W:
                            movePlayer(-1, 0);
                            break;
                        case KeyEvent.VK_A:
                            movePlayer(0, -1);
                            break;
                        case KeyEvent.VK_S:
                            movePlayer(1, 0);
                            break;
                        case KeyEvent.VK_D:
                            movePlayer(0, 1);
                            break;
                        case KeyEvent.VK_E:
                            movePlayer(0, 0);
                            player.direction = 'e';
                            break;
                        case KeyEvent.VK_Q:
                            movePlayer(0, 0);
                            player.direction = 'w';
                            break;

                    }
                } else if (player.direction == 'e') {
                    switch (key) {
                        case KeyEvent.VK_W:
                            movePlayer(0, 1);
                            break;
                        case KeyEvent.VK_A:
                            movePlayer(-1, 0);
                            break;
                        case KeyEvent.VK_S:
                            movePlayer(0, -1);
                            break;
                        case KeyEvent.VK_D:
                            movePlayer(1, 0);
                            break;
                        case KeyEvent.VK_E:
                            movePlayer(0, 0);
                            player.direction = 's';
                            break;
                        case KeyEvent.VK_Q:
                            movePlayer(0, 0);
                            player.direction = 'n';
                            break;

                    }
                } else if (player.direction == 's') {
                    switch (key) {

                        case KeyEvent.VK_W:
                            movePlayer(1, 0);
                            break;
                        case KeyEvent.VK_A:
                            movePlayer(0, 1);
                            break;
                        case KeyEvent.VK_S:
                            movePlayer(-1, 0);
                            break;
                        case KeyEvent.VK_D:
                            movePlayer(0, -1);
                            break;
                        case KeyEvent.VK_E:
                            movePlayer(0, 0);
                            player.direction = 'w';
                            break;
                        case KeyEvent.VK_Q:
                            movePlayer(0, 0);
                            player.direction = 'e';
                            break;

                    }

                } else if (player.direction == 'w') {
                    switch (key) {

                        case KeyEvent.VK_W:
                            movePlayer(0, -1);
                            break;
                        case KeyEvent.VK_A:
                            movePlayer(1, 0);
                            break;
                        case KeyEvent.VK_S:
                            movePlayer(0, 1);
                            break;
                        case KeyEvent.VK_D:
                            movePlayer(-1, 0);
                            break;
                        case KeyEvent.VK_E:
                            movePlayer(0, 0);
                            player.direction = 'n';
                            break;
                        case KeyEvent.VK_Q:
                            movePlayer(0, 0);
                            player.direction = 's';
                            break;

                    }

                }

            }

        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;

        // Check if the new position is within the room bounds
        if (newX >= 0 && newX < 3 && newY >= 0 && newY < 3) {
            playerX = newX;
            playerY = newY;

            playY = playerX; //added to swap to make readable z4
            playX = playerY;
            gamePanel.repaint();
            System.out.println("X: " + playerY + "Y: " + playerX);
            printGrid();
        }
    }

    private void printGrid() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (i == playerX && j == playerY) {
                    System.out.print(grid[i][j] + "P");
                } else
                    System.out.print(grid[i][j] + ".");
            }
            System.out.println();
        }
        System.out.println();
    }

 

    int XzeroOffset = 0;
    int XoneOffset = 1;
    int XtwoOffset = 2;
    int XleftOffset = -1;
    int XrightOffset = 1;

    int YleftOffset = -1;
    int YrightOffset = 1;
    int YzeroOffset = 0;
    int YoneOffset = -1;
    int YtwoOffset = -2;
    
    public void drawWallsFacingNorth(Graphics g) {

        icon = new ImageIcon("sky.png");
        image = icon.getImage();



        if (player.direction == 'n'){

XzeroOffset = 0;
XoneOffset = 0;
XtwoOffset = 0;

XleftOffset = -1;
XrightOffset = 1;
XzeroOffset = 0;
YoneOffset = -1;
YtwoOffset = -2;

YleftOffset = 0;
YrightOffset = 0;

        }
        
    else if (player.direction == 'e'){

        XzeroOffset = 0;
        XoneOffset = 1;
        XtwoOffset = 2;
        
        XleftOffset = 0;
        XrightOffset = 0;
       

        YleftOffset = -1;
        YrightOffset = 1;
        YoneOffset = 0;
        YtwoOffset = 0;

    }
        
        else if (player.direction == 's'){
            XzeroOffset = 0;
            XoneOffset = 0;
            XtwoOffset = 0;
            
            XleftOffset = 1;
            XrightOffset = -1;
            XzeroOffset = 0;
            YoneOffset = 1;
            YtwoOffset = 2;

            YleftOffset = 0;
            YrightOffset = 0;

        }
        
        else if (player.direction == 'w'){

            XzeroOffset = 0;
            XoneOffset = -1;
            XtwoOffset = -2;
            
            XleftOffset = 0;
            XrightOffset = 0;
           
    
            YleftOffset = 1;
            YrightOffset = -1;
            YoneOffset = 0;
            YtwoOffset = 0;
    
        }
         


        // Draw the wall that exists 2 grid spaces in front of you
        if (room[playX][playY].getRelativeNorthWall(player.direction) == INVISIBLE
                && room[playX+XoneOffset][playY + YoneOffset].getRelativeNorthWall(player.direction) == INVISIBLE) {
            image = room[playX+XtwoOffset][playY + YtwoOffset].getImage(room[playX+XtwoOffset][playY  + YtwoOffset].getRelativeNorthWall(player.direction));


            g.drawImage(image, 270, 230, 100, 100, this);

      
            if(player.direction == 'n' || player.direction == 's'){
            try{
                image = room[playX+XtwoOffset][playY].getImage(room[playX + XleftOffset][playY + YtwoOffset].getRelativeNorthWall(player.direction));
                g.drawImage(image, 150, 230, 100, 100, this);
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("CAUGHT ARRAY OUT OF BOUNDS!!");
            }
            try{
                image = room[playX+XtwoOffset][playY].getImage(room[playX + XrightOffset][playY + YtwoOffset].getRelativeNorthWall(player.direction));
                g.drawImage(image, 400, 230, 100, 100, this);
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("CAUGHT ARRAY OUT OF BOUNDS!!");
            }
        }
        if(player.direction == 'e' || player.direction == 'w'){
            try{
                image = room[playX+XtwoOffset][playY].getImage(room[playX + XtwoOffset][playY + YleftOffset].getRelativeNorthWall(player.direction));
                g.drawImage(image, 150, 230, 100, 100, this);
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("CAUGHT ARRAY OUT OF BOUNDS!!");
            }
            try{
                image = room[playX+XtwoOffset][playY].getImage(room[playX + XtwoOffset][playY + YrightOffset].getRelativeNorthWall(player.direction));
                g.drawImage(image, 400, 230, 100, 100, this);
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("CAUGHT ARRAY OUT OF BOUNDS!!");
            }
        }


            if(player.direction == 'n' ){
            image = room[playX][playY].getAngledImage(room[playX][playY  + YtwoOffset].getRelativeNorthWall('e'));
            g.drawImage(image, 400, 210, -30, 135, this);
            image = room[playX][playY].getAngledImage(room[playX][playY + YtwoOffset].getRelativeNorthWall('w'));
            g.drawImage(image, 230, 200, 45, 150, this);
            }
            else if(player.direction == 's'){           
            image = room[playX][playY].getAngledImage(room[playX][playY  + YtwoOffset].getRelativeNorthWall('w'));
            g.drawImage(image, 400, 210, -30, 135, this);
            image = room[playX][playY].getAngledImage(room[playX][playY + YtwoOffset].getRelativeNorthWall('e'));
            g.drawImage(image, 230, 200, 45, 150, this);
            }
            else if(player.direction == 'e'){           
                image = room[playX][playY].getAngledImage(room[playX  + XtwoOffset][playY].getRelativeNorthWall('s'));
                g.drawImage(image, 400, 210, -30, 135, this);
                image = room[playX][playY].getAngledImage(room[playX  + XtwoOffset][playY].getRelativeNorthWall('n'));
                g.drawImage(image, 230, 200, 45, 150, this);
                }


                    //Draw the floor 2 rooms away:
        image = room[playX+ XtwoOffset][playY ].getFloorImage(room[playX+ XtwoOffset][playY].floor);
        g.drawImage(image, 0, 330, 950, 30, this);

            // //Draw NPC characters
            // if (room[playX][playY + YtwoOffset].character.characterLocationX == playX && room[playX][playY  + YtwoOffset].character.characterLocationY == playY-2) {
            //     image = room[playX][playY  + YoneOffset].character.characterImage ;
            //     g.drawImage(image, 280, 250, 100, 100, this);
            //     }


            for(int i = 0; i < 3; i++){
                //Draw NPC characters
                if (room[playX+XtwoOffset][playY+YtwoOffset].character[i].characterLocationX == playX+XtwoOffset && room[playX+XtwoOffset][playY+YtwoOffset].character[i].characterLocationY == playY+YtwoOffset) {
                    image = room[playX+XtwoOffset][playY+YtwoOffset].character[i].characterImage ;
                    g.drawImage(image, 280, 250, 100, 100, this);
                    }
                }






        }

        // Draw the wall that exists 2 grid spaces in front of you
        if (room[playX][playY].getRelativeNorthWall(player.direction) == INVISIBLE) {
            image = room[playX+XoneOffset][playY  + YoneOffset].getImage(room[playX+XoneOffset][playY + YoneOffset].getRelativeNorthWall(player.direction));
            g.drawImage(image, 250, 200, 150, 150, this);



if(player.direction == 'n' || player.direction == 's'){
            // Get the North West wall diagonal from you and display it. Check that the wall
            // exists in the array[] or you'll get an out of bounds error
            try {
                image = room[playX][playY].getImage(room[playX + XleftOffset][playY + YoneOffset].getRelativeNorthWall(player.direction));
                g.drawImage(image, 100, 200, 150, 150, this);
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("CAUGHT ARRAY OUT OF BOUNDS!!");
            }
            // Get the North East wall diagonal from you and display it.Check that the wall
            // exists in the array[] or you'll get an out of bounds error

            try {
                image = room[playX][playY].getImage(room[playX + XrightOffset][playY + YoneOffset].getRelativeNorthWall(player.direction));
                g.drawImage(image, 420, 200, 150, 150, this);
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("CAUGHT ARRAY OUT OF BOUNDS!!");
            }
        }
        if(player.direction == 'e' || player.direction == 'w'){
        // Get the North West wall diagonal from you and display it. Check that the wall
        // exists in the array[] or you'll get an out of bounds error
        try {
            image = room[playX][playY].getImage(room[playX + XoneOffset][playY + YleftOffset].getRelativeNorthWall(player.direction));
            g.drawImage(image, 100, 200, 150, 150, this);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("CAUGHT ARRAY OUT OF BOUNDS!!");
        }
        // Get the North East wall diagonal from you and display it.Check that the wall
        // exists in the array[] or you'll get an out of bounds error

        try {
            image = room[playX][playY].getImage(room[playX + XoneOffset][playY + YrightOffset].getRelativeNorthWall(player.direction));
            g.drawImage(image, 420, 200, 150, 150, this);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("CAUGHT ARRAY OUT OF BOUNDS!!");
        }
    

    }

        //Draw the floor one room away:
        image = room[playX][playY+YoneOffset].getFloorImage(room[playX][playY + YoneOffset].floor);
        g.drawImage(image, 0, 350, 850, 100, this);


            image = room[playX][playY].getAngledImage(room[playX+XoneOffset][playY + YoneOffset].getRelativeEastWall(player.direction));
            g.drawImage(image, 500, 150, -100, 250, this);
            image = room[playX][playY].getAngledImage(room[playX+XoneOffset][playY + YoneOffset].getRelativeWestWall(player.direction));
            g.drawImage(image, 200, 150, 50, 250, this);




            // //Draw NPC characters
            // if (room[playX][playY + YoneOffset].character.characterLocationX == playX && room[playX][playY + YoneOffset].character.characterLocationY == playY-1) {
            //     image = room[playX][playY  + YoneOffset].character.characterImage ;
            //     g.drawImage(image, 240, 230, 180, 200, this);
            //     }

            for(int i = 0; i < 3; i++){
                //Draw NPC characters one room away

                if (room[playX+XoneOffset][playY+YoneOffset].character[i].characterLocationX == playX+XoneOffset && room[playX+XoneOffset][playY+YoneOffset].character[i].characterLocationY == playY+YoneOffset) {
                    image = room[playX+XoneOffset][playY+YoneOffset].character[i].characterImage ;
                    g.drawImage(image, 270, 220, 150, 180, this);
                    }
                }




        }
        // Draw immediate wall in front of you
        if (room[playX][playY].getRelativeNorthWall(player.direction) > INVISIBLE) {

            image = room[playX][playY].getImage(room[playX][playY].getRelativeNorthWall(player.direction));
            g.drawImage(image, 200, 150, 300, 300, this);



            if (room[playX][playY].getRelativeWestWall(player.direction) > 0) {
                // Draw the adjacent walls

            } else {

                image = room[playX][playY].getImage(room[playX+XleftOffset][playY+YleftOffset].getRelativeNorthWall(player.direction));
                g.drawImage(image, -100, 150, 300, 300, this);

            }

            if (room[playX][playY].getRelativeEastWall(player.direction) > INVISIBLE) {
                // Draw the adjacent walls

            } else {

                image = room[playX][playY].getImage(room[playX + XrightOffset][playY + YrightOffset].getRelativeNorthWall(player.direction));
                g.drawImage(image, 500, 150, 300, 300, this);

            }



        }

                //Draw the immediate floor:
                image = room[playX][playY].getFloorImage(room[playX][playY].floor);
                g.drawImage(image, -30, 410, 700, 200, this);
        // Draw immediate east and west walls

        image = room[playX][playY].getAngledImage(room[playX][playY].getRelativeEastWall(player.direction));
        g.drawImage(image, 600, 0, -100, 550, this);
        image = room[playX][playY].getAngledImage(room[playX][playY].getRelativeWestWall(player.direction));
        g.drawImage(image, 100, 0, 100, 550, this);


        



                    for(int i = 0; i < 3; i++){
                    //Draw NPC characters
                    if (room[playX][playY].character[i].characterLocationX == playX && room[playX][playY].character[i].characterLocationY == playY) {
                        image = room[playX][playY].character[i].characterImage ;
                        g.drawImage(image, 240, 200, 300, 420, this);
                        }
                    }











    }





}

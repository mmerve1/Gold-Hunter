import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel2 extends JPanel implements Runnable {
    public final int originalTileSize = 8;
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 100;
    public final int maxScreenRow = 100;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    int FPS = 20;

    KeyHandle KeyH = new KeyHandle();
    Thread gameThread;
    Player player = new Player(new GamePanel(), KeyH);
    //Engel engel = new Engel(this);
    SabitEngel sabitEngel =new SabitEngel(new GamePanel());
    HareketliEngel hareketEngel =new HareketliEngel(new GamePanel());





    private Image backgroundImage;

    int[][] gridMap = new int[maxScreenRow][maxScreenCol];

    public GamePanel2() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);
        try {

            backgroundImage = ImageIO.read(getClass().getResource("Tiles/grass00.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // initializeGridMap();

    }

    /*
    private void initializeGridMap() {
        // Populate the grid map with random values (1 or 0)
        for (int row = 0; row < maxScreenRow; row++) {
            for (int col = 0; col < maxScreenCol; col++) {
                gridMap[row][col] = Math.random() < 0.1 ? 1 : 0;
            }
        }
    }

     */

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        this.requestFocusInWindow();
    }

    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {

        player.update();

    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, this);

        g2.setColor(Color.BLACK);
        for (int row = 0; row <= maxScreenRow; row++) {
            g2.drawLine(0, row * tileSize, screenWidth, row * tileSize);
        }
        for (int col = 0; col <= maxScreenCol; col++) {
            g2.drawLine(col * tileSize, 0, col * tileSize, screenHeight);
        }




        player.draw(g2);
        sabitEngel.draw(g2);
        hareketEngel.draw(g2);


        g2.dispose();
    }


}
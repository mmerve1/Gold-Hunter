import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    public final int originalTileSize = 8;
    public final int scale = 3;
    public int maxScreenCol = 100; // Başlangıç değeri
    public int maxScreenRow = 100; // Başlangıç değeri
    public final int tileSize = originalTileSize * scale;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    int FPS = 20;

    KeyHandle KeyH = new KeyHandle();
    Thread gameThread;
    Player player = new Player(this, KeyH);
    // Engel engel = new Engel(this);
    SabitEngel sabitEngel = new SabitEngel(this);
    HareketliEngel hareketEngel = new HareketliEngel(this);
    Location location = new Location(getX(), getY());
    Environment environment = new Environment();

    private Image backgroundImage;

    int[][] gridMap = new int[maxScreenRow][maxScreenCol];

    public GamePanel() {
        askForGridSize(); // Kullanıcıdan maxScreenRow ve maxScreenCol değerlerini al
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);
        try {
            // Arka plan resmi yüklemdi
            backgroundImage = ImageIO.read(getClass().getResource("Tiles/grass00.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void askForGridSize() {
        String inputRow = JOptionPane.showInputDialog("Enter the number of rows:");
        String inputCol = JOptionPane.showInputDialog("Enter the number of columns:");
        try {
            maxScreenRow = Integer.parseInt(inputRow);
            maxScreenCol = Integer.parseInt(inputCol);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input, Using default values.");
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        this.requestFocusInWindow();
    }

    // player zaman kısmı
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

        // arkaplan çim
        g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, this);

        g2.setColor(Color.BLACK);
        for (int row = 0; row <= maxScreenRow; row++) {
            g2.drawLine(0, row * tileSize, screenWidth, row * tileSize);
        }
        for (int col = 0; col <= maxScreenCol; col++) {
            g2.drawLine(col * tileSize, 0, col * tileSize, screenHeight);
        }

        environment.draw(g2, player);
        player.draw(g2);
        sabitEngel.draw(g2);
        hareketEngel.draw(g2);

        g2.dispose();
    }
}

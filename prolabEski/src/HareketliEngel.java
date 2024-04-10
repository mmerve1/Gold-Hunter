import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class HareketliEngel extends Engel {
    private ArrayList<Point> birdCoordinates;
    private ArrayList<Integer> birdDirections;
    private ArrayList<Integer> birdPauseTimers;  // Added timer for each bird
    private ArrayList<Point> beeCoordinates;
    private ArrayList<Integer> beeDirections;
    private ArrayList<Integer> beePauseTimers;
    public BufferedImage bird;
    public BufferedImage bee;
    private boolean mapCreated = false;
    private int birdSpeed = 1;
    private int beeSpeed =1;//
    private int birdVerticalDistance = 5;
    private int birdPauseDuration = 5;
    private int beeVerticalDistance = 10;
    private int beePauseDuration = 10;
    public HareketliEngel(GamePanel gp) {
        super(gp);
        birdCoordinates = new ArrayList<>();
        birdDirections = new ArrayList<>();
        birdPauseTimers = new ArrayList<>();
        beeCoordinates = new ArrayList<>();
        beeDirections = new ArrayList<>();
        beePauseTimers = new ArrayList<>();
        initializeCoordinates(); // Kuşun koordinatları
        initializeBeeCoordinates();
        getEngelImage();
    }

    void getEngelImage() {
        try {
            bird = ImageIO.read(getClass().getResourceAsStream("Tiles/bat.png"));
            bee = ImageIO.read(getClass().getResourceAsStream("Tiles/bee2.png"));
        } catch (IOException e) {
            System.out.println("Image loading failed");
        }
    }

    private void initializeBeeCoordinates() {
        // Başlangıçta bee'nin koordinatları
        beeCoordinates.add(new Point(5, 20));
        beeDirections.add(1);
        beePauseTimers.add(0);

        beeCoordinates.add(new Point(5, 30));
        beeDirections.add(1);
        beePauseTimers.add(0);


    }

    private void initializeCoordinates() {
        // Başlangıçta kuşun koordinatları
        birdCoordinates.add(new Point(0, 0));
        birdDirections.add(1);
        birdPauseTimers.add(0);

        birdCoordinates.add(new Point(20, 2));
        birdDirections.add(1);
        birdPauseTimers.add(0);
    }

    private void createMapOnce() {
        if (!mapCreated) {
            mapCreated = true; // Harita olustu
        }
    }

    public void draw(Graphics2D g2) {
        createMapOnce();

        moveAndDrawBirds(g2);
        moveAndDrawBee(g2);
    }

    public void moveAndDrawBirds(Graphics2D g2) {
        for (int i = 0; i < birdCoordinates.size(); i++) {
            Point birdCoord = birdCoordinates.get(i);
            int col = (int) birdCoord.getX();
            int row = (int) birdCoord.getY();
            int x = col * tileSize;
            int y = row * tileSize;


            g2.setColor(Color.RED);
            g2.fillRect(x, y, tileSize*2, tileSize*2);



            // Engeli çiz
            g2.drawImage(bird, x, y, tileSize*2, tileSize*2, null);
        }

        // Hareketli engellerin koordinatları
        moveBirds();
    }


    public void moveAndDrawBee(Graphics2D g2) {
        // Engelleri çiz
        for (int i = 0; i < beeCoordinates.size(); i++) {
            Point beeCoord = beeCoordinates.get(i);
            int col = (int) beeCoord.getX();
            int row = (int) beeCoord.getY();
            int x = col * tileSize;
            int y = row * tileSize;


            g2.setColor(Color.RED);
            g2.fillRect(x, y, tileSize*2, tileSize*2);

            g2.drawImage(bee, x, y, tileSize*2, tileSize*2, null);
        }


        moveBee();
    }

    public void moveBirds() {
        for (int i = 0; i < birdCoordinates.size(); i++) {
            Point birdCoord = birdCoordinates.get(i);
            int direction = birdDirections.get(i);
            int row = (int) birdCoord.getY();
            int pauseTimer = birdPauseTimers.get(i);


            if (pauseTimer > 0) {
                birdPauseTimers.set(i, pauseTimer - 1);
                continue;
            }

            // Kuşun dikey hareket yönü
            row += birdSpeed * direction;

            // Eğer kuş belirli bir mesafeyi aştıysa dönsün
            if (row <= 0 || row >= birdVerticalDistance) {
                birdDirections.set(i, -direction);
                birdPauseTimers.set(i, birdPauseDuration);
            }

            // Yeni koordinatları güncelle
            birdCoord.setLocation(birdCoord.getX(), row);
        }
    }

    public void moveBee() {
        for (int i = 0; i < beeCoordinates.size(); i++) {
            Point beeCoord = beeCoordinates.get(i);
            int direction = beeDirections.get(i);
            int col = (int) beeCoord.getX();
            int pauseTimer = beePauseTimers.get(i);

            if (pauseTimer > 0) {
                beePauseTimers.set(i, pauseTimer - 1);
                continue;
            }

            col += beeSpeed * direction;

            if (col <= 0 || col >= beeVerticalDistance) {
                beeDirections.set(i, -direction);
                beePauseTimers.set(i, beePauseDuration);
            }

            beeCoord.setLocation(col, beeCoord.getY());
        }
    }


}

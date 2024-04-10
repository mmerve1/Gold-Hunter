import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class SabitEngel extends Engel {

    public ArrayList<Point> treeCoordinates;
    public ArrayList<Point> largeTreeCoordinates;
    private ArrayList<Point> wallCoordinates;
    private ArrayList<Point> rockCoordinates;
    private ArrayList<Point> mountainCoordinates;
    private ArrayList<Point> goldenchestCoordinates;
    private ArrayList<Point> emeraldchestCoordinates;
    private ArrayList<Point> copperchestCoordinates;
    private ArrayList<Point> silverchestCoordinates;

    // Koordinatları saklamak için ArrayList
    GamePanel gp;
    BufferedImage tree;
    BufferedImage largeTree;
    BufferedImage wall;
    BufferedImage rock;
    BufferedImage mountain;

    BufferedImage goldenchest;
    BufferedImage emeraldchest;
    BufferedImage copperchest;
    BufferedImage silverchest;
    private boolean mapCreated = false;

    public SabitEngel(GamePanel gp) {
        super(gp);
        this.gp = gp;
        treeCoordinates = new ArrayList<>();
        largeTreeCoordinates = new ArrayList<>();
        wallCoordinates = new ArrayList<>();
        rockCoordinates = new ArrayList<>();
        mountainCoordinates =new ArrayList<>();
        goldenchestCoordinates = new ArrayList<>();
        emeraldchestCoordinates = new ArrayList<>();
        copperchestCoordinates = new ArrayList<>();
        silverchestCoordinates = new ArrayList<>();
        getEngelImage();
    }

    void getEngelImage() {
        try {
            tree = ImageIO.read(getClass().getResourceAsStream("Tiles/drytree.png"));
            largeTree = ImageIO.read(getClass().getResourceAsStream("Tiles/drytree.png"));
            wall = ImageIO.read(getClass().getResourceAsStream("Tiles/wall.png"));
            rock = ImageIO.read(getClass().getResourceAsStream("Tiles/bigrock.png"));
            mountain = ImageIO.read(getClass().getResourceAsStream("Tiles/dagg22.png"));
            goldenchest = ImageIO.read(getClass().getResourceAsStream("Tiles/golden.png"));
            emeraldchest = ImageIO.read(getClass().getResourceAsStream("Tiles/emerald.png"));
            copperchest = ImageIO.read(getClass().getResourceAsStream("Tiles/copper.png"));
            silverchest = ImageIO.read(getClass().getResourceAsStream("Tiles/silver.png"));
        } catch (IOException e) {
            System.out.println("Image loading failed");
        }
    }

    private void createMapOnce() {
        if (!mapCreated) {
            for (int row = 0; row < maxScreenRow; row++) {
                for (int col = 0; col < maxScreenCol; col++) {
                    int randomChance = (int) (Math.random() * 1000);

                    // Rastgele olasılıkla harita elemanlarını oluştur
                    Point currentPoint = new Point(col, row);

                    if (randomChance < 5) {
                        addToCoordinatesList(currentPoint, treeCoordinates);
                    } else if (randomChance < 10) {
                        addToCoordinatesList(currentPoint, largeTreeCoordinates);
                    } else if (randomChance < 15) {
                        addToCoordinatesList(currentPoint, wallCoordinates);
                    } else if (randomChance < 20) {
                        addToCoordinatesList(currentPoint, rockCoordinates);
                    } else if (randomChance < 25) {
                        addToCoordinatesList(currentPoint, mountainCoordinates);
                    } else if (randomChance < 30) {
                        addToCoordinatesList(currentPoint, goldenchestCoordinates);
                    } else if (randomChance < 35) {
                        addToCoordinatesList(currentPoint, emeraldchestCoordinates);
                    } else if (randomChance < 40) {
                        addToCoordinatesList(currentPoint, copperchestCoordinates);
                    } else if (randomChance < 45) {
                        addToCoordinatesList(currentPoint, silverchestCoordinates);
                    }
                }
            }
            mapCreated = true;
        }
    }

    private void addToCoordinatesList(Point point, ArrayList<Point> coordinates) {
          //engel var mı bak
        if (!isOccupied(point, coordinates)) {
            coordinates.add(point);
        }
    }

          //engel var mı yok mu kontrol
       private boolean isOccupied(Point point, ArrayList<Point> coordinates) {
        for (Point coord : coordinates) {
            if (coord.equals(point)) {
                return true; // Koordinat zaten kullanımda
            }
        }
        return false; // Koordinat boşta
    }

    public void draw(Graphics2D g2) {
        createMapOnce();

        drawElements(g2, treeCoordinates, tree, tileSize, tileSize);

        drawElements(g2, largeTreeCoordinates, largeTree, tileSize * 2, tileSize * 2);

        drawElements(g2, wallCoordinates, wall, tileSize, tileSize);

        drawElements(g2, rockCoordinates, rock, tileSize, tileSize);

        drawElements(g2, mountainCoordinates, mountain, tileSize * 5, tileSize * 5);

        drawElements(g2, goldenchestCoordinates, goldenchest, tileSize, tileSize);

        drawElements(g2, emeraldchestCoordinates, emeraldchest, tileSize, tileSize);

        drawElements(g2, copperchestCoordinates, copperchest, tileSize, tileSize);

        drawElements(g2, silverchestCoordinates, silverchest, tileSize, tileSize);
    }

    private void drawElements(Graphics2D g2, ArrayList<Point> coordinates, BufferedImage image, int width, int height) {
        for (Point coord : coordinates) {
            int col = (int) coord.getX();
            int row = (int) coord.getY();

            int x = col * tileSize;
            int y = row * tileSize;
            g2.drawImage(image, x, y, width, height, null);
        }
    }
}

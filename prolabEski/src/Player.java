import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class Player {
    GamePanel gp;
    KeyHandle KeyH;
    public int x, y;
    // Lokasyon değişkenleri
    public int speed;
    int ID;
    String ad;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int width;  // Genişlik
    public int height; // Yükseklik

    // Constructor
    public Player(GamePanel gp, KeyHandle KeyH) {
        this.gp = gp;
        this.KeyH = KeyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("player/arka2.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("player/arka2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("player/up2_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("player/up2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("player/sol.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("player/sol.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("player/a1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("player/a1.png"));
            // Resmin boyutlarına göre genişlik ve yüksekliği ayarla
            width = up1.getWidth();
            height = up1.getHeight();
        } catch (IOException e) {
            System.out.println("Resim okunurken hata oluştu.");
        }
    }

    public void setDefaultValues() {
        Random rand = new Random();
        x = rand.nextInt(1000);
        y = rand.nextInt(1000);
        speed = 10;
        direction = "down";
    }

    private boolean checkCollision(int newX, int newY, int width, int height) {
        int numPoints = 4;
        int gridSize = gp.tileSize;

        for (int i = 0; i < numPoints; i++) {
            int checkX = newX + i * (width / numPoints);
            int checkY = newY + i * (height / numPoints);

            int newCol = checkX / gridSize;
            int newRow = checkY / gridSize;

            if (newRow >= 0 && newRow < gp.maxScreenRow && newCol >= 0 && newCol < gp.maxScreenCol) {
                if (gp.gridMap[newRow][newCol] == 1) {
                    return true; // Çarpışma var
                }
            }
        }

        return false;
    }

    public void update() {
        int newX = x;
        int newY = y;

        if (KeyH.upPress == true) {
            direction = "up";
            newY -= speed;
        } else if (KeyH.downPress == true) {
            direction = "down";
            newY += speed;
        } else if (KeyH.leftPress == true) {
            direction = "left";
            newX -= speed;
        } else if (KeyH.rightPress == true) {
            direction = "right";
            newX += speed;
        }


        if (!checkCollision(newX, newY, width, height)) {
            x = newX;
            y = newY;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
    }



}

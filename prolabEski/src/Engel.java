import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Engel {

    final int originalTileSize = 8;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 1000;
    public final int maxScreenRow = 1000;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;




    public Engel(GamePanel gp) {
        //this.gp = gp;

    }


    void getEngelImage() {

    }

    private void createMapOnce() {

    }
    public void draw(Graphics2D g2) {

    }
}



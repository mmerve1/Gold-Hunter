import java.awt.*;
import java.awt.geom.*;

public class Light {
    private int centerX;
    private int centerY;
    private int radius;

    public Light(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public void setCenter(int x, int y) {
        centerX = x;
        centerY = y;
    }

    public void draw(Graphics2D g2, int screenWidth, int screenHeight) {
        Ellipse2D mask = new Ellipse2D.Double(centerX - radius, centerY - radius, radius * 2, radius * 2);

        g2.setComposite(AlphaComposite.SrcOver.derive(0.5f)); // Işık şiddeti

        // Işık efekti çizildi
        g2.setColor(Color.LIGHT_GRAY);
        g2.fill(mask);
    }
}

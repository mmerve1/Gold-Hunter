import java.awt.*;

public class Environment {
    private Light light;

    public Environment() {
        // bir ışık nesnesi oluştur
        int lightRadius = 100; // Işık yarıçapı
        light = new Light(0, 0, lightRadius);
    }

    public void draw(Graphics2D g2, Player player) {
        // Işık merkezini oyuncunun merkezi
        light.setCenter(player.x + player.width / 2, player.y + player.height / 2);

        // Işığın çizimi
        light.draw(g2, player.gp.screenWidth, player.gp.screenHeight);


        player.draw(g2);
    }
}

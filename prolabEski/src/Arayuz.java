import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Arayuz extends JFrame {

    private GamePanel gamePanel;
    private GamePanel2 gamePanel2;
    BufferedImage photo;

    public Arayuz() {
        super("Oyun Arayüzü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JButton startButton = new JButton("Oyuna Başla");
        JButton newMapButton = new JButton("Yeni Harita Oluştur");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(startButton);
                remove(newMapButton);
                baslat();
            }
        });

        newMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel2 = new GamePanel2();
                add(gamePanel2);
                pack();
                setLocationRelativeTo(null);
                gamePanel2.startGameThread();
            }
        });


        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setPreferredSize(new Dimension(800, 600));

        //renk
        Color customColor = new Color(100, 150, 200);
        centerPanel.setBackground(customColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 5, 0);
        // Butonlar arasında boşluk bırakmak için

        centerPanel.add(startButton, gbc);

        gbc.gridy++; // Bir sonraki satır
        centerPanel.add(newMapButton, gbc);

        add(centerPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void baslat() {
        gamePanel = new GamePanel();
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);
        gamePanel.startGameThread();
    }

    public static void main(String[] args) {

        new Arayuz();
    }
}

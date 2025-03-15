package ui;
import java.awt.*;
import javax.swing.*;

public class WrongIcon extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.RED);

        // Draw an "X" (cross) symbol
        g2.drawLine(4, 4, 16, 16); // Diagonal line from top-left to bottom-right
        g2.drawLine(16, 4, 4, 16); // Diagonal line from top-right to bottom-left
    }

  
}


package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchButtonExample extends JFrame {

	 public static void main(String[] args) {
	        JFrame frame = new JFrame("Panel Size Issue Fix");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(600, 400);
	        frame.setLayout(new BorderLayout());

	        JPanel panel = new JPanel();
	        panel.setBackground(Color.BLUE);

	        // Set panel size AFTER frame is visible
	        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
	            public void componentResized(java.awt.event.ComponentEvent evt) {
	                panel.setPreferredSize(new Dimension(frame.getContentPane().getWidth() / 2, frame.getContentPane().getHeight() / 2));
	                frame.revalidate(); // Refresh layout
	            }
	        });

	        frame.add(panel, BorderLayout.CENTER);
	        frame.setVisible(true);
	    }

}

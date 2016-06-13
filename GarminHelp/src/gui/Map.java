package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Map {
	private static JFrame mapFrame;
	private static JPanel mapPanel;
	private static JLabel label;
	private static String showMapString = "/map.png";

	public static void loadMap(JFrame MainMenuFrame, Dimension d, Point p) throws IOException{
		mapFrame = MainMenuFrame;
		URL mapURL = URL.class.getResource(showMapString);
		label = new JLabel();
		Image img = ImageIO.read(mapURL);
		mapPanel = new JPanel();
		mapPanel.setBackground(Color.BLACK);
		mapPanel.setSize(d);

		label.setIcon(new ImageIcon(img));
		label.setSize(d);
		label.setLocation(p);
		mapPanel.add(label);
		showMap(mapPanel, mapFrame);
	}

	public static void showMap(JPanel mapPanel, JFrame mapFrame) {
		mapFrame.add(mapPanel);
		mapFrame.repaint();
		handleBack(mapPanel, mapFrame);
	}

	private static void handleBack(JPanel mapPanel, JFrame myFrame) {
		mapPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				Rectangle back = new Rectangle(0, 326, 169, 60);
				if(back.contains(event.getX(), event.getY(), 1, 1)) {
					myFrame.remove(mapPanel);
					myFrame.repaint();
					try {
						MainMenu.run("Garmin Help ReDesign", myFrame, new Dimension(674, 410));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
}

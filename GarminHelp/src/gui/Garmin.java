package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Garmin {

	public static String garminLogo = "/logo.png";


	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Garmin Help ReDesign"); 
		JPanel myPanel = new JPanel();
		try {
			run(myFrame, myPanel);
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void run(JFrame frame, JPanel panel) throws InterruptedException, IOException {

		Dimension d = new Dimension(674, 410);
		URL splash = URL.class.getResource(garminLogo);
		JLabel label = new JLabel();
		Image img = ImageIO.read(splash);

		panel.setBackground(Color.BLACK);
		panel.setSize(d);

		label.setIcon(new ImageIcon(img));
		label.setSize(d);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frame.setSize(d);
		frame.setLocation(200, 150);
		frame.add(label);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setVisible(true);
		Thread.sleep(2000); //Mudar para 5000 na v final
		frame.remove(label);
		frame.remove(panel);
		frame.repaint();
		
		MainMenu.run("Garmin Help ReDesign", frame, d);
	}
}

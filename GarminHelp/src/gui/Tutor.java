package gui;

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

public class Tutor {

	private JFrame tutorFrame;
	private Dimension d;
	private Point p;
	private JLabel label;
	private String tutorMenu = "/tutorHelpMenu.png";


	public Tutor(JFrame myFrame, Dimension d, Point p) throws IOException {
		tutorFrame = myFrame;
		this.d = d;
		this.p = p;
		URL menu = URL.class.getResource(tutorMenu);
		label = new JLabel();
		Image img = ImageIO.read(menu);
		label.setIcon(new ImageIcon(img));
		label.setSize(d);
		label.setLocation(p);
	}
	public void tutorMenu() {
		JPanel menuPanel = new JPanel();
		menuPanel.setSize(d);
		menuPanel.setLocation(p);
		//		menuPanel.setLayout(null);

		menuPanel.add(label);
		menuPanel.setVisible(true);
		tutorFrame.add(menuPanel);
		tutorFrame.repaint();
		handleBack(menuPanel, tutorFrame);
	}

	private void handleBack(JPanel tutorPanel, JFrame myFrame) {
		tutorPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				Rectangle back = new Rectangle(0, 326, 169, 60);
				if(back.contains(event.getX(), event.getY(), 1, 1)) {
					myFrame.remove(tutorPanel);
					try {
						myFrame.remove(tutorPanel);
						myFrame.repaint();
						MainMenu.whereTo(myFrame, tutorPanel);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		});
	}
}

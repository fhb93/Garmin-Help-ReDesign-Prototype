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

public class Interaction {
	
	private static String saveFav = "/tutorSaveFavourite.png";
	private static String saveHome = "/tutorSaveNavHome.png";
	private static String pointsInterest = "/tutorPointsOfInt.png";
	private static String address = "/tutorFindAddress.png";
	private static String stop = "/tutorAddStop.png";
	private static String interact = "/interaction.png";
	private static Dimension d;
	private static Point p;
	public static void user(JFrame frame, Dimension dimension, Point point, int fromWhere) {
		p = point;
		d = dimension;
		switch(fromWhere) {
		case 1:
			try {
				action(frame, d, p, interact);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			break;
		}

	}
	public static void action(JFrame frame, Dimension d, Point p, String url) throws IOException {
		URL urlString = URL.class.getResource(url);
		JLabel label = new JLabel();
		Image img = ImageIO.read(urlString);
		label.setIcon(new ImageIcon(img));
		label.setSize(d);
		label.setLocation(p);
		JFrame myFrame = frame;
		JPanel myPanel = new JPanel();
		myPanel.setSize(d);
		myPanel.setLayout(null);
		myPanel.add(label);
		myFrame.add(myPanel);
		myFrame.repaint();
		handleClicks(myFrame, myPanel, false);

	}


	public static void handleClicks(JFrame frame, JPanel myPanel, boolean fromTutor) {
		myPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);

				Rectangle previousToTutorial = new Rectangle(129, 321, 281, 48);
				Rectangle tutorialMenu = new Rectangle(0, 320, 127, 47);
				Rectangle backToWhereTo = new Rectangle(542, 320, 131, 48);
				
				if(previousToTutorial.contains(event.getX(), event.getY())){
					try {
						frame.remove(myPanel);
						frame.repaint();
						if(Tutor.view[0] == 1) {
							Tutor.view[0] = 0;
							Tutor.showHelp(frame, d, p, saveHome );
						}
						else if(Tutor.view[1] == 1) {
							Tutor.view[1] = 0;
							Tutor.showHelp(frame, d, p, pointsInterest );
						}
						else if(Tutor.view[2] == 1) {
							Tutor.view[2] = 0;
							Tutor.showHelp(frame, d, p, saveFav);
						}
						else if(Tutor.view[3] == 1) {
							Tutor.view[3] = 0;
							Tutor.showHelp(frame, d, p, address);
						}
						else if(Tutor.view[4] == 1) {
							Tutor.view[4] = 0;
							Tutor.showHelp(frame, d, p, stop);
						}
//							Tutor.tutorMenu(frame, new Dimension(674, 389), new Point(1,-1));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				// fazer para voltar para o menu do tutorial
				else if(tutorialMenu.contains(event.getX(), event.getY())){
					try {
						frame.remove(myPanel);
						frame.repaint();
						Tutor.tutorMenu(frame, new Dimension(674, 389), new Point(1,-1));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(backToWhereTo.contains(event.getX(), event.getY())) {
					try {
						frame.remove(myPanel);
						frame.repaint();
						MainMenu.whereTo(frame, myPanel);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		});
	}
}


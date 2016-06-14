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

	private static JFrame tutorFrame;
	private static Dimension d;
	private static Point p;
	private static JLabel label;
	private static String saveFav = "/tutorSaveFavourite.png";
	private static String tutorMenu = "/tutorHelpMenu.png";
	private static String saveHome = "/tutorSaveNavHome.png";
	private static String pointsInterest = "/tutorPointsOfInt.png";
	private static String address = "/tutorFindAddress.png";
	private static String stop = "/tutorAddStop.png";
	public static int view[] = new int[5];
	

	public static void tutorMenu(JFrame myFrame, Dimension d, Point p) throws IOException {
		tutorFrame = myFrame;
		Tutor.d = d;
		Tutor.p = p;
		URL menu = URL.class.getResource(tutorMenu);
		label = new JLabel();
		Image img = ImageIO.read(menu);
		label.setIcon(new ImageIcon(img));
		label.setSize(d);
		label.setLocation(p);
		boolean isTutor = true;
		JPanel menuPanel = new JPanel();
		menuPanel.setSize(d);
		menuPanel.setLocation(p);

		menuPanel.add(label);
		menuPanel.setVisible(true);
		tutorFrame.add(menuPanel);
		tutorFrame.repaint();
		handleClicks(tutorFrame, menuPanel, isTutor);
	}

	public static void handleClicks(JFrame frame, JPanel myPanel, boolean fromTutor) {
		myPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);

				Rectangle boundsAddress = new Rectangle(42, 3,132,142);
				Rectangle boundsBackTutor = new Rectangle(0, 321, 169, 65);
				Rectangle boundsPointsInterest = new Rectangle(275, 87, 137, 187);
				Rectangle boundsFav = new Rectangle(493, 3, 124, 167);
				Rectangle boundsHome = new Rectangle(10, 171, 195, 152);
				Rectangle boundsStop = new Rectangle(498, 172, 120, 147);
				Rectangle giveATry = new Rectangle(129, 321, 281, 48);
				

				if(boundsAddress.contains(event.getX(), event.getY())){
					try {
						handleView("address");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(giveATry.contains(event.getX(), event.getY())) {
					try {

						handleView("interaction");
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
				else if(boundsBackTutor.contains(event.getX(), event.getY())) {
					try {
						frame.remove(myPanel);
						frame.repaint();
						if(fromTutor) {
							MainMenu.whereTo(frame, myPanel);
						}else {
							Tutor.tutorMenu(frame, d, p);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				else if(boundsPointsInterest.contains(event.getX(), event.getY())) {
					try {
						handleView("pointsInterest");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(boundsFav.contains(event.getX(), event.getY())) {
					try {
						handleView("saveFav");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(boundsHome.contains(event.getX(), event.getY())) {
					try {
						handleView("saveHome");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if (boundsStop.contains(event.getX(), event.getY())) {
					try {
						handleView("stop");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			public void handleView(String tip) throws IOException {
				frame.remove(myPanel);
				frame.repaint();
				if(tip.equals("saveHome")) {
					view[0] = 1;
					Tutor.showHelp(frame, new Dimension(671, 389),  new Point(1,10), saveHome);
				}
				else if(tip.equals("pointsInterest")) {
					view[1] = 1;
					Tutor.showHelp(frame, new Dimension(671, 389),  new Point(1,10), pointsInterest);
				}
				else if(tip.equals("saveFav")) {
					view[2] = 1;
					Tutor.showHelp(frame, new Dimension(671, 389),  new Point(1,10), saveFav);
				}
				else if(tip.equals("address")) {
					view[3] = 1;
					Tutor.showHelp(frame, new Dimension(671, 389),  new Point(1,10), address);
				}
				else if(tip.equals("stop")) {
					view[4] = 1;
					Tutor.showHelp(frame, new Dimension(671, 389),  new Point(1,10), stop);
				}
				else if(tip.equals("interaction")) {
					//					Tutor.showHelp(frame, new Dimension(671, 389),  new Point(1,10), interact);
//					frame.removeAll();
					Interaction.user(frame, new Dimension(671, 389),  new Point(1,10), 1); //1 veio do tutor
				}
			}
		});
	}



	public static void showHelp(JFrame frame, Dimension d, Point p, String url) throws IOException {
//		frame.removeAll();
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
		handleBackToWhereTo(myFrame, myPanel);
		handleClicks(myFrame, myPanel, false);

	}
	
	public static void handleBackToWhereTo(JFrame frame, JPanel myPanel) {
		myPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				Rectangle backToWhereTo = new Rectangle(542, 320, 131, 48);
				if(backToWhereTo.contains(event.getX(), event.getY())) {
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

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

public class Supervisor {
	
	private static String SsaveFav = "/supervSaveFavourite.png";
	private static String SsaveHome = "/supervSaveNavHome.png";
	private static String SpointsInterest = "/supervPointsOfInt.png";
	private static String Saddress = "/supervFindAddress.png";
	private static String Sstop = "/supervAddStop.png";
	private static String SsupervMenu = "/supervHelpMenu.png";
	private static JFrame tutorFrame;
	private static Dimension d;
	private static Point p;
	private static JLabel label;
	public static int view[] = new int[5];
	
	public static void supervMenu(JFrame myFrame, Dimension d, Point p) throws IOException {
		tutorFrame = myFrame;
//		tutorFrame.removeAll();
//		tutorFrame.repaint();
		Supervisor.d = d;
		Supervisor.p = p;
		URL menu = URL.class.getResource(SsupervMenu);
		label = new JLabel();
		Image img = ImageIO.read(menu);
		label.setIcon(new ImageIcon(img));
		label.setSize(Supervisor.d);
		label.setLocation(Supervisor.p);
		boolean isSupervisor = true;
		JPanel menuPanel = new JPanel();
		menuPanel.setSize(Supervisor.d);
		menuPanel.setLocation(Supervisor.p);

		menuPanel.add(label);
		menuPanel.setVisible(true);
		tutorFrame.add(menuPanel);
		tutorFrame.repaint();
		handleClicks(tutorFrame, menuPanel, isSupervisor);
	}
	

	public static void handleClicks(JFrame frame, JPanel myPanel, boolean fromSuperv) {
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
				Rectangle itsCorrect = new Rectangle(337, 321, 319, 48);
				Rectangle boundsChangeHelp = new Rectangle(395, 325, 160, 62);

				if(boundsAddress.contains(event.getX(), event.getY())){
					try {
						view[3] = 1;
						handleView("interaction");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(boundsChangeHelp.contains(event.getX(), event.getY())){
					try {
						frame.remove(myPanel);
						frame.repaint();
						Tutor.tutorMenu(frame, d, p);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				else if(boundsBackTutor.contains(event.getX(), event.getY())) {
					try {
						frame.remove(myPanel);
						frame.repaint();
						if(fromSuperv) {
							MainMenu.whereTo(frame, myPanel);
						}else if(!fromSuperv){
							Supervisor.supervMenu(frame, d, p); 
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				else if(boundsPointsInterest.contains(event.getX(), event.getY())) {
					try {
						view[1] = 1;
						handleView("interaction");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(boundsFav.contains(event.getX(), event.getY())) {
					try {
						view[2] = 1;
						handleView("interaction");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(boundsHome.contains(event.getX(), event.getY())) {
					try {
						view[0] = 1;
						handleView("interaction");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if (boundsStop.contains(event.getX(), event.getY())) {
					try {
						view[4] = 1;
						handleView("interaction");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(itsCorrect.contains(event.getX(), event.getY())) {
					frame.remove(myPanel);
					frame.repaint();
					try {
						Supervisor.supervMenu(frame, d, p);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			public void handleView(String tip) throws IOException {
				frame.remove(myPanel);
				frame.repaint();
				if(tip.equals("saveHome")) {
					Tutor.showHelp(frame, new Dimension(671, 389),  new Point(1,10), SsaveHome);
				}
				else if(tip.equals("pointsInterest")) {
					Tutor.showHelp(frame, new Dimension(671, 389),  new Point(1,10), SpointsInterest);
				}
				else if(tip.equals("saveFav")) {
					Tutor.showHelp(frame, new Dimension(671, 389),  new Point(1,10), SsaveFav);
				}
				else if(tip.equals("address")) {
					Tutor.showHelp(frame, new Dimension(671, 389),  new Point(1,10), Saddress);
				}
				else if(tip.equals("stop")) {
					Tutor.showHelp(frame, new Dimension(671, 389),  new Point(1,10), Sstop);
				}
				else if(tip.equals("interaction")) {
					//					Tutor.showHelp(frame, new Dimension(671, 389),  new Point(1,10), interact);
//					frame.removeAll();
					Interaction.user(frame, new Dimension(671, 389),  new Point(1,10), 2); //2 veio supervisor
				}
			}
		});
	}


	public static void showHelp(JFrame frame, Dimension d2, Point p2, String url) throws IOException {
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
		handleBackChange(myFrame, myPanel);
		handleClicks(myFrame, myPanel, false);		
	}
	
	public static void handleBackChange(JFrame frame, JPanel myPanel) {
		myPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				Rectangle backToWhereTo = new Rectangle(542, 320, 131, 48);
				Rectangle boundsChangeHelp = new Rectangle(412, 321, 109, 46);
				Rectangle giveATry = new Rectangle(129, 332, 205, 48);
				
				
				if(backToWhereTo.contains(event.getX(), event.getY())) {
					try {
						frame.remove(myPanel);
						frame.repaint();
						MainMenu.whereTo(frame, myPanel);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(boundsChangeHelp.contains(event.getX(), event.getY())){
					try {
						frame.remove(myPanel);
						frame.repaint();
						Tutor.tutorMenu(frame, d, p);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(giveATry.contains(event.getX(), event.getY())) {
					frame.remove(myPanel);
					frame.repaint();
					Interaction.user(frame, new Dimension(671, 389),  new Point(1,10), 2); //2 veio do supervisor
				}
				
			}
		});
	}
	
}

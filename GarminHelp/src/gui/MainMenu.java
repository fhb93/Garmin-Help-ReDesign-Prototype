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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu {

	private static String menu = "/MainMenu2.png";
	private static String pass = "/pass.jpg";
	private static String menuWhere = "/whereToMenu.png";
	private static Tutor tutor;
	private static JPanel wherePanel;
	private static JPanel menuBackground;

	public static void run(String title, JFrame myFrame, Dimension d) throws IOException {
		myFrame.repaint();
		showMenu(title, myFrame, d);
	}


	public static void showMenu(String title, JFrame myFrame, Dimension d) throws IOException {
		JButton theReturn = new JButton("Return");

		URL menuPicture = URL.class.getResource(menu);
		JLabel label = new JLabel();
		Image img = ImageIO.read(menuPicture);

		URL shall = URL.class.getResource(pass);
		Image shallNotPass = ImageIO.read(shall);

		menuBackground = new JPanel();

		label.setIcon(new ImageIcon(img));
		label.setSize(d);
		menuBackground.setLocation(1,-10);
		menuBackground.setSize(d);
		menuBackground.setLayout(null);

		menuBackground.add(label);

		myFrame.add(menuBackground);
		myFrame.setVisible(true);
		myFrame.repaint();
		handleMapClicks(myFrame, menuBackground);
		handleClicks(theReturn, shallNotPass, menuBackground, myFrame);
	}


	public static void handleClicks(JButton ret, Image img, JPanel myPanel, JFrame myFrame) {
		myPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				Rectangle boundsWhere = new Rectangle(64, 70, 221, 204);
				Rectangle boundsShowMap = new Rectangle(406, 72, 196, 190);
				Rectangle boundsHelp = new Rectangle(295, 279, 85, 94);
				if(boundsWhere.contains(event.getX(), event.getY(), 1, 1))	{
					try {
						this.handleWhereTo();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(boundsShowMap.contains(event.getX(), event.getY(), 1, 1))
					this.handleShowMap();
				if(boundsHelp.contains(event.getX(), event.getY(), 1, 1))
					try {
						this.handleHelp();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
			public void handleWhereTo() throws IOException {
				whereTo(myFrame, myPanel);
			}

			public void handleShowMap() {
				showMap(ret, myPanel);
			}

			public void handleHelp() throws IOException {
				traditional(img, ret, myFrame, myPanel);
			}

		});

	}

	public static void handleMapClicks(JFrame frame, JPanel myPanel) {
		myPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				Rectangle boundsMap = new Rectangle(405, 76, 188, 188);
				if(boundsMap.contains(event.getX(), event.getY(), 1, 1)){
					map();
				}
			}
			public void map() {
				try {
					frame.remove(myPanel);
					frame.repaint();
					Map.loadMap(frame, new Dimension(674, 389), new Point(1,-1));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
	}
	
	
	public static void handleReturn(JButton ret, JFrame myFrame, JPanel tradPanel) {

		ret.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				myFrame.remove(tradPanel);
				try {
					run("Garmin Help ReDesign", myFrame, new Dimension(680, 400));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void whereTo(JFrame myFrame, JPanel myPanel) throws IOException {
		URL menuPicture = URL.class.getResource(menuWhere);
		Image img = ImageIO.read(menuPicture);
		
		JLabel menuWhere = new JLabel();
		menuWhere.setIcon(new ImageIcon(img)); // 674, 390
		menuWhere.setSize(new Dimension(674, 410));
		wherePanel = new JPanel();
		wherePanel.setSize(674, 410); //674, 390
		wherePanel.setLayout(null);
		wherePanel.add(menuWhere);
		wherePanel.setLocation(1,-10);
		wherePanel.setVisible(true);
		myFrame.remove(myPanel);
		myFrame.add(wherePanel);
		myFrame.repaint();
		handleTutorial(wherePanel, myFrame);
	}

	private static void handleTutorial(JPanel wherePanel, JFrame myFrame) {
		wherePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				Rectangle boundsTutor = new Rectangle(50, 180, 109, 128);
				Rectangle back = new Rectangle(0, 326, 169, 60);
				if(boundsTutor.contains(event.getX(), event.getY(), 1, 1))	{
					this.handleTutor();
				}
				if(back.contains(event.getX(), event.getY(), 1, 1)) {
					myFrame.remove(wherePanel);
					myFrame.repaint();
					try {
						run("Garmin Help ReDesign", myFrame, new Dimension(700, 410)); //680, 400
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			public void handleTutor() {
				try {
					tutor = new Tutor(myFrame, new Dimension(674, 410), new Point(1,-5));
				} catch (IOException e) {
					e.printStackTrace();
				}
				myFrame.remove(wherePanel);
				myFrame.repaint();
				tutor.tutorMenu();
				System.out.println("CLASS TUTOR!");
			}
		});
	}


	public static void showMap(JButton ret, JPanel myPanel) {

	}

	public static void traditional(Image img, JButton ret,JFrame myFrame, JPanel myPanel) throws IOException {
		JLabel labelPass = new JLabel();
		labelPass.setIcon(new ImageIcon(img));
		labelPass.setSize(300, 300);
		labelPass.setLocation(190, 0);
		JLabel text = new JLabel("The interaction with this section is not handled by this prototype.");
		text.setSize(500, 20);
		text.setLocation(140, 340);
		JPanel tradPanel = new JPanel();
		tradPanel.setSize(640, 380);
		ret.setLocation(300, 310);
		ret.setSize(70, 30);
		tradPanel.setLayout(null);
		tradPanel.add(labelPass);
		tradPanel.add(text);
		tradPanel.add(ret);
		tradPanel.setVisible(true);
		myFrame.remove(myPanel);
		myFrame.add(tradPanel);
		myFrame.repaint();
		handleReturn(ret, myFrame, tradPanel);
		
	}
}


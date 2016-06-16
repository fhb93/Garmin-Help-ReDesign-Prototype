package gui;

import java.awt.Dimension;
import java.awt.Font;
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

public class Interaction {

	private static String saveFav = "/tutorSaveFavourite.png";
	private static String saveHome = "/tutorSaveNavHome.png";
	private static String pointsInterest = "/tutorPointsOfInt.png";
	private static String address = "/tutorFindAddress.png";
	private static String stop = "/tutorAddStop.png";
	private static String interact = "/interaction.png";
	private static String Sinteract = "/Sinteraction.png";
	private static String SsaveFav = "/supervSaveFavourite.png";
	private static String SsaveHome = "/supervSaveNavHome.png";
	private static String SpointsInterest = "/supervPointsOfInt.png";
	private static String Saddress = "/supervFindAddress.png";
	private static String Sstop = "/supervAddStop.png";
	public static JLabel label = new JLabel("Everything is Correct! New Supervision?");
	private static Dimension d;
	private static Point p;
	public static void user(JFrame frame, Dimension dimension, Point point, int fromWhere) {
		p = point;
		d = dimension;
		switch(fromWhere) {
		case 1:
			try {
				action(frame, d, p, interact, 1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				action(frame, d, p, Sinteract, 2);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}

	}
	public static void action(JFrame frame, Dimension d, Point p, String url, int fromWhere) throws IOException {
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
		handleClicks(myFrame, myPanel, false, fromWhere);

	}


	public static void handleClicks(JFrame frame, JPanel myPanel, boolean fromTutor, int fromWhere) {
		myPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);

				Rectangle previousToTutorial = new Rectangle(129, 321, 281, 48);
				Rectangle toBackMenu = new Rectangle(0, 320, 127, 47);
				Rectangle backToWhereTo = new Rectangle(542, 320, 131, 48);
				Rectangle boundsChangeHelp = new Rectangle(412, 321, 109, 46);
				Rectangle itsCorrect = new Rectangle(337, 321, 319, 48);
				Rectangle previousToInter = new Rectangle(129, 332, 205, 48);


				if(fromWhere == 1) {
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
					else if(toBackMenu.contains(event.getX(), event.getY())){
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
					else if(boundsChangeHelp.contains(event.getX(), event.getY())){
						try {
							frame.remove(myPanel);
							frame.repaint();
							Supervisor.supervMenu(frame, d, p);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
				else if(fromWhere == 2) {
					if(previousToInter.contains(event.getX(), event.getY())){
						try {
							frame.remove(myPanel);
							frame.repaint();
							if(Supervisor.view[0] == 1) {
								Supervisor.view[0] = 0;
								Supervisor.showHelp(frame, d, p, SsaveHome );
							}
							else if(Supervisor.view[1] == 1) {
								Supervisor.view[1] = 0;
								Supervisor.showHelp(frame, d, p, SpointsInterest );
							}
							else if(Supervisor.view[2] == 1) {
								Supervisor.view[2] = 0;
								Supervisor.showHelp(frame, d, p, SsaveFav);
							}
							else if(Supervisor.view[3] == 1) {
								Supervisor.view[3] = 0;
								Supervisor.showHelp(frame, d, p, Saddress);
							}
							else if(Supervisor.view[4] == 1) {
								Supervisor.view[4] = 0;
								Supervisor.showHelp(frame, d, p, Sstop);
							}
							//							Tutor.tutorMenu(frame, new Dimension(674, 389), new Point(1,-1));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					// fazer para voltar para o menu do tutorial
					else if(toBackMenu.contains(event.getX(), event.getY())){
						try {
							frame.remove(myPanel);
							frame.repaint();
							Supervisor.supervMenu(frame, new Dimension(674, 389), new Point(1,-1));
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
					else if(boundsChangeHelp.contains(event.getX(), event.getY())){
						try {
							frame.remove(myPanel);
							frame.repaint();
							Tutor.tutorMenu(frame, d, p);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else if(itsCorrect.contains(event.getX(), event.getY())) {
						frame.remove(myPanel);
						frame.repaint();
						correct(frame, myPanel);
					}
				}
			}
		});
	}
	private static void correct(JFrame frame, JPanel panel) {
		JButton theReturn = new JButton("Return");
		theReturn.setLocation(300, 310);
		theReturn.setSize(70, 30);
		frame.remove(panel);
		label.setSize(500, 100);
		label.setLocation(100, 32);	
		label.setFont(new Font("Times New Roman", Font.BOLD, 26));
		JPanel newPanel = new JPanel();
		newPanel.setSize(d);
		newPanel.setLayout(null);
		newPanel.setLocation(0,-1);
		newPanel.add(label);
		newPanel.add(theReturn);
		frame.add(newPanel);
		frame.repaint();
		handleReturn(theReturn, frame, newPanel);
	}
	
	public static void handleReturn(JButton ret, JFrame myFrame, JPanel panel) {

		ret.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				myFrame.remove(panel);
				myFrame.repaint();
				try {
					Supervisor.supervMenu(myFrame, d, p);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
}


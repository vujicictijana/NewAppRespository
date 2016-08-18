package app.gui.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import app.file.io.Reader;
import app.gui.panels.ConfigurePanel;
import app.gui.panels.PredictPanel;
import app.gui.panels.TestPanel;
import app.gui.panels.TestRandomPanel;
import app.gui.panels.TrainPanel;
import app.gui.panels.TrainRandomPanel;
import app.gui.panels.TrainTemporalPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnTrain;
	private JMenuItem menuTrain;
	private JMenuItem menuTrainRandom;
	private JMenu mnTest;
	private JMenuItem menuTest;
	private JMenuItem menuTestRandom;
	private JMenu mnSettings;
	private JMenuItem menuFile;
	private JMenuItem menuParameters;
	private JFrame frame;
	private JPanel mainPanel;
	private JMenu mnPredict;
	private JMenuItem mnPredictYour;
	private JMenuItem mntmNewMenuItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 906, 800);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getMenuBar_1());
		setLocationRelativeTo(null);
		frame = this;

		if (Reader.checkFile("cfg.txt")) {

		} else {

			ConfigurePanel c = new ConfigurePanel(frame);
			c.setBounds(0, 61, 900, 750);
			if (mainPanel != null) {
				contentPane.remove(mainPanel);
				contentPane.repaint();
				contentPane.revalidate();
			}
			mainPanel = c;
			contentPane.add(mainPanel);
			contentPane.repaint();
			contentPane.revalidate();

			menuBar.setEnabled(false);
			for (int i = 0; i < menuBar.getComponentCount(); i++) {
				menuBar.getComponent(i).setEnabled(false);
			}
		}
	}

	public void enableMenu() {
		menuBar.setEnabled(true);
		for (int i = 0; i < menuBar.getComponentCount(); i++) {
			menuBar.getComponent(i).setEnabled(true);
		}
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.setBackground(Color.WHITE);
			menuBar.setBounds(0, 0, 900, 59);
			menuBar.add(getMnTrain());
			menuBar.add(getMnTest());
			menuBar.add(getMnPredict());
			menuBar.add(getMnSettings());
		}
		return menuBar;
	}

	private JMenu getMnTrain() {
		if (mnTrain == null) {
			mnTrain = new JMenu("Train");
			mnTrain.setIcon(new ImageIcon("images/train.png"));
			mnTrain.setFont(new Font("Segoe UI", Font.BOLD, 17));
			mnTrain.add(getMenuTrain());
			mnTrain.add(new JSeparator());
			mnTrain.add(getMntmNewMenuItem());
			mnTrain.add(new JSeparator());
			mnTrain.add(getMenuTrainRandom());
		}
		return mnTrain;
	}

	private JMenuItem getMenuTrain() {
		if (menuTrain == null) {
			menuTrain = new JMenuItem("Train on networks");
			menuTrain.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			menuTrain.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					TrainPanel t = new TrainPanel(frame);
					t.setBounds(0, 61, 900, 750);
					if (mainPanel != null) {
						contentPane.remove(mainPanel);
						contentPane.repaint();
						contentPane.revalidate();
					}
					mainPanel = t;
					contentPane.add(mainPanel);
					contentPane.repaint();
					contentPane.revalidate();

				}
			});
		}
		return menuTrain;
	}

	private JMenuItem getMenuTrainRandom() {
		if (menuTrainRandom == null) {
			menuTrainRandom = new JMenuItem("Train on random networks");
			menuTrainRandom.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			menuTrainRandom
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(
								java.awt.event.ActionEvent evt) {
							TrainRandomPanel t = new TrainRandomPanel(frame);
							t.setBounds(0, 61, 900, 750);
							if (mainPanel != null) {
								contentPane.remove(mainPanel);
								contentPane.repaint();
								contentPane.revalidate();
							}
							mainPanel = t;
							contentPane.add(mainPanel);
							contentPane.repaint();
							contentPane.revalidate();
						}
					});
		}
		return menuTrainRandom;
	}

	private JMenu getMnTest() {
		if (mnTest == null) {
			mnTest = new JMenu("Test");
			mnTest.setFont(new Font("Segoe UI", Font.BOLD, 17));
			mnTest.setIcon(new ImageIcon("images/test.png"));
			mnTest.add(getMenuTest());
			mnTest.add(new JSeparator());
			mnTest.add(getMenuTestRandom());
		}
		return mnTest;
	}

	private JMenuItem getMenuTest() {
		if (menuTest == null) {
			menuTest = new JMenuItem("Test on networks");
			menuTest.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			menuTest.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					TestPanel t = new TestPanel(frame);
					t.setBounds(0, 61, 900, 500);
					if (mainPanel != null) {
						contentPane.remove(mainPanel);
						contentPane.repaint();
						contentPane.revalidate();
					}
					mainPanel = t;
					contentPane.add(mainPanel);
					contentPane.repaint();
					contentPane.revalidate();
				}
			});
		}
		return menuTest;
	}

	private JMenuItem getMenuTestRandom() {
		if (menuTestRandom == null) {
			menuTestRandom = new JMenuItem("Test on random networks");
			menuTestRandom.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			menuTestRandom
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(
								java.awt.event.ActionEvent evt) {
							TestRandomPanel t = new TestRandomPanel(frame);
							t.setBounds(0, 61, 900, 500);
							if (mainPanel != null) {
								contentPane.remove(mainPanel);
								contentPane.repaint();
								contentPane.revalidate();
							}
							mainPanel = t;
							contentPane.add(mainPanel);
							contentPane.repaint();
							contentPane.revalidate();
						}
					});
		}
		return menuTestRandom;
	}

	private JMenu getMnSettings() {
		if (mnSettings == null) {
			mnSettings = new JMenu("Settings");
			mnSettings.setFont(new Font("Segoe UI", Font.BOLD, 17));
			mnSettings.setIcon(new ImageIcon("images/settings.png"));
			mnSettings.add(getMenuParameters());
			mnSettings.add(new JSeparator());
			mnSettings.add(getMenuFile());
			mnSettings.add(new JSeparator());

		}
		return mnSettings;
	}

	private JMenuItem getMenuFile() {
		if (menuFile == null) {
			menuFile = new JMenuItem("File system");
			menuFile.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		}
		return menuFile;
	}

	private JMenuItem getMenuParameters() {
		if (menuParameters == null) {
			menuParameters = new JMenuItem("Configuration");
			menuParameters.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			menuParameters
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(
								java.awt.event.ActionEvent evt) {
							ConfigurePanel c = new ConfigurePanel(frame);
							c.setBounds(0, 61, 900, 750);
							if (mainPanel != null) {
								contentPane.remove(mainPanel);
								contentPane.repaint();
								contentPane.revalidate();
							}
							mainPanel = c;
							contentPane.add(mainPanel);
							contentPane.repaint();
							contentPane.revalidate();
						}
					});
		}
		return menuParameters;
	}
	private JMenu getMnPredict() {
		if (mnPredict == null) {
			mnPredict = new JMenu("Predict");
			mnPredict.setFont(new Font("Segoe UI", Font.BOLD, 17));
			mnPredict.setIcon(new ImageIcon("images/predict.png"));
			mnPredict.add(getMnPredictYour());
		}
		return mnPredict;
	}
	private JMenuItem getMnPredictYour() {
		if (mnPredictYour == null) {
			mnPredictYour = new JMenuItem("Predict using existing model");
			mnPredictYour.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			mnPredictYour.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PredictPanel t = new PredictPanel(frame);
					t.setBounds(0, 61, 900, 500);
					if (mainPanel != null) {
						contentPane.remove(mainPanel);
						contentPane.repaint();
						contentPane.revalidate();
					}
					mainPanel = t;
					contentPane.add(mainPanel);
					contentPane.repaint();
					contentPane.revalidate();
				}
			});
		}
		return mnPredictYour;
	}
	private JMenuItem getMntmNewMenuItem() {
		if (mntmNewMenuItem == null) {
			mntmNewMenuItem = new JMenuItem("Train on temporal networks");
			mntmNewMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					TrainTemporalPanel t = new TrainTemporalPanel(frame);
					t.setBounds(0, 61, 900, 750);
					if (mainPanel != null) {
						contentPane.remove(mainPanel);
						contentPane.repaint();
						contentPane.revalidate();
					}
					mainPanel = t;
					contentPane.add(mainPanel);
					contentPane.repaint();
					contentPane.revalidate();
				}
			});
			mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		}
		return mntmNewMenuItem;
	}
}

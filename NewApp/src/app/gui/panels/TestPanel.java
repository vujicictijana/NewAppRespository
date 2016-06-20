package app.gui.panels;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.border.LineBorder;

import java.awt.SystemColor;

import javax.swing.UIManager;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;

import app.algorithms.asymmetric.CalculationsAsymmetric;
import app.algorithms.asymmetric.GradientDescentAsymmetric;
import app.data.generators.ArrayGenerator;
import app.data.generators.GraphGenerator;
import app.file.io.Reader;
import app.file.io.Writer;
import app.gui.frames.ProgressBar;
import app.gui.style.Style;
import app.gui.threads.TestMyModelForGUI;
import app.gui.threads.TestWithRandomForGUI;

import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JTable;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JCheckBox;

public class TestPanel extends JPanel {
	private JButton btnTrain;
	private JPanel panel;
	private JFrame mainFrame;
	private JPanel panelForTable;
	private JTextField txtMatrixFile;
	private JLabel label;
	private JButton button;
	private JButton button_1;
	private JLabel label_1;
	private JTextField txtModelName;
	private JLabel label_2;
	private JTextField txtRFile;
	private JButton button_2;
	private JButton button_3;
	private JLabel label_3;
	private JTextField txtYFile;
	private JButton button_4;
	private JButton button_5;
	private JLabel label_4;
	private JTextField txtNoOfNodes;
	private JFileChooser fc;
	/**
	 * Create the panel.
	 */
	public TestPanel(JFrame mainFrame) {
		setBackground(UIManager.getColor("Button.background"));
		setLayout(null);
		add(getBtnTrain());
		panel = this;
		this.mainFrame = mainFrame;
		add(getPanelForTable());
		add(getTxtMatrixFile());
		add(getLabel_1());
		add(getButton());
		add(getButton_1());
		add(getLabel_1_1());
		add(getTxtModelName());
		add(getLabel_2());
		add(getTxtRFile());
		add(getButton_2());
		add(getButton_3());
		add(getLabel_3());
		add(getTxtYFile());
		add(getButton_4());
		add(getButton_5());
		add(getLabel_4());
		add(getTxtNoOfNodes());
		fc = new JFileChooser();
		panel = this;
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"TEXT FILES", "txt", "text");
		fc.setFileFilter(filter);
	}

	private JButton getBtnTrain() {
		if (btnTrain == null) {
			btnTrain = new JButton("TEST");
			btnTrain.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String message = validateData();
					if (message != null) {
						JOptionPane.showMessageDialog(mainFrame, message,
								"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						int noOfNodes = Integer.parseInt(txtNoOfNodes.getText());

						String dataPath = "MyModels/" + txtModelName.getText() + "/results";
						
						double[][] s = Reader.readGraph(txtMatrixFile.getText(), noOfNodes);
						double[] r = Reader.readArray(txtRFile.getText(), noOfNodes);
						double[] y = Reader.readArray(txtYFile.getText(), noOfNodes);
						
						TestMyModelForGUI test = new TestMyModelForGUI(
								mainFrame, panelForTable, dataPath, s, r,
								y);
						test.start();
					}
				}
			});

			Style.buttonStyle(btnTrain);
			btnTrain.setBounds(307, 230, 112, 45);
		}
		return btnTrain;
	}

	public void chooseFile(JTextField txt) {
		int returnVal = fc.showOpenDialog(panel);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			txt.setText(fc.getSelectedFile().getPath());
		}
	}


	public String validateData() {
		if (txtMatrixFile.getText().equals("")) {
			return "Choose matrix file.";
		}
		if (txtRFile.getText().equals("")) {
			return "Choose R file.";
		}
		if (txtYFile.getText().equals("")) {
			return "Choose Y file.";
		}
		try {
			Integer.parseInt(txtNoOfNodes.getText());
		} catch (NumberFormatException e) {
			return "No. of nodes should be integer.";
		}
		if (txtModelName.getText().equals("")) {
			return "Insert model name.";
		}
		if (!Writer.checkFolder("MyModels/" + txtModelName.getText())) {
			return "Model with name " + txtModelName.getText()
					+ " does not exist.";
		}
		return null;
	}

	public boolean checkModel(String path) {
		return Writer.checkFolder(path);
	}

	private JPanel getPanelForTable() {
		if (panelForTable == null) {
			panelForTable = new JPanel();
			panelForTable.setBounds(49, 301, 850, 143);
			panelForTable.setLayout(null);
		}
		return panelForTable;
	}

	private JTextField getTxtMatrixFile() {
		if (txtMatrixFile == null) {
			txtMatrixFile = new JTextField();
			txtMatrixFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtMatrixFile.setColumns(10);
			txtMatrixFile.setBounds(155, 22, 315, 30);
		}
		return txtMatrixFile;
	}

	private JLabel getLabel_1() {
		if (label == null) {
			label = new JLabel("S matrix file:");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("Segoe UI", Font.BOLD, 15));
			label.setBounds(39, 22, 100, 30);
		}
		return label;
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("Browse");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					chooseFile(txtMatrixFile);
				}
			});
			button.setBounds(487, 22, 100, 30);
		}
		return button;
	}

	private JButton getButton_1() {
		if (button_1 == null) {
			button_1 = new JButton("");
			button_1.setBounds(606, 22, 30, 30);
		}
		return button_1;
	}

	private JLabel getLabel_1_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Model name:");
			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			label_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
			label_1.setBounds(39, 143, 100, 30);
		}
		return label_1;
	}

	private JTextField getTxtModelName() {
		if (txtModelName == null) {
			txtModelName = new JTextField();
			txtModelName.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtModelName.setColumns(10);
			txtModelName.setBounds(155, 143, 315, 30);
		}
		return txtModelName;
	}

	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("R array file:");
			label_2.setHorizontalAlignment(SwingConstants.RIGHT);
			label_2.setFont(new Font("Segoe UI", Font.BOLD, 15));
			label_2.setBounds(39, 62, 100, 30);
		}
		return label_2;
	}

	private JTextField getTxtRFile() {
		if (txtRFile == null) {
			txtRFile = new JTextField();
			txtRFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtRFile.setColumns(10);
			txtRFile.setBounds(155, 62, 315, 30);
		}
		return txtRFile;
	}

	private JButton getButton_2() {
		if (button_2 == null) {
			button_2 = new JButton("Browse");
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					chooseFile(txtRFile);
				}
			});
			button_2.setBounds(487, 62, 100, 30);
		}
		return button_2;
	}

	private JButton getButton_3() {
		if (button_3 == null) {
			button_3 = new JButton("");
			button_3.setBounds(606, 62, 30, 30);
		}
		return button_3;
	}

	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Y array file:");
			label_3.setHorizontalAlignment(SwingConstants.RIGHT);
			label_3.setFont(new Font("Segoe UI", Font.BOLD, 15));
			label_3.setBounds(39, 102, 100, 30);
		}
		return label_3;
	}

	private JTextField getTxtYFile() {
		if (txtYFile == null) {
			txtYFile = new JTextField();
			txtYFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtYFile.setColumns(10);
			txtYFile.setBounds(155, 102, 315, 30);
		}
		return txtYFile;
	}

	private JButton getButton_4() {
		if (button_4 == null) {
			button_4 = new JButton("Browse");
			button_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					chooseFile(txtYFile);
				}
			});
			button_4.setBounds(487, 102, 100, 30);
		}
		return button_4;
	}

	private JButton getButton_5() {
		if (button_5 == null) {
			button_5 = new JButton("");
			button_5.setBounds(606, 102, 30, 30);
		}
		return button_5;
	}

	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("No. of nodes:");
			label_4.setHorizontalAlignment(SwingConstants.RIGHT);
			label_4.setFont(new Font("Segoe UI", Font.BOLD, 15));
			label_4.setBounds(39, 185, 100, 30);
		}
		return label_4;
	}

	private JTextField getTxtNoOfNodes() {
		if (txtNoOfNodes == null) {
			txtNoOfNodes = new JTextField();
			txtNoOfNodes.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtNoOfNodes.setColumns(10);
			txtNoOfNodes.setBounds(155, 184, 91, 30);
		}
		return txtNoOfNodes;
	}
}
package app.gui.threads;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import app.algorithms.matlab.UmGCRF;
import app.file.io.Reader;
import app.gui.frames.ProgressBar;

public class UmGCRFTrainMyModelForGUI extends Thread {
	private ProgressBar frame;
	private JFrame mainFrame;

	private double s[][];
	private double r[];
	private double y[];
	private String modelFolder;
	private String time;
	private String matlabPath;
	private long proxyTime;

	public UmGCRFTrainMyModelForGUI(String matlabPath, String modelFolder,
			ProgressBar frame, JFrame mainFrame, double[][] s, double[] r,
			double[] y, long proxyTime) {
		super();
		this.frame = frame;
		this.mainFrame = mainFrame;
		this.s = s;
		this.r = r;
		this.y = y;
		this.modelFolder = modelFolder;
		this.matlabPath = matlabPath;
		this.proxyTime = proxyTime;
		time = "Time in seconds: ";
	}

	public void run() {
		if (Reader.checkFile(matlabPath) == false) {
			JOptionPane
					.showMessageDialog(
							mainFrame,
							"Path to MATLAB.exe is not good. Please change path in Settings->Configuration",
							"Results", JOptionPane.ERROR_MESSAGE);
			frame.setVisible(false);
			return;
		}
		mainFrame.setEnabled(false);
		frame.setTitle("Please wait - UmGCRF is in progress ");
		long start = System.currentTimeMillis();

		String message = UmGCRF.train(matlabPath, s, y, r, frame, modelFolder,
				proxyTime);

		long elapsedTime = System.currentTimeMillis() - start;
		time += Math.round(elapsedTime / 1000);
		if (message.contains("R^2")) {
			message += "\n" + time;
			JOptionPane.showMessageDialog(mainFrame, message, "Results",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(mainFrame, message, "Results",
					JOptionPane.ERROR_MESSAGE);
		}
		mainFrame.setEnabled(true);
		frame.setVisible(false);
	}

}

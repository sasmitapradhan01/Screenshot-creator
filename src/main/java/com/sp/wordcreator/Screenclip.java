package com.sp.wordcreator;

import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

import com.sp.wordcreator.support.CopyReqFiles;

/**
 * @author Sasmita
 *
 */
class Screenclip extends Frame {
	 /**
	 * 
	 */
	static String  input;
	private static final long serialVersionUID = 1L;
	private static JOptionPane pane;
	private static JDialog dialog;
	private static Timer timer;
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");
	Screenclip() {
		setSize(1, 1);// frame size 300 width and 300 height
		setLayout(null);// no layout manager
		setVisible(true);// now frame will be visible, by default not visible
		setVisible(false);
	}
	
	/**
	 * @param name
	 * @throws AWTException
	 * @throws IOException
	 */
	public void takeScreenShot(String name) throws AWTException, IOException
	{
		try
		{
		File theDir = new File(CopyReqFiles.screenshots);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			 theDir.mkdir();
		}
		Robot robot = new Robot();
		Calendar now = Calendar.getInstance();
        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        String fname=formatter.format(now.getTime())+".jpg";
        ImageIO.write(screenShot, "JPG", new File(theDir.toString()+"\\"+fname));
        new WriteContentintoDoc().writeDoc("", fname,input);
		}catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Message: " + "", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	/**
	 * @param args
	 * @throws AWTException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@SuppressWarnings("deprecation")
	public static void main(String args[]) throws AWTException, IOException, InterruptedException {
		Screenclip f =new Screenclip();

		JTextArea textArea = new JTextArea(6, 25);
		JScrollPane scrollPane = new JScrollPane(textArea);
		JOptionPane.showMessageDialog(scrollPane, scrollPane);
		System.out.println(textArea.getText());
		input = textArea.getText();
		//input = JOptionPane.showInputDialog("Enter Text:");
		f.takeScreenShot(input);
		Thread.sleep(500);
		
        timer = new Timer(2000, closeJDialog);
		timer.start();
		pane = new JOptionPane("Successfully placed the screenshot :)");
		dialog = pane.createDialog("Message");
		//JOptionPane.showMessageDialog(null, "Successfully placed the screenshot :)", "Message: " + "", JOptionPane.INFORMATION_MESSAGE);
		dialog.setVisible(true);
		System.exit(0);
	}
	
	/**
	 * @throws AWTException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void start() throws AWTException, IOException, InterruptedException
	{
		Screenclip f =new Screenclip();

		JTextArea textArea = new JTextArea(6, 25);
		JScrollPane scrollPane = new JScrollPane(textArea);
		JOptionPane.showMessageDialog(scrollPane, scrollPane);
		System.out.println(textArea.getText());
		input = textArea.getText();
		//input = JOptionPane.showInputDialog("Enter Text:");
		f.takeScreenShot(input);
		Thread.sleep(500);
		
        timer = new Timer(2000, closeJDialog);
		timer.start();
		pane = new JOptionPane("Successfully placed the screenshot :)");
		dialog = pane.createDialog("Message");
		//JOptionPane.showMessageDialog(null, "Successfully placed the screenshot :)", "Message: " + "", JOptionPane.INFORMATION_MESSAGE);
		dialog.setVisible(true);
		System.exit(0);
	}
	
	static java.awt.event.ActionListener closeJDialog = new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			if (dialog.isShowing()) {
				dialog.dispose();
			}
			
		}
	};
}
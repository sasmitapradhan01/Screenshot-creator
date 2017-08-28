package com.sp.wordcreator.support;

import java.awt.Frame;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

/**
 * @author Sasmita
 *
 */
public class CopyReqFiles extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String desktopPath = System.getProperty("user.home") + "\\Desktop\\ScreenClip\\Files";
	public static String screenshots = System.getProperty("user.home") + "\\Desktop\\ScreenClip\\screenshot";
	public static String document = System.getProperty("user.home") + "\\Desktop\\ScreenClip\\document";
	CopyReqFiles() {
		setSize(300, 300);// frame size 300 width and 300 height
		setLayout(null);// no layout manager
		setVisible(true);// now frame will be visible, by default not visible
		setVisible(false);
		
	}
	public static void main(String[] args) {
		CopyReqFiles cf = new CopyReqFiles();
		
		
		System.out.println(System.getProperty("user.dir"));
		File screenclip = new File(System.getProperty("user.dir")+"\\Files\\screenclip.jar");
		File documentwrite = new File(System.getProperty("user.dir")+"\\Files\\wordcreator.jar");
		try {
			File theDir = new File(desktopPath);
			File screenshot = new File(screenshots);
			File doc = new File(document);

			// if the directory does not exist, create it
			if (!theDir.exists()) {
				 theDir.mkdirs();
			}
			if (!screenshot.exists()) {
				screenshot.mkdirs();
			}
			if (!doc.exists()) {
				doc.mkdirs();
			}
		    FileUtils.copyFileToDirectory(screenclip, theDir);
		    FileUtils.copyFileToDirectory(documentwrite, theDir);
		    
		    cf.createShortCut();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Patch has ben Applied", "Message: " + "", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	
	public void createShortCut() throws IOException
	{
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\Files\\shortcutCreator.bat");
	}
}

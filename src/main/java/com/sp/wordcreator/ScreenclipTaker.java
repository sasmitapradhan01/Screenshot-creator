package com.sp.wordcreator;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import com.sp.wordcreator.support.Inputs;



/**
 * @author Sasmita
 *
 */
public class ScreenclipTaker {
	static SimpleDateFormat  formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");
	public static String takeScreenShot() throws AWTException, IOException
	{
		  String fname = "";
		try
		{
		File theDir = new File(Inputs.screenshots);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			 theDir.mkdir();
		}
		Robot robot = new Robot();
		Calendar now = Calendar.getInstance();
        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        fname=formatter.format(now.getTime())+".jpg";
        ImageIO.write(screenShot, "JPG", new File(theDir.toString()+"\\"+fname));
        System.out.println(formatter.format(now.getTime()));
		}catch(Exception e1)
		{
			
		}
		
		return fname;
        
	}

}

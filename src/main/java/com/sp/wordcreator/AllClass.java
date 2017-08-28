package com.sp.wordcreator;

import java.awt.AWTException;
import java.io.IOException;

/**
 * @author Sasmita
 *
 */
public class AllClass {

	
	@SuppressWarnings("deprecation")
	public static void main(String args[]) throws AWTException, IOException, InterruptedException {
		if(args != null && args.length > 0 && args[0].equals("textarea")){
			new Screenclip().start();
		}else if(args != null && args.length > 0 && args[0].equals("editor")){
			new HTMLTextEditor().start();
		}else
		{
			
		}
	}
}

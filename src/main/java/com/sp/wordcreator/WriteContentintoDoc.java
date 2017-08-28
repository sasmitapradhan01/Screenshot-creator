package com.sp.wordcreator;

import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.sp.wordcreator.support.CopyReqFiles;
import com.sp.wordcreator.support.CustomXWPFDocument;


/**
 * @author Sasmita
 *
 */
public class WriteContentintoDoc extends Frame {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	WriteContentintoDoc() {
		setSize(300, 300);// frame size 300 width and 300 height
		setLayout(null);// no layout manager
		setVisible(true);// now frame will be visible, by default not visible
		setVisible(false);
	}
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());
        new WriteContentintoDoc().writeDoc("","","");
        JOptionPane.showMessageDialog(null, "Doc file created successfully", "Message: " + "", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
      
    }
    
    /**
     * @param path
     * @param imgName
     * @param text
     * @throws IOException
     */
    public void writeDoc(String path,String imgName,String text) throws IOException
    {
    	  // Create a document file
    	CustomXWPFDocument document = null;
    	if(new File(CopyReqFiles.document+"\\doc.docx").exists())
    	{
    		 document = new CustomXWPFDocument(new FileInputStream(CopyReqFiles.document+"\\doc.docx"));
    	}else
    	{
    		document = new CustomXWPFDocument();
    	}
    	if(imgName.isEmpty())
    	{
        File file1 = new File(CopyReqFiles.screenshots);
        File[] files = file1.listFiles();
        // Adding a file
        for(File file:files)
        {	
        	if(file.isDirectory())
        	{
        		continue;
        	}
        		imageRead(document,file,"");
        // insert doc details
        // Createa a para -1
       /* XWPFParagraph paragraphOne = document.createParagraph();
        paragraphOne.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun paragraphOneRunOne = paragraphOne.createRun();
        paragraphOneRunOne.setBold(true);
        paragraphOneRunOne.setFontSize(20);
        paragraphOneRunOne.setFontFamily("Verdana");
        paragraphOneRunOne.setColor("000070");
        paragraphOneRunOne.setText("Daily Status Report");*/

       
        }
    	}else
    	{
    		File file = new File(CopyReqFiles.screenshots+"\\"+imgName);
    		imageRead(document,file,text);
    	}
        FileOutputStream outStream = null;
        try {
            double x = Math.random();
            String fileName = CopyReqFiles.document+"\\doc.docx";
            outStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("First Catch");
            e.printStackTrace();
        }
        try {
            document.write(outStream);
            outStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Second Catch");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Third Catch");
            e.printStackTrace();
        }
    }
    
    /**
     * @param document
     * @param file
     * @param text
     * @throws FileNotFoundException
     */
    public void imageRead(CustomXWPFDocument document,File file,String text) throws FileNotFoundException
    {
    	try {
    		
    		if(text!=null && !text.isEmpty())
    		{
    		XWPFParagraph paragraphOne = document.createParagraph();
            paragraphOne.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun paragraphOneRunOne = paragraphOne.createRun();
            paragraphOneRunOne.setBold(true);
            paragraphOneRunOne.setFontSize(10);
            paragraphOneRunOne.setFontFamily("Verdana");
            paragraphOneRunOne.setText(text);
    		}

            // Working addPicture Code below...
            XWPFParagraph paragraphX = document.createParagraph();
            paragraphX.setAlignment(ParagraphAlignment.LEFT);

            String blipId = paragraphX.getDocument().addPictureData(
                    new FileInputStream(new File(file.toString())),
                    Document.PICTURE_TYPE_JPEG);
            document.createPicture(blipId,
                    document.getNextPicNameNumber(Document.PICTURE_TYPE_JPEG),
                    700, 450);

        } catch (InvalidFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}

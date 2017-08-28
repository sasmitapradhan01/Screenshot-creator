package com.sp.wordcreator;

import java.io.File;
import java.util.Date;

import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.contenttype.ContentType;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.WordprocessingML.AlternativeFormatInputPart;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.NumberingDefinitionsPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.CTAltChunk;

import com.sp.wordcreator.support.Inputs;


/**
 * @author Sasmita
 *
 */
public class WriteContentsToDoc {

	
	public static void main(String[] args) throws Exception {
		//new WriteContentsToDoc().writeContents("t","20150706 06 41 46 AM.jpg");
	}
	/**
	 * @param html
	 * @param imgName
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public void writeContents(String html,String imgName) throws Exception {
		try {

			
			WordprocessingMLPackage wordMLPackage ;
				/*html = "<html><head></head><body contenteditable='true'><h4 style='text-align: left;'>"
						+ "<font face=''Segoe UI''>test5</font><font face='Aparajita'>&nbsp;teadasd afsa dsad sad S &nbsp;<font size='6'>ad asdaDasdsad</font></font></h4><p style='text-align: left;'><font face='Aparajita'><b>asdsad <i>asdsada</i>s <font size='5' color='#800000'>adsadsa dasd </font><font size='5' color='#ffff99'>asd dD</font></b></font></p></body></html>";*/
				File fileName = (new java.io.File(Inputs.document +Inputs.documentName));
				if(!fileName.exists())
				{
					wordMLPackage = WordprocessingMLPackage.createPackage();
				}else
				{
					wordMLPackage = WordprocessingMLPackage.load(fileName);
				}
				
				 NumberingDefinitionsPart ndp = new NumberingDefinitionsPart();
			        if (html != null && !html.isEmpty()) {
				        AlternativeFormatInputPart afiPart = new AlternativeFormatInputPart(new PartName("/hw"+new Date().getTime()+new Date().getDay()+".html"));
				        afiPart.setBinaryData(html.getBytes());
				        afiPart.setContentType(new ContentType("text/html"));
				        Relationship altChunkRel = wordMLPackage.getMainDocumentPart().addTargetPart(afiPart);
				         
				        // .. the bit in document body
				        CTAltChunk ac = Context.getWmlObjectFactory().createCTAltChunk();
				        ac.setId(altChunkRel.getId() );
				        if( wordMLPackage.getMainDocumentPart().getStyleDefinitionsPart() == null)
				        {
				        	wordMLPackage.getMainDocumentPart().getStyleDefinitionsPart(true);
				        }
				        wordMLPackage.getMainDocumentPart().addObject(ac);
				         
				        // .. content type
				        wordMLPackage.getContentTypeManager().addDefaultContentType("html", "text/html");
			        }
			        File file = new File(Inputs.screenshots+"\\"+imgName);
			        writeImage(wordMLPackage,file);
			        
			        @SuppressWarnings("deprecation")
					SaveToZipFile saver = new SaveToZipFile(wordMLPackage);
					saver.save(fileName);
					System.out.println("Its done !!! :)");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	/**
	 * @param wordMLPackage
	 * @param filename
	 * @throws Exception
	 */
	public void writeImage(WordprocessingMLPackage wordMLPackage,File filename) throws Exception
	{
		java.io.InputStream is = new java.io.FileInputStream(filename);
        long length = filename.length();    
        // You cannot create an array using a long type.
        // It needs to be an int type.
        if (length > Integer.MAX_VALUE) {
        	System.out.println("File too large!!");
        }
        byte[] bytes = new byte[(int)length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            System.out.println("Could not completely read file "+filename.getName());
        }
        is.close();
        
        String filenameHint = null;
        String altText = null;
        int id1 = 0;
        int id2 = 1;
        
        
        // Image 1: no width specified
        org.docx4j.wml.P p = newImage( wordMLPackage, bytes, 
        		filenameHint, altText, 
    			id1, id2, 10000);

		wordMLPackage.getMainDocumentPart().addObject(p);

	}
	
	public static org.docx4j.wml.P newImage( WordprocessingMLPackage wordMLPackage,
			byte[] bytes,
			String filenameHint, String altText, 
			int id1, int id2, long cx) throws Exception {
		
        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);
		
        Inline inline = imagePart.createImageInline( filenameHint, altText, 
    			id1, id2, cx, false);
        
		org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();
		org.docx4j.wml.P  p = factory.createP();
		org.docx4j.wml.R  run = factory.createR();		
		p.getContent().add(run);        
		org.docx4j.wml.Drawing drawing = factory.createDrawing();		
		run.getContent().add(drawing);		
		drawing.getAnchorOrInline().add(inline);
		
		return p;
		
	}

}

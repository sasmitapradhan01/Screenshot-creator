package com.sp.screenshot.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sasmita
 *
 */
public class DocumentPojo {

	String text_align;
	List<FontPojo> fonts = new ArrayList<FontPojo>();
	FontPojo font = new FontPojo();
	
	public String getText_align() {
		return text_align;
	}
	public void setText_align(String text_align) {
		this.text_align = text_align;
	}
	public List<FontPojo> getFonts() {
		return fonts;
	}
	public void setFonts(List<FontPojo> fonts) {
		this.fonts = fonts;
	}
	public FontPojo getFont() {
		return font;
	}
	public void setFont(FontPojo font) {
		this.font = font;
	}
	
	
	
	
}

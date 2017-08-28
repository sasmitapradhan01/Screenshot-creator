package com.sp.screenshot.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sasmita
 *
 */
public class FontPojo {
	
	boolean bold;
	boolean italic;
	boolean underline;
	String font_size;
	String font_color;
	String font_face;
	String value;
	List<FontPojo> font = new ArrayList<FontPojo>();
	public List<FontPojo> getFont() {
		return font;
	}
	public void setFont(List<FontPojo> font) {
		this.font = font;
	}
	public boolean isBold() {
		return bold;
	}
	public void setBold(boolean bold) {
		this.bold = bold;
	}
	public boolean isItalic() {
		return italic;
	}
	public void setItalic(boolean italic) {
		this.italic = italic;
	}
	public boolean isUnderline() {
		return underline;
	}
	public void setUnderline(boolean underline) {
		this.underline = underline;
	}
	public String getFont_size() {
		return font_size;
	}
	public void setFont_size(String font_size) {
		this.font_size = font_size;
	}
	public String getFont_color() {
		return font_color;
	}
	public void setFont_color(String font_color) {
		this.font_color = font_color;
	}
	public String getFont_face() {
		return font_face;
	}
	public void setFont_face(String font_face) {
		this.font_face = font_face;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	
	
}

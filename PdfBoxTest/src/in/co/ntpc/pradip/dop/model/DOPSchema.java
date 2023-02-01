package in.co.ntpc.pradip.dop.model;

import java.io.Serializable;


import java.nio.ByteBuffer;

import org.apache.solr.client.solrj.beans.Field;

public class DOPSchema implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Field
	private String page_no;
	
	@Field
	private String page_content;
	
	@Field
	private ByteBuffer page_image;
	
	
	
	public String getPage_no() {
		return page_no;
	}
	public void setPage_no(String page_no) {
		this.page_no = page_no;
	}
	public String getPage_content() {
		return page_content;
	}
	public void setPage_content(String page_content) {
		this.page_content = page_content;
	}
	
	public ByteBuffer getPage_image() {
		return page_image;
	}
	public void setPage_image(ByteBuffer page_image) {
		this.page_image = page_image;
	}
	
	
	
	
	

}

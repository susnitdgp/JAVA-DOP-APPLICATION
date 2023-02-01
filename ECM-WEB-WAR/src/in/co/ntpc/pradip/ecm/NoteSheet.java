package in.co.ntpc.pradip.ecm;

import java.io.Serializable;

public class NoteSheet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int page_no;
	
	private String page_content;

	public int getPage_no() {
		return page_no;
	}

	public void setPage_no(int page_no) {
		this.page_no = page_no;
	}

	public String getPage_content() {
		return page_content;
	}

	public void setPage_content(String page_content) {
		this.page_content = page_content;
	}
	
	
	

}

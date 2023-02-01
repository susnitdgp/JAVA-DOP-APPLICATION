package in.co.ntpc.pradip.ejb.model;

import java.io.Serializable;



public class CustomSearchResult implements Serializable,Comparable<CustomSearchResult>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dop_section;
	
	private String dop_clause;

	private String dop_subclause;

	private String dop_romansubclause;

	private String page_no;
	
	private String page_id;
	
	private String page_content;
	
	
	

	public String getDop_section() {
		return dop_section;
	}

	public void setDop_section(String dop_section) {
		this.dop_section = dop_section;
	}

	public String getDop_clause() {
		return dop_clause;
	}

	public void setDop_clause(String dop_clause) {
		this.dop_clause = dop_clause;
	}

	public String getDop_subclause() {
		return dop_subclause;
	}

	public void setDop_subclause(String dop_subclause) {
		this.dop_subclause = dop_subclause;
	}

	public String getDop_romansubclause() {
		return dop_romansubclause;
	}

	public void setDop_romansubclause(String dop_romansubclause) {
		this.dop_romansubclause = dop_romansubclause;
	}

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

	public String getPage_id() {
		return page_id;
	}

	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}

	@Override
	public int compareTo(CustomSearchResult o) {
		
		int own_page_no=Integer.parseInt(this.getPage_no());
		int oth_page_no=Integer.parseInt(o.getPage_no());
		
		if (own_page_no == oth_page_no){
			
			return 0;
			
		}else if (own_page_no > oth_page_no){
			
			return 1;
		
		}else{
			return -1;
		}
		
	}

	
	

}

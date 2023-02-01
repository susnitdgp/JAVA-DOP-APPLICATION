package in.co.ntpc.pradip.dop.model;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

public class DOPMetaDataSchema implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Field
	private String dop_section;
	@Field
	private String dop_clause;
	@Field
	private String dop_subclause;
	@Field
	private String dop_romansubclause;
	@Field
	private String page_no;
	@Field
	private String remarks_page_no;
	@Field
	private String amendment_page_no;
	
	
	
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

	public String getRemarks_page_no() {
		return remarks_page_no;
	}
	public void setRemarks_page_no(String remarks_page_no) {
		this.remarks_page_no = remarks_page_no;
	}
	public String getAmendment_page_no() {
		return amendment_page_no;
	}
	public void setAmendment_page_no(String amendment_page_no) {
		this.amendment_page_no = amendment_page_no;
	}
	public String getPage_no() {
		return page_no;
	}
	public void setPage_no(String page_no) {
		this.page_no = page_no;
	}
	
	
	
	
	
	

}

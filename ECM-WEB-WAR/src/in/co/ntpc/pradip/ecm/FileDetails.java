package in.co.ntpc.pradip.ecm;

import java.io.Serializable;

public class FileDetails implements Serializable {

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	
	
	
	
	private String docid;

	public FileDetails(){
		
	}
	
	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}
	
	
	
	

}

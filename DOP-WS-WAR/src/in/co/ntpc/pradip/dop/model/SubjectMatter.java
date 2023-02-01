package in.co.ntpc.pradip.dop.model;

import java.io.Serializable;

public class SubjectMatter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Empty Constructor
	public SubjectMatter(){
		
	}
	//Argument Constructor
	public SubjectMatter(int s, String m){
		this.serial=s;
		this.matter=m;
	}
	
	private int serial;
	
	private String matter;

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}
	
	

}

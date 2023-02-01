package in.co.ntpc.pradip.ecm;

import java.io.Serializable;

public class Metadata implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String NTPC_DRG_NO;
	String VENDOR_DRG_NO;
	String MAIN_VENDOR_CODE;
	String MAIN_VENDOR_DESC;
	String SUB_VENDOR_CODE;
	String SUB_VENDOR_DESC;
	String DRG_KEY_TITLE;
	String DRG_TITLE;
	String DRAWING_DSCPLN;
	String APPRVL_CODE;
	String APPRVL_DESC;
	String DOC_CATG_CODE;
	String DOC_CATG_DESC;
	int MDL_KEY;
	String PROJECT_CODE;
	String PROJECT_DESC;
	String PROJECT_LONG_DESC;
	String PKG_CODE;
	String PKG_DESC;
	String PKG_LONG_DESC;
	String SUB_STM_CODE;
	String SUB_STM_DESC;
	
	public Metadata(){
	
	}
	public String getNTPC_DRG_NO() {
		return NTPC_DRG_NO;
	}
	public void setNTPC_DRG_NO(String nTPC_DRG_NO) {
		NTPC_DRG_NO = nTPC_DRG_NO;
	}
	public String getVENDOR_DRG_NO() {
		return VENDOR_DRG_NO;
	}
	public void setVENDOR_DRG_NO(String vENDOR_DRG_NO) {
		VENDOR_DRG_NO = vENDOR_DRG_NO;
	}
	public String getMAIN_VENDOR_CODE() {
		return MAIN_VENDOR_CODE;
	}
	public void setMAIN_VENDOR_CODE(String mAIN_VENDOR_CODE) {
		MAIN_VENDOR_CODE = mAIN_VENDOR_CODE;
	}
	public String getMAIN_VENDOR_DESC() {
		return MAIN_VENDOR_DESC;
	}
	public void setMAIN_VENDOR_DESC(String mAIN_VENDOR_DESC) {
		MAIN_VENDOR_DESC = mAIN_VENDOR_DESC;
	}
	public String getSUB_VENDOR_CODE() {
		return SUB_VENDOR_CODE;
	}
	public void setSUB_VENDOR_CODE(String sUB_VENDOR_CODE) {
		SUB_VENDOR_CODE = sUB_VENDOR_CODE;
	}
	public String getSUB_VENDOR_DESC() {
		return SUB_VENDOR_DESC;
	}
	public void setSUB_VENDOR_DESC(String sUB_VENDOR_DESC) {
		SUB_VENDOR_DESC = sUB_VENDOR_DESC;
	}
	public String getDRG_KEY_TITLE() {
		return DRG_KEY_TITLE;
	}
	public void setDRG_KEY_TITLE(String dRG_KEY_TITLE) {
		DRG_KEY_TITLE = dRG_KEY_TITLE;
	}
	public String getDRG_TITLE() {
		return DRG_TITLE;
	}
	public void setDRG_TITLE(String dRG_TITLE) {
		DRG_TITLE = dRG_TITLE;
	}
	public String getDRAWING_DSCPLN() {
		return DRAWING_DSCPLN;
	}
	public void setDRAWING_DSCPLN(String dRAWING_DSCPLN) {
		DRAWING_DSCPLN = dRAWING_DSCPLN;
	}
	public String getAPPRVL_CODE() {
		return APPRVL_CODE;
	}
	public void setAPPRVL_CODE(String aPPRVL_CODE) {
		APPRVL_CODE = aPPRVL_CODE;
	}
	public String getAPPRVL_DESC() {
		return APPRVL_DESC;
	}
	public void setAPPRVL_DESC(String aPPRVL_DESC) {
		APPRVL_DESC = aPPRVL_DESC;
	}
	public String getDOC_CATG_CODE() {
		return DOC_CATG_CODE;
	}
	public void setDOC_CATG_CODE(String dOC_CATG_CODE) {
		DOC_CATG_CODE = dOC_CATG_CODE;
	}
	public String getDOC_CATG_DESC() {
		return DOC_CATG_DESC;
	}
	public void setDOC_CATG_DESC(String dOC_CATG_DESC) {
		DOC_CATG_DESC = dOC_CATG_DESC;
	}
	public int getMDL_KEY() {
		return MDL_KEY;
	}
	public void setMDL_KEY(int mDL_KEY) {
		MDL_KEY = mDL_KEY;
	}
	public String getPROJECT_CODE() {
		return PROJECT_CODE;
	}
	public void setPROJECT_CODE(String pROJECT_CODE) {
		PROJECT_CODE = pROJECT_CODE;
	}
	public String getPROJECT_DESC() {
		return PROJECT_DESC;
	}
	public void setPROJECT_DESC(String pROJECT_DESC) {
		PROJECT_DESC = pROJECT_DESC;
	}
	public String getPROJECT_LONG_DESC() {
		return PROJECT_LONG_DESC;
	}
	public void setPROJECT_LONG_DESC(String pROJECT_LONG_DESC) {
		PROJECT_LONG_DESC = pROJECT_LONG_DESC;
	}
	public String getPKG_CODE() {
		return PKG_CODE;
	}
	public void setPKG_CODE(String pKG_CODE) {
		PKG_CODE = pKG_CODE;
	}
	public String getPKG_DESC() {
		return PKG_DESC;
	}
	public void setPKG_DESC(String pKG_DESC) {
		PKG_DESC = pKG_DESC;
	}
	public String getPKG_LONG_DESC() {
		return PKG_LONG_DESC;
	}
	public void setPKG_LONG_DESC(String pKG_LONG_DESC) {
		PKG_LONG_DESC = pKG_LONG_DESC;
	}
	public String getSUB_STM_CODE() {
		return SUB_STM_CODE;
	}
	public void setSUB_STM_CODE(String sUB_STM_CODE) {
		SUB_STM_CODE = sUB_STM_CODE;
	}
	public String getSUB_STM_DESC() {
		return SUB_STM_DESC;
	}
	public void setSUB_STM_DESC(String sUB_STM_DESC) {
		SUB_STM_DESC = sUB_STM_DESC;
	}

}


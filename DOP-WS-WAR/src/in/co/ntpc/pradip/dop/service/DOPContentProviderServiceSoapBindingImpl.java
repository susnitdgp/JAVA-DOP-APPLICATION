package in.co.ntpc.pradip.dop.service;

import in.co.ntpc.pradip.dop.ejb.stateless.solr.SolrOperationLocal;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.DatatypeConverter;


@javax.jws.WebService (endpointInterface="in.co.ntpc.pradip.dop.service.DOPContentProviderDelegate", targetNamespace="http://dop.pradip.ntpc.co.in/", serviceName="DOPContentProviderService", portName="DOPContentProviderPort")
public class DOPContentProviderServiceSoapBindingImpl{

	private SolrOperationLocal solrOperation;
	
	public DOPContentProviderServiceSoapBindingImpl(){
  		final String jndi = "java:global/DOP-ROOT-EAR/DOP-EJB-JAR/SolrOperation!in.co.ntpc.pradip.dop.ejb.stateless.solr.SolrOperationLocal";
  		try{
  			
  			 solrOperation=(SolrOperationLocal) new InitialContext().lookup(jndi);
  		}
  		catch(NamingException ex){
  			System.out.println(ex.getMessage());
  			
  		}
  	}
  	
	/*
	
    public DopSchema getNextPage(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }
  

    public List<String> getRomanClauseBySubClauseAndClauseAndSection(String arg0, String arg1, String arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getPageImage(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<String> getClauseBySection(String arg0) {
        // TODO Auto-generated method stub
    	return solrOperation.getClauseBySection(arg0);
    }

    public String getPageImage2(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<CustomSearchResult> solrSearchResultWithHighlight(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<CustomSearchResult> getResultBasedOnMetaData(String arg0, String arg1, String arg2, String arg3) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<String> getDopSection() {
        // TODO Auto-generated method stub
        return solrOperation.getDopSection();
    }

    public String getTotalPages(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public DopSchema getPreviousPage(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public DopSchema getCurrentPage(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<String> getSubClauseByClauseAndSection(String arg0, String arg1) {
        // TODO Auto-generated method stub
        return null;
    }
    */
    
	//Exposed Public Methods
	
	public List<String> getDopSection(){
		
		
		return solrOperation.getDopSection();
		
	}
	public List<String> getClauseBySection(String section){
		
		return solrOperation.getClauseBySection(section);
	}
	
	public List<String> getSubClauseByClauseAndSection(String clause,String section){
		
		return solrOperation.getSubClauseByClauseAndSection(clause, section);
	}
	
	public List<String> getRomanClauseBySubClauseAndClauseAndSection(String subclause,String clause,String section){
		return solrOperation.getRomanClauseBySubClauseAndClauseAndSection(subclause, clause, section);
	}
	
	public List<CustomSearchResult> getResultBasedOnMetaData(String section,String clause,String subclause,String romanclause){
		
		return solrOperation.getResultBasedOnMetaData(section, clause, subclause, romanclause);
	}
	
	public List<CustomSearchResult> solrSearchResultWithHighlight(String page_text_content){
		
		return solrOperation.solrSearchResultWithHighlight(page_text_content);
	}
	
	public String getPageImage(String page_no){
		
			byte[] data=solrOperation.getPageImageByPageNo(page_no);
			
			String pre="data:image/gif;base64,";
			
			String codec =  DatatypeConverter.printBase64Binary(data);
			
			return pre+codec;
		
	}
	
	public String getPageImage2(String doc_id){
		
		if(!isNullOrEmpty(doc_id)){
			
			//ByteBuffer buffer=solrOperation.getPageImage(doc_id);
			byte[] data=solrOperation.getPageImageByPageId(doc_id);
			
			String pre="data:image/gif;base64,";
			
			String codec =  DatatypeConverter.printBase64Binary(data);
			
			return pre+codec;
			
		}else{
			
			return null;
		}
	
		
	}
	
	public DopSchema getCurrentPage(String page_no){
		
		return solrOperation.getCurrentPage(page_no);
	}
	
	public DOPSchema getNextPage(String page_no){
		
		return solrOperation.getNextPage(page_no);
	}
	public DOPSchema getPreviousPage (String page_no){
		
		return solrOperation.getPreviousPage(page_no);
	}
	
	public String getTotalPages(String data){
		return solrOperation.getTotalPages();
	}
	
	//Private Method
	private static boolean isNullOrEmpty(String str) {
		if (str != null && !str.trim().isEmpty())
			return false;
		return true;
	}
    
    
}
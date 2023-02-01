package in.co.ntpc.pradip.ejb.stateless;



import in.co.ntpc.pradip.ejb.model.DOPSchema;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;



/**
 * Session Bean implementation class TestEJB
 */
@Stateless
@LocalBean
public class TestEJB implements TestEJBRemote, TestEJBLocal {

    /**
 * Default constructor. 
 */
public TestEJB() {
    
}

@Override
public int addFromEjb(int a, int b) {
	
	return a+b;
}

@Override
public List<DOPSchema> solrSearch(String content) {
	
	//null check
	if(isNullOrEmpty(content)){
		content="Delegation";
	}
	
	Properties prop=this.getSolrConfigProperties();
	
	List<DOPSchema> lsr=new ArrayList<DOPSchema>();
	
	System.out.println("Search Starts");
	
	String solr_string=prop.getProperty("solr_url") + "/" + prop.getProperty("solr_core");
	
	SolrClient client = new HttpSolrClient.Builder(solr_string).build();

   SolrQuery query = new SolrQuery();
   
   query.setQuery("page_content:"+content);
   //query.addFilterQuery("cat:electronics","store:amazon.com");
   //query.setFacetSort("page_no");
   query.setParam("sort", "page_no asc");
   
   query.setFields("id,page_content,page_no,dop_section,dop_clause,dop_subclause");
   query.setStart(0);
   
   query.setHighlight(true);
   query.setHighlightSnippets(1);
   
   query.setParam("hl.fl", "*");
   query.setParam("hl.fragsize", "0");
   query.setParam("hl.simple.pre", "<mark>");
   query.setParam("hl.simple.post", "</mark>");

   try {
   	
   	 QueryResponse response = client.query(query);
   	 
   	 
   	
        SolrDocumentList results = response.getResults();
        
        response.getHighlighting();
        
        for (int i = 0; i < results.size(); ++i) {
       	 
       	 SolrDocument doc=results.get(i);
         System.out.println(doc.getFieldValue("page_no"));
            
           DOPSchema result=new DOPSchema();
           
           result.setId(doc.getFieldValue("id").toString());
           //result.setDop_section(doc.getFieldValue("dop_section").toString());
           //result.setDop_clause("36");
           //result.setDop_subclause(doc.getFieldValue("dop_subclause").toString());
           
           result.setPage_no(doc.getFieldValue("page_no").toString());
           
           String val=response.getHighlighting().get(doc.getFieldValue("id").toString()).get("page_content").get(0);
           
           //Content from Highlight Object
           val.replaceAll("\r", "");
           val.replaceAll("\n", "");
           
           result.setPage_content(val);
          
       	   lsr.add(result);
        }
   	
   }
	catch(SolrServerException ex) {
		ex.printStackTrace();
	}
	catch (IOException e) {
		
		e.printStackTrace();
	}
	
   System.out.println("Search Ends");
	
   return lsr;
}



private Properties getSolrConfigProperties() {
	
	
	Properties prop = new Properties();
	InputStream input = null;
	
	try {
        
		String filename = "ecm_ejb_config.properties";
		input = this.getClass().getClassLoader().getResourceAsStream(filename);
		if(input==null){
	            System.out.println("Sorry, unable to find " + filename);
		    return null;
		}

		//load a properties file from class path, inside static method
    		prop.load(input);
 
            //get the property value and print it out
        System.out.println(prop.getProperty("solr_url"));
	    System.out.println(prop.getProperty("solr_core"));
    	   
 
    	} catch (IOException ex) {
    		
    		ex.printStackTrace();
    		prop=null;
        } 
    	finally{
        	if(input!=null){
        		try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	}
        }
       
		return prop;
}


private static boolean isNullOrEmpty(String str) {
	    if(str != null && !str.isEmpty())
	        return false;
	    return true;
}


@Override
public ByteBuffer beanQyery(String id){
		
		//Null check
				if(isNullOrEmpty(id)){
					return null;
				}
		
		Properties prop=this.getSolrConfigProperties();
		
		ByteBuffer buffer=null;
			
		System.out.println("Search Starts");
		
		String solr_string=prop.getProperty("solr_url") + "/" + prop.getProperty("solr_core");
		
		SolrClient client = new HttpSolrClient.Builder(solr_string).build();
		
		SolrQuery query = new SolrQuery("id:"+id);
		query.addField("page_content");
		query.addField("page_image");
		
		try{
			
			final QueryResponse response=client.query(query);
			
			DOPSchema result=response.getBeans(DOPSchema.class).get(0);
			
			buffer= result.getPage_image();
			
			System.out.println(buffer);
			
		}
		catch(SolrServerException ex) {
			ex.printStackTrace();
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return buffer;
		
		
}


}

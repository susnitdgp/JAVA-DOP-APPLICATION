package in.co.ntpc.pradip.dop.ejb.stateless.solr;


import in.co.ntpc.pradip.ejb.model.CustomSearchResult;
import in.co.ntpc.pradip.ejb.model.DOPMeataDataSchema;
import in.co.ntpc.pradip.ejb.model.DOPSchema;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Properties;







//import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;






import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import se.sawano.java.text.AlphanumericComparator;






/**
 * Session Bean implementation class SolrOperation
 */
@Stateless
@LocalBean
@Remote
public class SolrOperation implements SolrOperationRemote, SolrOperationLocal {



	/**
	 * Default constructor.
	 */
	Properties prop = null;

	public SolrOperation() {

		prop = this.getSolrConfigProperties();

		//System.out.println("EJB SolrOperation: Instantiated");
	
	}
	
	//------------------------------------- Meta data Method Start---------------//
	@Override
	public List<String> getDopSection() {
		
		//System.out.println("EJB Function: getDopSection Invoked");
		
		List<String> ls= new ArrayList<String>();
		
		String solr_string = prop.getProperty("solr_url") + "/" + prop.getProperty("solr_metadata_core");
		SolrClient client = new HttpSolrClient.Builder(solr_string).build();
		
		SolrQuery query = new SolrQuery("dop_section:" + "*");
		
		query.setFields("id,dop_section,dop_clause,dop_subclause,dop_romansubclause,page_no,remarks_page_no");
		
		query.setFacet(true);
		query.addFacetField("dop_section");

		//query.setStart(0);
		//query.setRows(100);

		try {

			QueryResponse response = client.query(query);
						
			List<FacetField> ffList = response.getFacetFields();
				
			
			for(FacetField ff : ffList){

				    List<Count> counts = ff.getValues();
				    for(Count c : counts){
				        String facetLabel = c.getName();
				        long facetCount = c.getCount();
				        
				        //System.out.println(facetLabel + "/" + facetCount);
				        if(facetCount>0){
				        	ls.add(facetLabel);
				        	//System.out.println(facetLabel);
				        }
				        
				    }      
			}
			

		} catch (SolrServerException ex) {

			ex.printStackTrace();
			
		} catch (IOException ex) {

			ex.printStackTrace();
			
		}

		Collections.sort(ls, new AlphanumericComparator(Locale.ENGLISH));
		
		
		return ls;
	}
	
	@Override
	public List<String> getClauseBySection(String section){
		
		
		//System.out.println("EJB Function: getClauseBySection Invoked");
		
		List<String> ls= new ArrayList<String>();
		
		if(isNullOrEmpty(section)){
			
			section="*";
		}
		
		//String solr_string = "http://localhost:8983/solr/" + "dop_metadata";
		String solr_string = prop.getProperty("solr_url") + "/" + prop.getProperty("solr_metadata_core");
		
		SolrClient client = new HttpSolrClient.Builder(solr_string).build();
		
		SolrQuery query = new SolrQuery("dop_section:" + section);
		
		query.setFields("id,dop_section,dop_clause,dop_subclause,dop_romansubclause,page_no,remarks_page_no");
		
		query.setFacet(true);
		query.addFacetField("dop_clause");
		
		
		//query.setStart(0);
		//query.setRows(100);
		
		
		try {

			QueryResponse response = client.query(query);
			
			List<FacetField> ffList = response.getFacetFields();
				
			
			for(FacetField ff : ffList){
				
				 	//String ffname = ff.getName();
				    //int ffcount = ff.getValueCount();
				    
				    //System.out.println(ffname + "/" + ffcount);
				    
				    List<Count> counts = ff.getValues();
				    for(Count c : counts){
				        String facetLabel = c.getName();
				        long facetCount = c.getCount();
				        
				        //System.out.println(facetLabel + "/" + facetCount);
				        if(facetCount>0){
				        	ls.add(facetLabel);
				        	//System.out.println(facetLabel);
				        }
				        
				    }      
			}
			

		} catch (SolrServerException ex) {

			ex.printStackTrace();
			
		} catch (IOException ex) {

			ex.printStackTrace();
			
		}

		//Collections.sort(ls);
		Collections.sort(ls, new AlphanumericComparator(Locale.ENGLISH));
		
		return ls;
		
	}
	
	@Override
	public List<String> getSubClauseByClauseAndSection(String clause,String section){
		
		
		
		//System.out.println("EJB Function: getSubClauseByClauseAndSection Invoked");
		
		List<String> ls= new ArrayList<String>();
		
		if(isNullOrEmpty(clause)){
			clause="*";
		}
		if(isNullOrEmpty(section)){
			section="*";
		}
	
		String solr_string = prop.getProperty("solr_url") + "/" + prop.getProperty("solr_metadata_core");
		SolrClient client = new HttpSolrClient.Builder(solr_string).build();
		
		SolrQuery query = new SolrQuery("dop_section:" + section + " AND " + "dop_clause:" + clause);
		
		query.setFields("id,dop_section,dop_clause,dop_subclause,dop_romansubclause,page_no,remarks_page_no");
		
		query.setFacet(true);
		query.addFacetField("dop_subclause");
		
		
		//query.setStart(0);
		//query.setRows(100);
		
		
		try {

			QueryResponse response = client.query(query);
			
			//List<DOPMeataDataSchema> results = response.getBeans(DOPMeataDataSchema.class);
			
			List<FacetField> ffList = response.getFacetFields();
				
			
			//System.out.println("size of ffList:" + ffList.size());
			for(FacetField ff : ffList){
				
				 	//String ffname = ff.getName();
				    //int ffcount = ff.getValueCount();
				    
				    //System.out.println(ffname + "/" + ffcount);
				    
				    List<Count> counts = ff.getValues();
				    for(Count c : counts){
				        String facetLabel = c.getName();
				        long facetCount = c.getCount();
				        
				        //System.out.println(facetLabel + "/" + facetCount);
				        if(facetCount>0){
				        	ls.add(facetLabel);
				        	//System.out.println(facetLabel);
				        }
				        
				    }      
			}
			

		} catch (SolrServerException ex) {

			ex.printStackTrace();
			
		} catch (IOException ex) {

			ex.printStackTrace();
			
		}

		Collections.sort(ls, new AlphanumericComparator(Locale.ENGLISH));
		
		return ls;
	}
	
	@Override
	public List<String> getRomanClauseBySubClauseAndClauseAndSection(String subclause,String clause,String section){
		
		
		System.out.println("EJB Function: getRomanClauseBySubClauseAndClauseAndSection Invoked");
		
		List<String> ls= new ArrayList<String>();
		
		if(isNullOrEmpty(subclause)){
			subclause="*";
		}
		
		if(isNullOrEmpty(clause)){
			clause="*";
		}
		if(isNullOrEmpty(section)){
			section="*";
		}
		
		
		String solr_string = prop.getProperty("solr_url") + "/" + prop.getProperty("solr_metadata_core");
		
		SolrClient client = new HttpSolrClient.Builder(solr_string).build();
		
		SolrQuery query = new SolrQuery("dop_section:" + section + " AND " + "dop_clause:" + clause + " AND " + "dop_subclause:" + subclause);
		
		query.setFields("id,dop_section,dop_clause,dop_subclause,dop_romansubclause,page_no,remarks_page_no");
		
		query.setFacet(true);
		query.addFacetField("dop_romansubclause");
		
		
		//query.setStart(0);
		//query.setRows(100);
		
		
		try {

			QueryResponse response = client.query(query);
			
			//List<DOPMeataDataSchema> results = response.getBeans(DOPMeataDataSchema.class);
			
			List<FacetField> ffList = response.getFacetFields();
				
			
			//System.out.println("size of ffList:" + ffList.size());
			for(FacetField ff : ffList){
				
				 	//String ffname = ff.getName();
				    //int ffcount = ff.getValueCount();
				    
				    //System.out.println(ffname + "/" + ffcount);
				    
				    List<Count> counts = ff.getValues();
				    for(Count c : counts){
				        String facetLabel = c.getName();
				        long facetCount = c.getCount();
				        
				        //System.out.println(facetLabel + "/" + facetCount);
				        if(facetCount>0){
				        	ls.add(facetLabel);
				        	//System.out.println(facetLabel);
				        }
				        
				    }      
			}
			

		} catch (SolrServerException ex) {

			ex.printStackTrace();
			
		} catch (IOException ex) {

			ex.printStackTrace();
			
		}

		Collections.sort(ls, new AlphanumericComparator(Locale.ENGLISH));
		
		return ls;
	}
	
	
	public List<CustomSearchResult> getResultBasedOnMetaData(String section,String clause,String subclause,String romanclause){
		
		
		
		System.out.println("EJB Function: getResultBasedOnMetaData Invoked");
		
		List<CustomSearchResult> final_result=new ArrayList<CustomSearchResult>();
		
		if(isNullOrEmpty(section)){
			section="*";
		}
		if(isNullOrEmpty(clause)){
			clause="*";
		}
		if(isNullOrEmpty(subclause)){
			subclause="*";
		}
		if(isNullOrEmpty(romanclause)){
			romanclause="*";
		}
		
		
		//String solr_string = "http://localhost:8983/solr/" + "dop_metadata";
		String solr_string = prop.getProperty("solr_url") + "/" + prop.getProperty("solr_metadata_core");
		
		SolrClient client = new HttpSolrClient.Builder(solr_string).build();
		
		SolrQuery query = new SolrQuery("dop_section:" + section + " AND " + "dop_clause:" + clause + " AND " + "dop_subclause:" + subclause + " AND " + "dop_romansubclause:"+romanclause);
		
		query.setFields("id,dop_section,dop_clause,dop_subclause,dop_romansubclause,page_no,remarks_page_no");
		
		
		try{
			
			QueryResponse response = client.query(query);
			List<DOPMeataDataSchema> metadata_result=response.getBeans(DOPMeataDataSchema.class);
			
			for(DOPMeataDataSchema model:metadata_result){
				
				CustomSearchResult temp=new CustomSearchResult();
				temp.setDop_section(model.getDop_section());
				temp.setDop_clause(model.getDop_clause());
				temp.setDop_subclause(model.getDop_subclause());
				temp.setDop_romansubclause(model.getDop_romansubclause());
				
				DOPSchema schema=this.getCurrentPage(model.getPage_no());
				
				temp.setPage_no(schema.getPage_no());
				temp.setPage_id(schema.getId());
				temp.setPage_content(schema.getPage_content());
				
				final_result.add(temp);
				
				//System.out.println("Page No:" + model.getPage_no());
			}
			
		
			
		}
		 catch (SolrServerException ex) {
				
			ex.printStackTrace();
				
		} catch (IOException ex) {

			ex.printStackTrace();
		}
		return final_result;
	}

	
	
	//------------------------------------- Meta data Method End---------------//
	
	@Override
	public List<CustomSearchResult> solrSearchResultWithHighlight(String page_text_content) {
		
		System.out.println("EJB Function: solrSearchResultWithHighlight Invoked");

		// null check
		if (isNullOrEmpty(page_text_content)) {
			
			page_text_content = "marine";
			
		}else{
			
			if(page_text_content.length() > 10){
				
				page_text_content=ClientUtils.escapeQueryChars(page_text_content.substring(0,10));
				
			}else{
				
				page_text_content=ClientUtils.escapeQueryChars(page_text_content);
			}
			
			
		}

		// prop = this.getSolrConfigProperties();
		
		
		List<CustomSearchResult> lsr = new ArrayList<CustomSearchResult>();


		String solr_string = prop.getProperty("solr_url") + "/" + prop.getProperty("solr_core");

		SolrClient client = new HttpSolrClient.Builder(solr_string).build();

		SolrQuery query = new SolrQuery();

		query.setQuery("page_content:" + page_text_content);

		
		// query.setFacetSort("page_no");
		query.setParam("sort", "page_no asc");

		query.setFields("id,page_content,page_no");
		query.setStart(0);
		query.setRows(100);

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

				SolrDocument doc = results.get(i);
				System.out.println(doc.getFieldValue("page_no"));

				DOPSchema result = new DOPSchema();

				//Id
				result.setId(doc.getFieldValue("id").toString());
				
				//page_no
				result.setPage_no(doc.getFieldValue("page_no").toString());

				String val = response.getHighlighting()
						.get(doc.getFieldValue("id").toString())
						.get("page_content").get(0);
				
				//page content text
				result.setPage_content(val);
				
				//First Object of DOPMeataDataSchema
				List<DOPMeataDataSchema> lsd=this.getCurrentPageMetaData(result.getPage_no());
				CustomSearchResult temp=new CustomSearchResult();
				
				if(lsd.size() >0){
					
					DOPMeataDataSchema dm=lsd.get(0);
					
					temp.setDop_section(dm.getDop_section());
					temp.setDop_clause(dm.getDop_clause());
					temp.setDop_subclause(dm.getDop_subclause());
					temp.setDop_romansubclause(dm.getDop_romansubclause());
					
				}else{
					
					temp.setDop_section("N/A");
					temp.setDop_clause("N/A");
					temp.setDop_subclause("N/A");
					temp.setDop_romansubclause("N/A");
					
				}
				
			
				temp.setPage_id(result.getId());
				temp.setPage_no(result.getPage_no());
				temp.setPage_content(result.getPage_content());

				lsr.add(temp);
				
			}

		} catch (SolrServerException ex) {
			
			ex.printStackTrace();
			
		} catch (IOException ex) {

			ex.printStackTrace();
		}

		//Sorting CustomSearchResult based on Comparable Interface
		
		Collections.sort(lsr);
		return lsr;

	}

	@Override
	public byte[] getPageImageByPageId(String doc_id) {
		
		
		System.out.println("EJB Function: getPageImage Invoked");

		// Null check
		if (isNullOrEmpty(doc_id)) {
			
			doc_id="e4db4e47-b9bd-41c6-aebb-3a4187897fb1";
		}


		ByteBuffer buffer = null;
		//HeapByteBuffer heap;
		
		byte[] data=null;

		String solr_string = prop.getProperty("solr_url") + "/" + prop.getProperty("solr_core");

		SolrClient client = new HttpSolrClient.Builder(solr_string).build();

		SolrQuery query = new SolrQuery("id:" + doc_id);
		query.addField("page_no");
		query.addField("page_image");

		try {

			final QueryResponse response = client.query(query);
			

			DOPSchema result = response.getBeans(DOPSchema.class).get(0);

			buffer = result.getPage_image();

			data=buffer.array();
			

		} catch (SolrServerException ex) {

			ex.printStackTrace();
			
		} catch (IOException ex) {

			ex.printStackTrace();
		}
		catch(Exception ex){
			
			ex.printStackTrace();
		}
		finally{
			
			try{

				if(buffer.isDirect()){
					
					System.out.println("Buffer is Direct");
					
				}else{
					
					buffer=null;
					System.out.println("Buffer is Through Heap");
				}
				
				
			    
				System.out.println("Clear Buffer Success");
			}
			catch(Exception ex){
				
				System.out.println("Buffer Cleaning Error:" + ex.getMessage());
			}
		}

		return data;

	}
	
	@Override
	public byte[] getPageImageByPageNo(String page_no){
		
		System.out.println("EJB Function: getPageImageByPageNo Invoked");
		
		String result_page=this.validatePageNo(page_no);
		if(result_page.equals("N/A")){
			
			page_no="5";
			
		}else{
			
			page_no=result_page;
		}

		
		
		byte[] data=null;
		
		String solr_string = prop.getProperty("solr_url") + "/" + prop.getProperty("solr_core");

		SolrClient client = new HttpSolrClient.Builder(solr_string).build();

		SolrQuery query = new SolrQuery("page_no:" + page_no);
		query.addField("page_no");
		query.addField("id");
		
		try {

			final QueryResponse response = client.query(query);
			DOPSchema result = response.getBeans(DOPSchema.class).get(0);
			
			String id=result.getId();
			
			data=this.getPageImageByPageId(id);
			
			
		}
		catch (SolrServerException ex) {

			ex.printStackTrace();
			
		} catch (IOException ex) {

			ex.printStackTrace();
		}
		catch(Exception ex){
			
			ex.printStackTrace();
		}
		
		return data;
		
	}
	
	@Override
	public DOPSchema getCurrentPage(String page_no){
		
		
		System.out.println("EJB Function: getCurrentPage Invoked");
		
		DOPSchema searchResult = null;
		
		//Validate Page No to be get.
		
		String result=this.validatePageNo(page_no);
		
		if(result.equals("N/A")){
			
			page_no="5";
			
		}else{
			
			page_no=result;
		}
	
		
		String solr_string = prop.getProperty("solr_url") + "/" + prop.getProperty("solr_core");

		SolrClient client = new HttpSolrClient.Builder(solr_string).build();

		SolrQuery query = new SolrQuery("page_no:" + page_no);

		query.setFields("id,page_content,page_no");

		try {

			QueryResponse response = client.query(query);

			List<DOPSchema> results = response.getBeans(DOPSchema.class);
			if (results.size()> 0){
				
				 searchResult = results.get(0);
			}
			


		} catch (SolrServerException ex) {

			System.out.println("getCurrentPage:" + ex.getMessage());
			
		} catch (IOException ex) {

			System.out.println("getCurrentPage:" + ex.getMessage());
			
		}catch(Exception ex){
			
			System.out.println("getCurrentPage:" + ex.getMessage());
		}

		return searchResult;
		
	}

	//@RolesAllowed("ADMIN")
	@Override
	public DOPSchema getNextPage(String page_no) {
		
		
		System.out.println("EJB Function: getNextPage Invoked");

		DOPSchema searchResult = null;
		String next_page_string = null;
		
		String result=this.validatePageNo(page_no);
		
		if(result.equals("N/A")){
			
			next_page_string="10";
			
		}else{
			
			page_no=result;
			
			try{
				
				int next_page = Integer.parseInt(page_no) + 1;
				if(next_page > Integer.parseInt(this.getTotalPages())){
					
					next_page_string=this.getTotalPages();
				}else{
					next_page_string = Integer.toString(next_page);
				}
				
			}
			catch(NumberFormatException ex){
				
				System.out.println("getNextPage:" + ex.getMessage());
				next_page_string="10";
			}
			catch(Exception ex){
				
				System.out.println("getNextPage:" + ex.getMessage());
				next_page_string="10";
			}
			
		}
		
	
		
		String solr_string = prop.getProperty("solr_url") + "/" + prop.getProperty("solr_core");

		SolrClient client = new HttpSolrClient.Builder(solr_string).build();

		SolrQuery query = new SolrQuery("page_no:" + next_page_string);

		query.setFields("id,page_content,page_no");
		//query.addField("id");

		try {

			QueryResponse response = client.query(query);

			List<DOPSchema> results = response.getBeans(DOPSchema.class);
			if (results.size()>0){
				
				 searchResult = results.get(0);
			}

			//System.out.println("Next Page Id:" + searchResult.getId());

		} catch (SolrServerException ex) {

			System.out.println("getNextPage:" + ex.getMessage());
			
		} catch (IOException ex) {

			System.out.println("getNextPage:" + ex.getMessage());
			
		}catch(Exception ex){
			
			System.out.println("getNextPage:" + ex.getMessage());
		}

		return searchResult;

	}

	@Override
	public DOPSchema getPreviousPage(String page_no) {
		
		
		System.out.println("EJB Function: getPreviousPage Invoked");

		DOPSchema searchResult = null;
		String previous_page_string = null;
		
		String result=this.validatePageNo(page_no);
		
		if(result.equals("N/A")){
			
			previous_page_string="10";
			
		}else{
			
			page_no=result;
			
			try{
				
				int previous_page = Integer.parseInt(page_no) - 1;
				if(previous_page < 1){
					
					previous_page_string="1";
				}else{
					previous_page_string = Integer.toString(previous_page);
				}
				
			}
			catch(NumberFormatException ex){
				
				System.out.println("getNextPage:" + ex.getMessage());
				previous_page_string="10";
			}
			catch(Exception ex){
				
				System.out.println("getNextPage:" + ex.getMessage());
				previous_page_string="10";
			}
			
			
			
		}


		String solr_string = prop.getProperty("solr_url") + "/" + prop.getProperty("solr_core");

		SolrClient client = new HttpSolrClient.Builder(solr_string).build();

		SolrQuery query = new SolrQuery("page_no:" + previous_page_string);

		query.setFields("id,page_content,page_no");
		//query.addField("id");

		try {

			QueryResponse response = client.query(query);

			List<DOPSchema> results = response.getBeans(DOPSchema.class);
			if (results.size()>0){
				
				 searchResult = results.get(0);
			}

			//System.out.println("Previous Page Id:" + searchResult.getId());

		} catch (SolrServerException ex) {

			System.out.println("getPreviousPage:" + ex.getMessage());
			
		} catch (IOException ex) {

			System.out.println("getPreviousPage:" + ex.getMessage());
		} catch (Exception ex) {

			System.out.println("getPreviousPage:" + ex.getMessage());
		}

		return searchResult;

	}
	
	@Override
	public String getTotalPages(){
		
		
		long numFound=0;
		
		System.out.println("EJB Function: getTotalPages Invoked");
		
		String solr_string = prop.getProperty("solr_url") + "/" + prop.getProperty("solr_core");

		SolrClient client = new HttpSolrClient.Builder(solr_string).build();

		SolrQuery query = new SolrQuery("page_no:" + "*");
		query.setFields("id,page_no");
		
		try{
			
			QueryResponse response = client.query(query);
			
			SolrDocumentList rs = response.getResults();
			
			numFound=rs.getNumFound();
			
		}
		 catch (SolrServerException ex) {

				System.out.println("getTotalPages:" + ex.getMessage());
				
		}catch (IOException ex) {

				System.out.println("getTotalPages:" + ex.getMessage());
		}
		catch(Exception ex){
			
			System.out.println("getTotalPages:" + ex.getMessage());
		}
		
		return Long.toString(numFound);
	}
	
	@Override
	public List<DOPMeataDataSchema> getCurrentPageMetaData(String page_no){
		
		
		System.out.println("EJB Function: getCurrentPageMetaData Invoked");
		
		List<DOPMeataDataSchema> final_result =new ArrayList<DOPMeataDataSchema>();
		
		String result=this.validatePageNo(page_no);
		
		if(result.equals("N/A")){
			
			page_no="5";
			
		}else{
			
			page_no=result;
		}
		
		//String solr_string = "http://localhost:8983/solr/" + "dop_metadata";
		String solr_string = prop.getProperty("solr_url") + "/" + prop.getProperty("solr_metadata_core");
		
		SolrClient client = new HttpSolrClient.Builder(solr_string).build();
		
		SolrQuery query = new SolrQuery("page_no:" + page_no);

		query.setFields("id,dop_section,dop_clause,dop_subclause,dop_romansubclause,page_no,remarks_page_no");
		
		try{
			
			QueryResponse response = client.query(query);
			List<DOPMeataDataSchema> metadata_result=response.getBeans(DOPMeataDataSchema.class);
			
			final_result=metadata_result;
			
		}
		 catch (SolrServerException ex) {
				
			 	ex.printStackTrace();
				
		} catch (IOException ex) {

				ex.printStackTrace();
		}
		
				
		return final_result;
	
	}
	

	// - Private Utility Method---
	
	

	private static boolean isNullOrEmpty(String str) {
		//StringUtils.is
		
		if (str != null && !str.trim().isEmpty())
			return false;
		return true;
	}

	private Properties getSolrConfigProperties() {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "ecm_ejb_config.properties";
			input = this.getClass().getClassLoader()
					.getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return null;
			}

			// load a properties file from class path, inside static method
			prop.load(input);

			// get the property value and print it out
			//System.out.println(prop.getProperty("solr_url"));
			//System.out.println(prop.getProperty("solr_core"));

		} catch (IOException ex) {

			ex.printStackTrace();
			prop = null;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return prop;
	}

	private String validatePageNo(String page_no){
		
		page_no=StringUtils.stripStart(page_no,"0");
		
		String result="N/A";
		
		if(!isNullOrEmpty(page_no)){
			
				try{
					
					
					
					int temp = Integer.parseInt(page_no);
					int totalCount=Integer.parseInt(this.getTotalPages());
					
					if(temp >=1 && temp <= totalCount){
						
						result=page_no;
						
					}
					
				}
				catch(NumberFormatException ex){
					
					System.out.println("validatePageNo:" + ex.getMessage());
					result="N/A";
				}

		}else{
			
			result="N/A";
		}

		return result;
	}
	
	private static void destroyBuffer(Buffer buffer) {
	    if(buffer.isDirect()) {
	        try {
	            if(!buffer.getClass().getName().equals("java.nio.DirectByteBuffer")) {
	                Field attField = buffer.getClass().getDeclaredField("att");
	                attField.setAccessible(true);
	                buffer = (Buffer) attField.get(buffer);
	            }

	            Method cleanerMethod = buffer.getClass().getMethod("cleaner");
	            cleanerMethod.setAccessible(true);
	            Object cleaner = cleanerMethod.invoke(buffer);
	            Method cleanMethod = cleaner.getClass().getMethod("clean");
	            cleanMethod.setAccessible(true);
	            cleanMethod.invoke(cleaner);
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	
	
}

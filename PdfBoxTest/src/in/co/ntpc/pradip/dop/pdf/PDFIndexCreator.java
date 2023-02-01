package in.co.ntpc.pradip.dop.pdf;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.security.auth.login.LoginException;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
//import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;


import org.ldaptive.BindConnectionInitializer;
import org.ldaptive.BindOperation;
import org.ldaptive.ConnectionConfig;
import org.ldaptive.ConnectionFactory;
import org.ldaptive.Credential;
import org.ldaptive.DefaultConnectionFactory;
import org.ldaptive.LdapEntry;
import org.ldaptive.LdapException;
import org.ldaptive.SearchExecutor;
import org.ldaptive.SearchResult;
import org.ldaptive.auth.AccountState;
import org.ldaptive.auth.AuthenticationRequest;
import org.ldaptive.auth.AuthenticationResponse;
import org.ldaptive.auth.AuthenticationResultCode;
import org.ldaptive.auth.Authenticator;
import org.ldaptive.auth.BindAuthenticationHandler;
import org.ldaptive.auth.SearchDnResolver;
import org.ldaptive.auth.ext.ActiveDirectoryAuthenticationResponseHandler;
import org.ldaptive.auth.ext.PasswordPolicyAuthenticationResponseHandler;
import org.ldaptive.control.PasswordPolicyControl;

import in.co.ntpc.pradip.dop.model.DOPMetaDataSchema;
import in.co.ntpc.pradip.dop.model.DOPSchema;



public class PDFIndexCreator {

	public static void main(String[] args) {
		
		//PDFIndexCreator indexer=new  PDFIndexCreator();
		
		
		//Metadata Update
		//indexer.solrMetaDataUpdate();
		
		//indexer.solrDOPDBUpdate();
		
		//Deleting data
		//indexer.deleteAll();
		
		/*
		
		ActiveDirectoryAuthentication auth=new ActiveDirectoryAuthentication("ntpc.orgn");
		
		try
		{
			System.out.println("Random OTP:" + TimeBasedOneTimePasswordUtil.generateBase32Secret(6));
			System.out.println("Auth:" + auth.authenticate("009697", "Rainy@2ggg019"));
			
			SMSUtil.sendSms("9438334255", "PRADIP One Time Password(OTP) for user Susanta Goswami:009697 Login At: 25/09/2019 12:45PM is: 5683");
			
		}
		
		catch(LoginException ex){
			
			System.out.println("Auth:" + "false");
			
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}	
		
		*/
		
		
	
	}
	
	
	@SuppressWarnings({ "unused"})
	private void solrMetaDataUpdate() {
		
		 //File file = new File("G:\\pdf_sample\\dop_index_clause_IV.txt");
		 File file = new File("G:\\pdf_sample\\cw_wing.txt");
		 
		 //SolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr/").build();	 
		 SolrClient client = new HttpSolrClient.Builder("http://10.3.0.163:8983/solr/").build();
		 
		 System.out.println("Metadata Update Started"); 
			 
		 try{
			 
			 BufferedReader br = new BufferedReader(new FileReader(file));
			 	
			 String st;
			 while ((st = br.readLine()) != null) {
	
				   
				    String[] array=st.split("\t");
				    
				    System.out.println("Section: " + array[0] + " Clause: " + array[1] + " Subclause: " + array[2]
				    		+ " Romanclause: " + array[3] + " Pageno: " + array[4] + " Remarks Page: " + array[5] + " Amendment Page: " + array[6]);
				    
				   
				    DOPMetaDataSchema schema=new DOPMetaDataSchema();
				    
				    schema.setDop_section(array[0]);
				    schema.setDop_clause(array[1]);
				    schema.setDop_subclause(array[2]);
				    schema.setDop_romansubclause(array[3]);
				    
				    schema.setPage_no(array[4]);
				    schema.setRemarks_page_no(array[5]);
				    schema.setAmendment_page_no(array[6]);

				    				    
				    UpdateResponse response = client.addBean("dop_metadata", schema);
				     
				    client.commit("dop_metadata");
			 } 
			 
			 
			 br.close();
			 
	
		}
		catch(SolrServerException ex) {
		
			ex.printStackTrace();
		}
		catch (IOException e) {
				
			e.printStackTrace();
		}
		
		System.out.println("Metadata Update Completed"); 
	}
	
	
	@SuppressWarnings({ "unused"})
	private void solrDOPDBUpdate() {
		
		//Loading an existing document
		File file = new File("G:\\pdf_sample\\GARG\\searchable.pdf");
	    SolrClient client = new HttpSolrClient.Builder("http://10.3.0.163:8983/solr/").build();
	     
	    int current_page_count=281;
	    
	    System.out.println("DOP-DB Upload Starts After Page:"+ current_page_count);
		
		try {
			
			 PDDocument document = PDDocument.load(file);
			 int noOfpages=document.getPages().getCount();
			 
			 System.out.println("No of  Page :" + noOfpages);
			 
			 
			 //Instantiate PDFTextStripper class
		     //PDFTextStripper pdfStripper = new PDFTextStripper();
			  PDFTextStripper pdfStripper = new PDFLayoutTextStripper();
		      PDFRenderer pdfRenderer = new PDFRenderer(document);
			 
		      for(int i = 1; i <= noOfpages; i++) {
				 
				 //InputStream stream_content=document.getPage(i-1).getContents();
				 //byte[] bytes=IOUtils.toByteArray(stream_content);
				 
				
				  System.out.println("Content of Page:" + i);
			     
			      pdfStripper.setStartPage(i);
			      pdfStripper.setEndPage(i);
			      
			    
			      //Retrieving text from PDF document
			      String text = pdfStripper.getText(document);
			      
			      			      			      
			      System.out.println(text);
			      
			      BufferedImage bim = pdfRenderer.renderImageWithDPI(i-1, 200, ImageType.RGB);
			      //ImageIOUtil.writeImage(bim, "dop" + "-" + Integer.toString(current_page_count + i) + ".png", 200);
					
				  //Image Byte
			      
			      ByteArrayOutputStream baos = new ByteArrayOutputStream();
			      ImageIO.write(bim, "png", baos);
			      byte[] image_byte=baos.toByteArray();
			      
			      
			      
			      DOPSchema data=new DOPSchema();		      
			      data.setPage_no(Integer.toString(current_page_count + i));
			      data.setPage_content(text);
			      data.setPage_image(ByteBuffer.wrap(image_byte));;
			      
			      
			      UpdateResponse response = client.addBean("dop_db", data);
			     
			      client.commit("dop_db");
			  		
			 }
		
		}
		catch (IOException e) {
			
			
		}catch(Exception ex){
			
			ex.printStackTrace();
		}

		System.out.println("DOP-DB Upload Complete");
		
	}

	@SuppressWarnings("unused")
	private void deleteAll(){
		
		//Preparing the Solr client 
	      String urlString = "http://10.3.0.163:8983/solr/dop_db"; 
	      SolrClient Solr = new HttpSolrClient.Builder(urlString).build();   
	      
	      System.out.println("Deleting Started"); 
	      //Preparing the Solr document 
	      SolrInputDocument doc = new SolrInputDocument();   
	        
	      try {
		     
	    		  
	    		  Solr.deleteByQuery("*:*");        
			         
			      //Saving the document 
			      Solr.commit(); 
	    	
	      
	      }catch(SolrServerException ex) {
	    	  
	    	  ex.printStackTrace();
	      }
	      catch (IOException e) {
		
	    	  e.printStackTrace();
	      }
	      System.out.println("Specified Documents deleted"); 
		
	}
	

	@SuppressWarnings("unused")
	private SolrClient getDBClient(){
		
		CredentialsProvider provider = new BasicCredentialsProvider();
	    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
	            "myusername","mypassword");
	    provider.setCredentials(AuthScope.ANY, credentials);                
	    HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();   
	    
	   String urlString = "http://localhost:8983/solr/dop_metadata"; 
	    
	   // HttpSolrClient solrClient = new HttpSolrClient(urlString, client);
	   return null;
	}
	

}

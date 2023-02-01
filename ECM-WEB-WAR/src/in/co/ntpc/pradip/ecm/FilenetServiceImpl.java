package in.co.ntpc.pradip.ecm;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.security.auth.Subject;




import javax.xml.bind.DatatypeConverter;



import com.filenet.api.collection.ContentElementList;
import com.filenet.api.constants.*;
import com.filenet.api.core.*;
import com.filenet.api.exception.*;
import com.filenet.api.property.FilterElement;
import com.filenet.api.property.PropertyFilter;
import com.filenet.api.util.Id;
import com.filenet.api.util.UserContext;



@WebService(endpointInterface = "in.co.ntpc.pradip.ecm.FilenetService")  
public class FilenetServiceImpl implements FilenetService{
	
	private static final class ConfigInfo
	 {
	     /**
	      * The sample shows the use of a JAAS login and also the use of the
	      * API helpers for callers who still use the userid/password model.
	      * Which is which is controlled by this variable.  In either case, you
	      * will need to have configured JAAS for your JVM.
	      */
	     static boolean USE_EXPLICIT_JAAS_LOGIN = false;
	     /**
	      * If you use the API helpers, you need a userid and password.  To keep
	      * the sample simple, we've assumed the explicit JAAS login also needs
	      * them (via callbacks).
	      */
	     static String USERID = "noida.ecm9";
	     /**
	      * See comments for USERID.
	      */
	     static String PASSWORD = "INdia@1234";
	     /**
	      * More JAAS configuration.  Which stanza within our JAAS configuration
	      * should you use?
	      */
	     static String JAAS_STANZA_NAME = "Others";
	     /**
	      * This URI tells us how to find the CE and what protocol to use.
	      */
	     static String CE_URI = "http://ecmdev.ntpc.co.in/wsi/FNCEWS40MTOM/";
	     /**
	      * This ObjectStore must already exist.  It's where all the
	      * activity will happen.
	      */
	     static String OBJECT_STORE_NAME = "DREAMSII";
	     /**
	      * Documents will be filed into this folder.  If it doesn't exist, it
	      * will be created (directly under the root folder).  Don't include
	      * any slashes in this folder name.
	      */
	     static String FOLDER_NAME = "DRAWINGS";
	     /**
	      * This is the local file from which we will take content for the
	      * newly created repository document.  There aren't any particular
	      * constraints on size or type.
	      */
	     //static String LOCAL_FILE_NAME = "c:/Form.pdf";
	     /**
	      * Completely unnecessary for the system, but people tend to like
	      * seeing this populated.
	      */
	    // static String DOCUMENT_TITLE = "My Document Title new";
	     /**
	      * We set this explicitly to illustrate that it's unrelated to the
	      * DocumentTitle as well as the original file name.  When you are
	      * referencing a document by path, you use the containment name.
	      */
	     static String CONTAINMENT_NAME = "I Am a Contained Document";
	     /**
	      * You get your choice on the readback.  You can either read and
	      * compare from the beginning or you can skip to a quasi-random point
	      * in the second half of the content and start there.
	      */
	     static boolean USE_SKIP = true;
	 }
	
	

		@SuppressWarnings("unchecked")
		@Override
		public String FilenetConnection(byte[] content,Metadata data) {

	        try{
	        
	    	
			//String JAASStanzaEJB = "FileNetP8";
			String JAASStanzaWSI = "FileNetP8WSI";
			//String uri = "iiop://ecmdev.ntpc.co.in:2809/FileNet/Engine/";
			//String uriwsi = "http://vmnav02:9080/wsi/FNCEWS40MTOM/";
			String uriP = "https://ecmdev.ntpc.co.in/wsi/FNCEWS40MTOM/";
			String username = "noida.ecm9";
			String password = "INdia@1234";
			String objectStoreName = "DREAMSII";
			Connection con = Factory.Connection.getConnection(uriP);
			Subject sub = UserContext.createSubject(con, username, password, JAASStanzaWSI);
			UserContext.get().pushSubject(sub);
			
			Domain dom = Factory.Domain.fetchInstance(con, null, null);
			ObjectStore os = Factory.ObjectStore.fetchInstance(dom, objectStoreName, null);
			
			System.out.println("OS Name:" + os.get_DisplayName());
			
			
			//byte[] byte_array=test.getBytes();
		     InputStream targetStream = new ByteArrayInputStream(content);

		     // This is pretty standard for creating content for a brand new
		     // Document.  Since it is just being created, we know its ContentElements
		     // property will be an empty list, so it's no harm to just replace it
		     // with one cooked up from the Factory.
		     //
		     //no R/T
		     ContentTransfer ct = Factory.ContentTransfer.createInstance();
		     ct.setCaptureSource(targetStream);
		     // optional
		     ct.set_RetrievalName("Fist PDF");
		     // optional
		     ct.set_ContentType("application/pdf");
		     
		     ContentElementList cel = Factory.ContentElement.createList();
		     cel.add(ct);

		     //no R/T
		     Document doc = Factory.Document.createInstance(os, "NTPC_DRAWING");
		     //not required
		     doc.getProperties().putValue("NTPC_DRG_NO",data.getNTPC_DRG_NO());
		     doc.getProperties().putValue("VENDOR_DRG_NO",data.getVENDOR_DRG_NO());
		     doc.getProperties().putValue("MAIN_VENDOR_CODE",data.getMAIN_VENDOR_CODE());
		     doc.getProperties().putValue("MAIN_VENDOR_DESC",data.getMAIN_VENDOR_DESC());
		     doc.getProperties().putValue("SUB_VENDOR_CODE",data.getSUB_VENDOR_CODE());
		     doc.getProperties().putValue("SUB_VENDOR_DESC",data.getSUB_VENDOR_DESC());
		     doc.getProperties().putValue("DRG_KEY_TITLE",data.getDRG_KEY_TITLE());
		     doc.getProperties().putValue("DRG_TITLE",data.getDRG_TITLE());
		     doc.getProperties().putValue("DRAWING_DSCPLN",data.getDRAWING_DSCPLN());
		     doc.getProperties().putValue("APPRVL_CODE",data.getAPPRVL_CODE());
		     doc.getProperties().putValue("APPRVL_DESC",data.getAPPRVL_DESC());
		     doc.getProperties().putValue("DOC_CATG_CODE",data.getDOC_CATG_CODE());
		     doc.getProperties().putValue("DOC_CATG_DESC",data.getDOC_CATG_DESC());
		     doc.getProperties().putValue("MDL_KEY",data.getMDL_KEY());
		     doc.getProperties().putValue("PROJECT_CODE",data.getPROJECT_CODE());
		     doc.getProperties().putValue("PROJECT_DESC",data.getPROJECT_DESC());
		     doc.getProperties().putValue("PROJECT_LONG_DESC",data.getPROJECT_LONG_DESC());
		     doc.getProperties().putValue("PKG_CODE",data.getPKG_CODE());
		     doc.getProperties().putValue("PKG_DESC",data.getPKG_DESC());
		     doc.getProperties().putValue("PKG_LONG_DESC",data.getPKG_LONG_DESC());
		     doc.getProperties().putValue("SUB_STM_CODE",data.getSUB_STM_CODE());
		     doc.getProperties().putValue("SUB_STM_DESC",data.getSUB_STM_DESC());

		     doc.set_ContentElements(cel);
		     //no R/T
		     doc.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);  

		     // The API provides the convenience method Folder.file(), but it's so simple
		     // to just create the RCR/DRCR directly that we do it that way here.
		     Folder folder = instantiateFolder(os);
		     //no R/T
		     DynamicReferentialContainmentRelationship rcr = 
		         Factory.DynamicReferentialContainmentRelationship.createInstance(os, null, 
		                            AutoUniqueName.AUTO_UNIQUE, 
		                            DefineSecurityParentage.DO_NOT_DEFINE_SECURITY_PARENTAGE);
		     rcr.set_Tail(folder);
		     rcr.set_Head(doc);
		     rcr.set_ContainmentName(ConfigInfo.CONTAINMENT_NAME);
		    
		    
		     
		     // To minimize the number of R/Ts, we save both the document and the RCR in 
		     // a single batch.  So, we've created a document, added content to it, checked
		     // it in, filed it in a folder, and it only cost a single R/T.  Woo-hoo!
		     //
		     // (There was actually another R/T to check for the existence of the
		     // folder.  If we already knew we could depend on that, which would normally
		     // be the case in a real application, we wouldn't need that extra R/T.)
		     //
		     // ((OK, there are additional implicit R/Ts if the content being uploaded exceeds
		     // the configured chunk size for the API transport.))
		     UpdatingBatch ub = UpdatingBatch.createUpdatingBatchInstance(dom, RefreshMode.REFRESH);
		     ub.add(doc, null);
		     
		     ub.add(rcr, null);
		     //System.out.println("Doing updates via UpdatingBatch");
		     ub.updateBatch();
		     
		     // We used refresh mode on the batch because we wanted to get back the
		     // RCR containment name.  Because we asked the server to make a unique
		     // name (rather than throwing an exception on a collision), the actual
		     // containment name could be different from the value we passed in.
		     //
		     // We could have used property filters to get back just that single
		     // property on that single object, but we didn't want to clutter this
		     // sample code with property filter stuff.  PropertyFilters are very
		     // valuable to optimizing performance.
		     System.out.println("Filenet Doc id = " + doc.get_Id().toString());
		     
		    
		     
		     return doc.get_Id().toString();
			
			
			
			
			//return os.get_DisplayName();
		
			
		}	
		catch(Exception ex){
			System.out.println("ERROR" + ex.getMessage());
			return "Exeception";
		}
	    finally{
            	
	    	UserContext.get().popSubject();
        }
	}
	
	
	private Folder instantiateFolder(ObjectStore os)
	{
	     Folder folder = null;
	     try
	     {
	         //no R/T
	         folder = Factory.Folder.createInstance(os, null);
	         //no R/T
	         Folder rootFolder = Factory.Folder.getInstance(os, null, "/");
	         folder.set_Parent(rootFolder);
	         folder.set_FolderName(ConfigInfo.FOLDER_NAME);
	         //R/T
	         folder.save(RefreshMode.NO_REFRESH);
	     }
	     catch (EngineRuntimeException ere)
	     {
	         // Create failed.  See if it's because the folder exists.
	         ExceptionCode code = ere.getExceptionCode();
	         if (code != ExceptionCode.E_NOT_UNIQUE)
	         {
	             throw ere;
	         }
	         //System.out.println("Folder already exists: /" + ConfigInfo.FOLDER_NAME);
	         //no R/T
	         folder = Factory.Folder.getInstance(os, null, "/" + ConfigInfo.FOLDER_NAME);
	     }
	    
	     return folder;
	 }


	@Override
	public byte[] getBytes() {
		
		return "Test".getBytes();
	}


	@Override
	public String ReadDrawing() {
		
		
				//System.out.println("docid: " +file.getDocid());
					
		
				String codec="N/A";
	
		
				//return "String".getBytes();
				//String fullPath = "/" + ConfigInfo.FOLDER_NAME + "/" + containmentName;
				//System.out.println("Document: " + fullPath);
				
				//Document doc = Factory.Document.getInstance(os, "NTPC_DRAWING", fullPath);
	           
	                //String JAASStanzaEJB = "FileNetP8";
	                String JAASStanzaWSI = "FileNetP8WSI";
	                //String uri = "iiop://ecmdev.ntpc.co.in:2809/FileNet/Engine/";
	                //String uriwsi = "http://vmnav02:9080/wsi/FNCEWS40MTOM/";
	                String uriP = "https://ecmdev.ntpc.co.in/wsi/FNCEWS40MTOM/";
	                String username = "noida.ecm9";
	                String password = "India@1234";
	                String objectStoreName = "DREAMSII";
	               // byte[] bytecontent = null;
	                try{
	                
		                Connection con = Factory.Connection.getConnection(uriP);
		                Subject sub = UserContext.createSubject(con, username, password, JAASStanzaWSI);
		                UserContext.get().pushSubject(sub);
		                
		                Domain dom = Factory.Domain.fetchInstance(con, null, null);
		                ObjectStore os = Factory.ObjectStore.fetchInstance(dom, objectStoreName, null);
		                ///////////////////////////////
		                //Document doc = Factory.Document.getInstance(os, "NTPC_DRAWING",new Id(docid));
					   
		                System.out.println("OS Name:" + os.get_DisplayName());
		        		
		        		//List<NoteSheet> lst=new ArrayList<NoteSheet>();
		        		
		        	//	String access_denied="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAMAAACahl6sAAAC91BMVEVMaXHzQCjgOyjhOibiOyjhOifhOybiOyjzQCngOybhOyfpPCjxPyrxPinxPyrvPirwPynvPinwPyvfOifdOiboPCjyPyrvPyrwPyrwPizfOybqPSjtPSjcOibqPSiRJBvqPinpPCnoPCjaOSbqPSidLB3kOyjqPijnOyeaIxvkOyfqPSnqPingOyfeOSabKRvzQCraOSXwPinbNyfzQCrzPyr1PyrxPyrzPynUNyTrPijxQCryQCrzQCqdKRryPyrxQCmfKhzxQCrwQCrPNiPsPSjxPyrxPiqhKR3xPyucKRubKRrTAADXOCTrPSnzPyrvPinvOyXvOSTvOybvPCbvOiTvOiXwQCvuMRvuLxjuNR7uLBXxTTnxVkPwSTXuMBrvNyHvPSjvNiDuMx3wSjbuLhfuNB3xVEHxTzzxUD3uKBH0QCr2jYH4p534o5n3lov0dmf5san70Mr82NT829f819P7zcj4qJ/za1vvOCLvNR/vPSf0cmL4raT8zsn4rKPzbl/uOiXwRDD1f3H4qqH1h3rwRjHxUj/3lYn7xb/81M/7zMb5tq30cWH82dX82tacJxj94t//+vr/+fn+8/L7yML96OX///7/////+/v95OH6vbbyXErzZ1f7xL795uT//f3//Pz95eP6wrvzZVT5s6vwTDj1iXz+7+3wQi7yQCn1gXPzYlH0dGT939v81tH5urLvOSP+7ev5uLD80s3yXkz0fG7+6efwRzP0c2T+8O/6v7jwQSz0eWv+6+nyWEb0e2zyWkj5t6/5r6b+9vXyYVD94N33mY73nZL4oJX1g3X949/2jH/0eGn4pJr1hnjwRzL4oZfuMBn3nJH+6uj2lIj+8vD83dnyYE72jID3n5TwSDPyPir/+PjxV0X7ycT8087/9/b6u7T1hHf2kob2kISSJhfzaVnoPCj2joL+9fTaOCbzbV3/9vTyXUuOOADiPCfiOifiOifdOyfqPCnwPivxPyvxPyryPyryPyndOibqPincOCZ+IUzQAAAA/XRSTlMAHzFXdp22sRmF2PLy9Pb4+vw0dLjn8f//InDnLYDVHHrXR67qEWbRaA6C396BXj29jvxN95jrmMp24v+m8ibCw03T/1vchYZPpD9BAUzP/P////////////////////////////////////P///////////////////////////////////////////////8U//////////////////////////////////r/////////////////////////////////////////////////////////////////+P///////////yH/ef//fv///wRZh6DX9zXU1KiakUmxc7vOBgAAEY1JREFUeAHsz4MVA0EUAMAz195z+m8yD7HNPx1McDcAAAAAAABAGMVJkmb5UpYmSRyF31Qo4jKtaoQJpYyvMUoJRnWVlnHx+QkhlTaYMuvsYc4xio1WUnxuwjeqRZQ7e57jFLWq8R+46PrBkIsS6wwxQ9991GKcBkOdvZ6jZjaNc+bp6sBhIIaiaAHBr3laZgozJ2aK7f7LWW7AIM3cDo7AFMbj03ObUDZqPz89GqB4eX1TXUKVqKve3l/0Mj56/RahetTq9z50MgZtQj1Re6CL8jH8Z9RHGeqgjG7/GXVSbkfSjPFEEeqP1GQselXTqxl4ml1N5e5r/rYgcEWLt7mQY7kicEartch3bDrgrrPh/5Ttbg/+9rsts+NwJEhExwMn42R1IVXXPrE5HNeDXHvXYXL4wR6S7QOfxRGuCLLRKuRwKMinIgGHSCoUcIjUDOUd+q9LyKH/uiIFnTXr2om/gt5Wfi0OJyDojYK4BsfJ3UN3e/dUHWJ50J9nVXYkHkzIO1R0bBcwo8W2kuO8I5gR7dIKjmzjwZS8TVYesp7BnGbL0o78i53z/GobexPw9p7kTL3be/9tb9/2H9gqW7Ily9hIbtjs0Ksw1Q6mp4K5oSahDyTEFMcQ44T0QhkIQxhI78my0+f76l6LyE6wQuItwMkz/T1HjB7eW6X76h/AZuIf/vVtX338G9hc/NtbvkT5638Hm4t//+u38vjnX3uzEYtQqUni5YiKAIpQag0ZfY1Wo6FijVy/9lYj11/RMW+Z0el0DEXLEZpi9WyCwZio53gpwpv0ZovBwuhNKEKwVp2M1Yb9NIl62pBkserNWhogCJOesSc56GSdiVh35Pqrt3n/8R8xPchPUlJT0uwq2YMB6RmZWdk5uXn5QgGOmJ2FRWIkq7iktMxGO13uolSZ3eUeClCmisqq6uzsnJracnsyunFBqCsp3pO9t7oqtZBlafAq//jm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";
		        		
		        		//String pre="data:image/gif;base64,";
		        		
		        		byte[] buf=null;
		        		// Get document and populate property cache.
		        		PropertyFilter pf = new PropertyFilter();
		        		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.CONTENT_SIZE, null) );
		        		pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.CONTENT_ELEMENTS, null) );
		        		Document doc=Factory.Document.fetchInstance(os, new Id("{3080A76E-0000-C21A-A748-F5F4A16BED3F}"), pf );
		        		
		        		
		        		//Document doc = Factory.Document.getInstance(os, "NTPC_DRAWING",new Id("{3080A76E-0000-C21A-A748-F5F4A16BED3F}"));
		        		
		        						
		        			System.out.println("Sufficient Access Available");
		        			
		        			// Print information about content elements.
		        			System.out.println("No. of document content elements: " + doc.get_ContentElements().size() + "\n" + "Total size of content: " + doc.get_ContentSize() + "\n");
	
		        			// Get content elements and iterate list.
		        			ContentElementList docContentList = doc.get_ContentElements();
		        			Iterator iter = docContentList.iterator();
		        			while (iter.hasNext() )
		        			{
		        			    ContentTransfer ct = (ContentTransfer) iter.next();
	
		        			    // Print element sequence number and content type of the element.
		        			    System.out.println("\nElement Sequence number: " + ct.get_ElementSequenceNumber().intValue() + "\n" + "Content type: " + ct.get_ContentType() + "\n");
	
		        			    // Get and print the content of the element.
		        			    int docLen = ct.get_ContentSize().intValue();
		        			    buf = new byte[docLen];
		        			    InputStream stream = ct.accessContentStream();
		        			    try
		        			    {
		        			        stream.read(buf, 0, docLen);
		        			        //String readStr = new String(buf);
		        			       // System.out.println("Content:\n " + readStr);
		        			        stream.close();
		        			        
		        			        codec =  DatatypeConverter.printBase64Binary(buf);
		        			        
		        			    }
		        			    catch(IOException ioe)
		        			    {
		        			        ioe.printStackTrace();
		        			    }
		        			   
		        			}
		        			
	                }
	                catch(Exception ex){
	                	
	                	ex.printStackTrace();
	                }
	                finally{
	                	UserContext.get().popSubject();
	                }
	                	                
	                return codec;
		}
		
}
	        		 

	                
         
		
	


package in.co.ntpc.pradip.dop.rs;

import in.co.ntpc.pradip.dop.ejb.stateless.solr.SolrOperationLocal;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.google.gson.Gson;


@Path("/SolrOperation")
public class SolrResource {
	
	Gson gson = new Gson();
	SolrOperationLocal  operation=null;
	
	public SolrResource(){
		
		final String jndi = "java:global/DOP-ROOT-EAR/DOP-EJB-JAR/SolrOperation!in.co.ntpc.pradip.dop.ejb.stateless.solr.SolrOperationLocal";
		try{
			
			 operation=(SolrOperationLocal) new InitialContext().lookup(jndi);
		}
		catch(NamingException ex){
			System.out.println(ex.getMessage());
			
		}
		
		
	}
	
	@GET
	@Path("/getToken")
    public String getBookCollectionInfo(@CookieParam("LtpaToken2") String token) {
        /* The cookie value is passed to the mycustomid variable. */
		
		
		String result="N/A";
		
		if(token.isEmpty() || token==null){
			
			result="N/A";
			
		}else{
			result=token;
		}
			
		
		return result;
    }
	
	@GET
	@Path("/getSection")
	public String getSection(){
		
		
			
		String result=gson.toJson(operation.getDopSection());
			
		
        return result;
    }
	
	@GET
	@Path("/getClause")
	public String getClause(@QueryParam("section") String section){
		
		
		String result=gson.toJson(operation.getClauseBySection(section));
		
		return result;
		
	}
	
	@GET
	@Path("/getSubClause")
	public String getSubClause(@QueryParam("section") String section,@QueryParam("clause") String clause){
		
		String result=gson.toJson(operation.getSubClauseByClauseAndSection(clause, section));
		
		return result;
		
	}
	
	@GET
	@Path("/getRomanClause")
	public String getRomanClause(@QueryParam("section") String section,
			@QueryParam("clause") String clause,
			@QueryParam("subclause") String subclause){
		
		String result=gson.toJson(operation.getRomanClauseBySubClauseAndClauseAndSection(subclause, clause, section));
		
		return result;
		
	}

}

package in.co.ntpc.pradip.secure.servlet;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.login.CredentialExpiredException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ibm.websphere.security.WSSecurityException;
import com.ibm.websphere.security.WSSecurityHelper;
import com.ibm.websphere.security.auth.CredentialDestroyedException;
import com.ibm.websphere.security.auth.WSSubject;
import com.ibm.websphere.security.cred.WSCredential;



/**
 * Servlet implementation class SecurityServlet
 */
@WebServlet(description = "Security Servlet", urlPatterns = { "/Admin/SecurityServlet" })
public class SecurityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Gson gson = new Gson();
    
    public SecurityServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Get The subject from JASS context
		//Subject subject=Subject.getSubject(AccessController.getContext());
		
		//int list_of_principal=subject.getPrincipals().size();
		
		/** Subject
		
		a) contains multiple principal as identities
		b) private credentials
		c) public credentials
		
		 **/
		
		//WSSecurityHelper.getLTPACookieFromSSOToken();
		
		
		ServletRequest r=(ServletRequest)request;
		
		
		if (WSSecurityHelper.isServerSecurityEnabled()){
			
			//Deprecated
			try {
				WSSecurityHelper.getLTPACookieFromSSOToken();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//Currently available api
			//com.ibm.websphere.security.web.WebSecurityHelper.getSSOCookieFromSSOToken
			
			Subject callerSubject;
			try {
				callerSubject = WSSubject.getCallerSubject();
				
				Set<Principal> sp=callerSubject.getPrincipals();
				for(Principal p:sp){
					
					System.out.println(p.getName());
				}
				
				Set<WSCredential> wsCredentials =
						callerSubject.getPublicCredentials(WSCredential.class);
				
				Set<WSCredential> private_credentials=callerSubject.getPrivateCredentials(WSCredential.class);
				Iterator<WSCredential> it=private_credentials.iterator();
				
				
				
				Iterator<WSCredential> wsCredentialsIterator = wsCredentials.iterator();
				if (wsCredentialsIterator.hasNext()) {
					WSCredential wsCred = wsCredentialsIterator.next();
					try {
						// Print out the groups
						ArrayList<String> groups =wsCred.getGroupIds();
						for (String group : groups) {
							System.out.println("Group name: " + group);
							
				
						}
						
						
					} catch (CredentialExpiredException e) {
						// record some diagnostic info
						return;
					} catch (CredentialDestroyedException e) {
						// record some diagnostic info
						return;
					}
				}
				
				
				
			} catch (WSSecurityException e) {
				System.out.println(e.getMessage());
			}
		}
		
		
		Principal principal=request.getUserPrincipal();
		
		String user_name=principal.getName();
		
		//response.setContentType("text/html");
	    //PrintWriter out = response.getWriter();
	    //out.println("<B>Secured Page: </B>" + user_name + "Principal Count:");		
	    
	    String nextJSP = "/secured_page.jsp";
	    request.setAttribute("username", user_name);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
		
	}

}

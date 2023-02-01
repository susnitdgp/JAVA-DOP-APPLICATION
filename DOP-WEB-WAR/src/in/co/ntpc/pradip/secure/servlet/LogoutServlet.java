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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.websphere.security.WSSecurityException;
import com.ibm.websphere.security.WSSecurityHelper;
import com.ibm.websphere.security.auth.CredentialDestroyedException;
import com.ibm.websphere.security.auth.WSSubject;
import com.ibm.websphere.security.cred.WSCredential;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/Authentication/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LogoutServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//WSSecurityHelper.getLTPACookieFromSSOToken();
		
				if (WSSecurityHelper.isServerSecurityEnabled()){
					
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
				
		
		// Invalidate current HTTP session.
	    // Will call JAAS LoginModule logout() method
	    request.getSession().invalidate();
	    request.logout();
	    
	    // Redirect the user to the secure web page.
	    // Since the user is now logged out the
	    // authentication form will be shown
	    String nextJSP = "/index.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
		
	}

}

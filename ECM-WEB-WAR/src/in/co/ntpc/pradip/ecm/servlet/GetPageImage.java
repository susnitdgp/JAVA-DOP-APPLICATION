package in.co.ntpc.pradip.ecm.servlet;

import in.co.ntpc.pradip.ecm.ConnectionResponse;
import in.co.ntpc.pradip.ecm.EcmService;
import in.co.ntpc.pradip.ecm.EcmServiceImpl;
import in.co.ntpc.pradip.ecm.NoteSheet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.security.auth.Subject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.filenet.api.core.Connection;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.util.UserContext;



/**
 * Servlet implementation class GetPageImage
 */
@WebServlet("/GetPageImage")
public class GetPageImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public GetPageImage() {
        super();
        
    }
    
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String docId=request.getParameter("docId");
		PrintWriter out = response.getWriter();
		
		
		try{
			
			String JAASStanzaEJB = "FileNetP8";
			String JAASStanzaWSI = "FileNetP8WSI";
			//String uri = "iiop://ecmdev.ntpc.co.in:2809/FileNet/Engine/";
			//String uriwsi = "http://vmnav02:9080/wsi/FNCEWS40MTOM/";
			String uriP = "https://ecmtest.ntpc.co.in/wsi/FNCEWS40MTOM/";
			String username = "009697";
			String password = "Autumn@2019";
			String objectStoreName = "PRADIPOS";
			Connection con = Factory.Connection.getConnection(uriP);
			Subject sub = UserContext.createSubject(con, username, password, JAASStanzaWSI);
			UserContext.get().pushSubject(sub);
			
			Domain dom = Factory.Domain.fetchInstance(con, null, null);
			ObjectStore os = Factory.ObjectStore.fetchInstance(dom, objectStoreName, null);
			
			out.println("OS Name:" + os.get_DisplayName());
			
		}	
		catch(Exception ex){
			out.println("ERROR" + ex.getMessage());
		}
		
		
		
		/*
		
		if (!isNullOrEmpty(docId)){
			
			ecmService.establishConnection();
			
			List<NoteSheet> result=ecmService.getDocumentContent(docId);
			
			ecmService.removeConnection();
			
			String nextJSP = "/WEB-INF/viewer.jsp";
			request.setAttribute("list_of_notesheet", result);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
			
		}else{
			
			
			PrintWriter out = response.getWriter();
			out.println("DOID Missing");
			
		}
		*/
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private static boolean isNullOrEmpty(String str) {
		//StringUtils.is
		
		if (str != null && !str.trim().isEmpty())
			return false;
		return true;
	}


}

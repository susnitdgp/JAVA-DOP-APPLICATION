package in.co.ntpc.pradip.ecm.servlet;

import javax.security.auth.Subject;

import com.filenet.api.core.Connection;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.util.UserContext;

import in.co.ntpc.pradip.ecm.ConnectionResponse;
import in.co.ntpc.pradip.ecm.EcmService;
import in.co.ntpc.pradip.ecm.EcmServiceImpl;

public class Start {

	public static void main(String[] args) {
		

		
	
	//String JAASStanzaEJB = "FileNetP8";
	String JAASStanzaWSI = "FileNetP8WSI";
	//String uri = "iiop://vmnav02:2809/FileNet/Engine/";
	//String uriwsi = "http://vmnav02:9080/wsi/FNCEWS40MTOM/";
	String uriP = "http://ecmdev.ntpc.co.in/wsi/FNCEWS40MTOM/";
	String username = "noida.ecm9";
	String password = "India@1234";
	String objectStoreName = "DREAMSII";
	Connection con = Factory.Connection.getConnection(uriP);
	Subject sub = UserContext.createSubject(con, username, password, JAASStanzaWSI);
	UserContext.get().pushSubject(sub);
	Domain dom = Factory.Domain.fetchInstance(con, null, null);
	ObjectStore os = Factory.ObjectStore.fetchInstance(dom, objectStoreName, null);
		
		
		
		System.out.println("Con Stat:" + os.get_DisplayName());
		
		
		

	}

}

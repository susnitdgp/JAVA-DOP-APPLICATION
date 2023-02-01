package in.co.ntpc.pradip.ecm;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface FilenetService {

	@WebMethod
	public String FilenetConnection(byte[] content,Metadata data);
	
	@WebMethod
	public String ReadDrawing();
	
	@WebMethod
	public byte[] getBytes();
}	
	
	


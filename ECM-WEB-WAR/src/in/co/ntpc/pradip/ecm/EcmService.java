package in.co.ntpc.pradip.ecm;

import java.util.List;


public interface EcmService {
	
	public ConnectionResponse establishConnection();
	public boolean isConnected();
	public void removeConnection();

	public List<NoteSheet> getDocumentContent(String docId);

}

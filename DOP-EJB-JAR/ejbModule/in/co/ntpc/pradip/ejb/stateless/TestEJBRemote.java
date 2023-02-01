package in.co.ntpc.pradip.ejb.stateless;

import in.co.ntpc.pradip.ejb.model.DOPSchema;

import java.nio.ByteBuffer;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface TestEJBRemote {
	
	public int addFromEjb(int a,int b);
	public List<DOPSchema> solrSearch(String content);

	public  ByteBuffer beanQyery(String id);

}

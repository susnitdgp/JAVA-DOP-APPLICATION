package in.co.ntpc.pradip.ejb.statefull.UserInfo;

import javax.ejb.Remote;

@Remote
public interface UserInfoEJBRemote {
	public int getUserVisitCount();

}

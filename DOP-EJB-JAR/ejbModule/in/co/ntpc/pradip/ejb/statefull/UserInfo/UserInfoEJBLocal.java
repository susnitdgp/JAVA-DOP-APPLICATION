package in.co.ntpc.pradip.ejb.statefull.UserInfo;

import javax.ejb.Local;

@Local
public interface UserInfoEJBLocal {

	public int getUserVisitCount();
}

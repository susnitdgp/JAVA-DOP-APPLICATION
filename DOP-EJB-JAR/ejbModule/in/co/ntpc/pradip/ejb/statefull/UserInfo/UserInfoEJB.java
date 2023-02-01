package in.co.ntpc.pradip.ejb.statefull.UserInfo;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class UserInfoEJB
 */
@Stateful
@LocalBean
public class UserInfoEJB implements UserInfoEJBRemote, UserInfoEJBLocal {
	
	private int userVisitCount;

    /**
     * Default constructor. 
     */
    public UserInfoEJB() {
    	userVisitCount=0;
    }
    
    public int getUserVisitCount(){
    	
    	return userVisitCount+1;
    }

}

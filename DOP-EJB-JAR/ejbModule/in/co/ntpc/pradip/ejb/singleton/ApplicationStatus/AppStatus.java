package in.co.ntpc.pradip.ejb.singleton.ApplicationStatus;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Session Bean implementation class AppStatus
 */
@Singleton
@LocalBean
public class AppStatus implements AppStatusRemote, AppStatusLocal {

    /**
     * Default constructor. 
     */
    public AppStatus() {
        
    }

}

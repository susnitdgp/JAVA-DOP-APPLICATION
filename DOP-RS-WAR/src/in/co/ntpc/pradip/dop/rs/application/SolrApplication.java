package in.co.ntpc.pradip.dop.rs.application;

import in.co.ntpc.pradip.dop.rs.SolrResource;

import java.util.HashSet;
import java.util.Set;

public class SolrApplication extends javax.ws.rs.core.Application {
	
	public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(SolrResource.class);
        return classes;
    }

}

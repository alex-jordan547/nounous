package nounous.jsf.util;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import nounous.commun.dto.Roles;


@ApplicationScoped
@Named
public class UtilAppli {
	
	
	@Produces
	@ApplicationScoped
	@Named( "roles" )
	public Collection<String> getRoles() {
		return Roles.getRoles();
	}

	public String getLibelléDeRole( String role )  {
		return Roles.getLibellé( role );
	}
	
	
	
	// Evènements du cycle de vie
	
//	public void applicationInit( @Observes @Initialized( ApplicationScoped.class )  ServletContext serletContext ) throws SecurityException, IOException {
//    	InputStream in = this.getClass().getResourceAsStream("/META-INF/logging.properties");
//    	if (in != null ) {
//        	LogManager logManager = LogManager.getLogManager();
//			logManager.readConfiguration( in );
//			in.close();
//    	}
//	}

}

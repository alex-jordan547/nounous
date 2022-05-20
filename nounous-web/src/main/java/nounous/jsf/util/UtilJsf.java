package nounous.jsf.util;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;


@Named
@RequestScoped
public class UtilJsf {
	
	
	// Constantes
	
	public static final String MSG_ERROR = "UtilJsf.MSG_ERROR";
	public static final String MSG_WARNING = "UtilJsf.MSG_WARNING";
	public static final String MSG_INFO = "UtilJsf.MSG_INFO";

	
	// Messages d'erreur
	
	public static void messageInfo( String message ) {
		addMessag( FacesMessage.SEVERITY_INFO, message, null );
	}
	
	public static void messageWarning( String message ) {
		addMessag( FacesMessage.SEVERITY_WARN, message, null );
	}
	
	public static void messageError( String message ) {
		addMessag( FacesMessage.SEVERITY_ERROR, message, null );
	}

	public static void messageError( Exception exception) {
		addMessag( FacesMessage.SEVERITY_ERROR, null, exception );
	}

	public static void addMessag( Severity severity, String message, Exception exception) {

		if ( exception != null ) {
			if (message == null ) {
				message = "Ecec du traitement demandé.";
			}
		}

		if ( message != null ) {
			FacesContext fc = FacesContext.getCurrentInstance();
			String[] lignes = message.split("\\n");
		    for ( String ligne : lignes ) {
		 		fc.addMessage( null, new FacesMessage( severity, ligne, null ) );
		    }
		    fc.getExternalContext().getFlash().setKeepMessages(true);
		}
	}
	
	
	// Message via un attribut de requête
	
	public static void messageFromRequestAttribute() {
		String message;
		message = UtilJsf.getRequestAttribute( MSG_ERROR );
		if( message != null ) {
			UtilJsf.messageError(message);
		}
		message = UtilJsf.getRequestAttribute( MSG_WARNING );
		if( message != null ) {
			UtilJsf.messageWarning(message);
		}
		message = UtilJsf.getRequestAttribute( MSG_INFO );
		if( message != null ) {
			UtilJsf.messageInfo(message);
		}
	}

	
	// Session
	
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttribute(String name) {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	  	return (T) ec.getSessionMap().get( name );
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T removeSessionAttribute(String name) {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	  	return (T) ec.getSessionMap().remove( name );
	}
	
	public static <T> void setSessionAttribute(String name, T value ) {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	  	ec.getSessionMap().put( name, value );
	}
	
	public static void sessionInvalidate() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		if( session != null ) {
			session.invalidate();
		}
	}

	
	// Requête
	
	public static String getRequestParameter( String nom ) {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		return ec.getRequestParameterMap().get( nom ); 
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getRequestAttribute(String name) {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	  	return (T) ec.getRequestMap().get( name );
	}
	
	public static void forward(String uri) throws IOException {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.dispatch(uri);
	}
	
	public static void navigate( String outcome ) {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getApplication().getNavigationHandler().handleNavigation( fc, null, outcome );
	}	
	
	
	// web.xml
	
	public static String getInitParameter( String nom  ) {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		return ec.getInitParameter (nom );
	}
	
	
	// Validation
	
	public static <T> boolean validate( T object, Class<?>... groups  ) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<T>> violations = validator.validate(object, groups );
		for (ConstraintViolation<T> violation : violations) {
			messageError( violation.getMessage() );
		}
		return violations.isEmpty();
	}
	
	
	// Now
	
	public static Date now() {
		return new Date();
	}

}

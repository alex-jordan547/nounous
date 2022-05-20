package nounous.jsf.model.standard;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import nounous.commun.dto.DtoCompte;
import nounous.commun.service.IServiceConnexion;
import nounous.jsf.data.Compte;
import nounous.jsf.util.CompteActif;
import nounous.jsf.util.UtilJsf;


@RequestScoped
@Named
public class ModelConnexion {

	// Champs

	private Compte			courant;

	@Inject
	private CompteActif		compteActif;
	@Inject
	private ModelInfo		modelInfo;
	@EJB
	private IServiceConnexion serviceConnexion;


	// Getters 
	
	public Compte getCourant() {
		if ( courant == null ) {
			courant = new Compte();
		}
		return courant;
	}

	
	// Actons
	
	public String connect() {
	    
	    DtoCompte dto = serviceConnexion.sessionUtilisateurOuvrir( courant.getPseudo(), courant.getMotDePasse() );
	    
	    if ( dto != null ){
	    	
//		    try {
//			    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//				( (HttpServletRequest) ec.getRequest() ).login( courant.getPseudo(), courant.getMotDePasse() );
//			} catch (ServletException e) {
//				throw new RuntimeException( e );
//			}

	        compteActif.setPseudo( dto.getPseudo() );
	        compteActif.setRoles( dto.getRoles() );
	        
	    	modelInfo.setTitre( "Bienvenue" );
	    	modelInfo.setTexte( "Vous êtes connecté en tant que '" + courant.getPseudo() +"'.");
		    return "info";

	    } else {
	        UtilJsf.messageError( "Pseudo ou mot de passe invalide." );
	    	return null;
	    }
	}	
}

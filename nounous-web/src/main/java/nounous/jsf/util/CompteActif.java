package nounous.jsf.util;

import static nounous.commun.dto.Roles.ADMINISTRATEUR;
import static nounous.commun.dto.Roles.NOUNOU;
import static nounous.commun.dto.Roles.PARENT;
import static nounous.commun.dto.Roles.UTILISATEUR;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import nounous.commun.dto.Roles;
import nounous.jsf.data.Compte;

@SuppressWarnings("serial")
@SessionScoped
@Named
public class CompteActif extends Compte {
	

	public boolean isLoggedIn() {
		return getPseudo() != null;
	}	
	
	public boolean isUtilisateur() {
		return isLoggedIn() && isInRole( UTILISATEUR );
	}
	
	public boolean isAdmininstrateur() {
		return isLoggedIn() && isInRole( ADMINISTRATEUR );
	}
	
	public boolean isParent() {
		return isLoggedIn() && isInRole( PARENT );
	}
	
	public boolean isNounou() {
		return isLoggedIn() && isInRole( NOUNOU );
	}

	public String disconnect() {
	    UtilJsf.sessionInvalidate();
        UtilJsf.messageInfo( "Vous avez été déconnecté" );
	    return "connexion";
	}

}

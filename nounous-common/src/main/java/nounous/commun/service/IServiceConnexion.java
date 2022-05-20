package nounous.commun.service;

import nounous.commun.dto.DtoCompte;


public interface IServiceConnexion {

	DtoCompte	sessionUtilisateurOuvrir( String pseudo, String motDePasse );

	void		sessionUtilisateurFermer();

}

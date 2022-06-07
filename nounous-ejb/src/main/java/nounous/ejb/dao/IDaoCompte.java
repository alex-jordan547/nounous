package nounous.ejb.dao;

import java.util.List;

import nounous.commun.dto.DtoCompte;
import nounous.commun.dto.DtoNounou;
import nounous.ejb.data.Compte;
import nounous.ejb.data.Nounou;


public interface IDaoCompte {

	int			inserer( Compte compte );

	void 		modifier( Compte compte );

	void 		supprimer( int idCompte );

	Compte 		retrouver( int idCompte );

	List<Compte> listerTout();

	Compte 		validerAuthentification( String pseudo, String motDePasse );

	boolean 	verifierUnicitePseudo( String pseudo, int idCompte );

	Nounou retrouverNounou(Integer id);

}

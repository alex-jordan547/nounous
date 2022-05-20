package nounous.commun.service;

import java.util.List;

import nounous.commun.dto.DtoCompte;
import nounous.commun.exception.ExceptionValidation;


public interface IServiceCompte {
	
	int				inserer( DtoCompte dtoCompte ) throws ExceptionValidation;

	void			modifier( DtoCompte dtoCompte ) throws ExceptionValidation; 

	void			supprimer( int idCompte ) throws ExceptionValidation;

	DtoCompte 		retrouver( int idCompte ) ;

	List<DtoCompte>	listerTout() ;

}

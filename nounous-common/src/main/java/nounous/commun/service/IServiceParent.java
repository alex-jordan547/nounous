package nounous.commun.service;

import java.util.List;

import nounous.commun.dto.DtoParent;
import nounous.commun.exception.ExceptionValidation;


public interface IServiceParent {
	
	int		inserer( DtoParent dtoParent ) throws ExceptionValidation;

	void	modifier( DtoParent dtoParent ) throws ExceptionValidation;

	void	supprimer( int idParent ) throws ExceptionValidation;

	DtoParent	retrouver( int idParent );

	List<DtoParent>	listerTout();

}

package nounous.commun.service;

import java.util.List;

import nounous.commun.dto.DtoEnfant;
import nounous.commun.exception.ExceptionValidation;


public interface IServiceEnfant {
	
	int		inserer( DtoEnfant dtoEnfant ) throws ExceptionValidation;

	void	modifier( DtoEnfant dtoEnfant ) throws ExceptionValidation;

	void	supprimer( int idEnfant ) throws ExceptionValidation;

	DtoEnfant	retrouver( int idEnfant );

	List<DtoEnfant>	listerTout();

}

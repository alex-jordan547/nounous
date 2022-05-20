package nounous.commun.service;

import java.util.List;

import nounous.commun.dto.DtoPersonne;
import nounous.commun.exception.ExceptionValidation;


public interface IServicePersonne {
	
	int				inserer( DtoPersonne dtoPersonne ) throws ExceptionValidation;
	
	void			modifier( DtoPersonne dtoPersonne ) throws ExceptionValidation;
	
	void			supprimer( int idPersonne ) throws ExceptionValidation;
	
	DtoPersonne 	retrouver( int idPersonne );
	
	List<DtoPersonne> listerTout();
	

}

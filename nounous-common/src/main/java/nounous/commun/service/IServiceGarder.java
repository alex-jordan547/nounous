package nounous.commun.service;

import java.util.List;

import nounous.commun.dto.DtoGarder;
import nounous.commun.exception.ExceptionValidation;


public interface IServiceGarder {
	
	int		inserer( DtoGarder dtoGarder ) throws ExceptionValidation;

	void	modifier( DtoGarder dtoGarder ) throws ExceptionValidation;

	void	supprimer( int idGarder ) throws ExceptionValidation;

	DtoGarder	retrouver( int idGarder );

	List<DtoGarder>	listerTout();

}

package nounous.commun.service;

import java.util.List;

import nounous.commun.dto.DtoContrat;
import nounous.commun.exception.ExceptionValidation;


public interface IServiceContrat {
	
	int		inserer( DtoContrat dtoContrat ) throws ExceptionValidation;

	void	modifier( DtoContrat dtoContrat ) throws ExceptionValidation;

	void	supprimer( int idContrat ) throws ExceptionValidation;

	DtoContrat	retrouver( int idContrat );

	List<DtoContrat>	listerTout();
	
	 List<DtoContrat> listerParNounous(int idN);

}

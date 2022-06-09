package nounous.ejb.dao;

import java.util.List;

import nounous.ejb.data.Garder;


public interface IDaoGarder {

	int			inserer( Garder garde );

	void 		modifier( Garder garde );

	void 		supprimer( int idGarde );

	Garder 	retrouver( int idGarde );

	List<Garder> listerTout();

}

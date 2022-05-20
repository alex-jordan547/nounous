package nounous.ejb.dao;

import java.util.List;

import nounous.ejb.data.Personne;
import nounous.ejb.data.Telephone;


public interface IDaoTelephone {

	void insererPourPersonne(Personne personne);

	void modifierPourPersonne(Personne personne);

	void supprimerPourPersonne(int idPersonne);

	List<Telephone> listerPourPersonne( Personne personne );

}

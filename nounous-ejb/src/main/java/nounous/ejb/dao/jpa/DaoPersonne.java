package nounous.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nounous.ejb.dao.IDaoPersonne;
import nounous.ejb.data.Personne;


@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoPersonne implements IDaoPersonne {
	
	// Champs
	
	@PersistenceContext
	private EntityManager	em;

	
	// Actions
	
	@Override
	public int inserer(Personne personne) {
		em.persist(personne);
		em.flush();
		return personne.getId();
	}

	@Override
	public void modifier(Personne personne) {
		em.merge( personne );
	}

	@Override
	public void supprimer(int idPersonne) {
		em.remove( retrouver(idPersonne) );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public Personne retrouver(int idPersonne) {
		var graph = em.createEntityGraph( Personne.class );
		graph.addAttributeNodes( "categorie" );
		graph.addAttributeNodes( "telephones" );
		var props = new HashMap<String, Object>();
		props.put( "javax.persistence.loadgraph", graph );
		return em.find( Personne.class, idPersonne, props );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Personne> listerTout() {
		em.clear();
		var jpql = "SELECT p FROM Personne p ORDER BY p.nom, p.prenom";
		var query = em.createQuery( jpql, Personne.class );
		return query.getResultList();
	}


	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public int compterPourCategorie(int idCategorie) {
		var jpql = "SELECT COUNT(p) FROM Personne p WHERE p.categorie.id = :idCategorie";
		var query = em.createQuery( jpql, Long.class );
		query.setParameter( "idCategorie", idCategorie );
		return query.getSingleResult().intValue();
	}

}

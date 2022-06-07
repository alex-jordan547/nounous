package nounous.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nounous.ejb.dao.IDaoEnfant;
import nounous.ejb.data.Enfant;

@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoEnfant implements IDaoEnfant{

	
	// Champs
	
	@PersistenceContext
	private EntityManager	em;
	
	
	// Actions
	
	@Override
	public int inserer(Enfant enfant) {
		em.persist(enfant);
		em.flush();
		return enfant.getId();
	}

	@Override
	public void modifier(Enfant enfant) {
		em.merge( enfant );
	}

	@Override
	public void supprimer(int idEnfant) {
		em.remove( retrouver(idEnfant) );
	}

	@Override
	public Enfant retrouver(int idEnfant) {
		return em.find( Enfant.class, idEnfant );
	}
	@Override
	public List<Enfant> listerTout() {
		em.clear();
		var jpql = "SELECT e FROM Enfant e ORDER BY e.firstname";
		var query = em.createQuery( jpql, Enfant.class );
		return query.getResultList();
	}	
}

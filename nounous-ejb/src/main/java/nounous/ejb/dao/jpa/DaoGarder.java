package nounous.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nounous.ejb.dao.IDaoGarder;
import nounous.ejb.data.Garder;

@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoGarder implements IDaoGarder{

	
	// Champs
	
	@PersistenceContext
	private EntityManager	em;
	
	
	// Actions
	
	@Override
	public int inserer(Garder garder) {
		em.persist(garder);
		em.flush();
		return garder.getId();
	}

	@Override
	public void modifier(Garder garder) {
		em.merge( garder );
	}

	@Override
	public void supprimer(int idGarder) {
		em.remove( retrouver(idGarder) );
	}

	@Override
	public Garder retrouver(int idGarder) {
		return em.find( Garder.class, idGarder );
	}
	@Override
	public List<Garder> listerTout() {
		em.clear();
		var jpql = "SELECT g FROM Garder g ORDER BY g.contrat.enfant.firstname";
		var query = em.createQuery( jpql, Garder.class );
		return query.getResultList();
	}	
}

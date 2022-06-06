package nounous.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nounous.ejb.dao.IDaoParent;
import nounous.ejb.data.Parent;

@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoParent implements IDaoParent{

	
	// Champs
	
	@PersistenceContext
	private EntityManager	em;
	
	
	// Actions
	
	@Override
	public int inserer(Parent parent) {
		em.persist(parent);
		em.flush();
		return parent.getId();
	}

	@Override
	public void modifier(Parent parent) {
		em.merge( parent );
	}

	@Override
	public void supprimer(int idParent) {
		em.remove( retrouver(idParent) );
	}

	@Override
	public Parent retrouver(int idParent) {
		return em.find( Parent.class, idParent );
	}
	@Override
	public List<Parent> listerTout() {
		em.clear();
		var jpql = "SELECT p FROM Parent p ORDER BY p.firstname";
		var query = em.createQuery( jpql, Parent.class );
		return query.getResultList();
	}	
}

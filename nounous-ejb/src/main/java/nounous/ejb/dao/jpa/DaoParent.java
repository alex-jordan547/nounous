package nounous.ejb.dao.jpa;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import nounous.ejb.data.Parent;


public class DaoParent{

	
	// Champs
	
	@Inject
	private EntityManager	em;
	
	
	// Actions
	
	
	public int inserer(Parent parent) {
		em.persist(parent);
		em.flush();
		return parent.getId();
	}

	
	public void modifier(Parent parent) {
		em.merge( parent );
	}

	
	public void supprimer(int idParent) {
		em.remove( retrouver(idParent) );
	}

	
	public Parent retrouver(int idParent) {
		return em.find( Parent.class, idParent );
	}
	
	public List<Parent> listerTout() {
		em.clear();
		var jpql = "SELECT p FROM Parent p ORDER BY p.firstname";
		var query = em.createQuery( jpql, Parent.class );
		return query.getResultList();
	}	
}

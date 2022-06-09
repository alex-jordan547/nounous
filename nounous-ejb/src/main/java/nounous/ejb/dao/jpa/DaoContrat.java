package nounous.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nounous.ejb.dao.IDaoContrat;
import nounous.ejb.data.Contrat;

@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoContrat implements IDaoContrat{

	
	// Champs
	
	@PersistenceContext
	private EntityManager	em;
	
	
	// Actions
	
	@Override
	public int inserer(Contrat contrat) {
		em.persist(contrat);
		em.flush();
		return contrat.getId();
	}

	@Override
	public void modifier(Contrat contrat) {
		em.merge( contrat );
	}

	@Override
	public void supprimer(int idContrat) {
		em.remove( retrouver(idContrat) );
	}

	@Override
	public Contrat retrouver(int idContrat) {
		return em.find( Contrat.class, idContrat );
	}
	@Override
	public List<Contrat> listerTout() {
		em.clear();
		var jpql = "SELECT c FROM Contrat c ORDER BY c.id";
		var query = em.createQuery( jpql, Contrat.class );
		return query.getResultList();
	}	
}

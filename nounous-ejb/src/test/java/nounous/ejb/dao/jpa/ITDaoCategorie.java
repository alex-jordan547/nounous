package nounous.ejb.dao.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.junit5.ArquillianExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import nounous.ejb.dao.IDaoCategorie;
import nounous.ejb.data.Categorie;


@ExtendWith(ArquillianExtension.class)
public class ITDaoCategorie {
	
	
	// Champs

	@PersistenceContext( unitName = "nounous" )
	private EntityManager	em;
	@Inject
	private UserTransaction	utx;
	@EJB
	private UtilTest	utilTest;
	@EJB
	private IDaoCategorie	dao;
	
	
	private Categorie 		itemRef1;
	private Categorie 		itemRef2;
	private long			itemCount;
	
	
	// Initialisation
	
	@BeforeEach
	public void beforeEach() {
		itemRef1 = new Categorie( 3, "Clients" );
		itemRef2 = new Categorie( 0, "Test" );
		itemCount = 5;
		utilTest.initDb();
		em.clear();
	}
	
	
	// Tests
	
	@Test
	public void testListerTout() {
		var liste = dao.listerTout();
		// Nombre d'items dans la liste
		assertEquals( itemCount, liste.size() );
		// Identifiant du premier item de laliste
		assertEquals( itemRef1.getId(), liste.get(0).getId(), "Ordre de tri incorrect" );
	}
	
	@Test
	public void testRetrouver_OK() {
		// Récupère l'item dans la base
		var item = dao.retrouver( itemRef1.getId() );
		assertNotNull( item );
		// Vérifie les données de l'item
		assertCompare( itemRef1, item );
	}
	
	@Test
	public void testRetrouver_Null() {
		// Item non trouvé dans la base
		var item = dao.retrouver( -1 );
		assertNull( item );
	}
	
	@Test
	public void testInsererSupprimer() throws Exception {
		// Insère un nouvel item
		itemRef2.setId( 0 );
		utx.begin();
		var id = dao.inserer( itemRef2 );
		utx.commit();
		// Identifiant > 0 affecté au nouvel item
		assertTrue( id > 0 );
		assertEquals( itemRef2.getId(), id );
		em.detach( itemRef2 );
		// Récupère l'item dans la base
		var item = dao.retrouver( itemRef2.getId() );
		assertNotNull( item );
		// Vérifie les données de l'item
		assertCompare( itemRef2, item );
		em.detach( item );
		// Supprime l'item de la base
		utx.begin();
		dao.supprimer( itemRef2.getId() );
		utx.commit();
		// L'item ne peut pas être retrouvé car il est supprimé
		assertNull( dao.retrouver( itemRef2.getId() ) );
	}
	
	@Test
	public void testModifier() throws Exception {
		// Enregistrement dans la base
		itemRef2.setId( itemRef1.getId() );
		utx.begin();
		dao.modifier( itemRef2 );
		utx.commit();
		em.detach( itemRef2 );
		// Récupère l'item dans la base
		var item = dao.retrouver( itemRef2.getId() );
		assertNotNull( item );
		// Vérifie les données de l'item
		assertCompare( itemRef2, item );
	}
	
	
	// Méthode auxilaire
	
	private void assertCompare( Categorie item1, Categorie item2 ) {
		assertEquals( item1.getId(), item2.getId() );
		assertEquals( item1.getLibelle(), item2.getLibelle() );
	}

}

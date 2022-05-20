package nounous.jsf.model.mock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import nounous.commun.dto.Roles;
import nounous.jsf.data.Categorie;
import nounous.jsf.data.Compte;
import nounous.jsf.data.Personne;
import nounous.jsf.data.Telephone;
import nounous.jsf.data.mapper.IMapper;


@SuppressWarnings("serial")
@ApplicationScoped
public class Donnees implements Serializable {

	
    // Champs 

    private final Map<Integer, Compte>  	mapComptes 		= new HashMap<>();
	private final Map<Integer, Categorie>	mapCategories 	= new HashMap<>();
	private final Map<Integer, Personne>	mapPersonnes	= new HashMap<>();

	private int 	dernierIdTelephone;
	
	@Inject
	private IMapper	mapper;

	
	// Getters
	
	public List<Categorie> getCategories() {
		List<Categorie> categories = new ArrayList<>();
		for ( Categorie categorie : mapCategories.values() ) {
			categories.add( mapper.duplicate( categorie ) );
		}
		return categories;
	}
	
	public List<Compte> getComptes() {
		List<Compte> comptes = new ArrayList<>();
		for ( Compte compte : mapComptes.values() ) {
			comptes.add( mapper.duplicate( compte ) );
		}
		return comptes;
	}
	
	public List<Personne> getPersonnes() {
		List<Personne> personnes = new ArrayList<>();
		for ( Personne personne : mapPersonnes.values() ) {
			personnes.add( mapper.duplicate( personne ) );
		}
		return personnes;
	}
	
	
	// Constructeur
	
	public Donnees() {
		initialiserDonnees();
	}
	
	
	// Actions
	
	public int categorieAjouter( Categorie categorie ) {
		Integer idMax = Collections.max( mapCategories.keySet() );
		if ( idMax == null ) {
			idMax = 0;
		}
		categorie.setId( idMax + 1 );
		mapCategories.put( categorie.getId(), mapper.duplicate( categorie ) );
		return categorie.getId();
	}
	public void categorieModifier( Categorie categorie ) {
		mapCategories.replace( categorie.getId(), mapper.duplicate( categorie ) );
	}
	public void categorieSupprimer( int id ) {
		mapCategories.remove( id );
	}
	public Categorie categorieRetrouver( int id ) {
		return mapper.duplicate( mapCategories.get( id ) );
	}
	
	public int compteAjouter( Compte compte ) {
		Integer idMax = Collections.max( mapComptes.keySet() );
		if ( idMax == null ) {
			idMax = 0;
		}
		compte.setId( idMax + 1 );
		mapComptes.put( compte.getId(), mapper.duplicate( compte ) );
		return compte.getId();
	}
	public void compteModifier( Compte compte ) {
		mapComptes.replace( compte.getId(), mapper.duplicate( compte ) );
	}
	public void compteSupprimer( int id ) {
		mapComptes.remove( id );
	}
	public Compte compteRetrouver( int id ) {
		return mapper.duplicate( mapComptes.get( id ) );
	}
	
	public int personneAjouter( Personne personne ) {
		Integer idMax = Collections.max( mapPersonnes.keySet() );
		if ( idMax == null ) {
			idMax = 0;
		}
		personne.setId( idMax + 1 );
		affecterIdTelephones(personne);
		mapPersonnes.put( personne.getId(), mapper.duplicate( personne ) );
		return personne.getId();
	}
	public void personneModifier( Personne personne ) {
		affecterIdTelephones(personne);
		mapPersonnes.replace( personne.getId(), mapper.duplicate( personne ) );
	}
	public void personneSupprimer( int id ) {
		mapPersonnes.remove( id );
	}
	public Personne personneRetrouver( int id ) {
		return mapper.duplicate( mapPersonnes.get( id ) );
	}

	
	// Méthodes auxiliaires

	private void initialiserDonnees() {
		
		
		// Comptes
		
		Compte compte;
		compte = new Compte( 1, "geek", "geek", "geek@3il.fr" );
		compte.getRoles().add( Roles.ADMINISTRATEUR  );
		compte.getRoles().add( Roles.UTILISATEUR  );
		mapComptes.put( compte.getId(), compte );

		compte = new Compte(2, "chef", "chef", "chef@3il.fr");
		compte.getRoles().add( Roles.UTILISATEUR  );
		mapComptes.put( compte.getId(), compte );
		
		compte = new Compte( 3, "job", "job", "job@3il.fr" );
		compte.getRoles().add( Roles.UTILISATEUR  );
		mapComptes.put( compte.getId(), compte );

		compte = new Compte(4, "_" + this.getClass().getPackage().getName(), "xxx", "xxx@3il.fr");
		compte.getRoles().add( Roles.UTILISATEUR  );
		mapComptes.put( compte.getId(), compte );

		
		// Catégories
	
		Categorie categorie;
		categorie =  new Categorie( 1, "Ecrivains" );
    	mapCategories.put( categorie.getId(), categorie );
    	categorie =  new Categorie( 2, "Peintres" );
    	mapCategories.put( categorie.getId(), categorie );

    	
    	// Personnes
    	
    	Categorie categorie1 = mapCategories.get(1);

        Personne personne;

        personne = new Personne( 1, "VERLAINE", "Paul", categorie1 );
        personne.getTelephones().add(new Telephone(31, "Portable", "06 33 33 33 33"));
        personne.getTelephones().add(new Telephone(32, "Domicile", "05 55 33 33 33"));
        personne.getTelephones().add(new Telephone(33, "Travail", "05 55 99 33 33"));
        mapPersonnes.put(personne.getId(), personne);

        personne = new Personne( 2, "HUGO", "Victor", categorie1 );
        personne.getTelephones().add(new Telephone(11, "Portable", "06 11 11 11 11"));
        personne.getTelephones().add(new Telephone(12, "Domicile", "05 55 11 11 11"));
        personne.getTelephones().add(new Telephone(13, "Travail", "05 55 99 11 11"));
        mapPersonnes.put(personne.getId(), personne);

        personne = new Personne( 3, "TRIOLET", "Elsa", categorie1 );
        personne.getTelephones().add(new Telephone(21, "Portable", "06 22 22 22 22"));
        personne.getTelephones().add(new Telephone(22, "Domicile", "05 55 22 22 22"));
        personne.getTelephones().add(new Telephone(23, "Travail", "05 55 99 22 22"));
        mapPersonnes.put(personne.getId(), personne);
        
        dernierIdTelephone = 100;
	
	}
	
    
	private void affecterIdTelephones( Personne personne ) {
		for( Telephone t : personne.getTelephones() ) {
			if ( t.getId() == 0 ) {
				t.setId( ++dernierIdTelephone );
			}
		}
	}
	
	
}

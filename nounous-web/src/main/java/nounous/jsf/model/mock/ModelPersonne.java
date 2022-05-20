package nounous.jsf.model.mock;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import nounous.jsf.data.Personne;
import nounous.jsf.data.Telephone;
import nounous.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@ViewScoped
@Named
public class ModelPersonne implements Serializable {

	
	// Champs
	
	private List<Personne>		liste;
	
	private Personne			courant;
	
	@Inject
	private Donnees				données;

	
	// Getters
	
	public List<Personne> getListe() {
		if ( liste == null ) {
			liste = données.getPersonnes();
		}
		return liste;
	}

	public Personne getCourant() {
		if ( courant == null ) {
			courant = new Personne();
		}
		return courant;
	}
	
	
	// Initialisaitons
	
	public String actualiserCourant() {
		if ( courant != null ) {
			courant = données.personneRetrouver( courant.getId() );
			if ( courant == null ) {
				UtilJsf.messageError( "La catégorie demandée n'existe pas" );
				return "liste";
			}
		}
		return null;
	}
	
	
	// Actions
	
	public String validerMiseAJour() {
		if ( courant.getId() == null) {
			données.personneAjouter(courant);
		} else {
			données.personneModifier(courant);
		}
		UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
		return "liste";
	}
	
	public String supprimer( Personne item ) {
		données.personneSupprimer( item.getId() );
		liste.remove(item);
		UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		return null;
	}
	
	
	public String ajouterTelephone() {
		courant.getTelephones().add( new Telephone() );
		return null;
	}
	
	
	public String supprimerTelephone( Telephone item ) {
		for ( int i=0; i < courant.getTelephones().size(); ++i ) {
			if ( courant.getTelephones().get(i) == item ) {
				courant.getTelephones().remove( i );
				break;
			}
		}
		return null;
	}
	
}

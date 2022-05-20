package nounous.jsf.model.mock;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import nounous.jsf.data.Compte;
import nounous.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelCompte implements Serializable {

	
	// Champs
	
	private List<Compte>	liste;
	
	private Compte			courant;
	
	@Inject
	private Donnees			données;

	
	// Getters & Setters
	
	public List<Compte> getListe() {
		if ( liste == null ) {
			liste = données.getComptes();
		}
		return liste;
	}

	public Compte getCourant() {
		if ( courant == null ) {
			courant = new Compte();
		}
		return courant;
	}
	
	
	// Initialisaitons
	
	public String actualiserCourant() {
		if ( courant != null ) {
			courant = données.compteRetrouver( courant.getId() );
			if ( courant == null ) {
				UtilJsf.messageError( "Le compte demandé n'existe pas" );
				return "liste";
			}
		}
		return null;
	}
	
	
	// Actions
	
	public String validerMiseAJour() {
		if ( courant.getId() == null) {
			données.compteAjouter(courant);
		} else {
			données.compteModifier(courant);
		}
		UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
		return "liste";
	}
	
	public String supprimer( Compte item ) {
		données.compteSupprimer( item.getId() );
		liste.remove(item);
		UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		return null;
	}
	
}

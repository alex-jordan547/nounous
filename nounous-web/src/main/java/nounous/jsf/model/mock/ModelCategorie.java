package nounous.jsf.model.mock;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import nounous.jsf.data.Categorie;
import nounous.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@ViewScoped
@Named
public class ModelCategorie implements Serializable {

	
	// Champs
	
	private List<Categorie>	liste;
	
	private Categorie		courant;
	
	@Inject
	private Donnees			données;

	
	// Getters 
	
	public List<Categorie> getListe() {
		if ( liste == null ) {
			liste = données.getCategories();
		}
		return liste;
	}

	public Categorie getCourant() {
		if ( courant == null ) {
			courant = new Categorie();
		}
		return courant;
	}
	
	
	// Initialisaitons
	
	public String actualiserCourant() {
		if ( courant != null ) {
			courant = données.categorieRetrouver( courant.getId() );
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
			données.categorieAjouter(courant);
		} else {
			données.categorieModifier(courant);
		}
		UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
		return "liste";
	}
	
	public String supprimer( Categorie item ) {
		données.categorieSupprimer( item.getId() );
		liste.remove(item);
		UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		return null;
	}
	
}

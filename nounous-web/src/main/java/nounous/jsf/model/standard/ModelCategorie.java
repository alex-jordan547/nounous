package nounous.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import nounous.commun.dto.DtoCategorie;
import nounous.commun.exception.ExceptionValidation;
import nounous.commun.service.IServiceCategorie;
import nounous.jsf.data.Categorie;
import nounous.jsf.data.mapper.IMapper;
import nounous.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelCategorie implements Serializable {

	
	// Champs
	
	private List<Categorie>		liste;
	
	private Categorie			courant;
	
	@EJB
	private IServiceCategorie	serviceCategorie;
	
	@Inject
	private IMapper				mapper;

	
	// Getters 
	
	public List<Categorie> getListe() {
		if ( liste == null ) {
			liste = new ArrayList<>();
			for ( DtoCategorie dto : serviceCategorie.listerTout() ) {
				liste.add( mapper.map( dto ) );
			}
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
			DtoCategorie dto = serviceCategorie.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "La catégorie demandée n'existe pas" );
				return "liste";
			} else {
				courant = mapper.map( dto );
			}
		}
		return null;
	}
	
	
	// Actions
	
	public String validerMiseAJour() {
		try {
			if ( courant.getId() == null) {
				serviceCategorie.inserer( mapper.map(courant) );
			} else {
				serviceCategorie.modifier( mapper.map(courant) );
			}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}
	
	public String supprimer( Categorie item ) {
		try {
			serviceCategorie.supprimer( item.getId() );
			liste.remove(item);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e );
		}
		return null;
	}
	
}

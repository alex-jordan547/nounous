package nounous.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import nounous.commun.dto.DtoParent;
import nounous.commun.exception.ExceptionValidation;
import nounous.commun.service.IServiceParent;
import nounous.jsf.data.Parent;
import nounous.jsf.data.mapper.IMapper;
import nounous.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelParent implements Serializable {

	
	// Champs
	
	private List<Parent>		liste;
	
	private Parent			courant;
	
	@EJB
	private IServiceParent	serviceParent;
	
	@Inject
	private IMapper				mapper;

	
	// Getters 
	
	public List<Parent> getListe() {
		if ( liste == null ) {
			liste = new ArrayList<>();
			for ( DtoParent dto : serviceParent.listerTout() ) {
				liste.add( mapper.map( dto ) );
			}
		}
		return liste;
	}

	public Parent getCourant() {
		if ( courant == null ) {
			courant = new Parent();
		}
		return courant;
	}
	
	
	// Initialisaitons
	
	public String actualiserCourant() {
		if ( courant != null ) {
			DtoParent dto = serviceParent.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "Le parent demandé n'existe pas" );
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
				serviceParent.inserer( mapper.map(courant) );
			} else {
				serviceParent.modifier( mapper.map(courant) );
			}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}
	
	public String supprimer( Parent item ) {
		try {
			serviceParent.supprimer( item.getId() );
			liste.remove(item);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e );
		}
		return null;
	}
	
}

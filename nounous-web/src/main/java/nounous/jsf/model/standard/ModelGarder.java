package nounous.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import nounous.commun.dto.DtoGarder;
import nounous.commun.exception.ExceptionValidation;
import nounous.commun.service.IServiceGarder;
import nounous.jsf.data.Garder;
import nounous.jsf.data.mapper.IMapper;
import nounous.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelGarder implements Serializable {

	
	// Champs
	
	private List<Garder>		liste;
	
	private Garder			courant;
	
	@EJB
	private IServiceGarder	serviceGarder;
	
	@Inject
	private IMapper				mapper;
	


	
	// Getters 
	
	public List<Garder> getListe() {
		if ( liste == null ) {
			liste = new ArrayList<>();
			for ( DtoGarder dto : serviceGarder.listerTout() ) {
				liste.add( mapper.map( dto ) );
			}
		}
		return liste;
	}

	public Garder getCourant() {
		if ( courant == null ) {
			courant = new Garder();
		}
		return courant;
	}
	
	
	// Initialisaitons
	
	public String actualiserCourant() {
		if ( courant != null ) {
			DtoGarder dto = serviceGarder.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "La garde n'existe pas" );
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
			if ( courant == null) {
				serviceGarder.inserer( mapper.map(courant) );
			} else {
				serviceGarder.modifier( mapper.map(courant) );
			}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}
	
	public String supprimer( Garder item ) {
		try {
			serviceGarder.supprimer( item.getId() );
			liste.remove(item);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e );
		}
		return null;
	}
	
}

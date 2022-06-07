package nounous.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import nounous.commun.dto.DtoEnfant;
import nounous.commun.exception.ExceptionValidation;
import nounous.commun.service.IServiceEnfant;
import nounous.commun.service.IServiceParent;
import nounous.jsf.data.Enfant;
import nounous.jsf.data.mapper.IMapper;
import nounous.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelEnfant implements Serializable {

	
	// Champs
	
	private List<Enfant>		liste;
	
	private Enfant			courant;
	
	@EJB
	private IServiceEnfant	serviceEnfant;
	
	@Inject
	private IMapper				mapper;
	
	@Inject
	private ModelParent modelParent;
	
	@EJB
	private IServiceParent serviceParent;

	
	// Getters 
	
	public List<Enfant> getListe() {
		if ( liste == null ) {
			liste = new ArrayList<>();
			for ( DtoEnfant dto : serviceEnfant.listerTout() ) {
				liste.add( mapper.map( dto ) );
			}
		}
		return liste;
	}

	public Enfant getCourant() {
		if ( courant == null ) {
			courant = new Enfant();
		}
		return courant;
	}
	
	
	// Initialisaitons
	
	public String actualiserCourant() {
		if ( courant != null ) {
			DtoEnfant dto = serviceEnfant.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "Lenfant demandé n'existe pas" );
				return "liste";
			} else {
				courant = mapper.map( dto );
			}
		}
		return null;
	}
	
	
	// Actions
	
	public String validerMiseAJour() {
		System.out.println(modelParent.getCourant().getId());
		try {
			if ( courant.getId() == null) {
					courant.setParent(mapper.map(serviceParent.retrouver(modelParent.getCourant().getId())));
				courant.setId(serviceEnfant.inserer( mapper.map(courant) ));
				actualiserCourant();
				
			} else {
				serviceEnfant.modifier( mapper.map(courant) );
			}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}
	
	public String supprimer( Enfant item ) {
		try {
			serviceEnfant.supprimer( item.getId() );
			liste.remove(item);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e );
		}
		return null;
	}
	
}

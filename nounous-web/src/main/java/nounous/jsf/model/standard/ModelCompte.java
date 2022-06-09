package nounous.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import nounous.commun.dto.DtoCompte;
import nounous.commun.exception.ExceptionValidation;
import nounous.commun.service.IServiceCompte;
import nounous.jsf.data.Compte;
import nounous.jsf.data.Nounou;
import nounous.jsf.data.mapper.IMapper;
import nounous.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@Named
@SessionScoped
public class ModelCompte implements Serializable {

	
	// Champs
	
	private List<Compte>	liste;
	
	private Compte			courant;
	
	@EJB
	private IServiceCompte	serviceCompte;
	
	@Inject
	private IMapper			mapper;
	
	private Nounou nounou;
	

	
	// Getters 
	
	public List<Compte> getListe() {
		if ( liste == null ) {
			liste = new ArrayList<>();
			for ( DtoCompte dto : serviceCompte.listerTout() ) {
				liste.add( mapper.map( dto ) );
			}
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
			DtoCompte dto = serviceCompte.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "Le compte demandé n'existe pas" );
				return "test/liste";
			} else {
//TODO Recuperer l'objet nounou courant
				nounou = mapper.map(serviceCompte.retrouverNounou(3));
				courant = mapper.map( dto );
			}
		}
		return null;
	}
	
	
	// Actions
	
	public Nounou getNounou() {
		return nounou;
	}

	public void setNounou(Nounou nounou) {
		this.nounou = nounou;
	}

	public int validerMiseAJour() {
		try {
			int id =-1;
			if ( courant.getId() == null) {
				id = serviceCompte.inserer( mapper.map(courant) );
				courant.setId(id);
				actualiserCourant();
			} else {
				serviceCompte.modifier( mapper.map(courant) );
			}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return id;
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return -1;
		}
	}
	
	public String supprimer( Compte item ) {
		try {
			serviceCompte.supprimer( item.getId() );
			liste.remove(item);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e ); 
		}
		return null;
	}
	
}

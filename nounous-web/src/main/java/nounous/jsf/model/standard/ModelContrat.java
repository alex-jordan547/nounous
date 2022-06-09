package nounous.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import nounous.commun.dto.DtoContrat;
import nounous.commun.exception.ExceptionValidation;
import nounous.commun.service.IServiceContrat;
import nounous.commun.service.IServiceParent;
import nounous.jsf.data.Contrat;
import nounous.jsf.data.mapper.IMapper;
import nounous.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@Named
@SessionScoped
public class ModelContrat implements Serializable {

	
	// Champs
	
	private List<Contrat>		liste;
	
	private List<Contrat>		listeParNounou;
	
	private Contrat			courant;
	
	@EJB
	private IServiceContrat	serviceContrat;
	
	@Inject
	private IMapper				mapper;
	
	@Inject
	private ModelEnfant modelEnfant;
	@Inject
	private ModelCompte modelCompte;
	
	@Inject
	private ModelConnexion modelConnexion;
	
	@EJB
	private IServiceParent serviceParent;

	
	// Getters 
	
	public List<Contrat> getListe() {
		

			liste = new ArrayList<>();
			for ( DtoContrat dto : serviceContrat.listerTout() ) {
				liste.add( mapper.map( dto ) );
		}
		
		return liste;
	}
	
	public List<Contrat> getListeParNounou() {
		
		listeParNounou = new ArrayList<>();
		for ( DtoContrat dto : serviceContrat.listerParNounous(2) ) {
			liste.add( mapper.map( dto ) );
	}
	System.out.println(serviceContrat.listerTout());
	return liste;
}
	
	

	public Contrat getCourant() {
		if ( courant == null ) {
			courant = new Contrat();
		}
		return courant;
	}
	
	
	// Initialisaitons
	
	public String actualiserCourant() {
		if ( courant != null ) {
			DtoContrat dto = serviceContrat.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "Le contrat demandé n'existe pas" );
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
					modelEnfant.validerMiseAJour();
					courant.setEnfant(modelEnfant.getCourant());
					courant.setNounou(modelCompte.getNounou());
				 	serviceContrat.inserer( mapper.map(courant) );
				
			} else {
				serviceContrat.modifier( mapper.map(courant) );
			}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "contrats";
		} catch (ExceptionValidation e) {
			System.out.println(courant);
			UtilJsf.messageError(e);
			return null;
		}
	}
	
	public String supprimer( Contrat item ) {
		try {
			serviceContrat.supprimer( item.getId() );
			liste.remove(item);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e );
		}
		return null;
	}
	
}

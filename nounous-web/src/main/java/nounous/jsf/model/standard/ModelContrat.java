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
	
	@Inject
	private ModelContrat modelContrat;
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
		for ( DtoContrat dto : serviceContrat.listerParNounous(modelCompte.getNounou(modelConnexion.getCompteActif().getId()).getId()) ) {
			listeParNounou.add( mapper.map( dto ) );
	}
	
	return listeParNounou;
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
					courant.setNounou(modelCompte.getNounou(modelConnexion.getCompteActif().getId()));
				 serviceContrat.inserer( mapper.map(courant) );

				
			} else {
				serviceContrat.modifier( mapper.map(courant) );
			}
			
			return "contrats";
		} catch (ExceptionValidation e) {
			
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

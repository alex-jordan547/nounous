package nounous.ejb.service.standard;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.REQUIRED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import nounous.commun.dto.DtoEnfant;
import nounous.commun.exception.ExceptionValidation;
import nounous.commun.service.IServiceEnfant;
import nounous.ejb.dao.IDaoEnfant;
import nounous.ejb.data.Enfant;
import nounous.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServiceEnfant implements IServiceEnfant {

	// Champs
	@Inject
	private IMapperEjb mapper;
	@Inject
	private IDaoEnfant daoEnfant;

	// Actions

	@Override
	public int inserer(DtoEnfant dtoEnfant) throws ExceptionValidation {
		verifierValiditeDonnees(dtoEnfant);
		int id = daoEnfant.inserer(mapper.map(dtoEnfant));
		return id;
	}

	@Override
	public void modifier(DtoEnfant dtoEnfant) throws ExceptionValidation {
		verifierValiditeDonnees(dtoEnfant);
		daoEnfant.modifier(mapper.map(dtoEnfant));
	}

	@Override
	public void supprimer(int idEnfant) throws ExceptionValidation {
	
	}

	@Override
	@TransactionAttribute(REQUIRED)
	public DtoEnfant retrouver(int idEnfant) {
		return mapper.map(daoEnfant.retrouver(idEnfant));
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoEnfant> listerTout() {
		List<DtoEnfant> liste = new ArrayList<>();
		for (Enfant enfant : daoEnfant.listerTout()) {
			liste.add(mapper.map(enfant));
		}
		return liste;
	}

	// Méthodes auxiliaires

	private void verifierValiditeDonnees(DtoEnfant dtoEnfant) throws ExceptionValidation {

	
	}

}

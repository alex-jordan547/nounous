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

import nounous.commun.dto.DtoParent;
import nounous.commun.exception.ExceptionValidation;
import nounous.commun.service.IServiceParent;
import nounous.ejb.dao.IDaoParent;
import nounous.ejb.dao.IDaoPersonne;
import nounous.ejb.data.Parent;
import nounous.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServiceParent implements IServiceParent {

	// Champs
	@Inject
	private IMapperEjb mapper;
	@Inject
	private IDaoParent daoParent;

	// Actions

	@Override
	public int inserer(DtoParent dtoParent) throws ExceptionValidation {
		verifierValiditeDonnees(dtoParent);
		int id = daoParent.inserer(mapper.map(dtoParent));
		return id;
	}

	@Override
	public void modifier(DtoParent dtoParent) throws ExceptionValidation {
		verifierValiditeDonnees(dtoParent);
		daoParent.modifier(mapper.map(dtoParent));
	}

	@Override
	public void supprimer(int idParent) throws ExceptionValidation {
	
	}

	@Override
	@TransactionAttribute(REQUIRED)
	public DtoParent retrouver(int idParent) {
		return mapper.map(daoParent.retrouver(idParent));
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoParent> listerTout() {
		List<DtoParent> liste = new ArrayList<>();
		for (Parent parent : daoParent.listerTout()) {
			liste.add(mapper.map(parent));
		}
		return liste;
	}

	// Méthodes auxiliaires

	private void verifierValiditeDonnees(DtoParent dtoParent) throws ExceptionValidation {

	
	}

}

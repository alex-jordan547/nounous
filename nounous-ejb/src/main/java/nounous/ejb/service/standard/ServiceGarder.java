package nounous.ejb.service.standard;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.REQUIRED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import nounous.commun.dto.DtoGarder;
import nounous.commun.exception.ExceptionValidation;
import nounous.commun.service.IServiceGarder;
import nounous.ejb.dao.IDaoGarder;
import nounous.ejb.data.Garder;
import nounous.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
@TransactionAttribute(REQUIRED)
public class ServiceGarder implements IServiceGarder {

	// Champs
	@Inject
	private IMapperEjb mapper;
	@Inject
	private IDaoGarder daoGarder;

	// Actions

	@Override
	public int inserer(DtoGarder dtoGarder) throws ExceptionValidation {
		verifierValiditeDonnees(dtoGarder);
		int id = daoGarder.inserer(mapper.map(dtoGarder));
		return id;
	}

	@Override
	public void modifier(DtoGarder dtoGarder) throws ExceptionValidation {
		verifierValiditeDonnees(dtoGarder);
		daoGarder.modifier(mapper.map(dtoGarder));
	}

	@Override
	public void supprimer(int idGarder) throws ExceptionValidation {
	
	}

	@Override
	@TransactionAttribute(REQUIRED)
	public DtoGarder retrouver(int idGarder) {
		return mapper.map(daoGarder.retrouver(idGarder));
	}

	@Override
	@TransactionAttribute(REQUIRED)
	public List<DtoGarder> listerTout() {
		List<DtoGarder> liste = new ArrayList<>();
		for (Garder garder : daoGarder.listerTout()) {
			liste.add(mapper.map(garder));
		}
		return liste;
	}

	// Méthodes auxiliaires

	private void verifierValiditeDonnees(DtoGarder dtoGarder) throws ExceptionValidation {

	
	}

}

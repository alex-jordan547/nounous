package nounous.ejb.service.standard;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import nounous.commun.dto.DtoPersonne;
import nounous.commun.dto.DtoTelephone;
import nounous.commun.exception.ExceptionValidation;
import nounous.commun.service.IServicePersonne;
import nounous.ejb.dao.IDaoPersonne;
import nounous.ejb.data.Personne;
import nounous.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServicePersonne implements IServicePersonne {

	// Champs
	@Inject
	private IMapperEjb mapper;
	@Inject
	private IDaoPersonne daoPersonne;

	// Actions

	@Override
	public int inserer(DtoPersonne dtoPersonne) throws ExceptionValidation {
		verifierValiditeDonnees(dtoPersonne);
		int id = daoPersonne.inserer(mapper.map(dtoPersonne));
		return id;
	}

	@Override
	public void modifier(DtoPersonne dtoPersonne) throws ExceptionValidation {
		verifierValiditeDonnees(dtoPersonne);
		daoPersonne.modifier(mapper.map(dtoPersonne));
	}

	@Override
	public void supprimer(int idPersonne) throws ExceptionValidation {
		daoPersonne.supprimer(idPersonne);
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public DtoPersonne retrouver(int idPersonne) {
		Personne personne = daoPersonne.retrouver(idPersonne);
		return mapper.map(personne);

	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoPersonne> listerTout() {
		List<DtoPersonne> liste = new ArrayList<>();
		for (Personne personne : daoPersonne.listerTout()) {
			liste.add( mapper.mapMinimal(personne) );
		}
		return liste;
	}

	// Méthodes auxiliaires

	private void verifierValiditeDonnees(DtoPersonne dtoPersonne) throws ExceptionValidation {

		StringBuilder message = new StringBuilder();

		if (dtoPersonne.getNom() == null || dtoPersonne.getNom().isEmpty()) {
			message.append("\nLe nom est absent.");
		} else if (dtoPersonne.getNom().length() > 25) {
			message.append("\nLe nom est trop long.");
		}

		if (dtoPersonne.getPrenom() == null || dtoPersonne.getPrenom().isEmpty()) {
			message.append("\nLe prénom est absent.");
		} else if (dtoPersonne.getPrenom().length() > 25) {
			message.append("\nLe prénom est trop long.");
		}

		for (DtoTelephone telephoe : dtoPersonne.getTelephones()) {
			if (telephoe.getLibelle() == null || telephoe.getLibelle().isEmpty()) {
				message.append("\nLlibellé absent pour le téléphone : " + telephoe.getNumero());
			} else if (telephoe.getLibelle().length() > 25) {
				message.append("\nLe libellé du téléphone est trop long : " + telephoe.getLibelle());
			}

			if (telephoe.getNumero() == null || telephoe.getNumero().isEmpty()) {
				message.append("\nNuméro absent pour le téléphone : " + telephoe.getLibelle());
			} else if (telephoe.getNumero().length() > 25) {
				message.append("\nLe numéro du téléphone est trop long : " + telephoe.getNumero());
			}
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}
	}

}

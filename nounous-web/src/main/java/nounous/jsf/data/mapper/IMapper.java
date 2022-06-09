package nounous.jsf.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import nounous.commun.dto.DtoCategorie;
import nounous.commun.dto.DtoCompte;
import nounous.commun.dto.DtoContrat;
import nounous.commun.dto.DtoEnfant;
import nounous.commun.dto.DtoGarder;
import nounous.commun.dto.DtoNounou;
import nounous.commun.dto.DtoParent;
import nounous.commun.dto.DtoPersonne;
import nounous.commun.dto.DtoTelephone;
import nounous.jsf.data.Categorie;
import nounous.jsf.data.Compte;
import nounous.jsf.data.Contrat;
import nounous.jsf.data.Enfant;
import nounous.jsf.data.Garder;
import nounous.jsf.data.Nounou;
import nounous.jsf.data.Parent;
import nounous.jsf.data.Personne;
import nounous.jsf.data.Telephone;

@Mapper(componentModel = "cdi")
public interface IMapper {

	// Compte

	Compte map(DtoCompte source);

	DtoCompte map(Compte source);

	Compte duplicate(Compte source);

	Compte update(@MappingTarget Compte target, Compte source);

	// Categorie

	Categorie map(DtoCategorie source);

	DtoCategorie map(Categorie source);

	Categorie duplicate(Categorie source);

	// Personne

	Personne map(DtoPersonne source);

	DtoPersonne map(Personne source);

	Personne duplicate(Personne source);

	// Telephone

	Telephone map(DtoTelephone source);

	DtoTelephone map(Telephone source);

	// Méthodes nécessaire pour update( Personne )

	Telephone duplicate(Telephone source);

	List<Telephone> duplicate(List<Telephone> source);

	// Parent

	Parent map(DtoParent source);

	DtoParent map(Parent source);

	Parent duplicate(Parent source);

	// Enfant

	Enfant map(DtoEnfant source);

	DtoEnfant map(Enfant source);

	Enfant duplicate(Enfant source);
	

	// Contrat

	Contrat map(DtoContrat source);

	DtoContrat map(Contrat source);

	Contrat duplicate(Contrat source);

	// Nounou

	Nounou map(DtoNounou source);

	DtoNounou map(Nounou source);

	Nounou duplicate(Nounou source);
	
	// Garder

		Garder map(DtoGarder source);

		DtoGarder map(Garder source);

		Garder duplicate(Garder source);

}

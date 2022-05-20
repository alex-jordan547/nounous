package nounous.ejb.data.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import nounous.commun.dto.DtoCategorie;
import nounous.commun.dto.DtoCompte;
import nounous.commun.dto.DtoPersonne;
import nounous.commun.dto.DtoTelephone;
import nounous.ejb.data.Categorie;
import nounous.ejb.data.Compte;
import nounous.ejb.data.Personne;
import nounous.ejb.data.Telephone;

 
@Mapper( componentModel = "cdi" )
public interface IMapperEjb {  
	
	static final IMapperEjb INSTANCE = Mappers.getMapper( IMapperEjb.class );
	
	
	// Compte
	
	Compte map( DtoCompte source );
	
	DtoCompte map( Compte source );

	
	// Categorie
	
	Categorie map( DtoCategorie source );
	
	DtoCategorie map( Categorie source );

	
	// Personne
	
	Personne map( DtoPersonne source );
	
	DtoPersonne map( Personne source );

	@Mapping( target="categorie", ignore = true )
	@Mapping( target="telephones", ignore = true )
	DtoPersonne mapMinimal( Personne source );
	
	
	// Telephone
	
	@Mapping( target="personne", ignore=true )
	Telephone map( DtoTelephone source );
	
	DtoTelephone map( Telephone source );
	

	// Méthodes auxiliaires
	
    @AfterMapping
    public default void addBackReference(@MappingTarget Personne target) {
        for (Telephone telephone : target.getTelephones() ) {
        	telephone.setPersonne( target );
        }
    }	
	
}

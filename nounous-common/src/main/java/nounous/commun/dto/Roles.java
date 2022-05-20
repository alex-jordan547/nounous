package nounous.commun.dto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public final class Roles {
	
	// Champs statiques
	
	public static final String ADMINISTRATEUR	= "ADMINISTRATEUR";
	public static final String UTILISATEUR		= "UTILISATEUR";
	
	
	private static final List<String>	roles = Collections.unmodifiableList( Arrays.asList( 
			ADMINISTRATEUR,			
			UTILISATEUR
	) );

	private static final String[]	 	libellés = new String[] {
			"Administrateur",
			"Utilisateur",
	};
	
	
	// Constructeur privé qui empêche l'instanciation de la classe
	private Roles() {
	}
	
	
	// Actions

	public static List<String> getRoles() {
		return roles;
	}
	
	public static String getLibellé( String role ) {
		int index = roles.indexOf( role );
		if ( index == -1 ) {
			throw new IllegalArgumentException();
		} 
		return libellés[index];
	}

}

package nounous.jsf.data;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import nounous.commun.dto.DtoParent;


@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@NoArgsConstructor



public class Parent implements Serializable {

	
	// Champs

    private Integer        	id;
    

	@NotBlank( message = "Le firstname doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le libellé : 25 car. maxi" )
	private String firstname;
	@NotBlank( message = "Le lastname doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le libellé : 25 car. maxi" )
	private String lastname;
	@NotBlank( message = "L'adresse doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le libellé : 25 car. maxi" )
	private String adresse;
	@NotBlank( message = "Le telephone doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le libellé : 25 car. maxi" )
	private String phone;
    
  
	@Override
	public String toString() {
		return firstname + " "+ lastname;
	}


}

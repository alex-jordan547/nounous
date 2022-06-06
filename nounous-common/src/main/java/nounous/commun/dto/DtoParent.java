package nounous.commun.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class DtoParent implements Serializable {

	// Champs

	private int id;
	private String firstname;
	private String lastname;
	private String adresse;
	private String phone;
	private DtoCompte compte;
	private List<DtoEnfant> enfants= new ArrayList<DtoEnfant>();

	
}

package nounous.commun.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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
public class DtoEnfant implements Serializable {

	// Champs

	private int id;
	private String firstname;
	private String lastname;
	private Date dateNaissance;
	private DtoContrat contrat;
	private DtoParent parent;

	
}

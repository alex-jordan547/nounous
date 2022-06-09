package nounous.commun.dto;

import java.io.Serializable;
import java.util.Date;

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
public class DtoGarder implements Serializable {

	// Champs

	private int id;
	private String heureDebut;
	private String heureFin;
	private Date dateJ; 
	private boolean aMange;
	
	private DtoContrat contrat;

	
}

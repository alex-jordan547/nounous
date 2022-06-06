package nounous.commun.dto;

import java.io.Serializable;
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
public class DtoContrat implements Serializable {

	// Champs

	private int id;
	private Date dateDebut;
	private Date dateFin;
	private double tarifHoraire;
	private double indemRepas;
	private DtoNounou nounou;
	private DtoEnfant enfant;
	
}

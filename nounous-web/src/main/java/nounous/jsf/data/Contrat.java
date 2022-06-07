package nounous.jsf.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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



public class Contrat implements Serializable {

	
	// Champs

    private Integer        	id;
    
	private Date dateDebut;
	private Date dateFin;
	private String tarifHoraire;
	private String indemRepas;
	private Nounou nounou;
	private Enfant enfant;
    
  
	@Override
	public String toString() {
		return "" + id;
	}


}

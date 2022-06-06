package nounous.ejb.data;

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
public class Contrat {
	private int id;
	private Date dateDebut;
	private Date dateFin;
	private double tarifHoraire;
	private double indemRepas;
	private Nounou nounou;
	private Enfant enfant;

}

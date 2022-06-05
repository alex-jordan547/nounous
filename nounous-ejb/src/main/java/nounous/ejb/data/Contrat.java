package nounous.ejb.data;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@NoArgsConstructor
public class Contrat {
	private int id;
	private Date date_debut;
	private Date date_fin;
	private double tarif_horaire;
	private boolean aManger;

}

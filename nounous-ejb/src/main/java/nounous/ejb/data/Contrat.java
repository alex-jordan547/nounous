package nounous.ejb.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
@Entity
public class Contrat {
	@Id
	@Column(name = "idContrat")
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	private Date dateDebut;
	private Date dateFin;
	private double tarifHoraire;
	private double indemRepas;
	
	@ManyToOne
	@JoinColumn(name="idNounou")
	private Nounou nounou;
	
	@OneToOne
	@JoinColumn(name="idEnfant")
	private Enfant enfant;

}

package nounous.ejb.data;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@NoArgsConstructor
@Entity
public class Garder {
	@Id
	@Column(name="idGarder")
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	private LocalTime heureDebut;
	private LocalTime heureFin;
	private LocalDate dateJ; 
	private boolean aMange;
	
	@ManyToOne
	@JoinColumn(name="idContrat")
	private Contrat contrat;
}

package nounous.jsf.data;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@NoArgsConstructor
public class Garder {
	
	private int id;
	
	@NotBlank( message = "L'heure de début doit être renseignée")
	private LocalTime heureDebut;
	
	@NotBlank( message = "L'heure de fin doit être renseignée")
	private LocalTime heureFin;
	
	@NotBlank( message = "La date doit être renseignée")
	private LocalDate dateJ; 
	
	private boolean aMange;
	
	
	private Contrat contrat;
	
	@Override
	public String toString() {
		return heureDebut.toString() + " - "+ heureFin.toString();
	}
}

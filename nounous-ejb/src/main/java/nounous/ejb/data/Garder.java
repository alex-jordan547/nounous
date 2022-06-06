package nounous.ejb.data;

import java.time.LocalDate;
import java.time.LocalTime;

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
	private LocalTime heureDebut;
	private LocalTime heureFin;
	private LocalDate dateJ; 
	private boolean aMange;
	private Contrat contrat;
}
